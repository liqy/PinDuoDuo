package bw.pdd.util.gson;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/9/1.
 */

public class GsonHelper {
    public static <T>T gsonFormat(String json,Class<T> c){
        Gson gson = new Gson();
        T t = gson.fromJson(json, c);
        return t;
    }
}
