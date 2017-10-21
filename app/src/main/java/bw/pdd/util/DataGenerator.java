package bw.pdd.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import bw.pdd.R;
import bw.pdd.fragmen.SeaAmoyFragment;
import bw.pdd.fragmen.NewFragment;
import bw.pdd.fragmen.HomeFragment;
import bw.pdd.fragmen.PersonalCenterFragment;
import bw.pdd.fragmen.SearchFragment;

/**
 * Created by Administrator on 2017/8/30.
 */

public class DataGenerator {

    public static final int []mTabRes = new int[]{R.drawable.home,R.drawable.new_arrivals,R.drawable.haitao,R.drawable.classification,R.drawable.personal};
    public static final int []mTabResPressed = new int[]{R.drawable.home_hl,R.drawable.new_arrivals_hl,R.drawable.haitao_hl,R.drawable.classification_hl,R.drawable.personal_hl};
    public static final String []mTabTitle = new String[]{"首页","新品","海淘","搜索","个人中心"};

    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[5];
        fragments[0] = HomeFragment.newInstance(from);
        fragments[1] = NewFragment.newInstance(from);
        fragments[2] = SeaAmoyFragment.newInstance(from);
        fragments[3] = SearchFragment.newInstance(from);
        fragments[4] = PersonalCenterFragment.newInstance(from);
        return fragments;
    }

    /**
     * 获取Tab 显示的内容
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position){
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
