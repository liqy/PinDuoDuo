package bw.pdd.fragmen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bw.pdd.Base.BaseFragment;
import bw.pdd.R;
import bw.pdd.activit.PersonalMyOrder;
import bw.pdd.activit.PersonalSet;
import bw.pdd.app.AppConstants;
import bw.pdd.util.SharedPreferencesUtil;
import bw.pdd.util.onClick.ViewOnClickListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/8/30.
 */

public class PersonalCenterFragment extends BaseFragment {
    @BindView(R.id.pc_login)
    CircleImageView Login;
    @BindView(R.id.pc_name)
    TextView Name;
    @BindView(R.id.pc_waiting_for_pay)
    TextView WaitingForPay;
    @BindView(R.id.pc_waiting_for_spell_list)
    TextView WaitingForSpellList;
    @BindView(R.id.pc_waiting_delivery)
    TextView WaitingDelivery;
    @BindView(R.id.pc_waiting_for_goods)
    TextView WaitingForGoods;
    @BindView(R.id.pc_waiting_for_evaluation)
    TextView WaitingForEvaluation;
    @BindView(R.id.pc_message)
    TextView Message;
    @BindView(R.id.pc_preferential)
    TextView Preferential;
    @BindView(R.id.pc_collect)
    TextView Collect;
    @BindView(R.id.pc_refunds)
    TextView Refunds;
    @BindView(R.id.pc_share)
    TextView Share;
    @BindView(R.id.pc_histore)
    TextView Histore;
    @BindView(R.id.pc_lucky_draw)
    TextView LuckyDraw;
    @BindView(R.id.pc_spell_list)
    TextView SpellList;
    @BindView(R.id.pc_free_of_charge)
    TextView FreeOfCharge;
    @BindView(R.id.pc_address)
    TextView Address;
    @BindView(R.id.pc_setting)
    TextView Setting;

    private String mFrom;
    private Unbinder bind;
    private boolean STATE = false;

    public static PersonalCenterFragment newInstance(String from) {
        PersonalCenterFragment fragment = new PersonalCenterFragment();
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personalcenter, null);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewOnClickListener onClickListener = new ViewOnClickListener() {
            @Override
            public Context getContent() {
                return getActivity();
            }

            @Override
            public void onLoginClick(View var1) {
                switch (var1.getId()) {
                    case R.id.pc_login:
                        Toast.makeText(getActivity(), "登陆", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pc_name:
                        Toast.makeText(getActivity(), "名字", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pc_waiting_for_pay:
                    case R.id.pc_waiting_for_spell_list:
                    case R.id.pc_waiting_delivery:
                    case R.id.pc_waiting_for_goods:
                    case R.id.pc_waiting_for_evaluation:
                        Intent order = new Intent(getActivity(), PersonalMyOrder.class);
                        getActivity().startActivity(order);
                        break;
                    case R.id.pc_message:
                        Toast.makeText(getActivity(), "消息", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pc_preferential:
                        Toast.makeText(getActivity(), "优惠券", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pc_collect:
                        Toast.makeText(getActivity(), "收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pc_refunds:
                        Toast.makeText(getActivity(), "退款", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pc_share:
                        Toast.makeText(getActivity(), "邀请好友领红包", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pc_histore:
                        Toast.makeText(getActivity(), "足迹", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pc_lucky_draw:
                        Toast.makeText(getActivity(), "抽奖", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pc_spell_list:
                        Toast.makeText(getActivity(), "拼单（下面）", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pc_free_of_charge:
                        Toast.makeText(getActivity(), "我的免单券", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pc_address:
                        Toast.makeText(getActivity(), "收货地址", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pc_setting:
                        Intent set = new Intent(getActivity(), PersonalSet.class);
                        getActivity().startActivity(set);
                        break;
                }
            }
        };

        Login.setOnClickListener(onClickListener);
        Name.setOnClickListener(onClickListener);
        WaitingForPay.setOnClickListener(onClickListener);
        WaitingForSpellList.setOnClickListener(onClickListener);
        WaitingDelivery.setOnClickListener(onClickListener);
        WaitingForGoods.setOnClickListener(onClickListener);
        WaitingForEvaluation.setOnClickListener(onClickListener);
        Message.setOnClickListener(onClickListener);
        Preferential.setOnClickListener(onClickListener);
        Collect.setOnClickListener(onClickListener);
        Refunds.setOnClickListener(onClickListener);
        Share.setOnClickListener(onClickListener);
        Histore.setOnClickListener(onClickListener);
        LuckyDraw.setOnClickListener(onClickListener);
        SpellList.setOnClickListener(onClickListener);
        FreeOfCharge.setOnClickListener(onClickListener);
        Address.setOnClickListener(onClickListener);
        Setting.setOnClickListener(onClickListener);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(getActivity().getResources().getColor(R.color.colorRed));
        }
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter && !STATE) {
            STATE = true;
        } else {
            STATE = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!STATE)
        {
            if (SharedPreferencesUtil.getBoolean(getActivity(), AppConstants.ISLOGIN, false)) {
                SharedPreferences wei = getActivity().getSharedPreferences("wei", Context.MODE_PRIVATE);
                String login = wei.getString("login", null);
                String name = wei.getString("name", null);
                Glide.with(getActivity()).load(login).into(Login);
                Name.setText(name);
            }else {
                Login.setImageResource(R.drawable.denglutouxiang);
                Name.setText("未登录");
            }
            STATE=true;
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        STATE = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

}