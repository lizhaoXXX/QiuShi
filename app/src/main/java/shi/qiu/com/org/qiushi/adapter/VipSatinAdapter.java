package shi.qiu.com.org.qiushi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.ArrayList;
import java.util.List;

import shi.qiu.com.org.qiushi.R;
import shi.qiu.com.org.qiushi.bean.VipBean;

/**
 * @author azhao
 * @date 2017/12/4
 * $desc 专享--单纯文字显示的布局类型
 */
public class VipSatinAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {
	
	private ArrayList<VipBean> mData;
	private Context            mContext;
	private LayoutHelper       mLayoutHelper;
	private final int TEXT_TYPE           = 0;
	private final int TEXT_AND_IMAGE_TYPE = 1;
	private final int IMAGE_TYPE          = 2;
	private final int VIDEO_TYPE          = 3;
	private final int ADVERTISEMENT_TYPE  = 4;
	private LayoutInflater mLayoutInflater;
	
	public VipSatinAdapter(Context context, LayoutHelper layoutHelper) {
		mContext = context;
		mLayoutHelper = layoutHelper;
		mData = new ArrayList<>();
		mLayoutInflater = LayoutInflater.from(context);
	}
	
	@Override
	public LayoutHelper onCreateLayoutHelper() {
		return mLayoutHelper;
	}
	
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		switch (viewType) {
			case TEXT_TYPE:
				//只有文字
				return new VipSatinTextViewHolder(mLayoutInflater.inflate(R.layout.recyler_vip_text, parent, false));
			case TEXT_AND_IMAGE_TYPE:
				//只有文字 + 图片
				return new VipSatinTextAndPicturesViewHolder(mLayoutInflater.inflate(R.layout.recyler_vip_text_iamge, parent, false));
			case VIDEO_TYPE:
				//只有文字 + 视频
				return new VipSatinTextAndVideoViewHolder(mLayoutInflater.inflate(R.layout.recyler_vip_text_iamge, parent, false));
			case IMAGE_TYPE:
				//单张图片
				return new VipSatinImageViewHolder(mLayoutInflater.inflate(R.layout.recyler_vip_text_iamge, parent, false));
			case ADVERTISEMENT_TYPE:
				//广告（文字 + 图片）
				return new VipSatinAdvertisementViewHolder(mLayoutInflater.inflate(R.layout.recyler_vip_advertisement_text_iamge, parent, false));
			default:
				break;
		}
		return null;
		
	}
	
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		int type = mData
					   .get(position)
					   .getType();
		switch (type) {
			case TEXT_TYPE:
				showTextType((VipSatinTextViewHolder)holder, position);
				break;
			case TEXT_AND_IMAGE_TYPE:
				showTextAndImageType((VipSatinTextAndPicturesViewHolder)holder, position);
				break;
			case VIDEO_TYPE:
				showVideoType((VipSatinTextAndVideoViewHolder)holder, position);
				break;
			case IMAGE_TYPE:
				showImageType((VipSatinImageViewHolder)holder, position);
				break;
			case ADVERTISEMENT_TYPE:
				showAdvertisementType((VipSatinAdvertisementViewHolder)holder, position);
				break;
			default:
				break;
		}
	}
	
	private void showAdvertisementType(VipSatinAdvertisementViewHolder holder, int position) {
	}
	
	private void showImageType(VipSatinImageViewHolder holder, int position) {
	
	}
	
	private void showVideoType(VipSatinTextAndVideoViewHolder holder, int position) {
	
	}
	
	private void showTextAndImageType(final VipSatinTextAndPicturesViewHolder holder, int position) {
		VipBean vipBean = mData.get(position);
		holder.mName.setText(vipBean.getName());
		holder.mContent.setText(vipBean.getContent());
		/*Glide
			.with(mContext).load(vipBean.getImage()).into(holder.mContentIcon);
		Glide.with(mContext).load(vipBean.getIcon()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.mHead) {
			@Override
			protected void setResource(Bitmap resource) {
				RoundedBitmapDrawable circularBitmapDrawable =
					RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
				circularBitmapDrawable.setCircular(true);
				holder.mHead.setImageDrawable(circularBitmapDrawable);
			}
		});*/
	}
	
	private void showTextType(final VipSatinTextViewHolder holder, int position) {
		VipBean vipBean = mData.get(position);
		holder.mName.setText(vipBean.getName());
		holder.mContent.setText(vipBean.getContent());
		/*Glide.with(mContext).load(vipBean.getIcon()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.mHead) {
			@Override
			protected void setResource(Bitmap resource) {
				RoundedBitmapDrawable circularBitmapDrawable =
					RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
				circularBitmapDrawable.setCircular(true);
				holder.mHead.setImageDrawable(circularBitmapDrawable);
			}
		});*/
	}
	
	@Override
	public int getItemCount() {
		return mData != null ? mData.size() : 0;
	}
	
	private class VipSatinTextViewHolder extends RecyclerView.ViewHolder {
		
		private  ImageView mHead;
		private  TextView mName;
		private  TextView mContent;
		
		private VipSatinTextViewHolder(View itemView) {
			super(itemView);
			mHead = itemView.findViewById(R.id.iv_head);
			mName = itemView.findViewById(R.id.tv_vip_name);
			mContent = itemView.findViewById(R.id.tv_vip_content);
		}
	}
	
	private class VipSatinTextAndPicturesViewHolder extends RecyclerView.ViewHolder {
		
		private  ImageView mHead;
		private  ImageView mContentIcon;
		private  TextView mName;
		private  TextView mContent;
		
		private VipSatinTextAndPicturesViewHolder(View itemView) {
			super(itemView);
			mHead = itemView.findViewById(R.id.iv_head);
			mContentIcon = itemView.findViewById(R.id.iv_content_image);
			mName = itemView.findViewById(R.id.tv_vip_name);
			mContent = itemView.findViewById(R.id.tv_vip_content);
		}
	}
	
	private class VipSatinTextAndVideoViewHolder extends RecyclerView.ViewHolder {
		private VipSatinTextAndVideoViewHolder(View itemView) {
			super(itemView);
		}
	}
	
	private class VipSatinImageViewHolder extends RecyclerView.ViewHolder {
		private VipSatinImageViewHolder(View itemView) {
			super(itemView);
		}
	}
	
	private class VipSatinAdvertisementViewHolder extends RecyclerView.ViewHolder {
		private VipSatinAdvertisementViewHolder(View itemView) {
			super(itemView);
		}
	}
	
	@Override
	public int getItemViewType(int position) {
		int type = mData
					   .get(position)
					   .getType();
		switch (type) {
			case TEXT_TYPE:
				return TEXT_TYPE;
			case TEXT_AND_IMAGE_TYPE:
				return TEXT_AND_IMAGE_TYPE;
			case VIDEO_TYPE:
				return VIDEO_TYPE;
			case IMAGE_TYPE:
				return IMAGE_TYPE;
			case ADVERTISEMENT_TYPE:
				return ADVERTISEMENT_TYPE;
			default:
				break;
		}
		return super.getItemViewType(position);
	}
	
	public void addData(List<VipBean> data){
		int size = mData.size();
		mData.addAll(data);
		notifyItemRangeInserted(size, data.size());
	}
}
