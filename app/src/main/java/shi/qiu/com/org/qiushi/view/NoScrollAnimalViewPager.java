package shi.qiu.com.org.qiushi.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * @author azhao
 * @date 2017/11/29
 * $desc    点击切换，无动画效果
 */
public class NoScrollAnimalViewPager extends ViewPager {
	
	public NoScrollAnimalViewPager(Context context) {
		super(context);
	}
	
	public NoScrollAnimalViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public void setCurrentItem(int item) {
		//表示切换的时候，不需要切换时间。
		super.setCurrentItem(item, false);
	}
}
