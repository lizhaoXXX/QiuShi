package shi.qiu.com.org.qiushi.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import shi.qiu.com.org.qiushi.R;
import shi.qiu.com.org.qiushi.scan.view.popup.SimplePopupWindow;

/**
 * @author azhao
 * @date 2018/1/15
 * $desc
 */
public class PopupWindowActivity extends AppCompatActivity {
	
	private AppCompatButton   mPopupRight;
	private SimplePopupWindow mBuilder;
	private AppCompatButton   mPopupLeft;
	private AppCompatButton   mPopupUp;
	private AppCompatButton   mDown;
	private AppCompatButton   mAll;
	private SimplePopupWindow mAllBuilder;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activty_popup_window);
		
		initView();
		initPopup();
		initEvent();
		
	}
	
	private void initPopup() {
		SimplePopupWindow.SimpleBuilder simpleBuilder = new SimplePopupWindow.SimpleBuilder(PopupWindowActivity.this);
		mBuilder = simpleBuilder
					   .setView(R.layout.popup_me_demo)
					   .setFocusable(true)
					   .setBackGroundLevel(1.0f)
					   .builder();
		
	}
	
	private void initEvent() {
		mPopupRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mBuilder.setAnimationStyle(R.style.leftAnimation);
				mBuilder.showAsDropDown(mPopupRight);
			}
		});
		
		mPopupLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mBuilder.setAnimationStyle(R.style.rightAnimation);
				mBuilder.showAsDropDown(mPopupLeft);
			}
		});
		
		mPopupUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mBuilder.setAnimationStyle(R.style.bottomToTopScaleAnimation);
				int measuredHeight = mPopupUp.getMeasuredHeight();
				int height = mBuilder.getHeight();
				Log.i(getClass().getName() + "==", "measuredHeight = " + measuredHeight + "--height=" + height);
				//显示的位置 记得前面加个 （ - ）
				mBuilder.showAsDropDown(mPopupUp, 0, -(height + measuredHeight));
			}
		});
		
		mDown.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mBuilder.setAnimationStyle(R.style.topToBottomScaleAnimation);
				mBuilder.showAsDropDown(mDown);
			}
		});
		
		mAll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
					SimplePopupWindow.SimpleBuilder simpleBuilder = new SimplePopupWindow.SimpleBuilder(PopupWindowActivity.this);
					mAllBuilder = simpleBuilder
									  .setView(R.layout.popup_all)
									  .setOutsideTouchable(true)
									  .setBackGroundLevel(0.5f)
									  .setAnimationStyle(R.style.bottomToTopScaleAnimation)
									  .setViewOnclickListener(new SimplePopupWindow.ViewInterface() {
										  @Override
										  public void popupView(View view, int layoutResId) {
											 
										  }
									  })
									  .builder();
					mAllBuilder.showAtLocation(mAll, Gravity.BOTTOM, 0, 0);
			
			}
		});
	}
	
	private void initView() {
		mPopupRight = findViewById(R.id.bt_popup_right);
		mPopupLeft = findViewById(R.id.bt_popup_left);
		mPopupUp = findViewById(R.id.bt_popup_up);
		mDown = findViewById(R.id.bt_popup_down);
		mAll = findViewById(R.id.bt_popup_all);
	}
}
