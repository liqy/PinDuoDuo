package bw.pdd.fragmen.personal_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bw.pdd.R;
import bw.pdd.adapter.personaladapter.Orderadapter;
import bw.pdd.bean.personalbean.PersonalData;
import bw.pdd.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by asus on 2017/9/5.
 */

public class FragmentOrder  extends Fragment{
    private View view;
    private RecyclerView biglist;
    private List<PersonalData.ListBean> list = new ArrayList<>();
    String url="http://apiv4.yangkeduo.com/rec_order_list?offset=0&count=20&pdduid=31282601443,624application/json;charset=UTF-8noxvmhandle:1440";
    private Orderadapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentorder,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        biglist = view.findViewById(R.id.forder_biglist);
        adapter = new Orderadapter(list,getActivity());
        GridLayoutManager gm = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        gm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position)
                {
                    case 0:
                        return 2;
                    case 1:
                        return 1;
                    default:
                        return 1;
                }
            }
        });
        biglist.setLayoutManager(gm);
        biglist.setAdapter(adapter);
        getData();
    }

    private void getData() {
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                PersonalData data = new Gson().fromJson(s, PersonalData.class);
                list.addAll(data.getList());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

}
