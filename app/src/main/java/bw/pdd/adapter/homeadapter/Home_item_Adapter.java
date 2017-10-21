package bw.pdd.adapter.homeadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bw.pdd.R;
import bw.pdd.bean.homebean.Home_item_Context;

/**
 * Created by Administrator on 2017/9/5.
 */

public class Home_item_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Home_item_Context.GoodsListBean> list;
    public Home_item_Adapter(List<Home_item_Context.GoodsListBean> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_fragment_recycler_item, parent, false);
        MysViewHolder holder = new MysViewHolder(view);
//        ButterKnife.bind(holder,parent);
        if (holder != null) {
            return holder;
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MysViewHolder){
                MysViewHolder mys = (MysViewHolder) holder;
                Glide.with(mys.newFragmentRecyclerImage).load(list.get(position).getHd_thumb_url()).into(mys.newFragmentRecyclerImage);
                mys.newFragmentRecyclerContent.setText(list.get(position).getShort_name());
                float pprice=list.get(position).getMarket_price()/100;
                mys.newFragmentRecyclerPricce.setText("￥"+pprice);
            }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //    自定义ViewHolder
    static class MysViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.new_fragment_recycler_image)
        ImageView newFragmentRecyclerImage;
//        @BindView(R.id.new_fragment_recycler_content)
        TextView newFragmentRecyclerContent;
//        @BindView(R.id.new_fragment_recycler_pricce)
        TextView newFragmentRecyclerPricce;
//        @BindView(R.id.recycler_textView)
        TextView recyclerTextView;
        public MysViewHolder(View itemView) {
            super(itemView);
            newFragmentRecyclerImage=itemView.findViewById(R.id.new_fragment_recycler_image);
            newFragmentRecyclerContent=itemView.findViewById(R.id.new_fragment_recycler_content);
            newFragmentRecyclerPricce=itemView.findViewById(R.id.new_fragment_recycler_pricce);
            recyclerTextView=itemView.findViewById(R.id.recycler_textView);
        }
    }
}
