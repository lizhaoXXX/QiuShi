package shi.qiu.com.org.qiushi.scan;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.Result;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import shi.qiu.com.org.qiushi.R;
import shi.qiu.com.org.qiushi.scan.view.ImageScanningTask;

/**
 * @author azhao
 * @date 2017/12/22
 * $desc 自定义扫描的界面
 */
public class CustomScanActivity extends AppCompatActivity implements DecoratedBarcodeView.TorchListener {
	private CaptureManager       capture;
	private DecoratedBarcodeView barcodeScannerView;
	private TextView             mOpenLight;
	private TextView             mPhoto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		barcodeScannerView = initializeContent();
		mOpenLight = findViewById(R.id.tv_open_light);
		mPhoto = findViewById(R.id.tv_open_photo);
		capture = new CaptureManager(this, barcodeScannerView);
		capture.initializeFromIntent(getIntent(), savedInstanceState);
		capture.decode();
		
		barcodeScannerView.setTorchListener(this);
		final String off = "off";
		mOpenLight.setTag(off);
		mOpenLight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(getClass().getName() + "==", "有小电筒吗？ " + hasFlash());
				if (hasFlash()) {
					if (off.equals(mOpenLight.getTag())) {
						// 打开
						barcodeScannerView.setTorchOn();
					} else {
						// 关闭
						barcodeScannerView.setTorchOff();
					}
				}
			}
		});
		
		mPhoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 101);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 101) {
			if (AppCompatActivity.RESULT_OK == resultCode && null != data) {
				Uri uri = data.getData();
				Log.i(getClass().getName()+"==", "uri = " + uri.getPath());
				ImageScanningTask scanningTask = new ImageScanningTask(CustomScanActivity.this, uri, new ImageScanningTask.ImageScanningCallback() {
					@Override
					public void onFinishScanning(Result result) {
						if (result != null) {
							Intent intent = new Intent();
							intent.putExtra("result", result.getText());
							setResult(100, intent);
							finish();
						} else { // 识别失败
						}
					}
				});
				scanningTask.execute();
			}
			
		}
	}
	
	/**
	 * 判断是否有手电筒
	 */
	
	private boolean hasFlash() {
		return getApplicationContext()
				   .getPackageManager()
				   .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
	}
	
	/**
	 * Override to use a different layout.
	 *
	 * @return the DecoratedBarcodeView
	 */
	protected DecoratedBarcodeView initializeContent() {
		setContentView(R.layout.activity_custom_scan);
		return (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		capture.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		capture.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		capture.onDestroy();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		capture.onSaveInstanceState(outState);
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
		capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onTorchOn() {
		Log.i(getClass().getName() + "==", "回调吗？ on");
		mOpenLight.setTag("on");
	}
	
	@Override
	public void onTorchOff() {
		Log.i(getClass().getName() + "==", "回调吗？ off");
		mOpenLight.setTag("off");
	}
}
