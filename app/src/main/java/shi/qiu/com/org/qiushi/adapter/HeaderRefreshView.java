package shi.qiu.com.org.qiushi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import shi.qiu.com.org.qiushi.R;

/**
 * @author azhao
 * @date 2018/5/7
 * $desc
 */
public class HeaderRefreshView extends LinearLayout {
	public HeaderRefreshView(Context context) {
		this(context, null);
	}
	
	public HeaderRefreshView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.view_header, this);
	}
}
