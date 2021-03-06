package shi.qiu.com.org.qiushi;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import java.net.Socket;

import shi.qiu.com.org.qiushi.adapter.HomeViewPagerAdapter;
import shi.qiu.com.org.qiushi.helper.BottomNavigationViewHelper;
import shi.qiu.com.org.qiushi.view.NoScrollLazyViewPager;

/**
 * @author azhao
 * @date 2017/11/29
 * $desc    主页
 */
public class HomeActivity extends AppCompatActivity {
	
	private NoScrollLazyViewPager mViewPager;
	private Socket                mSocket;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BottomNavigationView navigation = findViewById(R.id.navigation);
		BottomNavigationViewHelper.disableShiftMode(navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		mViewPager = findViewById(R.id.vp_view_pager);
		mViewPager.setAdapter(new HomeViewPagerAdapter(getSupportFragmentManager()));
		
		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		int memoryClass = am.getMemoryClass();
		Log.i(getClass().getName() + "==", "memoryClass =" + memoryClass);
		
		
	}
	
	
	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
		
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			switch (item.getItemId()) {
				case R.id.navigation_qiushi:
					mViewPager.setCurrentItem(0);
					return true;
				case R.id.navigation_friends:
					mViewPager.setCurrentItem(1);
					return true;
				case R.id.navigation_live_tv:
					mViewPager.setCurrentItem(2);
					return true;
				case R.id.navigation_notes:
					mViewPager.setCurrentItem(3);
					return true;
				case R.id.navigation_me:
					mViewPager.setCurrentItem(4);
					return true;
				default:
					break;
			}
			return false;
		}
	};
	
	
}
