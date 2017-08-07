package cn.dianedun.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import cn.dianedun.R;
import cn.dianedun.base.BaseTitlActivity;

/**
 * Created by Administrator on 2017/8/7.
 */

public class HisWorkOrderActivity extends BaseTitlActivity {

    @Bind(R.id.lv_hisworkorder)
    ListView lv_hisworkorder;

    private IndentCusAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hisworkorder);
        setTvTitleText("历史工单");
        setTitleBack(R.mipmap.home_backg_rightnull);
        setImgLeftVisibility(View.VISIBLE);
        setImgLeft(R.mipmap.bt_back);
        adapter = new IndentCusAdapter();
        lv_hisworkorder.setAdapter(adapter);

    }

    private class IndentCusAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Cache cache;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_hisworkorder, null);
                cache = new Cache();
                convertView.setTag(cache);
            } else {
                cache = (Cache) convertView.getTag();
            }
            return convertView;
        }
    }

    class Cache {
        TextView tv_grouponall_name, tv_grouponall_from, tv_grouponall_offered;
        ImageView img_grouponall_head;
    }

}