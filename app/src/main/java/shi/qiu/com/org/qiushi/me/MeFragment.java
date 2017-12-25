package shi.qiu.com.org.qiushi.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import shi.qiu.com.org.qiushi.R;
import shi.qiu.com.org.qiushi.scan.ScanningActivity;

/**
 * @author azhao
 * @date 2017/12/5
 * $desc
 */
public class MeFragment extends Fragment {
	
	private AppCompatButton mTextView;
	
	public static MeFragment newInstance(String s) {
		MeFragment friendFragment = new MeFragment();
		Bundle bundle = new Bundle();
		bundle.putString("title", s);
		friendFragment.setArguments(bundle);
		return friendFragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View inflate = View.inflate(getActivity(), R.layout.fragment_friends, null);
		mTextView = inflate.findViewById(R.id.tv_text);
		return inflate;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			startActivity(new Intent(getActivity(), ScanningActivity.class));
			}
		});
		
	}
	
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		Log.i(getClass().getName()+"=回调吗=", "444444");
		if(result != null) {
			if(result.getContents() == null) {
				Toast
					.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getActivity(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
				Log.i(getClass().getName()+"=回调吗=", "Scanned: " + result.getContents());
			}
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
		
	}
}
