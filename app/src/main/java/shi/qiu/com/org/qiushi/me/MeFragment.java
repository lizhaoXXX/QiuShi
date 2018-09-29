package shi.qiu.com.org.qiushi.me;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tencent.bugly.crashreport.CrashReport;

import shi.qiu.com.org.qiushi.R;
import shi.qiu.com.org.qiushi.scan.ScanningActivity;

/**
 * @author azhao
 * @date 2017/12/5
 * $desc
 */
public class MeFragment extends Fragment {
	
	private TextView mTextView;
	private TextView mPopup;
	private boolean  bb;
	private TextView mBugly;
	private TextView text;
	
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
		
		initView(inflate);
		initEvent();
		initData();
		return inflate;
	}
	
	
	
	String aa;
	private void initEvent() {
		mTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), ScanningActivity.class));
			}
		});
		
		mPopup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				startActivity(new Intent(getActivity(), PopupWindowActivity.class));
				
				
			}
		});
		
		mBugly.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
//				CrashReport.testJavaCrash();
				
				// bugly会将这个throwable上报
				//					CrashReport.postCatchedException(e);
				
				try {
					Double.parseDouble(aa);
				} catch (Throwable e) {
					Toast
						.makeText(getActivity(), "11", Toast.LENGTH_SHORT)
						.show();
					CrashReport.postCatchedException(e);
				}
			}
		});
	}
	
	private void initView(View view) {
		mTextView = view.findViewById(R.id.tv_me_scan);
		mPopup = view.findViewById(R.id.tv_me_popup);
		mBugly = view.findViewById(R.id.tv_me_bugly);
		text = view.findViewById(R.id.tv_me_text);
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		Log.i(getClass().getName() + "=回调吗=", "444444");
		if (result != null) {
			if (result.getContents() == null) {
				Toast
					.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG)
					.show();
			} else {
				Toast
					.makeText(getActivity(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG)
					.show();
				Log.i(getClass().getName() + "=回调吗=", "Scanned: " + result.getContents());
			}
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
		
	}
	
	private void initData() {
//		numberRandom("abcd");
	};
	private void numberRandom(String lotNo) {
		StringBuilder number = new StringBuilder();
		int number10 = 10;
		String number7 = "7";
		int size = 8;
		int startSize;
		int enSize;
		String lotNoString;
		for (int i = 0; i < size; i++) {
			int num = (int) (Math.random() * number10);
			number.append(num);
		}
		String s = number.toString();
		if (s.contains(number7)){
			Log.i(getClass().getName()+"==", "1修改之前 abc = " + number.toString());
			Log.i(getClass().getName()+"==", "1修改之后 index abc = " + s.indexOf(number7));
			startSize = s.indexOf(number7) + 1;
			enSize = s.indexOf(number7) + 5;
			lotNoString = number.insert(s.indexOf(number7) + 1, lotNo).toString();
		}else {
			startSize = number.length();
			//整个随机字符中没有数字7，在末尾添加
			lotNoString = number.replace(number.length()- 1, number.length(),number7).append(lotNo).toString();
			enSize = lotNoString.length();
		}
		SpannableString spannableString = new SpannableString(lotNoString);
		//设置字体
		spannableString.setSpan(new AbsoluteSizeSpan(80), startSize, enSize, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//设置粗体
		spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), startSize, enSize, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//设置颜色
		spannableString.setSpan(new ForegroundColorSpan(Color.GREEN), startSize,enSize, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		text.setText(spannableString);
	}
}
