package bw.pdd.fragmen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bw.pdd.R;
import bw.pdd.adapter.newrecycleradapter.NewLeftAdapter;
import bw.pdd.app.AppConstants;
import bw.pdd.bean.newbean.NewLeftBean;
import bw.pdd.util.HttpUtil;
import bw.pdd.util.share.ShareInterface;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 郭鑫 on 2017/9/22.
 */

public class NewLeftFragment extends AppCompatActivity {
    @BindView(R.id.new_activity_left)
    ImageView newActivityLeft;
    @BindView(R.id.public_toolbar_tv)
    TextView publicToolbarTv;
    @BindView(R.id.new_activity_right)
    ImageView newActivityRight;
    @BindView(R.id.new_activity_left_toolbar)
    RelativeLayout newActivityLeftToolbar;
    @BindView(R.id.new_left_recycler)
    RecyclerView newLeftRecycler;
    NewLeftAdapter newLeftAdapter;
    private List<String> mDatas;
    private List<NewLeftBean> newBean;
    private List<NewLeftBean.GoodsListBean> goodsListBean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_left);
        ButterKnife.bind(this);
        sendHttpRequest(AppConstants.new_left_bean);
        newLeftRecycler.setLayoutManager(new LinearLayoutManager(this));
        newActivityLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        newActivityRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareInterface shareInterface = new ShareInterface();
                shareInterface.show(getSupportFragmentManager(),"bottom");
            }
        });
    }
    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
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
                NewLeftBean newLeftBean = new Gson().fromJson(responseData, NewLeftBean.class);
                newBean = new ArrayList<>();
                newBean.add(newLeftBean);
                Log.e("--------------", "onResponse: "+newBean.toString());
                goodsListBean = new ArrayList<NewLeftBean.GoodsListBean>();
                for (NewLeftBean a:newBean){
                    goodsListBean.addAll(a.getGoods_list());
                    Log.e("++++++++++++++++++++", "onResponse: "+goodsListBean.toString());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        newLeftAdapter = new NewLeftAdapter(NewLeftFragment.this,goodsListBean);
                        newLeftRecycler.setAdapter(newLeftAdapter);
                    }
                });
            }
        });
    }
}
