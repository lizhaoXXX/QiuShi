package shi.qiu.com.org.qiushi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import shi.qiu.com.org.qiushi.qiu.QiuShiFragment;

/**
 * @author azhao
 * @date 2017/11/29
 * $desc 主页viewPager的adapter
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter {
	public HomeViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public Fragment getItem(int position) {
		switch (position){
		    case 0:
		    return QiuShiFragment.newInstance();
			case 1:
				return QiuShiFragment.newInstance();
			case 2:
				return QiuShiFragment.newInstance();
			case 3:
				return QiuShiFragment.newInstance();
			case 4:
				return QiuShiFragment.newInstance();
		    default:
		        break;
		}
		return null;
	}
	
	@Override
	public int getCount() {
		return 5;
	}
}
