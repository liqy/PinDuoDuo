package bw.pdd.adapter.newrecycleradapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import bw.pdd.R;
import bw.pdd.activit.DetailActivity;
import bw.pdd.bean.newbean.NewLeftBean;
import bw.pdd.util.ImageUtil;

/**
 * Created by 郭鑫 on 2017/9/22.
 */

public class NewLeftAdapter extends RecyclerView.Adapter<NewLeftAdapter.ViewHolder> {
    private Context context;
    private List<NewLeftBean.GoodsListBean> list;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
    private OnItemClickLitener mOnItemClickLitener;
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    public NewLeftAdapter(Context context, List<NewLeftBean.GoodsListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public NewLeftAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder myViewHodler = new ViewHolder(getView(parent, R.layout.new_activity_left_item));
        return myViewHodler;
    }
    public View getView(ViewGroup parent, int res) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(res, parent, false);
    }
    @Override
    public void onBindViewHolder(NewLeftAdapter.ViewHolder holder, int position) {
        ImageUtil.disPlayImage(context,list.get(position).getThumb_url(),holder.new_activity_left_item_image);
        int cnt = list.get(position).getCnt();
        float c = list.get(position).getMarket_price();
        float a = c/100;
        String s = String.valueOf(cnt);
        if(cnt>1000000){
            String substring = s.substring(0,3);
            holder.new_activity_left_item_tv1.setText("已拼"+substring+"万件，"+"市场价￥"+a);
        }else if(cnt>1000000){
            String substring1 = s.substring(0,2);
            holder.new_activity_left_item_tv1.setText("已拼"+substring1+"万件，"+"市场价￥"+a);
        }else if(cnt>100000){
            String substring2 = s.substring(0,2);
            holder.new_activity_left_item_tv1.setText("已拼"+substring2+"万件，"+"市场价￥"+a);
        }else if(cnt>10000){
            float cnt1 = list.get(position).getMarket_price();
            float cnt2 = cnt1/10000;
            BigDecimal b = new BigDecimal(cnt2);
            float f1 = b.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
            holder.new_activity_left_item_tv1.setText("已拼"+f1+"万件，"+"市场价￥"+a);
        }
        float price = list.get(position).getGroup().getPrice();
        float mprice = price/100;
        holder.new_activity_left_item_tv2.setText("￥"+mprice);
        holder.new_activity_left_item_tv.setText(list.get(position).getGoods_name());
        final int goods_id = list.get(position).getGoods_id();
        holder.new_activity_left_item_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("GUOXIN",goods_id);
                context.startActivity(intent);
            }
        });
        holder.new_activity_left_item_tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("GUOXIN",goods_id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView new_activity_left_item_tv,new_activity_left_item_tv1,new_activity_left_item_tv2,new_activity_left_item_tv3;
        ImageView new_activity_left_item_image;
        public ViewHolder(View itemView) {
            super(itemView);
            new_activity_left_item_tv = itemView.findViewById(R.id.new_activity_left_item_tv);
            new_activity_left_item_tv1 = itemView.findViewById(R.id.new_activity_left_item_tv1);
            new_activity_left_item_image = itemView.findViewById(R.id.new_activity_left_item_image);
            new_activity_left_item_tv2 = itemView.findViewById(R.id.new_activity_left_item_tv2);
            new_activity_left_item_tv3 = itemView.findViewById(R.id.new_activity_left_item_tv3);
        }
    }
}
