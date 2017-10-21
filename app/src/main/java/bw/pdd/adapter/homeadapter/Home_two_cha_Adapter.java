package bw.pdd.adapter.homeadapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bw.pdd.R;
import bw.pdd.bean.homebean.Home_Cha;

/**
 * Created by Administrator on 2017/9/2.
 */

public class Home_two_cha_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Home_Cha.GoodsListBean> list;

    public Home_two_cha_Adapter(List<Home_Cha.GoodsListBean> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_cha, parent, false);
        MyViewHolders holders = new MyViewHolders(view);
        return holders;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof  MyViewHolders){
                MyViewHolders holders= (MyViewHolders) holder;

                Glide.with(holders.imageView).load(list.get(position).getHd_thumb_url()).into(holders.imageView);
                ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(holders.imageView, "alpha", 0f, 1f);
                alphaAnimation.setDuration(500);
                alphaAnimation.setRepeatCount(0);
                alphaAnimation.setRepeatMode(ValueAnimator.REVERSE);
                alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                alphaAnimation.start();
                float price=list.get(position).getGroup().getPrice();
               float nowprice= price/100f;
                holders.textView.setText("￥"+nowprice);
                holders.font.setText(list.get(position).getShort_name());
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static  class  MyViewHolders extends  RecyclerView.ViewHolder{
       ImageView imageView;
        TextView textView;
        TextView font;
        public MyViewHolders(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.home_cha_imgss);
           font=itemView.findViewById(R.id.home_cha_text);
            textView=itemView.findViewById(R.id.home_cha_price);

        }
    }
}
