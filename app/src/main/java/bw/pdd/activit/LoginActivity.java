package bw.pdd.activit;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArraySet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import bw.pdd.Base.BaseActivity;
import bw.pdd.R;
import bw.pdd.app.AppConstants;
import bw.pdd.util.SharedPreferencesUtil;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by asus on 2017/9/2.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_back)
    TextView loginBack;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.login_weixindl)
    Button loginWeixindl;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.login_otherdl)
    TextView loginOtherdl;
    @BindView(R.id.login_look)
    TextView loginLook;
    Dialog bottomDialog;
    UMShareAPI umShareAPI;
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        umShareAPI = UMShareAPI.get(this);
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        loginWeixindl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "weixinup", Toast.LENGTH_SHORT).show();
            }
        });
       Otherup();
    }
    private void Otherup() {
        loginOtherdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog = new Dialog(LoginActivity.this, R.style.BottomDialog);
                View contentView = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_content_normal, null);
                bottomDialog.setContentView(contentView);
                ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
                layoutParams.width = getResources().getDisplayMetrics().widthPixels;
                contentView.setLayoutParams(layoutParams);
                bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
                bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
                bottomDialog.show();
                LinearLayout qqup = contentView.findViewById(R.id.diog_qq);
                LinearLayout phoneup = contentView.findViewById(R.id.dlog_phone);
                LinearLayout wbup = contentView.findViewById(R.id.dlog_wb);
                TextView qx = contentView.findViewById(R.id.dlog_back);
                qqup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomDialog.dismiss();
                        if(umShareAPI.isInstall(LoginActivity.this,SHARE_MEDIA.QQ)) {
                            umShareAPI.doOauthVerify(LoginActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                                @Override
                                public void onStart(SHARE_MEDIA share_media) {
                                }

                                @Override
                                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                                    umShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                                        @Override
                                        public void onStart(SHARE_MEDIA share_media) {

                                        }
                                        @Override
                                        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                                            Toast.makeText(LoginActivity.this, map.toString(), Toast.LENGTH_SHORT).show();
                                            String iconurl = map.get("iconurl");
                                            String screen_name = map.get("screen_name");
                                            SharedPreferences wei = getSharedPreferences("wei", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor edit = wei.edit();
                                            SharedPreferencesUtil.setBoolean(LoginActivity.this,AppConstants.ISLOGIN,true);
                                            edit.putString("login",iconurl);
                                            edit.putString("name",screen_name);
                                            edit.commit();
                                            Toast.makeText(LoginActivity.this, "登陆ok", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                        @Override
                                        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                                        }
                                        @Override
                                        public void onCancel(SHARE_MEDIA share_media, int i) {

                                        }
                                    });
                                }
                                @Override
                                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                                    Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();

                                }
                                @Override
                                public void onCancel(SHARE_MEDIA share_media, int i) {

                                }
                            });
                        }else {
                            Toast.makeText(LoginActivity.this, "你给我上一边喇儿去，没有qq瞎登陆干哈", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                phoneup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(LoginActivity.this, "phone", Toast.LENGTH_SHORT).show();
                    }
                });
                wbup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(LoginActivity.this, "wb", Toast.LENGTH_SHORT).show();
                    }
                });
                qx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomDialog.dismiss();
                    }
                });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
