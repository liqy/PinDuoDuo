package bw.pdd.adapter.homeadapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bw.pdd.R;
import bw.pdd.bean.homebean.HomeTwoBean;
import bw.pdd.bean.searchbean.Search_Replace;
import bw.pdd.util.ImageUtil;

/**
 * Created by 郭鑫 on 2017/9/2.
 */

public class Homefragemnttwoitem extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<Search_Replace> twobean;
    private List<HomeTwoBean.GoodsListBean> list;
    public static final int TYPE_LEFT = 0;
    //说明是带有Header的
    public static final int TYPE_RIGHT = 1;
    // 说明是带有Footer的
    public static final int TYPE_TEXT = 2;
    public static final int TYPE_NORMAL = 3;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position,int id);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public Homefragemnttwoitem(Context context, List<Search_Replace> twobean, List<HomeTwoBean.GoodsListBean> list) {
        this.context = context;
        this.twobean = twobean;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_LEFT;
        }else if(position==1){
            return TYPE_RIGHT;
        }else if(position == 2){
            return TYPE_TEXT;
        }
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch(viewType){
            case 0:
                return new LeftViewHolder(getView(parent, R.layout.home_fragment_toptwo));
            default:
                return new MyViewHodler(getView(parent, R.layout.new_fragment_recycler_item));
        }
    }
    public View getView(ViewGroup parent, int res) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(res, parent, false);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof LeftViewHolder){
            LeftViewHolder leftviewhodler = (LeftViewHolder)holder;
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    switch (position){
                        case 0:
                            return 1;
                        case 1:
                            return 1;
                        default:
                            return 1;
                    }
                }
            });
            Homefragmenttwoitemtwo homefragmenttwoitemtwo = new Homefragmenttwoitemtwo(twobean, context);
            leftviewhodler.hometwo_recycleron.setLayoutManager(gridLayoutManager);
            leftviewhodler.hometwo_recycleron.setAdapter(homefragmenttwoitemtwo);
            leftviewhodler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"left",Toast.LENGTH_SHORT).show();
                }
            });

        }else {
            final MyViewHodler myViewHodler = (MyViewHodler) holder;
            myViewHodler.new_fragment_recycler_content.setText(list.get(position).getShort_name());
            float price = list.get(position).getGroup().getPrice();
            float pricea = price/100;
            myViewHodler.new_fragment_recycler_pricce.setText("￥"+pricea);
            ImageUtil.disPlayImage(context,list.get(position).getThumb_url(),myViewHodler.new_fragment_recycler_image);
            ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(myViewHodler.new_fragment_recycler_image, "alpha", 0f, 1f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setRepeatCount(0);
            alphaAnimation.setRepeatMode(ValueAnimator.REVERSE);
            alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
            alphaAnimation.start();
            if (mOnItemClickLitener != null) {
                myViewHodler.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = myViewHodler.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(myViewHodler.itemView, pos,list.get(pos).getGoods_id());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }
    public class MyViewHodler extends RecyclerView.ViewHolder {
        TextView new_fragment_recycler_content, new_fragment_recycler_pricce;
        ImageView new_fragment_recycler_image;
        public MyViewHodler(View itemView) {
            super(itemView);
            new_fragment_recycler_content = itemView.findViewById(R.id.new_fragment_recycler_content);
            new_fragment_recycler_image = itemView.findViewById(R.id.new_fragment_recycler_image);
            new_fragment_recycler_pricce = itemView.findViewById(R.id.new_fragment_recycler_pricce);
        }
    }
    static class LeftViewHolder extends RecyclerView.ViewHolder {
        RecyclerView hometwo_recycleron;
        public LeftViewHolder(View itemView) {
            super(itemView);
            hometwo_recycleron = itemView.findViewById(R.id.hometwo_recycleron);
        }
    }

}
