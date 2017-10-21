package bw.pdd.adapter.homeadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bw.pdd.R;
import bw.pdd.bean.homebean.HomethreeBean;

/**
 * Created by Administrator on 2017/9/2.
 */

public class Home_two_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomethreeBean> list;
//定义监听事件
        OnClickListener listener;//定义监听事件
    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public Home_two_Adapter(List<HomethreeBean> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_two_item, parent, false);
        MyViewHolders holders = new MyViewHolders(view);
        return holders;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof  MyViewHolders){
                MyViewHolders holders= (MyViewHolders) holder;
//                加载文字
                holders.textView.setText(list.get(position).getTitle());
//                图片
                Glide.with(holders.imageView).load(list.get(position).getPic()).into(holders.imageView);
//                holders.imageView
//在这里设置条目监听
                holders.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            if (listener!=null){
                                listener.OnItemClick(view,position);
                            }
                    }
                });
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static  class  MyViewHolders extends  RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public MyViewHolders(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.home_one_text);
            imageView=itemView.findViewById(R.id.home_one);
        }
    }
//    定义点击事件的接口
    public  interface  OnClickListener{
        void  OnItemClick(View view, int var);
    }
}
