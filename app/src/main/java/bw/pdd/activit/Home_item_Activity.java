package bw.pdd.activit;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bw.pdd.R;
import bw.pdd.bean.homebean.Home_tab_nine;
import bw.pdd.fragmen.home_fragment.Home_item_Fragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Home_item_Activity extends AppCompatActivity {
    //在这个页面写详情页
    @BindView(R.id.home_nav_arrow)
    ImageView home_nav_arrow;
    @BindView(R.id.home_title)
    TextView homeTitle;
    @BindView(R.id.home_nav_share)
    ImageView homeNavShare;
    @BindView(R.id.home_item_tab)
    TabLayout homeItemTab;
    @BindView(R.id.home_item_viewpager)
    ViewPager homeItemViewpager;
    private String tabpath="http://apiv4.yangkeduo.com/penny/tablist?pdduid=";
    private String tabvalue;
    private List<Home_tab_nine.ListBean> tablist;
    private String tabname;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_item_);
        ButterKnife.bind(this);
        tablist=new ArrayList<>();
//        调用网络请求
        GetTab();


    }
    @OnClick({R.id.home_nav_arrow, R.id.home_nav_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_nav_arrow:
                Toast.makeText(Home_item_Activity.this,"点击了返回页",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.home_nav_share:
                Toast.makeText(Home_item_Activity.this,"点击了分享页",Toast.LENGTH_SHORT).show();
                break;
        }
    }
//    进行网络请求获取标题
public  void  GetTab(){

    OkHttpClient client = new OkHttpClient();
    Request request=new Request.Builder()
            .url(tabpath)
            .build();
    Call call = client.newCall(request);
    call.enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
          tabvalue= response.body().string();
                 runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
//                        Type type = new TypeToken<ArrayList<Home_tab_nine>>() {
//                        }.getType();
                        Home_tab_nine tab_nine = gson.fromJson(tabvalue.toString(),Home_tab_nine.class);
                        tabname=tab_nine.getName();
                        homeTitle.setText(tabname);
                        tablist.addAll(tab_nine.getList());
                        Log.e("TAg",tablist.toString());

                        //        绑定ViewPager和Fragment
                        final  Home_item_Fragment GG[]=  new Home_item_Fragment[tablist.size()];
//                        Home_item_Fragment fragment = new Home_item_Fragment();
//                        bundle = new Bundle();
//                        bundle.putString("values","ceshi");
//                        fragment.setArguments(bundle);

                        homeItemViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                            @Override
                            public Fragment getItem(int position) {
//                                Intent intent = new Intent(Home_item_Activity.this, Home_item_Fragment.class);
//                                intent.putExtra("date",position+"");
//                                startActivity(intent);
                                if (GG[position]==null){
                                    return GG[position]=new Home_item_Fragment();
                                }else {
                                    return GG[position];
                                }

                            }
                            @Override
                            public int getCount() {
                                return tablist.size();
                            }
                            @Override
                            public CharSequence getPageTitle(int position) {
                                return tablist.get(position).getTab();
                            }
                        });
//绑定
                        homeItemTab.setupWithViewPager(homeItemViewpager);
                    }
                });


        }
    });
}

}
