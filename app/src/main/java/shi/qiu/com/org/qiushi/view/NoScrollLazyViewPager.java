package shi.qiu.com.org.qiushi.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @描述: 不可滑动的viewpager
 */
public class NoScrollLazyViewPager extends ViewPager {

    public NoScrollLazyViewPager(Context context) {
        super(context);
    }

    public NoScrollLazyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override

    public void setCurrentItem(int item) {

        super.setCurrentItem(item, false);//表示切换的时候，不需要切换时间。

    }
}
