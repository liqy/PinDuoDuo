package bw.pdd.util;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/31.
 */

public class HttpUtil {
    /**
     * Http工具类
     * 注意声明权限
     *  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
     *  <uses-permission android:name="android.permission.INTERNET"/>
     */
        //网络请求OKHttp
        public static void sendOkHttpRequest(String address,Callback callback){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(address)
                    .build();
            client.newCall(request).enqueue(callback);
        }

        //判断网络是否连接
        public static boolean isNetworkAvailable(Context context) {
            // 得到网络连接信息
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            // 去进行判断网络是否连接
            if (manager.getActiveNetworkInfo() != null) {
                return manager.getActiveNetworkInfo().isAvailable();
            }
            return false;
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
                }
            });
        }

}
