package bw.pdd.adapter.personaladapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bw.pdd.R;
import bw.pdd.adapter.searchrecycleradapter.SearchLeftRecyclerViewAdapter;
import bw.pdd.bean.personalbean.PersonalData;

/**
 * Created by asus on 2017/9/5.
 */

public class Orderadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE1=0;
    private final int TYPE2=1;
    private List<PersonalData.ListBean> list;
    private Context context;
    public Orderadapter(List<PersonalData.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType)
        {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.personal_orderadapter, parent, false);
                holder = new HeaderViewHolder(view);
            break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.personal_torderadapter, parent, false);
                holder = new MyViewholder(view);
        }
            return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof HeaderViewHolder)
            {

            }else if (holder instanceof MyViewholder)
            {
               MyViewholder holders = (MyViewholder) holder;
                Glide.with(context).load(list.get(position).getThumb_url()).into(holders.adt_image);
               holders.adt_tv1.setText(list.get(position).getGoods_name()+"");
               holders.adt_tv2.setText(" ￥"+list.get(position).getMarket_price()/100+".00元");
            }
    }
    @Override
    public int getItemViewType(int position) {
        if(position==0)
        {
            return TYPE1;
        }else  if(position==1)
        {
            return TYPE2;
        }
        return TYPE2;
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder{

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
    class  MyViewholder extends  RecyclerView.ViewHolder{
        ImageView adt_image;
        TextView adt_tv1,adt_tv2;
        public MyViewholder(View itemView) {
            super(itemView);
            adt_image = itemView.findViewById(R.id.padt_image);
            adt_tv1 = itemView.findViewById(R.id.padt_tv1);
            adt_tv2 = itemView.findViewById(R.id.padt_tv2);
        }
    }
}
