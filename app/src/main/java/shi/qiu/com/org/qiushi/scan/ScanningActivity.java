package shi.qiu.com.org.qiushi.scan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import shi.qiu.com.org.qiushi.R;

/**
 * @author azhao
 * @date 2017/12/22
 * $desc 扫码界面
 *
 * 注意事项：
 * 1. activity 和 fragment 使用时候，初始化是不同的
 * 2. 默认的是横屏的，可以在清单文件中设置为竖屏形式
 *
 *https://github.com/journeyapps/zxing-android-embedded
 */
public class ScanningActivity extends AppCompatActivity{
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scanning);
		
		//扫描二维码
		AppCompatButton appCompatButton = findViewById(R.id.button_scan);
		appCompatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentIntegrator intentIntegrator = new IntentIntegrator(ScanningActivity.this);
				// 扫码的类型,可选：一维码，二维码，一/二维码
				intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
								// 设置提示语
								.setPrompt("请对准二维码")
								// 选择摄像头,可使用前置或者后置
								.setCameraId(0)
								// 是否开启声音,扫完码之后会"哔"的一声
								.setBeepEnabled(false)
								// 扫完码之后生成二维码的图片
								.setBarcodeImageEnabled(true)
								//设置自定义的扫描界面
								.setCaptureActivity(CustomScanActivity.class)
								// 初始化扫码
								.initiateScan();
			}
		});
		
		//预览摄像图
		AppCompatButton compatButton = findViewById(R.id.button_see);
		compatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ScanningActivity.this, DemoCameraActivity.class));
			
			}
		});
		
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if (resultCode == 100){
			String result1 = data.getStringExtra("result");
			Log.i(getClass().getName()+"==", "扫码结果 111111");
			if (!TextUtils.isEmpty(result1)){
				Toast
					.makeText(this, result1, Toast.LENGTH_LONG).show();
			}
		}
		
		if(result != null) {
			if(result.getContents() == null) {
				Toast
					.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
				Log.i(getClass().getName()+"==", "扫码结果 失败");
			} else {
				Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
				Log.i(getClass().getName()+"==", "扫码结果 成功Scanned: " + result.getContents());
			}
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
}
