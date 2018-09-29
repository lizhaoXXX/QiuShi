package shi.qiu.com.org.qiushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author azhao
 * @date 2018/5/7
 * $desc
 */
public class WrapAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	public static final int TYPE_HEADER_REFRESH = 10000;
	public static final int TYPE_FOOT = 10001;
	public static final int TYPE_HEADER = 10002;
	private  Context context;
	private  RecyclerView.Adapter adapter;
	private boolean mLoadMoreEnable;
	
	public WrapAdapter(RecyclerView.Adapter adapter, Context context) {
		this.adapter = adapter;
		this.context = context;
	}
	
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		//判断类型是否使用下拉上拉
		
		//判断是否下拉刷新
		if (viewType == TYPE_HEADER_REFRESH){
		return new SimpleViewHolder(new HeaderRefreshView(context));
		}else if (viewType == TYPE_FOOT){
			return new SimpleViewHolder(new HeaderRefreshView(context));
		}
		return adapter.onCreateViewHolder(parent, viewType);
	}
	
	private boolean isHeader(int position){
		return position == 1;
	}
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		//如果是头，或者尾，则不需要跑这里
		if (isHeader(position) || isFoot(position)){
			return;
		}
		
		if (adapter != null){
			int contentPosition = position - 1;
			int itemCount = adapter.getItemCount();
			//如果当前显示的position比adapter要显示的数量小，则继续调用显示onBindViewHolder
			if (contentPosition < itemCount) {
				adapter.onBindViewHolder(holder, contentPosition);
			}
		}
	}
	
	private boolean isFoot(int position) {
		return position <= getItemCount() - 1;
	}
	
	@Override
	public int getItemCount() {
		//如果设置下拉更多则 + 2
		//如果不设置下拉更多则 + 1（刷新头）
		int headAndFoot = mLoadMoreEnable? 2:1;
		if (adapter != null){
			return adapter.getItemCount() + headAndFoot;
		}
		return 0;
	}
	
	@Override
	public int getItemViewType(int position) {
		
		if (isHeaderRefresh(position)){
			//刷新头的类型
			return TYPE_HEADER_REFRESH;
		}
		
		if (adapter != null){
			int contentPosition = position - 1;
			int adapterPosition = adapter.getItemCount();
			if (adapterPosition > contentPosition){
				//对用户使用的adapter的类型做一些判断
				int itemViewType = adapter.getItemViewType(contentPosition);
				if (itemViewType == TYPE_HEADER_REFRESH){
					throw new IllegalStateException("你使用的adapter type和内部的type有冲突");
				}
				return itemViewType;
			}
		}
		return 0;
	}
	
	private boolean isHeaderRefresh(int position) {
		return position == 0;
	}
	
	public void setLoadMoreEnable(boolean enable){
		mLoadMoreEnable = enable;
	}
	private class SimpleViewHolder extends RecyclerView.ViewHolder{
		 SimpleViewHolder(View itemView) {
			super(itemView);
		}
	}
}
