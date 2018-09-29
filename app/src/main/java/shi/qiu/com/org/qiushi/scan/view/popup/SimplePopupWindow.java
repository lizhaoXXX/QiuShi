package shi.qiu.com.org.qiushi.scan.view.popup;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/**
 * @author azhao
 * @date 2018/1/9
 * $desc
 */
public class SimplePopupWindow extends PopupWindow {
	
	private SimplePopupControl mSimplePopupControl;
	
	public SimplePopupWindow(Context context) {
		mSimplePopupControl = new SimplePopupControl(context, this);
	}
	
	@Override
	public void dismiss() {
		super.dismiss();
		mSimplePopupControl.setBackGroundLevel(1.0f);
	}
	
	@Override
	public int getHeight() {
		return mSimplePopupControl.mPopupView.getMeasuredHeight();
	}
	
	@Override
	public int getWidth() {
		return mSimplePopupControl.mPopupView.getMeasuredWidth();
	}
	
	public interface ViewInterface {
		/**
		 * 返回设置的view或者id
		 *
		 * @param view        view
		 * @param layoutResId layoutResId
		 */
		void popupView(View view, int layoutResId);
	}
	
	/**
	 * 临时获取用户设置的POPUP设置
	 */
	public static class SimpleBuilder {
		private SimplePopupControl.PopupParams mPopupParams;
		private ViewInterface                  listener;
		
		public SimpleBuilder(Context context) {
			mPopupParams = new SimplePopupControl.PopupParams(context);
		}
		
		/**
		 * @param layoutResId 设置PopupWindow 布局ID
		 * @return SimpleBuilder
		 */
		public SimpleBuilder setView(int layoutResId) {
			mPopupParams.mView = null;
			mPopupParams.layoutResId = layoutResId;
			return this;
		}
		
		/**
		 * @param view 设置PopupWindow布局
		 * @return SimpleBuilder
		 */
		public SimpleBuilder setView(View view) {
			mPopupParams.mView = view;
			mPopupParams.layoutResId = 0;
			return this;
		}
		
		/**
		 * 设置子View
		 *
		 * @param listener ViewInterface
		 * @return SimpleBuilder
		 */
		public SimpleBuilder setViewOnclickListener(ViewInterface listener) {
			this.listener = listener;
			return this;
		}
		
		/**
		 * 设置宽度和高度 如果不设置 默认是wrap_content
		 *
		 * @param width 宽
		 * @return SimpleBuilder
		 */
		public SimpleBuilder setWidthAndHeight(int width, int height) {
			mPopupParams.mWidth = width;
			mPopupParams.mHeight = height;
			return this;
		}
		
		/**
		 * 设置背景灰色程度
		 *
		 * @param level 0.0f-1.0f
		 * @return SimpleBuilder
		 */
		public SimpleBuilder setBackGroundLevel(float level) {
			mPopupParams.isShowBg = true;
			mPopupParams.backGroundLevel = level;
			return this;
		}
		
		/**
		 * 是否可点击Outside消失
		 *
		 * @param touchable 是否可点击
		 * @return SimpleBuilder
		 */
		public SimpleBuilder setOutsideTouchable(boolean touchable) {
			mPopupParams.isTouchable = touchable;
			return this;
		}
		
		/**
		 * 如果popup中使用edit text，需要获取焦点
		 *
		 * @param focusable 是否可获取焦点
		 * @return SimpleBuilder
		 */
		public SimpleBuilder setFocusable(boolean focusable) {
			mPopupParams.isFocusable = focusable;
			return this;
		}
		
		/**
		 * 设置动画
		 *
		 * @return SimpleBuilder
		 */
		public SimpleBuilder setAnimationStyle(int animationStyle) {
			mPopupParams.isShowAnim = true;
			mPopupParams.animationStyle = animationStyle;
			return this;
		}
		
		public SimplePopupWindow builder() {
			SimplePopupWindow simplePopupWindow = new SimplePopupWindow(mPopupParams.mContext);
			mPopupParams.apply(simplePopupWindow.mSimplePopupControl);
			if (listener != null && mPopupParams.layoutResId != 0) {
				listener.popupView(simplePopupWindow.mSimplePopupControl.mPopupView, mPopupParams.layoutResId);
			}
			
			//https://www.cnblogs.com/gumf/p/4148863.html
			//强制绘制view的大小，否则getHeight , getWith都是0
			int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
			int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
			simplePopupWindow.mSimplePopupControl.mPopupView.measure(widthMeasureSpec, heightMeasureSpec);
			
			return simplePopupWindow;
		}
	}
	
	/*Window w = getWindow();
		if (w != null) {
		WindowManager.LayoutParams lp = w.getAttributes();
		w.setGravity(Gravity.CENTER);
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		if (manager != null) {
			//设置popupWindow的布局
			setContentView(mInflate);
			manager
				.getDefaultDisplay()
				.getMetrics(metrics);
			int width = metrics.widthPixels;
			int height = metrics.heightPixels;
			//这里是设置popupWindow的宽度为手机屏幕的80%
			lp.width = (int) (width * 0.8);
			lp.height = LinearLayout.LayoutParams.WRAP_CONTENT | (int) (height * 0.8);
			w.setAttributes(lp);
			initView(mInflate);
			
		}
	}*/
}
