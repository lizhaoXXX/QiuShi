package shi.qiu.com.org.qiushi.qiu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import shi.qiu.com.org.qiushi.R;
import shi.qiu.com.org.qiushi.adapter.VipSatinAdapter;
import shi.qiu.com.org.qiushi.bean.VipBean;

/**
 * @author azhao
 * @date 2017/11/29
 * $desc 专享
 */
public class VipFragment extends Fragment {
	
	private RecyclerView mRecyclerView;
	private VipSatinAdapter mVipSatinAdapter;
	
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
		
		mRecyclerView = inflate.findViewById(R.id.recycler_recycler_view);
		return inflate;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
	}
	
	private void initData() {
		VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getActivity(), VirtualLayoutManager.VERTICAL, false);
		mRecyclerView.setLayoutManager(virtualLayoutManager);
		
		//设置线性布局
		LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
		linearLayoutHelper.setItemCount(4);
		linearLayoutHelper.setMarginBottom(100);
		linearLayoutHelper.setDividerHeight(10);
		
		
		List<DelegateAdapter.Adapter> adapterData = new LinkedList<>();
		mVipSatinAdapter = new VipSatinAdapter(getActivity(), linearLayoutHelper);
		adapterData.add(mVipSatinAdapter);
		
		DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
		delegateAdapter.setAdapters(adapterData);
		
		mRecyclerView.setAdapter(delegateAdapter);
		
		refreshData();
		
	}
	
	private void refreshData() {
		List<VipBean> data = new ArrayList<>();
		        for (int i = 0; i < 20; i++) {
		        	if (i % 2 == 0) {
						VipBean vipBean = new VipBean();
						vipBean.setType(0);
						vipBean.setName("Demo" + i);
						vipBean.setContent("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
						vipBean.setIcon("http://img.zcool.cn/community/01b17358ad6c33a801219c7742145f.jpg@2o.jpg");
						data.add(vipBean);
					}else {
						VipBean vipBean = new VipBean();
						vipBean.setType(1);
						vipBean.setName("Demo" + i);
						vipBean.setContent("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd22222222222222222222222222222222222222222222222222222222222");
						vipBean.setIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512388059507&di=7da1e11185d6f6cb649f833fb20627ba&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F6f061d950a7b0208d8607f0d68d9f2d3562cc8b5.jpg");
						vipBean.setImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512388059507&di=7da1e11185d6f6cb649f833fb20627ba&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F6f061d950a7b0208d8607f0d68d9f2d3562cc8b5.jpg");
						data.add(vipBean);
					}
					
		        
		        }
		
		mVipSatinAdapter.addData(data);
	}
}
