package bw.pdd.activit;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bw.pdd.Base.BaseActivity;
import bw.pdd.R;
import bw.pdd.adapter.DetailAdapter;
import bw.pdd.bean.DetailBanner;
import bw.pdd.bean.DetailBean;
import bw.pdd.util.HttpUtil;
import bw.pdd.util.share.ShareInterface;
import okhttp3.Callback;
import okhttp3.Response;
import zhan.transparent.OnTransparentListener;
import zhan.transparent.widget.TransparentFrameLayout;

import static bw.pdd.R.id.pic_iv;

/**
 * Created by 郭鑫 on 2017/9/5.
 */

public class DetailActivity extends BaseActivity{
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<DetailBanner.ListBean> lista;
    private List<DetailBanner> bannerl;
    private List<Object> mData = new ArrayList<>();
    private List<String> bannerlist;
    private List<String> banner;
    private TransparentFrameLayout mTransparentFrameLayout;
    private RecyclerView mRecyclerView;
    private TextView numTv;
    private List<DetailBean> list;
    private ImageView picIv;
    private TextView titleTv;
    private View line;
    private ImageView pic_iv1;
    private DetailAdapter mMyAdapter;
    private ImageView pic_iv2,pic_iv3;
    private int mDy;
    private RadioButton radioButton,radioButton1,radioButton2;
    int guoxin;
    public String dealun = "http://apiv4.yangkeduo.com/recommendation/mall?goods_id=68446794&pdduid=";
    private ShareInterface shareInterface;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deatailactivity);
        Intent intent = getIntent();
        guoxin = intent.getIntExtra("GUOXIN",0);
        TowsendHttpRequest("http://apiv4.yangkeduo.com/recommendation/mall?goods_id="+guoxin+"&pdduid=3470667255");
        sendHttpRequest("http://apiv4.yangkeduo.com/v5/goods/"+guoxin+"?pdduid=3470667255");
        Log.e(TAG, "onCreate: "+guoxin);
        initView();
        initListener();
        SelectButton();
    }

    private void initData() {
        mData.add("");
        for (int x = 0; x < 33; x++) {
            mData.add(x);
        }
        LinearLayoutManager manager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
            mMyAdapter = new DetailAdapter(list,mData,DetailActivity.this,banner);
            mRecyclerView.setAdapter(mMyAdapter);
        mTransparentFrameLayout.setColorToBackGround(getResources().getColor(R.color.white));
        mTransparentFrameLayout.setMaxOffset(getResources().getDimension(R.dimen.offset));
    }

    private void initListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mDy += dy;
                mTransparentFrameLayout.updateTop(mDy);
            }
        });

        mTransparentFrameLayout.addOnScrollStateListener(new OnTransparentListener() {
            @Override public void onTransparentStart(float fraction) {  //scroll to top

            }

            @Override public void onTransparentEnd(float fraction) {  //scroll to max offset
                if (titleTv.getVisibility() == View.GONE) {
                    titleTv.setVisibility(View.VISIBLE);
                    picIv.setVisibility(View.VISIBLE);
                    pic_iv1.setVisibility(View.VISIBLE);
                    pic_iv2.setVisibility(View.GONE);
                    pic_iv3.setVisibility(View.GONE);
                    picIv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                            toast("郭鑫");
                        }
                    });
                    pic_iv1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            toast("郭鑫");
                            shareInterface.show(getSupportFragmentManager(),"buttom");
                        }
                    });
                }
            }

            @Override public void onTransparentUpdateFraction(float fraction) {  //scrolling...
                if (titleTv.getVisibility() == View.VISIBLE && fraction < 1) {
                    titleTv.setVisibility(View.GONE);
                    picIv.setVisibility(View.GONE);
                    pic_iv1.setVisibility(View.GONE);
                    pic_iv2.setVisibility(View.VISIBLE);
                    pic_iv3.setVisibility(View.VISIBLE);
                    pic_iv2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                            toast("未消失郭鑫");
                        }
                    });
                    pic_iv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            toast("未消失郭鑫");
                            shareInterface.show(getSupportFragmentManager(),"buttom");
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
                DetailBean detailBean = new Gson().fromJson(responseData, DetailBean.class);
                list = new ArrayList<DetailBean>();
                list.add(detailBean);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                    }
                });
            }
        });
    }
    public void TowsendHttpRequest(String url){
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                //异常处理操作
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                //得到服务器返回的具体数据 //切换会主线程更新UI
                String responseData = response.body().string();
                DetailBanner gson = new Gson().fromJson(responseData, DetailBanner.class);
                Log.e("1111111111111111", "onResponse: "+gson.getList().toString());
                lista = new ArrayList<>();
                bannerl = new ArrayList<DetailBanner>();
                banner = new ArrayList<String>();
                bannerl.add(gson);
                for (DetailBanner a:bannerl) {
                    lista.addAll(a.getList());
                }
                for (DetailBanner.ListBean b:lista) {
                    banner.add(b.getHd_thumb_url());
                    Log.e("99999999", "onResponse: "+b.toString());
                }

            }
        });
    }
    public void SelectButton(){
        Drawable drawable = getResources().getDrawable(R.drawable.deatailselectormain);
        drawable.setBounds(0, 0, 20, 20);
        radioButton.setCompoundDrawables(null, drawable, null, null);
        Drawable drawable1 = getResources().getDrawable(R.drawable.deatailselectorcollect);
        drawable1.setBounds(0, 0, 20, 20);
        radioButton1.setCompoundDrawables(null, drawable1, null, null);
        Drawable drawable2 = getResources().getDrawable(R.drawable.deatailselectorservice);
        drawable2.setBounds(0, 0, 20, 20);
        radioButton2.setCompoundDrawables(null, drawable2, null, null);
    }

    private void initView() {
        shareInterface = new ShareInterface();
        radioButton = (RadioButton) findViewById(R.id.radio_detail_main);
        radioButton1 = (RadioButton) findViewById(R.id.radio_detail_collect);
        radioButton2 = (RadioButton) findViewById(R.id.radio_detail_service);
        mTransparentFrameLayout = (TransparentFrameLayout) findViewById(R.id.tool_bar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        numTv = (TextView) findViewById(R.id.num_tv);
        titleTv = (TextView) findViewById(R.id.title_tv);
        picIv = (ImageView) findViewById(pic_iv);
        pic_iv1 = (ImageView) findViewById(R.id.pic_iv1);
        pic_iv2 = (ImageView) findViewById(R.id.pic_iv2);
        pic_iv3 = (ImageView) findViewById(R.id.pic_iv3);
        pic_iv3.setVisibility(View.VISIBLE);
        pic_iv2.setVisibility(View.VISIBLE);
        pic_iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                toast("未消失郭鑫");
            }
        });
        pic_iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("未消失郭鑫");
                shareInterface.show(getSupportFragmentManager(),"buttom");
            }
        });
    }
}
