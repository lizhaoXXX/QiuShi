package shi.qiu.com.org.qiushi.scan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import shi.qiu.com.org.qiushi.R;

/**
 * @author azhao
 * @date 2017/12/22
 * $desc 自定义扫描的界面
 */
public class CustomScanActivity extends AppCompatActivity {
	private CaptureManager       capture;
	private DecoratedBarcodeView barcodeScannerView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		barcodeScannerView = initializeContent();
		
//		barcodeScannerView.getViewFinder().drawViewfinder();
		capture = new CaptureManager(this, barcodeScannerView);
		capture.initializeFromIntent(getIntent(), savedInstanceState);
		capture.decode();
		
	
		barcodeScannerView.setStatusText("");
		barcodeScannerView.setBackgroundColor(Color.TRANSPARENT);
		
	}
	
	/**
	 * Override to use a different layout.
	 *
	 * @return the DecoratedBarcodeView
	 */
	protected DecoratedBarcodeView initializeContent() {
		setContentView(R.layout.activity_custom_scan);
		return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
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
}
