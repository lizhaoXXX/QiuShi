//package shi.qiu.com.org.qiushi.view;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Rect;
//import android.support.annotation.Nullable;
//import android.text.BoringLayout;
//import android.text.TextUtils;
//import android.util.AttributeSet;
//
///**
// * @author azhao
// * @date 2017/12/18
// * $desc
// */
//public class LookAtMoreTextView extends android.support.v7.widget.AppCompatTextView{
//	public LookAtMoreTextView(Context context) {
//		this(context, null);
//	}
//	public LookAtMoreTextView(Context context, @Nullable AttributeSet attrs) {
//		super(context, attrs);
//	}
//
//	@Override
//	public int getLineCount() {
//		return super.getLineCount();
//	}
//
//	@Override
//	public TextUtils.TruncateAt getEllipsize() {
//		return super.getEllipsize();
//	}
//
//	@Override
//	public void setEllipsize(TextUtils.TruncateAt where) {
//		super.setEllipsize(where);
//	}
//
//	@Override
//	public int getLineBounds(int line, Rect bounds) {
//		return super.getLineBounds(line, bounds);
//	}
//
//	@Override
//	public int getLineHeight() {
//		return super.getLineHeight();
//	}
//
//	getla
//	@Override
//	protected void onDraw(Canvas canvas) {
//		TextUtils.ellipsize(getText(), getPaint(), , TextUtils.TruncateAt.END,false,
//		new TextUtils.EllipsizeCallback(){
//			@Override
//			public void ellipsized(int start, int end) {
//
//			}
//		});
//
//
//	}
//}
