package bw.pdd.fragmen.home_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bw.pdd.R;
import bw.pdd.adapter.homeadapter.Home_item_Adapter;
import bw.pdd.bean.homebean.Home_item_Context;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/5.
 */

public class Home_item_Fragment extends Fragment {
    @BindView(R.id.home_item_recycle)
    RecyclerView homeItemRecycle;
    Unbinder unbinder;
    private String urlpath="http://apiv4.yangkeduo.com/penny/goods?tab_id=0&page=1&size=20&pdduid=";
    private List<Home_item_Context.GoodsListBean> contextlist;
    private String value;
    private Home_item_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_item_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
//        在这里加载RecycleView
        contextlist=new ArrayList<>();
//        进行适配
        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
          homeItemRecycle.setLayoutManager(manager);
        adapter = new Home_item_Adapter(contextlist);
        homeItemRecycle.setAdapter(adapter);
//        Intent intent = getActivity().getIntent();
//        intent.getStringExtra("date");
//        Bundle bundle = getArguments();
//        String values = bundle.getString("values");
//        if (values!=null){
//            Toast.makeText(getActivity(),"GG"+values,Toast.LENGTH_SHORT).show();
//        }

        GetData();


        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
//    加载网络请求数据
    public void GetData(){

        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(urlpath)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                value= response.body().string();
                if (getActivity()!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Gson gson = new Gson();
//                        Type type = new TypeToken<ArrayList<Home_tab_nine>>() {
//                        }.getType();
                            Home_item_Context item_context = gson.fromJson(value.toString(), Home_item_Context.class);
                            contextlist.addAll(item_context.getGoods_list());
                            GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
                            homeItemRecycle.setLayoutManager(manager);
                            if (adapter!=null){
                                adapter = new Home_item_Adapter(contextlist);
                                homeItemRecycle.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
//                            Bundle bundle = getArguments();
//                            String values = bundle.getString("values");
//                            if (values!=null){
//                                Toast.makeText(getActivity(),"GG"+values,Toast.LENGTH_SHORT).show();
//
//                            }
//                            Log.e("TAg",tablist.toString());
                        }
                    });
                }



            }
        });
    }
}
