package bw.pdd.util.onClick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import bw.pdd.activit.LoginActivity;
import bw.pdd.app.AppConstants;
import bw.pdd.util.SharedPreferencesUtil;

/**
 * Created by Administrator on 2017/9/1.
 * 判断是否登录，如果没有登录登录跳转到登录页面
 * 贾永超
 */

public abstract class ViewOnClickListener implements OnClickListener {
    @Override
    public void onClick(View view) {

        if (SharedPreferencesUtil.getBoolean(view.getContext(), AppConstants.ISLOGIN,false)){
            onLoginClick(view);
            return;
        }else {
            Intent it = new Intent(getContent(), LoginActivity.class);
            getContent().startActivity(it);
        }
    }
    public abstract Context getContent();
    public abstract void onLoginClick(View var1);
}
