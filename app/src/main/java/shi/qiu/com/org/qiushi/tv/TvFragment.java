package shi.qiu.com.org.qiushi.tv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shi.qiu.com.org.qiushi.R;

/**
 * @author azhao
 * @date 2017/12/5
 * $desc
 */
public class TvFragment extends Fragment {
	public static TvFragment newInstance(String s) {
		TvFragment friendFragment = new TvFragment();
		Bundle bundle = new Bundle();
		bundle.putString("title", s);
		friendFragment.setArguments(bundle);
		return friendFragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View inflate = View.inflate(getActivity(), R.layout.fragment_friends, null);
		Bundle arguments = getArguments();
		return inflate;
	}
}
