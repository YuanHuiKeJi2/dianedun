package cn.dianedun.activity;

import android.os.Bundle;
import android.view.View;

import cn.dianedun.R;
import cn.dianedun.base.BaseTitlActivity;

/**
 * Created by Administrator on 2017/8/7.
 */

public class DisposeJbActivity extends BaseTitlActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disposejb);
        setTvTitleText("警报处理");
        setTitleBack(R.mipmap.home_backg_rightnull);
        setImgLeftVisibility(View.VISIBLE);
        setImgLeft(R.mipmap.bt_back);
    }

}
