package shi.qiu.com.org.qiushi.qiu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import shi.qiu.com.org.qiushi.R;
import shi.qiu.com.org.qiushi.adapter.ItemsDetailPagerAdapter;
import shi.qiu.com.org.qiushi.view.NoScrollAnimalViewPager;

/**
 * @author azhao
 * @date 2017/11/29
 * $desc 糗事
 */
public class QiuShiFragment extends Fragment {
	
	private TabLayout               mTabLayout;
	private NoScrollAnimalViewPager mViewPager;
	private ArrayList<String>       mData;
	
	public static QiuShiFragment newInstance() {
		return new QiuShiFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View inflate = View.inflate(getActivity(), R.layout.fragment_qiu_shi, null);
		initView(inflate);
		return inflate;
	}
	
	private void initView(View view) {
		mTabLayout = view.findViewById(R.id.tl_tab);
		mViewPager = view.findViewById(R.id.vp_qiu_view_pager);
		
		mData = new ArrayList<>();
		mData.add("专享");
		mData.add("视频");
		mData.add("爆社");
		mData.add("纯文");
		mData.add("纯图");
		mData.add("精华");
		mData.add("穿越");
		
		List<Fragment> fragmentData = new ArrayList<>();
		int size = mData.size();
		
		for (int i = 0; i < size; i++) {
			fragmentData.add(VipFragment.newInstance("页面：" ));
		}
		mViewPager.setAdapter(new ItemsDetailPagerAdapter(getChildFragmentManager(), null, mData, fragmentData));
		mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		mTabLayout.setupWithViewPager(mViewPager);
		
		setTagView();
	}
	
	private void setTagView() {
		
		//		mTabLayout.post(new Runnable() {
		//			@Override
		//			public void run() {
		//				setIndicator(mTabLayout, 2, 2);
		//			}
		//		});
		
		int mTabCount = mTabLayout.getTabCount();
		for (int i = 0; i < mTabCount; i++) {
			TabLayout.Tab mTabAt = mTabLayout.getTabAt(i);
			mTabAt.setCustomView(R.layout.tab_layout);
			MyViewHolder mMyViewHolder = new MyViewHolder(mTabAt.getCustomView());
			mMyViewHolder.tabTitle.setText(mData.get(i));
			if (i != 0) {
				mMyViewHolder.tabTitle.setTextSize(14);
				mMyViewHolder.tabTitle.setTextColor(ContextCompat.getColor(getActivity(), R.color.title_tab_no_select));
				
			} else {
				mMyViewHolder.tabTitle.setTextSize(18);
				mMyViewHolder.tabTitle.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_white));
			}
		}
		
		mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				
				MyViewHolder mMyViewHolder = new MyViewHolder(tab.getCustomView());
				mMyViewHolder.tabTitle.setTextSize(18);
				mMyViewHolder.tabTitle.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_white));
			}
			
			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
				MyViewHolder mMyViewHolder = new MyViewHolder(tab.getCustomView());
				mMyViewHolder.tabTitle.setTextSize(14);
				mMyViewHolder.tabTitle.setTextColor(ContextCompat.getColor(getActivity(), R.color.title_tab_no_select));
				
			}
			
			@Override
			public void onTabReselected(TabLayout.Tab tab) {
			
			}
		});
	}
	
	public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
		Class<?> tabLayout = tabs.getClass();
		Field tabStrip = null;
		try {
			tabStrip = tabLayout.getDeclaredField("mTabStrip");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		
		tabStrip.setAccessible(true);
		LinearLayout llTab = null;
		try {
			llTab = (LinearLayout) tabStrip.get(tabs);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources
																							 .getSystem()
																							 .getDisplayMetrics());
		int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources
																							   .getSystem()
																							   .getDisplayMetrics());
		
		for (int i = 0; i < llTab.getChildCount(); i++) {
			View child = llTab.getChildAt(i);
			child.setPadding(0, 0, 0, 0);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
			params.leftMargin = left;
			params.rightMargin = right;
			child.setLayoutParams(params);
			child.invalidate();
		}
	}
	
	private class MyViewHolder {
		private TextView tabTitle;
		
		public MyViewHolder(View itemView) {
			tabTitle = (TextView) itemView.findViewById(R.id.tv_tab_text);
		}
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
	public void onDestroy() {
		super.onDestroy();
		if (!mData.isEmpty()){
			mData.clear();
		}
		mViewPager.setAdapter(null);
	}
}
