package bw.pdd.activit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import bw.pdd.Base.BaseActivity;
import bw.pdd.R;
import bw.pdd.app.AppConstants;
import bw.pdd.util.SharedPreferencesUtil;

/**
 * Created by asus on 2017/9/5.
 */

public class PersonalSet extends BaseActivity{
    private TextView back,out;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        Toast.makeText(this, "真的舍得离开么！！！", Toast.LENGTH_SHORT).show();
        back = (TextView) findViewById(R.id.set_back);
        out = (TextView) findViewById(R.id.set_out);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferencesUtil.setBoolean(PersonalSet.this, AppConstants.ISLOGIN,false);
                finish();
            }
        });

    }
}
