package bw.pdd.adapter.commonadapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import bw.pdd.R;

/**
 * Created by Administrator on 2017/9/5.
 */

public class TagRecyclerViewAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public TagRecyclerViewAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(R.layout.tag, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tag,item).addOnClickListener(R.id.tag);
        View view = helper.getView(R.id.tag);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams) lp;
            flexboxLp.setFlexGrow(1.0f);
        }
    }
}
