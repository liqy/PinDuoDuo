package bw.pdd.fragmen;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bw.pdd.Base.BaseFragment;
import bw.pdd.R;
import bw.pdd.fragmen.home_fragment.Home_Fragment_One;
import bw.pdd.fragmen.home_fragment.Qs_Fragment_Two;

/**
 * Created by zhouwei on 17/4/23.
 */

public class HomeFragment extends BaseFragment {
    private TabLayout layout;

    private ViewPager viewPager;
    private String mFrom;
    public static HomeFragment newInstance(String from){
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("from",from);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            mFrom = getArguments().getString("from");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_layout,null);
        layout=view.findViewById(R.id.qs_tab);
        viewPager=view.findViewById(R.id.qs_viewpager);
        final String name[]={"首页","装饰","男装","母婴","家具","美食","电器","美妆","家纺","手机","运动","水果"};
        final Qs_Fragment_Two [] GG= new Qs_Fragment_Two[name.length];
        viewPager.setCurrentItem(0);
        layout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position==0){
                    return new Home_Fragment_One();
                }else {
                    if (GG[position]==null){
                        return GG[position]=new Qs_Fragment_Two().getnewInstance(position);
                    }else {
                        return GG[position];
                    }

                }
            }

            @Override
            public int getCount() {
                return name.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return name[position];
            }
        });
//绑定
        layout.setupWithViewPager(viewPager);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(getActivity().getResources().getColor(R.color.colorRed));
        }
    }
}
