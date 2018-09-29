package shi.qiu.com.org.qiushi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author azhao
 * @date 2018/5/7
 * $desc
 */
public class EasyRecyclerView extends RecyclerView {
	public EasyRecyclerView(Context context) {
		this(context, null);
	}
	
	public EasyRecyclerView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public EasyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	public void setAdapter(Adapter adapter) {
		WrapAdapter wrapAdapter = new WrapAdapter(adapter, getContext());
		super.setAdapter(wrapAdapter);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		return super.onTouchEvent(e);
	}
	
}
