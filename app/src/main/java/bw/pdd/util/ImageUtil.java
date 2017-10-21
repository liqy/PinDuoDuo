package bw.pdd.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/8/31.
 */

public class ImageUtil {
    public static void disPlayImage(Context context,String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }


}
