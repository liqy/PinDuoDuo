package bw.pdd.fragmen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import bw.pdd.adapter.newrecycleradapter.NewAdapter;
import bw.pdd.app.AppConstants;
import bw.pdd.bean.newbean.NewLeftImage;
import bw.pdd.bean.newbean.New_Bean;
import bw.pdd.util.HttpUtil;
import bw.pdd.util.share.ShareInterface;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zhouwei on 17/4/23.
 */

public class NewFragment extends BaseFragment{
    @BindView(R.id.public_toolbar_tv)
    TextView publicToolbarTv;
    @BindView(R.id.public_toolbar_image)
    ImageView publicToolbarImage;
    @BindView(R.id.new_content_recycler)
    RecyclerView newContentRecycler;
    ImageView newHeaderImgleft;
    ImageView newHeaderImgright;
    Unbinder unbinder;
    private List<String> mDatas;
    private String mFrom;
    private NewAdapter mAdapter;
    private String URL = "http://apiv3.yangkeduo.com/v5/newlist?page=2&size=20&ver=1504326000028&pdduid=";
    private List<New_Bean.GoodsListBean> list;
    private List<NewLeftImage> newleft;
    public static NewFragment newInstance(String from) {
        NewFragment fragment = new NewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFrom = getArguments().getString("from");
        }
        ImagesendHttpRequest(AppConstants.new_left_imag);
        sendHttpRequest(URL);
    }
    //网络请求操作类，OKHttp
    public void ImagesendHttpRequest(String url){
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                //异常处理操作
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                //得到服务器返回的具体数据 //切换会主线程更新UI
                String responseData = response.body().string();
                NewLeftImage newLeftImage = new Gson().fromJson(responseData, NewLeftImage.class);
                Log.e("66666666666666666", "onResponse: "+newLeftImage.toString());
                newleft = new ArrayList<NewLeftImage>();
                newleft.add(newLeftImage);
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
                list = new ArrayList<New_Bean.GoodsListBean>();
                Gson gson = new Gson();
                New_Bean new_bean = gson.fromJson(responseData, New_Bean.class);
                list.addAll(new_bean.getGoods_list());
                Log.e("------", "onResponse: "+list.size());

                if (getActivity()!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final GridLayoutManager manager = new GridLayoutManager(getActivity(), 6);
                            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    switch (position) {
                                        case 0:
                                            return 3;
                                        case 1:
                                            return 3;
                                        case  2:
                                            return 6;
                                        default:
                                            return 3;
                                    }
                                }
                            });
                            newContentRecycler.setLayoutManager(manager);
                            mAdapter = new NewAdapter(getActivity(), list,newleft);
                            newContentRecycler.setAdapter(mAdapter);
                            mAdapter.setOnItemClickLitener(new NewAdapter.OnItemClickLitener() {
                                @Override
                                public void onItemClick(View view, int position,int id) {
                                    Toast.makeText(getActivity(),""+position,Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                                    intent.putExtra("GUOXIN",id);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }


            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment_layout, null);
        unbinder = ButterKnife.bind(this,view);
        publicToolbarTv.setText("新品");
        publicToolbarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareInterface shareInterface = new ShareInterface();
                shareInterface.show(getFragmentManager(), "bottom");
            }
        });
        return view;
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
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
