package shi.qiu.com.org.qiushi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import shi.qiu.com.org.qiushi.util.ScreenUtils;
import shi.qiu.com.org.qiushi.view.ExpandTextView;
/**
 * @author  azhao
 * @date    2017/12/19
 * $desc    textView 查看更多
 */
public class DemoActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_acitiy);
		ExpandTextView mContentExpandTextView =  findViewById(R.id.txt_content);
		// 设置TextView可展示的宽度 ( 父控件宽度 - 左右margin - 左右padding）
		
		int whidth = ScreenUtils.getScreenWidth(this) - ScreenUtils.dip2px(this, 16 * 2);
		// 设置最大行数(如果SDK >= 16 也可以直接在xml里设置)
		mContentExpandTextView.initWidth(whidth);
		mContentExpandTextView.setMaxLines(3);
		String content = "茫茫的长白大山，浩瀚的原始森林，大山脚下，原始森林环抱中散落着几十户人家的" + "一个小山村，茅草房，对面炕，烟筒立在屋后边。在村东头有一个独立的房子，那就是青年点，" + "窗前有一道小溪流过。学子在这里吃饭，由这里出发每天随社员去地里干活。干的活要么上山伐" + "树，抬树，要么砍柳树毛子开荒种地。在山里，可听那吆呵声：“顺山倒了！”放树谨防回头棒！" + "树上的枯枝打到别的树上再蹦回来，这回头棒打人最厉害。";
		mContentExpandTextView.setCloseText(content);
	}
}
