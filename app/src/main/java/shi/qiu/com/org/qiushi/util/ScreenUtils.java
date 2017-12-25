package shi.qiu.com.org.qiushi.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author azhao
 * @date 2017/12/19
 * $desc    获得屏幕相关的辅助类
 */
public class ScreenUtils {
	private int appHeight = 0;
	private int appWidth  = 0;
	private static int        StatusBarHeight;
	private        Context    context;
	private static ScreenUtils screenUtil;
	
	public static ScreenUtils getInstance() {
		return screenUtil;
	}
	
	public static void init(Context context) {
		screenUtil = new ScreenUtils(context);
	}
	
	/**
	 * 获得屏幕高度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context)
	{
		WindowManager wm = (WindowManager) context
											   .getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}
	
	/**
	 * 获得屏幕宽度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context)
	{
		WindowManager wm = (WindowManager) context
											   .getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}
	
	
	public ScreenUtils(Context context) {
		this.context = context;
		DisplayMetrics dm = context
								.getResources()
								.getDisplayMetrics();
		int appHeight = dm.heightPixels;
		int appWidth = dm.widthPixels;
		if (appWidth > appHeight) {
			this.appWidth = appHeight;
			this.appHeight = appWidth;
		} else {
			this.appWidth = appWidth;
			this.appHeight = appHeight;
		}
		
	}
	
	public int getAppHeight() {
		return this.appHeight;
	}
	
	public int getAppWidth() {
		return this.appWidth;
	}
	
	public int px2dip(float pxValue) {
		float scale = this.context
						  .getResources()
						  .getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5F);
	}
	
	public int dip2px(float dipValue) {
		float scale = this.context
						  .getResources()
						  .getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5F);
	}
	
	public int px2sp(float pxValue) {
		float fontScale = this.context
							  .getResources()
							  .getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5F);
	}
	
	public int sp2px(float spValue) {
		float fontScale = this.context
							  .getResources()
							  .getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5F);
	}
	
	public static int sp2px(Context context, float spValue) {
		float fontScale = context
							  .getResources()
							  .getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5F);
	}
	
	public static int px2sp(Context context, float pxValue) {
		float scaled = context
						   .getResources()
						   .getDisplayMetrics().scaledDensity;
		return (int) (pxValue / scaled + 0.5F);
	}
	
	public static final int getHeightInPx(Context context) {
		int height = context
						 .getResources()
						 .getDisplayMetrics().heightPixels;
		return height;
	}
	
	public static final int getWidthInPx(Context context) {
		int width = context
						.getResources()
						.getDisplayMetrics().widthPixels;
		return width;
	}
	
	public static int dip2px(Context context, float dpValue) {
		float scale = context
						  .getResources()
						  .getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5F);
	}
	
	public static int px2dip(Context context, float pxValue) {
		float scale = context
						  .getResources()
						  .getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5F);
	}
	

	
	
	
	
	
}