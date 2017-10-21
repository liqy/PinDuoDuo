package bw.pdd.util.share;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import bw.pdd.R;
import bw.pdd.app.Applicat;

/**
 * 类描述：
 * Created by 杨雪雪 on 2017/9/5.
 */

public class ShareInterface extends DialogFragment {
    IWXAPI api;
    UMShareAPI umShareAPI;
    String share_url = "http://mobile.yangkeduo.com/haitao.html?refer_share_id=9b21d642436a475abe238eaba743a2d6&refer_share_uid=&refer_share_channel=qq";
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(),"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),"取消了",Toast.LENGTH_LONG).show();

        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.share_layout, null);
        umShareAPI = UMShareAPI.get(getActivity());
        api = WXAPIFactory.createWXAPI(getActivity(), Applicat.APP_ID, false);
        api.registerApp(Applicat.APP_ID);
        v.findViewById(R.id.share_pyq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                wechatShare(0);//分享到微信朋友圈
            }
        });
        v.findViewById(R.id.share_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    UMImage image = new UMImage(getActivity(), R.drawable.icon_luxury);
                    UMWeb web = new UMWeb(share_url);
                    web.setTitle("拼多多");//标题
                    web.setThumb(image);  //缩略图
                    web.setDescription("This is a nice App!");//描述
                    new ShareAction(getActivity())
                            .setPlatform(SHARE_MEDIA.QQ)//传入平台
                            .withMedia(web)
                            .setCallback(shareListener)//回调监听器
                            .share();

            }
        });
        v.findViewById(R.id.share_qqkj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    UMImage image = new UMImage(getActivity(), R.drawable.icon_luxury);
                    UMWeb web = new UMWeb(share_url);
                    web.setTitle("拼多多");//标题
                    web.setThumb(image);  //缩略图
                    web.setDescription("This is a nice App!");//描述
                    new ShareAction(getActivity())
                            .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                            .withMedia(web)
                            .setCallback(shareListener)//回调监听器
                            .share();

            }
        });
        v.findViewById(R.id.share_wx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        v.findViewById(R.id.share_wb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return v;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.MyBottom_sheet_style);
        dialog.setContentView(R.layout.share_layout);
        dialog.setCanceledOnTouchOutside(true);//点击面板外部可以dismiss面板
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;//设置面板的位置在屏幕底部
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        return dialog;
    }
    private void wechatShare(int flag){
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://www.baidu.com";
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "这里填写标题";
        msg.description = "这里填写内容";
        //这里替换一张自己工程里的图片资源
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        msg.setThumbImage(thumb);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag==0?SendMessageToWX.Req.WXSceneSession:SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
    }
}
