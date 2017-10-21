package bw.pdd.adapter.newrecycleradapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import bw.pdd.R;
import bw.pdd.bean.newbean.NewLeftImage;
import bw.pdd.bean.newbean.New_Bean;
import bw.pdd.fragmen.NewLeftFragment;
import bw.pdd.util.ImageUtil;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by 郭鑫 on 2017/9/1.
 */

public class NewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<New_Bean.GoodsListBean> list;
    private List<NewLeftImage> image;
    public static final int TYPE_LEFT = 0;
    //说明是带有Header的
    public static final int TYPE_RIGHT = 1;
    // 说明是带有Footer的
    public static final int TYPE_TEXT = 2;
    public static final int TYPE_NORMAL = 3;
    // 说明是不带有header和footer的
    private View mHeaderView;
    private View mFooterView;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position,int id);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_LEFT;
        }else if(position==1){
            return TYPE_RIGHT;
        }else if(position == 2){
            return TYPE_TEXT;
        }
        return TYPE_NORMAL;
    }

    public NewAdapter(Context context, List<New_Bean.GoodsListBean> list,List<NewLeftImage> image) {
        this.context = context;
        this.list = list;
        this.image = image;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHodler myViewHodler = new MyViewHodler(getView(parent, R.layout.new_fragment_recycler_item));
        switch(viewType){
            case 0:
                return new LeftViewHolder(getView(parent, R.layout.new_fragment_leftimage));
            case 1:
                return new RightViewHolder(getView(parent, R.layout.new_fragment_rightimage));
            case 2:
                return new TextViewHolder(getView(parent, R.layout.new_fragment_text));
            default:
                return myViewHodler;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof LeftViewHolder){
            final LeftViewHolder leftviewhodler = (LeftViewHolder)holder;
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(context).load(image.get(position).getAvatars().get(0)).apply(requestOptions).into(leftviewhodler.new_left_tu);
            Glide.with(context).load(image.get(position).getAvatars().get(1)).apply(requestOptions).into(leftviewhodler.new_left_tu1);
            leftviewhodler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"left",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, NewLeftFragment.class);
                    context.startActivity(intent);
                }
            });
        }else if(holder instanceof RightViewHolder){
            RightViewHolder rightviewhodler = (RightViewHolder)holder;
            ImageUtil.disPlayImage(context,list.get(0).getThumb_url(),rightviewhodler.new_right_tu);
            ImageUtil.disPlayImage(context,list.get(1).getThumb_url(),rightviewhodler.new_right_tu1);
            rightviewhodler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"right",Toast.LENGTH_SHORT).show();
                }
            });
        }else if(holder instanceof TextViewHolder){
            TextViewHolder textViewHolder = (TextViewHolder)holder;
        }else {
            final MyViewHodler myViewHodler = (MyViewHodler) holder;
            myViewHodler.new_fragment_recycler_content.setText(list.get(position).getShort_name());
            ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(myViewHodler.new_fragment_recycler_image, "alpha", 0f, 1f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setRepeatCount(0);
            alphaAnimation.setRepeatMode(ValueAnimator.REVERSE);
            alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
            alphaAnimation.start();
            ImageUtil.disPlayImage(context,list.get(position).getThumb_url(),myViewHodler.new_fragment_recycler_image);
            float price = list.get(position).getGroup().getPrice();
            float pricea = price/100f;
            Log.e(TAG, "onBindViewHolder: "+pricea );
            myViewHodler.new_fragment_recycler_pricce.setText("￥"+pricea);
            if (mOnItemClickLitener != null) {
                myViewHodler.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = myViewHodler.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(myViewHodler.itemView, pos,list.get(pos).getGoods_id());
                    }
                });
            }

        }
    }

    public View getView(ViewGroup parent, int res) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(res, parent, false);
    }
    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        TextView new_fragment_recycler_content,new_fragment_recycler_pricce;
        ImageView new_fragment_recycler_image;

        public MyViewHodler(View itemView) {
            super(itemView);
            new_fragment_recycler_content = itemView.findViewById(R.id.new_fragment_recycler_content);
            new_fragment_recycler_image = itemView.findViewById(R.id.new_fragment_recycler_image);
            new_fragment_recycler_pricce = itemView.findViewById(R.id.new_fragment_recycler_pricce);
        }
    }
    static class LeftViewHolder extends RecyclerView.ViewHolder {
        ImageView new_left_tu,new_left_tu1;
        public LeftViewHolder(View itemView) {
            super(itemView);
            new_left_tu = itemView.findViewById(R.id.new_left_tu);
            new_left_tu1 = itemView.findViewById(R.id.new_left_tu1);
        }
    }

    static class RightViewHolder extends RecyclerView.ViewHolder {
        ImageView new_right_tu,new_right_tu1;
        public RightViewHolder(View itemView) {
            super(itemView);
            new_right_tu = itemView.findViewById(R.id.new_right_tu);
            new_right_tu1 = itemView.findViewById(R.id.new_right_tu1);
        }
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {
        public TextViewHolder(View itemView) {
            super(itemView);
        }
    }
}
