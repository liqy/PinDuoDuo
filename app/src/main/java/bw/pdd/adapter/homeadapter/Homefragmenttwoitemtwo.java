package bw.pdd.adapter.homeadapter;

import android.content.Context;
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
import bw.pdd.bean.searchbean.Search_Replace;
import bw.pdd.util.ImageUtil;
import bw.pdd.util.onClick.RvListener;

/**
 * Created by 郭鑫 on 2017/9/5.
 */

public class Homefragmenttwoitemtwo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Search_Replace> list;
    Context context;
    RvListener listener;
    public Homefragmenttwoitemtwo(List<Search_Replace> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context)
                .inflate(R.layout.home_fragment_toptwo_item, parent, false);
        return new ContentHolder(inflate);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContentHolder holder1 = (ContentHolder) holder;
        ImageUtil.disPlayImage(context,list.get(position).image_url,holder1.home_right_content_image);
        holder1.home_right_content_title.setText(list.get(position).opt_name);
    }

    @Override
    public int getItemCount() {
        return list != null?list.size():0;
    }
    class ContentHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.home_right_content_image)
        ImageView home_right_content_image;
        @BindView(R.id.home_right_content_title)
        TextView home_right_content_title;
        public ContentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onItemClick(view.getId(), getAdapterPosition());
                }
            });
        }
    }
}
