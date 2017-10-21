package bw.pdd.activit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bw.pdd.Base.BaseActivity;
import bw.pdd.R;
import bw.pdd.fragmen.personal_fragment.FragmentOrder;

/**
 * Created by asus on 2017/9/5.
 */

public class PersonalMyOrder extends BaseActivity {
    private TextView back;
    private TabLayout tab;
    private ViewPager pager;
    List<Fragment> list = new ArrayList<>();
    String[] sz = {"全部","待付款","带拼单","代发货","代收货","带评价"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        back = (TextView) findViewById(R.id.order_back);
        tab = (TabLayout) findViewById(R.id.order_mtab);
        pager = (ViewPager) findViewById(R.id.order_pager);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        list.add(new FragmentOrder());
        list.add(new FragmentOrder());
        list.add(new FragmentOrder());
        list.add(new FragmentOrder());
        list.add(new FragmentOrder());
        tab.setupWithViewPager(pager);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }
            @Override
            public int getCount() {
                return list.size();
            }
            @Override
            public CharSequence getPageTitle(int position) {

                return sz[position];
            }
        });

    }
}
