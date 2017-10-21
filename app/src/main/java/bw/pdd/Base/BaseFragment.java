package bw.pdd.Base;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/8/30.
 */

public class BaseFragment extends Fragment {
    //进度条toast
    public void toast(String str){
        if(str!=null){
            Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
        }
    }

    ProgressDialog progressDialog = null;
    public void showProgressBar(String url){
        progressDialog = new ProgressDialog(getActivity());
        if(url != null) progressDialog.setMessage(url);
        else progressDialog.setMessage("加载中…");
        progressDialog.show();
    }

    public void dismissProgresDialog(){
        if(progressDialog!=null){
            if( progressDialog.isShowing()) progressDialog.dismiss();
        }
    }
}
