package shi.qiu.com.org.qiushi.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;
import shi.qiu.com.org.qiushi.bean.DemoBean;

/**
 * @author azhao
 * @date 2018/1/2
 * $desc
 */
public class MyDemo extends BroadcastReceiver {
	NotificationManager nm;
	@Override
	public void onReceive(Context context, Intent intent) {
		
		if (null == nm) {
			nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		}
		
		Bundle bundle = intent.getExtras();
		
		if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
			//JPush用户注册成功
			
		} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
			Log.i(getClass().getName()+"==", "接受到推送下来的自定义消息 = " );
			dealWithMessage(bundle);
		} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
			Log.i(getClass().getName()+"==", "接受到推送下来的通知");
			receivingNotification(context,bundle);
			
		} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
			Log.i(getClass().getName()+"==", "用户点击打开了通知");
			openNotification(context,bundle);
			
		} else {
			Log.i(getClass().getName()+"==", "Unhandled intent - " + intent.getAction());
		}
	}
	
	private void dealWithMessage(Bundle bundle) {
		String extras = bundle.getString(JPushInterface.EXTRA_MESSAGE);
		try {
			Gson gson = new Gson();
			Log.i(getClass().getName()+"==", "extras = " +extras);
			
			DemoBean beans = gson.fromJson(extras, new TypeToken<DemoBean>() {}.getType());
			
				String content = beans.getResult()
									 .get(0)
									 .getContent();
				Log.i(getClass().getName() + "==", "content = " + content);
			
		} catch (Exception e) {
			Log.i(getClass().getName()+"==", "Unexpected: extras is not a valid json", e);
			return;
		}
	}
	
	private void receivingNotification(Context context, Bundle bundle){
		String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
		Log.i(getClass().getName()+"==", " title : " + title);
		String message = bundle.getString(JPushInterface.EXTRA_ALERT);
		Log.i(getClass().getName()+"==", "message : " + message);
		String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
		Log.i(getClass().getName()+"==", "extras : " + extras);
		
	}
	
	private void openNotification(Context context, Bundle bundle){
		String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
		String myValue = "";
		try {
			JSONObject extrasJson = new JSONObject(extras);
			myValue = extrasJson.optString("myKey");
		} catch (Exception e) {
			Log.i(getClass().getName()+"==", "Unexpected: extras is not a valid json", e);
			return;
		}
		/*if (TYPE_THIS.equals(myValue)) {
			Intent mIntent = new Intent(context, ThisActivity.class);
			mIntent.putExtras(bundle);
			mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(mIntent);
		} else if (TYPE_ANOTHER.equals(myValue)){
			Intent mIntent = new Intent(context, AnotherActivity.class);
			mIntent.putExtras(bundle);
			mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(mIntent);
		}*/
	}
	
}
