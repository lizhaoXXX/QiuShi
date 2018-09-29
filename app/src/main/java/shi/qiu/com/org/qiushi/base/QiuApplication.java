package shi.qiu.com.org.qiushi.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.bugly.crashreport.CrashReport;

import cn.jpush.android.api.JPushInterface;

/**
 * @author azhao
 * @date 2017/12/27
 * $desc
 */
public class QiuApplication extends Application {
	
	private static Context mContext;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
		
		//bugly的初始化
//		为了保证运营数据的准确性，建议不要在异步线程初始化Bugly。
//		第三个参数为SDK调试模式开关，调试模式的行为特性如下：
//		输出详细的Bugly SDK的Log；
//		每一条Crash都会被立即上报；
//		自定义日志将会在Logcat中输出。
//		建议在测试阶段建议设置成true，发布时设置为false。
		CrashReport.initCrashReport(getApplicationContext(), "9655a704cb", true);
//		CrashReport.initCrashReport(getApplicationContext());
	}
	
	public static Context getContext(){
		return mContext;
	}
	
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
}
