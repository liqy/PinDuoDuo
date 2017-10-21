package bw.pdd.fragmen.home_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import bw.pdd.R;
import bw.pdd.adapter.homeadapter.Souye_Adapter;
import bw.pdd.bean.homebean.BannerBean;
import bw.pdd.bean.homebean.HomeChaID;
import bw.pdd.bean.homebean.HomeData;
import bw.pdd.bean.homebean.Home_Cha;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/1.
 */
//implements Souye_Adapter.HomeListener
public class Home_Fragment_One extends Fragment implements Souye_Adapter.HomeListener{
    private RecyclerView recyclerView;
    private List<HomeData.GoodsListBean> mainlist;
    private List<BannerBean> bannerlist;
    private List<Home_Cha.GoodsListBean> chalist;
    private List<HomeChaID.HomeRecommendSubjectsBean> idlist;
    private String  bannerpath="http://apiv4.yangkeduo.com/subjects?pdduid=";
    private String mainpath="http://apiv3.yangkeduo.com/v4/goods?page=1&size=20&list_id=0224117285&pdduid=";
    private String maincha="http://apiv4.yangkeduo.com/v2/subject/155/goods?page=1&size=20&pdduid=";
//    这个用来判断到底加载那个具体的接口

    private String passid="http://apiv4.yangkeduo.com/home_show?pdduid=";

    private String s;
    private String s1;
    private String s2;
    private String s3;
    private Souye_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qs_onefragment, container, false);
//        这里实现首页效果
        recyclerView=view.findViewById(R.id.qs_one_recycle);
        mainlist=new ArrayList<>();
        bannerlist=new ArrayList<>();
        chalist=new ArrayList<>();
        idlist=new ArrayList<>();
//加载RecycleView数据
//在这里写网络请求传过去
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        adapter = new Souye_Adapter(mainlist,bannerlist,chalist,getActivity());
        recyclerView.setLayoutManager(manager);
        adapter.setHomeListener(this);
        recyclerView.setAdapter(adapter);
        geteDate();
        Getmian();
        GetCha();
        GetId();
        adapter.setHomeListener(this);

        return view;
    }
//获取轮播图的方法
    private void geteDate() {
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(bannerpath)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                s = response.body().string();
                if (getActivity()!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            Type type = new TypeToken<ArrayList<BannerBean>>() {
                            }.getType();
                            bannerlist=gson.fromJson(s.toString(), type);
                            if (adapter!=null&&recyclerView!=null){
                                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                           adapter = new Souye_Adapter(mainlist,bannerlist,chalist,getActivity());
                           recyclerView.setLayoutManager(manager);
                           recyclerView.setAdapter(adapter);

                                adapter.setHomeListener(new Souye_Adapter.HomeListener() {
                                    @Override
                                    public void Homeitemonclick(View view, int var) {
                                        Toast.makeText(getActivity(),"点击了主页"+var,Toast.LENGTH_SHORT).show();
                                    }
                                });
//                            adapter.notifyDataSetChanged();
                            }
                        }
                    });
                }

            }
        });
    }

   public void Getmian(){

       OkHttpClient client = new OkHttpClient();
       Request request=new Request.Builder()
               .url(mainpath)
               .build();
       Call call = client.newCall(request);
       call.enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
           }
           @Override
           public void onResponse(Call call, Response response) throws IOException {
               s1 = response.body().string();
               if (getActivity()!=null){
                   getActivity().runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           Gson gson = new Gson();
//                           mainlist=new ArrayList<>();
                           HomeData data = gson.fromJson(s1.toString(), HomeData.class);
                           mainlist.addAll(data.getGoods_list());
                           adapter.notifyDataSetChanged();
                           LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                           adapter = new Souye_Adapter(mainlist,bannerlist,chalist,getActivity());
                           recyclerView.setLayoutManager(manager);
                           recyclerView.setAdapter(adapter);

                           adapter.setHomeListener(new Souye_Adapter.HomeListener() {
                               @Override
                               public void Homeitemonclick(View view, int var) {
                                   Toast.makeText(getActivity(),"点击了主页"+var,Toast.LENGTH_SHORT).show();
                               }
                           });

                       }
                   });
               }

           }
       });

    }
//    插入的网络请求

    //网络请求操作类，OKHttp
public  void  GetCha(){

    OkHttpClient client = new OkHttpClient();
    Request request=new Request.Builder()
            .url(maincha)
            .build();
    Call call = client.newCall(request);
    call.enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            s2 = response.body().string();
            if (getActivity()!=null){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        chalist=new ArrayList<>();
//                       mainlist=gson.fromJson(s1.toString(), type);
                        Home_Cha cha = gson.fromJson(s2.toString(), Home_Cha.class);
                        chalist.addAll(cha.getGoods_list());
                        adapter.notifyDataSetChanged();
                        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                        adapter = new Souye_Adapter(mainlist,bannerlist,chalist,getActivity());
                        if (recyclerView!=null){
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(adapter);
                            adapter.setHomeListener(new Souye_Adapter.HomeListener() {
                                @Override
                                public void Homeitemonclick(View view, int var) {
                                    Toast.makeText(getActivity(),"点击了主页"+var,Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }

        }
    });
}
//查找的id

    public  void  GetId(){

        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(passid)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                s3 = response.body().string();
                if (getActivity()!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                          idlist=new ArrayList<>();

                            HomeChaID chaID = gson.fromJson(s3.toString(), HomeChaID.class);
                          idlist.addAll(chaID.getHome_recommend_subjects());

//                            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//                            adapter = new Souye_Adapter(mainlist,bannerlist,chalist,getActivity());
//                            if (recyclerView!=null){
//                                recyclerView.setLayoutManager(manager);
//                                recyclerView.setAdapter(adapter);
//                            }
                        }
                    });
                }

            }
        });
    }

    @Override
    public void Homeitemonclick(View view, int var) {

        Toast.makeText(getActivity(),"点击了主页"+var,Toast.LENGTH_SHORT).show();
    }
}
