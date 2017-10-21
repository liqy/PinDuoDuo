package bw.pdd.adapter.searchrecycleradapter;

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
 * Created by Administrator on 2017/9/1.
 */

public class SearchRightRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Search_Replace> list;
    Context context;
    RvListener listener;

    public SearchRightRecyclerAdapter(List<Search_Replace> list, Context context, RvListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    class ContentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_right_content_image)
        ImageView searchRightContentImage;
        @BindView(R.id.search_right_content_title)
        TextView searchRightContentTitle;

        public ContentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) listener.onItemClick(view.getId(), getAdapterPosition());
                }
            });
        }
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_right_title_letf_Title) TextView searchRightTitleLetfTitle;
        @BindView(R.id.search_right_title_right_More) TextView searchRightTitleRightMore;
        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view.getId(),getAdapterPosition());
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.search_right_recycler_content, parent, false);
            return new ContentHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.search_right_recycler_title, parent, false);
            return new TitleHolder(inflate);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Search_Replace search_replace = list.get(position);
        if(search_replace.selected_url!=null)return 1;else return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentHolder) {
            ContentHolder holder1 = (ContentHolder) holder;
            ImageUtil.disPlayImage(context,list.get(position).image_url,holder1.searchRightContentImage);
            holder1.searchRightContentTitle.setText(list.get(position).opt_name);
        } else if (holder instanceof TitleHolder) {
            TitleHolder holder1 = (TitleHolder) holder;
            holder1.searchRightTitleLetfTitle.setText(list.get(position).opt_name);
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}
