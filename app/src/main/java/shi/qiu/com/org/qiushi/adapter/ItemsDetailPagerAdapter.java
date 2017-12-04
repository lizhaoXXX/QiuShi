package shi.qiu.com.org.qiushi.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author azhao
 * @date 2017/6/12
 * @dec
 */

public class ItemsDetailPagerAdapter extends FragmentPagerAdapter {
	
	private final List<Fragment> mFragmentData;
	private List<String> mTitle;
    private Bundle       mBundle;

    public ItemsDetailPagerAdapter(FragmentManager fm, Bundle mArguments, List<String> title, List<Fragment> fragmentData) {
        super(fm);
        mTitle = title;
        mBundle = mArguments;
		mFragmentData = fragmentData;
    }

    @Override
    public Fragment getItem(int position) {
		return mFragmentData.get(position);
    }

    @Override
    public int getCount() {
        return mTitle != null?mTitle.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
