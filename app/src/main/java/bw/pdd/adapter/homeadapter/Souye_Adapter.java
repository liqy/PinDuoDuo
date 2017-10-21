package bw.pdd.adapter.homeadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import bw.pdd.R;
import bw.pdd.activit.Home_item_Activity;
import bw.pdd.bean.homebean.BannerBean;
import bw.pdd.bean.homebean.HomeData;
import bw.pdd.bean.homebean.Home_Cha;
import bw.pdd.bean.homebean.HomethreeBean;
import bw.pdd.fragmen.home_fragment.Home_imageloader;



/**
 * Created by Administrator on 2017/9/1.
 */
//implements Home_two_Adapter.OnClickListener
public class Souye_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Home_two_Adapter.OnClickListener{
    private Context context;
    private List<HomeData.GoodsListBean> list;
    private static List<BannerBean> bannerlist;
    private List<Home_Cha.GoodsListBean> chalist;



    public Souye_Adapter(List<HomeData.GoodsListBean> list, List<BannerBean> bannerlist,List<Home_Cha.GoodsListBean> chalist,Context context) {
        this.list = list;
        this.bannerlist = bannerlist;
        this.chalist=chalist;
        this.context=context;
    }

    HomeListener homeListener;
    public void setHomeListener(HomeListener homeListener){
        this.homeListener=homeListener;
    }

    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
//    头二
    public static final int ITEM_TYPE_HEADERtwo = 1;
//内容
    public static final int ITEM_TYPE_CONTENT = 2;

//插入
public static final int ITEM_TYPE_CHA = 3;
    //添加头部布局来解决
//重写加载头部布局的方法

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return ITEM_TYPE_HEADER;
        }else if (position==1){
            return  ITEM_TYPE_HEADERtwo;
        }else if (position%5==1){
            return  ITEM_TYPE_CHA;
        }else {
            return ITEM_TYPE_CONTENT;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                context=parent.getContext();
        switch (viewType){
            case 0:

                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_shouye_itemone, parent, false);
                HeaderHolder holder1 = new HeaderHolder(view1);

                return holder1;
            case 1:
            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_shouye_itemtwo, parent, false);
            HeaderHolderTwo two = new HeaderHolderTwo(view2);
            return  two;
            case  2:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_shouye_item, parent, false);
                MyViewHolder holder = new MyViewHolder(view);
                return holder;

            default:
                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_cha_item, parent, false);
                ChaViewHolder holder2 = new ChaViewHolder(view3);
                return  holder2;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof  MyViewHolder){
                MyViewHolder my= (MyViewHolder) holder;
                my.textView.setText(list.get(position).getShort_name());
                Glide.with(context).load(list.get(position).getImage_url()).into(my.imageView);
//                设置它的点击事件
                my.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (homeListener!=null){
                            homeListener.Homeitemonclick(view,position);
                        }
                    }
                });
            }else if (holder instanceof  HeaderHolder){

//                写轮播图尝试给轮播图的点击事件

                HeaderHolder headerHolder= (HeaderHolder) holder;
                headerHolder.banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(context,"轮播图点击了"+position,Toast.LENGTH_SHORT).show();
                    }
                });


            } else if (holder instanceof  HeaderHolderTwo) {
//            加载测试的数据

                List<HomethreeBean> slist=new ArrayList<>();
                HomethreeBean b1 = new HomethreeBean("限时秒杀", R.drawable.icon_spike);
                HomethreeBean b2 = new HomethreeBean("爱逛街", R.drawable.icon_shopping);
                HomethreeBean b3 = new HomethreeBean("品牌清仓", R.drawable.icon_super_spike);
                HomethreeBean b4 = new HomethreeBean("9块9特卖", R.drawable.icon_9_9);
                HomethreeBean b5 = new HomethreeBean("名品折扣", R.drawable.icon_eco_brand);
                HomethreeBean b6 = new HomethreeBean("新人礼包", R.drawable.icon_new_customer);
                HomethreeBean b7 = new HomethreeBean("品质水果", R.drawable.icon_fruits);
                HomethreeBean b8 = new HomethreeBean("美食汇", R.drawable.icon_food);
                HomethreeBean b9 = new HomethreeBean("助力享免单", R.drawable.icon_relay_order);
                HomethreeBean b10 = new HomethreeBean("手机充值", R.drawable.icon_charge);
                HomethreeBean b11 = new HomethreeBean("极速红包",R.drawable.icon_flash_groups);
                HomethreeBean b12 = new HomethreeBean("电器城", R.drawable.icon_electric);
                HomethreeBean b13 = new HomethreeBean("嘉居优品", R.drawable.icon_housing);
                HomethreeBean b14 = new HomethreeBean("时尚穿搭", R.drawable.icon_fashion);
                HomethreeBean b15 = new HomethreeBean("新歌声投票", R.drawable.icon_sing_vote);
                HomethreeBean b16 = new HomethreeBean("爱轻奢", R.drawable.icon_luxury);
//              添加到集合
                slist.add(b1); slist.add(b2); slist.add(b3); slist.add(b4); slist.add(b5); slist.add(b6); slist.add(b7); slist.add(b8);
                slist.add(b9); slist.add(b10); slist.add(b11); slist.add(b12); slist.add(b13); slist.add(b14); slist.add(b15); slist.add(b16);

//                这个里写水平布局
                HeaderHolderTwo holderTwo= (HeaderHolderTwo) holder;
                Home_two_Adapter adapter = new Home_two_Adapter(slist);
                //设置布局管理器
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
                gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
//                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    adapter.setListener(this);
                holderTwo.recyclerView.setLayoutManager(gridLayoutManager);
                holderTwo.recyclerView.setAdapter(adapter);
//                给这个写一个点击事件


// 写查找的布局
            }else if (holder instanceof  ChaViewHolder){
                final ChaViewHolder chaViewHolder= (ChaViewHolder) holder;
//                模拟加载数据
                LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(context);
                linearLayoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
                chaViewHolder.charecycle.setLayoutManager(linearLayoutManagers);
////                定义它的适配器
                Home_two_cha_Adapter home_two_cha_adapter = new Home_two_cha_Adapter(chalist);

                chaViewHolder.charecycle.setAdapter(home_two_cha_adapter);

            }
    }

    //写主要内容
    static class  MyViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.home_shouye_item);
            imageView=itemView.findViewById(R.id.home_zhu_img);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
//第一个加载Banner
    static class  HeaderHolder extends RecyclerView.ViewHolder{

        Banner banner;
        public HeaderHolder(View itemView) {
            super(itemView);
            banner=itemView.findViewById(R.id.qs_banner);


            ArrayList<String> list1 = new ArrayList<>();
            for (int i=0;i<bannerlist.size();i++){
                list1.add(bannerlist.get(i).getHome_banner());
            }

////        设置样
//            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            banner.setImageLoader(new Home_imageloader());
            banner.setImages(list1);
//            banner.setBannerStyle(BannerConfig.RIGHT);
             banner.start();
        }
    }
//水平滚动的view
    static class  HeaderHolderTwo extends RecyclerView.ViewHolder{
            RecyclerView recyclerView;
        public HeaderHolderTwo(View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.home_two_recycle);
        }
    }
//插入的布局
    static class  ChaViewHolder extends RecyclerView.ViewHolder{
   RecyclerView charecycle;
    public ChaViewHolder(View itemView) {
        super(itemView);
       charecycle=itemView.findViewById(R.id.home_cha_recycles);
    }
}

    /**
     *
     * 点击事件
     * @param view
     * @param var
     */
    //轮播图下面的点击事件

    @Override
    public void OnItemClick(View view, int var) {
        //        跳转到具体的页面
        Intent intent = new Intent(context, Home_item_Activity.class);
        intent.putExtra("postion",var);
        context.startActivity(intent);
        Toast.makeText(context,"轮播图下面点击了"+var,Toast.LENGTH_SHORT).show();
    }
//写首页的点击事件
    public interface  HomeListener{
    void Homeitemonclick(View view,int var);

}

}
