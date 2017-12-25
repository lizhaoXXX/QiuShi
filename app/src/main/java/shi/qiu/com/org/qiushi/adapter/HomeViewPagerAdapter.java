package shi.qiu.com.org.qiushi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import shi.qiu.com.org.qiushi.friend.FriendFragment;
import shi.qiu.com.org.qiushi.me.MeFragment;
import shi.qiu.com.org.qiushi.notes.NotesFragment;
import shi.qiu.com.org.qiushi.qiu.QiuShiFragment;
import shi.qiu.com.org.qiushi.tv.TvFragment;

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
				return FriendFragment.newInstance("糗友圈");
			case 2:
				return TvFragment.newInstance("直播");
			case 3:
				return NotesFragment.newInstance("小纸条");
			case 4:
				return MeFragment.newInstance("我");
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
