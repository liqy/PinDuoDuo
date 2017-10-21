package bw.pdd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

import bw.pdd.R;
import bw.pdd.bean.DetailBean;
import bw.pdd.fragmen.home_fragment.Home_imageloader;

/**
 * Created by 郭鑫 on 2017/9/5.
 */

public class DetailAdapter extends RecyclerView.Adapter  {
    private static final int TYPE_NORMAL = 10000;
    private static final int TYPE_HEADER = 2000;
    private List<DetailBean> list1;
    private List<String> list;
    private MyHeaderHolder mMyHeaderHolder;
    private List<Object> mData;
    private Context context;
    public DetailAdapter(List<DetailBean> list1, List<Object> mData, Context context,List<String> list) {
        this.list1 = list1;
        this.mData = mData;
        this.context = context;
        this.list = list;
    }
    public int getHeaderHeight() {
        if (mMyHeaderHolder != null) {
            return mMyHeaderHolder.getHeaderHeight();
        }
        return -1;
    }

    @Override public int getItemViewType(int position) {
        Object object = mData.get(position);
        if (object instanceof String) {
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (TYPE_HEADER == viewType) {
            return new MyHeaderHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.deatailactivity_head, parent, false));
        }
        return new MyHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.deatailactivity_item, parent, false));
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (TYPE_HEADER == viewType) {
            ((MyHeaderHolder) holder).bind();
            float price = list1.get(position).getMin_normal_price();
            float pricea = price/100;
            ((MyHeaderHolder) holder).text.setText("￥"+pricea);
            ((MyHeaderHolder) holder).text1.setText(list1.get(position).getGoods_name());
            ((MyHeaderHolder) holder).text2.setText(list1.get(position).getShare_desc());
        } else if (TYPE_NORMAL == viewType) {
            ((MyHolder) holder).bind();
        }
    }

    @Override public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
    public class MyHeaderHolder extends RecyclerView.ViewHolder {

        private int mHeaderHeight;
        private Banner banner;
        private TextView text,text1,text2;
        public MyHeaderHolder(final View itemView) {
            super(itemView);
            itemView.getViewTreeObserver()
                    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override public void onGlobalLayout() {
                            mHeaderHeight = itemView.getMeasuredHeight();
                            itemView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }
                    });
            text = itemView.findViewById(R.id.deatailactivity_recycler_pricce);
            text1 = itemView.findViewById(R.id.deatail_fragment_recycler_content);
            text2 = itemView.findViewById(R.id.deatail_fragment_recycler_contentdeatail);
            banner = itemView.findViewById(R.id.deatailactivity_img);
            if(list!=null){
                banner.setImageLoader(new Home_imageloader());
                banner.setImages(list);
                banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                banner.isAutoPlay(true);
                banner.start();
            }

        }

        public void bind() {
        }
        public int getHeaderHeight() {
            return mHeaderHeight;
        }
    }
    public class MyHolder extends RecyclerView.ViewHolder {

        public MyHolder(View itemView) {
            super(itemView);
        }

        public void bind() {

        }
    }
}
