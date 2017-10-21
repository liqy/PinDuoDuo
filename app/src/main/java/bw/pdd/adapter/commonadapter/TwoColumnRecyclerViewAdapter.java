package bw.pdd.adapter.commonadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import bw.pdd.R;
import bw.pdd.bean.searchbean.SearchGoods;
import bw.pdd.util.ImageUtil;

/**
 * Created by Administrator on 2017/9/5.
 */

public class TwoColumnRecyclerViewAdapter extends BaseQuickAdapter<SearchGoods.ItemsBean,BaseViewHolder> {
    Context context;
    public TwoColumnRecyclerViewAdapter(Context content, @LayoutRes int layoutResId, @Nullable List<SearchGoods.ItemsBean> data) {
        super(R.layout.new_fragment_recycler_item, data);
        this.context = content;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchGoods.ItemsBean item) {
        helper.setText(R.id.new_fragment_recycler_content,item.getGoods_name())
                .setText(R.id.new_fragment_recycler_pricce,"￥ "+String.valueOf(item.getPrice()/100));
        ImageView view = helper.getView(R.id.new_fragment_recycler_image);
        ImageUtil.disPlayImage(context,item.getImage_url(),view);
    }
}
