package bw.pdd.adapter.searchrecycleradapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bw.pdd.R;
import bw.pdd.bean.searchbean.Search_Data;
import bw.pdd.util.ImageUtil;
import bw.pdd.util.onClick.RvListener;

/**
 * Created by Administrator on 2017/9/1.
 */

public class SearchLeftRecyclerViewAdapter extends RecyclerView.Adapter<SearchLeftRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Search_Data> list;
    RvListener listener;

    private int checkedPosition;

    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        notifyDataSetChanged();
    }

    public SearchLeftRecyclerViewAdapter(Context context, List<Search_Data> list, RvListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.search_left_recycler_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageUtil.disPlayImage(context,list.get(position).getUnselected_url(),holder.searchLeftItemImage);
        holder.searchLeftItemTitle.setText(list.get(position).getOpt_name());
        if (position == checkedPosition) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.searchLeftItemTitle.setTextColor(Color.RED);
            ImageUtil.disPlayImage(context,list.get(position).getSelected_url(),holder.searchLeftItemImage);
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FAFAFA"));
            holder.searchLeftItemTitle.setTextColor(Color.parseColor("#868686"));
            ImageUtil.disPlayImage(context,list.get(position).getUnselected_url(),holder.searchLeftItemImage);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_left_item_title) TextView searchLeftItemTitle;
        @BindView(R.id.search_left_item_image) ImageView searchLeftItemImage;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view.getId(),getAdapterPosition());
                }
            });
        }
    }
}
