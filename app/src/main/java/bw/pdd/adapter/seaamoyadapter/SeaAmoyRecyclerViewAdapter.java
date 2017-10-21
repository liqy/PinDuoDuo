package bw.pdd.adapter.seaamoyadapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bw.pdd.R;
import bw.pdd.adapter.homeadapter.Home_two_cha_Adapter;
import bw.pdd.bean.homebean.Home_Cha;
import bw.pdd.bean.seaamoybean.SeaAmoyBean;
import bw.pdd.util.ImageUtil;

/**
 * Created by Administrator on 2017/9/1.
 */

public class SeaAmoyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<SeaAmoyBean.GoodsListBeanX> list;

    public static final int TYPE_LEFT = 0;
    //说明是带有Header的
    public static final int TYPE_RIGHT = 1;
    // 说明是带有Footer的
    public static final int TYPE_TEXT = 2;
    public static final int TYPE_DUO = 3;
    public static final int TYPE_NORMAL = 4;
    private View mHeaderView;
    private View mFooterView;
    private List<Home_Cha.GoodsListBean> chalist;

    public SeaAmoyRecyclerViewAdapter(Context context, List<SeaAmoyBean.GoodsListBeanX> list, List<Home_Cha.GoodsListBean> chalist) {
        this.context = context;
        this.list = list;
        this.chalist = chalist;
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int positionm,int id);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_LEFT;
        }else if(position==1){
            return TYPE_RIGHT;
        }else if(position == 2){
            return TYPE_TEXT;
        }else if((position)%5==2){
            return TYPE_DUO;
        }
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch(viewType){
            case 0:
                return new LeftViewHolder(getView(parent, R.layout.seaamoy_fragment_leftimage));
            case 1:
                return new RightViewHolder(getView(parent, R.layout.seaamoy_fragment_rightimage));
            case 2:
                return new TextViewHolder(getView(parent, R.layout.seaamoy_recycle1));
            case 3:
                return new DuoViewHolder(getView(parent,R.layout.home_item_cha_item));
            default:
                return new ViewHodler(getView(parent, R.layout.seaamoy_recycle2));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof LeftViewHolder){
            LeftViewHolder leftviewhodler = (LeftViewHolder)holder;
        }else if(holder instanceof RightViewHolder){
            RightViewHolder rightviewhodler = (RightViewHolder)holder;
        }else if(holder instanceof TextViewHolder){
            TextViewHolder textViewHolder = (TextViewHolder)holder;
        }else if(holder instanceof DuoViewHolder){
            DuoViewHolder duoViewHolder = (DuoViewHolder) holder;
//            List<Integer> imglist=new ArrayList<>();
            List<Home_Cha.GoodsListBean> imglist=new ArrayList<>();
//            for (int i=0;i<=20;i++){
//                imglist.add(R.mipmap.ic_launcher);
//            }
            LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(context);
            linearLayoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
            duoViewHolder.charecycle.setLayoutManager(linearLayoutManagers);
////                定义它的适配器
            Home_two_cha_Adapter home_two_cha_adapter = new Home_two_cha_Adapter(chalist);
            duoViewHolder.charecycle.setAdapter(home_two_cha_adapter);
        }else {
            final ViewHodler myViewHodler = (ViewHodler) holder;
            ImageUtil.disPlayImage(context,list.get(position).getThumb_url(),myViewHodler.seaamoy_recycler2_image);
            ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(myViewHodler.seaamoy_recycler2_image, "alpha", 0f, 1f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setRepeatCount(0);
            alphaAnimation.setRepeatMode(ValueAnimator.REVERSE);
            alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
            alphaAnimation.start();
            myViewHodler.seaamoy_recycler2_tv.setText(list.get(position).getGoods_name());
            float price = list.get(position).getGroup().getPrice();
            float pricea = price/100;
            myViewHodler.seaamoy_recycler2_tv1.setText("￥"+pricea);
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

    public View getView(ViewGroup parent, int res) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(res, parent, false);
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }
    public class ViewHodler extends RecyclerView.ViewHolder {
        ImageView seaamoy_recycler2_image;
        TextView seaamoy_recycler2_tv,seaamoy_recycler2_tv1;
        public ViewHodler(View itemView) {
            super(itemView);
            seaamoy_recycler2_image = itemView.findViewById(R.id.seaamoy_recycler2_image);
            seaamoy_recycler2_tv = itemView.findViewById(R.id.seaamoy_recycler2_tv);
            seaamoy_recycler2_tv1 = itemView.findViewById(R.id.seaamoy_recycler2_tv1);
        }
    }
    static class LeftViewHolder extends RecyclerView.ViewHolder {
        public LeftViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class RightViewHolder extends RecyclerView.ViewHolder {
        public RightViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {
        public TextViewHolder(View itemView) {
            super(itemView);
        }
    }
    static class DuoViewHolder extends RecyclerView.ViewHolder {
        RecyclerView charecycle;
        public DuoViewHolder(View itemView) {
            super(itemView);
            charecycle=itemView.findViewById(R.id.home_cha_recycles);
        }
    }

}
