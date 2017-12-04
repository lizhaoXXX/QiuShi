package shi.qiu.com.org.qiushi.qiu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import shi.qiu.com.org.qiushi.R;

/**
 * @author azhao
 * @date 2017/11/29
 * $desc 专享
 */
public class VipFragment extends Fragment {
	public static VipFragment newInstance(String title){
		VipFragment vipFragment = new VipFragment();
		Bundle bundle = new Bundle();
		bundle.putString("title", title);
		vipFragment.setArguments(bundle);
		return vipFragment;
	}
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View inflate = View.inflate(getActivity(), R.layout.fragment_vip, null);
		TextView textView = inflate.findViewById(R.id.tv_text);
		Bundle arguments = getArguments();
		textView.setText(arguments.getString("title"));
		return inflate;
	}
}
