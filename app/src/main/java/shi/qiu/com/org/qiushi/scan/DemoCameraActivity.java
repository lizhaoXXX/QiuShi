package shi.qiu.com.org.qiushi.scan;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import shi.qiu.com.org.qiushi.R;

/**
 * @author azhao
 * @date 2017/12/25
 * $desc
 */
public class DemoCameraActivity extends Activity {
	
	private SurfaceHolder mHolder;
	private Camera mCamera;
	private AppCompatImageView mShowPhoto;
	private SurfaceView mSurfaceView;
	private int mFaceBackCameraId;
	private int mFaceBackCameraOrientation;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_camera);
		initView();
		mShowPhoto = findViewById(R.id.iv_showPhoto);
		mSurfaceView = findViewById(R.id.sf_surface);
		
		mHolder = mSurfaceView.getHolder();
		// mSurfaceView 不需要自己的缓冲区
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mHolder.setFormat(PixelFormat.TRANSPARENT);
		mHolder.addCallback(mCallback);
	}
	
	private void initView() {
		AppCompatImageView backImage = findViewById(R.id.iv_back);
		backImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		AppCompatImageView takePhoto = findViewById(R.id.iv_take_photo);
		takePhoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//点击拍照
				if (mCamera != null){
					//自动对焦，拍照
					mCamera.autoFocus(mAutoFocusCallback);
				}
			}
		});
		
		AppCompatImageView compatImageView = findViewById(R.id.iv_take);
		compatImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			
			}
		});
	}
	
	SurfaceHolder.Callback mCallback = new SurfaceHolder.Callback(){
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			Log.i(getClass().getName()+"==", "surfaceCreated");
			//获取摄像头的信息（前置摄像头，后置摄像头）
			getCameraInfo();
			//默认开启后置Camera.open()
			mCamera = Camera.open();
			if (mCamera != null){
				try {
					//设置角度
					mCamera.setDisplayOrientation(90);
					//对图片和预览一些参数的设置
					Camera.Parameters parameters = mCamera.getParameters();
					//闪光灯的设置
					parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON );
					//设置预览照片的大小
					parameters.setPreviewFpsRange(mSurfaceView.getWidth(), mSurfaceView.getHeight());
					//设置预览照片的帧数
					parameters.setPreviewFpsRange(4, 10);
					//设置图片格式
					parameters.setPictureFormat(ImageFormat.JPEG);
					//设置图片的质量
					parameters.set("jpeg-quality", 90);
					//设置照片的大小
					parameters.setPictureSize(mSurfaceView.getWidth(), mSurfaceView.getHeight());
					//通过SurfaceView显示预览
					mCamera.setPreviewDisplay(holder);
					//开始预览
					mCamera.startPreview();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			
		}
		
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			Log.i(getClass().getName()+"==", "surfaceChanged");
		}
		
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			Log.i(getClass().getName()+"==", "surfaceDestroyed");
			// 释放Camera资源
			if (mCamera != null) {
				mCamera.stopPreview();
				mCamera.release();
			}
		}
	};
	
	
	private Camera.AutoFocusCallback mAutoFocusCallback = new Camera.AutoFocusCallback() {
		@Override
		public void onAutoFocus(boolean success, Camera camera) {
			//对焦成功
			if (success){
				mCamera.takePicture(new Camera.ShutterCallback() {
					@Override
					public void onShutter() {
						//按下快门瞬间的操作
					}
				}, new Camera.PictureCallback() {
					@Override
					public void onPictureTaken(byte[] data, Camera camera) {
						//是否保存原始图片的信息
					}
				}, mPictureCallback);
				
			}
		}
	};
	private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			//获取图片
			final Bitmap resource = BitmapFactory.decodeByteArray(data, 0, data.length);
			if (resource == null) {
				Toast
					.makeText(DemoCameraActivity.this, "拍照失败", Toast.LENGTH_SHORT).show();
			}
			final Matrix matrix = new Matrix();
			matrix.setRotate(90);
			final Bitmap bitmap = Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, true);
			if (bitmap != null  && mShowPhoto.getVisibility() == View.GONE) {
				mCamera.stopPreview();
				mShowPhoto.setVisibility(View.VISIBLE);
				mSurfaceView.setVisibility(View.GONE);
				Toast.makeText(DemoCameraActivity.this, "拍照", Toast.LENGTH_SHORT).show();
				mShowPhoto.setImageBitmap(bitmap);
			}
			
		}
	};
	
	/**
	 * 获取摄像头的信息（前置摄像头，后置摄像头）
	 */
	private void getCameraInfo(){
		//返回摄像头的个数
		int numberOfCameras = Camera.getNumberOfCameras();
		for (int i = 0; i < numberOfCameras; i++) {
			//获取信息
			Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
			Camera.getCameraInfo(i, cameraInfo);
			
			//cameraInfo.facing摄像头所面向的方向（前面，后面）
			if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK ){
				//后置摄像头
				//获取对应的摄像头，通过Camera.open(cameraId)打开对应的摄像头
				mFaceBackCameraId = i;
				//摄像头显示画面的角度（0,90,180,270度）
				mFaceBackCameraOrientation = cameraInfo.orientation;
			}
			
			if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT ){
				//前置置摄像头
				mFaceBackCameraId = i;
				mFaceBackCameraOrientation = cameraInfo.orientation;
			}
			
			
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mHolder != null){
			mHolder.removeCallback(mCallback);
			mHolder = null;
		}
	}
}
