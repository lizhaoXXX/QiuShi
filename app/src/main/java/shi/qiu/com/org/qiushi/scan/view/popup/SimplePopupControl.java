package shi.qiu.com.org.qiushi.scan.view.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * @author azhao
 * @date 2018/1/9
 * $desc 处理popup参数的设置
 */
public class SimplePopupControl {
	
	private Context     mContext;
	private PopupWindow mPopupWindow;
	public View        mPopupView;
	private int         mLayoutID;
	private View        mView;
	
	public SimplePopupControl(Context context, PopupWindow popupWindow) {
		mPopupWindow = popupWindow;
		mContext = context;
	}
	
	private void setView(View view) {
		mView = view;
		mLayoutID = 0;
		initContentView();
	}
	
	private void setLayoutID(int layoutID) {
		mLayoutID = layoutID;
		mView = null;
		initContentView();
		
	}
	
	private void initContentView() {
		if (mView != null) {
			mPopupView = mView;
		} else if (mLayoutID != 0) {
			View inflate = LayoutInflater
							   .from(mContext)
							   .inflate(mLayoutID, null);
			mPopupView = inflate;
			
		}
		mPopupWindow.setContentView(mPopupView);
	}
	
	private void setOutsideTouchable(boolean outsideTouchable) {
		//设置透明
		mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
		//设置点击外部消失
		mPopupWindow.setOutsideTouchable(outsideTouchable);
	}
	
	private void setFocusable(boolean focusable){
		mPopupWindow.setFocusable(focusable);
	}
	public void setBackGroundLevel(float backGroundLevel) {
		Window window = ((Activity) mContext).getWindow();
		WindowManager.LayoutParams attributes = window.getAttributes();
		attributes.alpha = backGroundLevel;
		window.setAttributes(attributes);
	}
	
	private void setAnimationStyle(int animationStyle) {
		mPopupWindow.setAnimationStyle(animationStyle);
	}
	
	private void setWidthAndHeight(int width, int height) {
		if (height == 0) {
			mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		} else {
			mPopupWindow.setHeight(height);
		}
		
		if (width == 0) {
			mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
		} else {
			mPopupWindow.setWidth(width);
		}
	}
	
	/**
	 * 把临时存储的参数设置信息，传递给SimplePopupControl
	 */
	static class PopupParams {
		public View mView;
		public int  layoutResId;
		
		/**
		 * popupWindow的宽和高
		 */
		public int mWidth, mHeight;
		
		/**
		 * 是否显示背景，是否显示动画
		 */
		public boolean isShowBg, isShowAnim;
		
		/**
		 * 动画Id
		 */
		public int animationStyle;
		
		/**
		 * 屏幕背景灰色程度
		 */
		public float backGroundLevel;
		
		/**
		 * 点击其他区域，关闭popup
		 */
		public boolean isTouchable = true;
		public boolean isFocusable = false;
		public Context mContext;
		
		public PopupParams(Context context) {
			mContext = context;
		}
		
		public void apply(SimplePopupControl simplePopupControl) {
			//如果两个都设置，则以第一个为主
			if (mView != null) {
				simplePopupControl.setView(mView);
			} else if (layoutResId != 0) {
				simplePopupControl.setLayoutID(layoutResId);
			} else {
				throw new IllegalArgumentException("PopupView's contentView is null");
			}
			
			simplePopupControl.setWidthAndHeight(mWidth, mHeight);
			simplePopupControl.setOutsideTouchable(isTouchable);
			simplePopupControl.setFocusable(isFocusable);
			if (isShowBg) {
				//设置背景
				simplePopupControl.setBackGroundLevel(backGroundLevel);
			}
			
			if (isShowAnim) {
				simplePopupControl.setAnimationStyle(animationStyle);
			}
			
		}
	}
	
}
