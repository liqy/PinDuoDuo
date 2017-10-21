package bw.pdd.fragmen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bw.pdd.Base.BaseFragment;
import bw.pdd.R;
import bw.pdd.activit.DetailActivity;
import bw.pdd.adapter.seaamoyadapter.SeaAmoyRecyclerViewAdapter;
import bw.pdd.bean.homebean.Home_Cha;
import bw.pdd.bean.seaamoybean.SeaAmoyBean;
import bw.pdd.util.HttpUtil;
import bw.pdd.util.share.ShareInterface;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zhouwei on 17/4/23.
 */

public class SeaAmoyFragment extends BaseFragment{
    @BindView(R.id.public_toolbar_tv)
    TextView publicToolbarTv;
    @BindView(R.id.public_toolbar_image)
    ImageView publicToolbarImage;
    @BindView(R.id.public_toolbar_xian)
    ImageView publicToolbarXian;
    @BindView(R.id.seaamoy_fragment_recycler)
    RecyclerView seaamoyFragmentRecycler;
    Unbinder unbinder;
    private List<Home_Cha.GoodsListBean> chalist;
    private List<SeaAmoyBean.GoodsListBeanX> list;
    private TextView titletext;
    private List<String> mDatas;
    private String mFrom;
    private String URL = "http://apiv4.yangkeduo.com/v2/haitaov2?page=1&size=20&pdduid=";
    private String maincha="http://apiv4.yangkeduo.com/v2/subject/155/goods?page=1&size=20&pdduid=";
    private SeaAmoyRecyclerViewAdapter mbaseAdapter;
    private String responseData;

    public static SeaAmoyFragment newInstance(String from) {
        SeaAmoyFragment fragment = new SeaAmoyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        fragment.setArguments(bundle);
        return fragment;
    }
    //网络请求操作类，OKHttp
    public void chaHttpRequest(String url){
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                //异常处理操作
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                //得到服务器返回的具体数据 //切换会主线程更新UI
                responseData = response.body().string();
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            chalist = new ArrayList<>();
                            list = new ArrayList<SeaAmoyBean.GoodsListBeanX>();
//                       mainlist=gson.fromJson(s1.toString(), type);
                            Home_Cha cha = gson.fromJson(responseData.toString(), Home_Cha.class);
                            chalist.addAll(cha.getGoods_list());
                            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                            mbaseAdapter = new SeaAmoyRecyclerViewAdapter(getActivity(), list, chalist);
                            if (seaamoyFragmentRecycler != null) {
                                final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 6);
                                manager1.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                    @Override
                                    public int getSpanSize(int position) {
                                        switch ((position)) {
                                            case 0:
                                                return 3;
                                            case 1:
                                                return 3;
                                            default:
                                                return 6;
                                        }
                                    }
                                });
                                seaamoyFragmentRecycler.setLayoutManager(manager1);
                                seaamoyFragmentRecycler.setAdapter(mbaseAdapter);
                                mbaseAdapter.setOnItemClickLitener(new SeaAmoyRecyclerViewAdapter.OnItemClickLitener() {
                                    @Override
                                    public void onItemClick(View view, int position,int id) {
                                        Toast.makeText(getActivity(),""+position,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                                        intent.putExtra("GUOXIN",id);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }
    //网络请求操作类，OKHttp
    public void sendHttpRequest(String url){
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                //异常处理操作
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                //得到服务器返回的具体数据 //切换会主线程更新UI
                String responseData = response.body().string();
                SeaAmoyBean seaAmoyBean = new Gson().fromJson(responseData, SeaAmoyBean.class);
                list = new ArrayList<SeaAmoyBean.GoodsListBeanX>();
                list.addAll(seaAmoyBean.getGoods_list());
                if(getActivity()!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mbaseAdapter = new SeaAmoyRecyclerViewAdapter(getActivity(), list, chalist);
                            if (seaamoyFragmentRecycler != null) {
                                final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 6);
                                manager1.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                    @Override
                                    public int getSpanSize(int position) {
                                        switch ((position)) {
                                            case 0:
                                                return 3;
                                            case 1:
                                                return 3;
                                            default:
                                                return 6;
                                        }
                                    }
                                });
                                seaamoyFragmentRecycler.setLayoutManager(manager1);
                                seaamoyFragmentRecycler.setAdapter(mbaseAdapter);
                                mbaseAdapter.setOnItemClickLitener(new SeaAmoyRecyclerViewAdapter.OnItemClickLitener() {
                                    @Override
                                    public void onItemClick(View view, int position,int id) {
                                        Toast.makeText(getActivity(),""+position,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                                        intent.putExtra("GUOXIN",id);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                    });
                }

            }
        });
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFrom = getArguments().getString("from");
        }
        sendHttpRequest(URL);
        chaHttpRequest(maincha);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.seaamoy_fragment_layout, null);
        unbinder = ButterKnife.bind(this,view);
        titletext = view.findViewById(R.id.public_toolbar_tv);
        titletext.setText("海淘专区");
        publicToolbarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareInterface shareInterface = new ShareInterface();
                shareInterface.show(getFragmentManager(), "bottom");
            }
        });
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(getActivity().getResources().getColor(R.color.colorBlack));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
