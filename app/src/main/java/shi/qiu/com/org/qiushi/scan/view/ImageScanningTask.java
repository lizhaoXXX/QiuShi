package shi.qiu.com.org.qiushi.scan.view;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.google.zxing.Result;

/**
 * @author azhao
 * @date 2017/12/27
 * $desc
 */
public class ImageScanningTask extends AsyncTask<Uri, Void, Result> {
	private  Context context;
	private Uri uri;
	private ImageScanningCallback callback;
	
	public ImageScanningTask(Context context, Uri uri, ImageScanningCallback callback) {
		this.uri = uri;
		this.callback = callback;
		this.context = context;
	}
	
	@Override
	protected Result doInBackground( Uri... params) {
		return CodeScanningUtil.scanImage(uri, context);
	}
	
	@Override
	protected void onPostExecute(Result result) {
		super.onPostExecute(result);
		callback.onFinishScanning(result);
	}
	
	public interface ImageScanningCallback {
		void onFinishScanning(Result result);
	}
}

