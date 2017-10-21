package bw.pdd.util.share;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 类描述：
 * Created by 杨雪雪 on 2017/9/6.
 */

public class AppRegister extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        final IWXAPI api = WXAPIFactory.createWXAPI(context, null);

//         api.registerApp(Constants.APP_ID);
    }
}
