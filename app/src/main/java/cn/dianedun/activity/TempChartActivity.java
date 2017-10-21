package cn.dianedun.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import cn.dianedun.R;
import cn.dianedun.base.BaseTitlActivity;
import cn.dianedun.bean.RealTimeConBean;
import cn.dianedun.tools.App;
import cn.dianedun.tools.AppConfig;
import cn.dianedun.tools.GsonUtil;
import cn.dianedun.tools.MyAsyncTast;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Administrator on 2017/9/26.
 */

public class TempChartActivity extends BaseTitlActivity implements View.OnClickListener {


    @Bind(R.id.tv_tempchart_1)
    TextView tv_tempchart_1;

    @Bind(R.id.tv_tempchart_2)
    TextView tv_tempchart_2;

    @Bind(R.id.tv_tempchart_3)
    TextView tv_tempchart_3;

    @Bind(R.id.tv_tempchart_4)
    TextView tv_tempchart_4;

    @Bind(R.id.cash_chart)
    LineChartView lineChart;

    @Bind(R.id.tv_tempchart_adress)
    TextView tv_tempchart_adress;

    private String RoomId;
    private String deviceNum;
    private int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempchart);
        setTvTitleText("实时监测");
        setTitleBack(R.mipmap.home_backg_rightnull);
        setImgLeftVisibility(View.VISIBLE);
        setImgLeft(R.mipmap.bt_back);
        tv_tempchart_1.setOnClickListener(this);
        tv_tempchart_2.setOnClickListener(this);
        tv_tempchart_3.setOnClickListener(this);
        tv_tempchart_4.setOnClickListener(this);
        tv_tempchart_adress.setText(getIntent().getStringExtra("depart"));
        RoomId = getIntent().getStringExtra("RoomId");
        deviceNum = getIntent().getStringExtra("deviceNum");
        getData("day", "1");
    }

    /**
     * 初始化图表
     */
    private void initLineChart(LineChartView chart, List<String> data, List<String> data1, List<String> data2, List<String> Ybz, int max) {
        List<Line> lines = new ArrayList<Line>();//折线的集合
        Line line = new Line(getAxisPoints(data)).setColor(Color.parseColor("#e84b06"));  //折线的颜色
        line.setCubic(true);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(false);//曲线的数据坐标是否加上备注
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(false);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line.setStrokeWidth(1);
        lines.add(line);
        Line line1 = new Line(getAxisPoints(data1)).setColor(Color.parseColor("#1179ce"));  //折线的颜色
        line1.setCubic(true);//曲线是否平滑，即是曲线还是折线
        line1.setFilled(false);//是否填充曲线的面积
        line1.setHasLabels(false);//曲线的数据坐标是否加上备注
        line1.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line1.setHasPoints(false);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line1.setStrokeWidth(1);
        lines.add(line1);
        Line line2 = new Line(getAxisPoints(data2)).setColor(Color.parseColor("#3dc281"));  //折线的颜色
        line2.setCubic(true);//曲线是否平滑，即是曲线还是折线
        line2.setFilled(false);//是否填充曲线的面积
        line2.setHasLabels(false);//曲线的数据坐标是否加上备注
        line2.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line2.setHasPoints(false);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line2.setStrokeWidth(1);
        lines.add(line2);
        LineChartData datas = new LineChartData();
        datas.setLines(lines);//加入图表中
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.WHITE);  //设置字体颜色
        axisX.setTextSize(8);
        //axisX.setName("date");  //表格名称
        axisX.setMaxLabelChars(1); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(getAxisXLables(Ybz));
        axisX.setHasLines(false); //x 轴分割线
        datas.setAxisXBottom(axisX); //x 轴在底部
        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        axisY.setTextColor(Color.WHITE);
        datas.setAxisYLeft(axisY);  //Y轴设置在左边
        chart.setInteractive(true);
        chart.setScrollEnabled(true);
        chart.setZoomEnabled(false);
        chart.setZoomType(ZoomType.HORIZONTAL);
        chart.setLineChartData(datas);
        Viewport v = new Viewport(chart.getMaximumViewport());
        v.top = max + 10;
        v.bottom = 0;
        chart.setMaximumViewport(v);
        v.left = 0;
        v.right = 4;
        chart.setCurrentViewport(v);
    }

    /**
     * 数据点
     *
     * @param data
     * @return
     */
    private List<PointValue> getAxisPoints(List<String> data) {
        List<PointValue> mPointValues = new ArrayList<PointValue>();
        for (int i = 0; i < data.size(); i++) {
            mPointValues.add(new PointValue(i, Integer.parseInt(data.get(i))));
        }
        return mPointValues;
    }

    /**
     * X轴标注
     */
    private List<AxisValue> getAxisXLables(List<String> Ybz) {
        List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
        for (int i = 0; i < Ybz.size(); i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(Ybz.get(i)));
        }
        return mAxisXValues;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tempchart_1:
                tv_tempchart_1.setBackgroundResource(R.mipmap.jc_tab1);
                tv_tempchart_2.setBackgroundResource(R.mipmap.jc_tab);
                tv_tempchart_3.setBackgroundResource(R.mipmap.jc_tab);
                tv_tempchart_4.setBackgroundResource(R.mipmap.jc_tab);
                if (type != 1) {
                    getData("day", "1");
                    type = 1;
                }
                break;
            case R.id.tv_tempchart_2:
                tv_tempchart_1.setBackgroundResource(R.mipmap.jc_tab);
                tv_tempchart_2.setBackgroundResource(R.mipmap.jc_tab1);
                tv_tempchart_3.setBackgroundResource(R.mipmap.jc_tab);
                tv_tempchart_4.setBackgroundResource(R.mipmap.jc_tab);
                if (type != 2) {
                    getData("week", "1");
                    type = 2;
                }
                break;
            case R.id.tv_tempchart_3:
                tv_tempchart_1.setBackgroundResource(R.mipmap.jc_tab);
                tv_tempchart_2.setBackgroundResource(R.mipmap.jc_tab);
                tv_tempchart_3.setBackgroundResource(R.mipmap.jc_tab1);
                tv_tempchart_4.setBackgroundResource(R.mipmap.jc_tab);
                if (type != 3) {
                    getData("month", "1");
                    type = 3;
                }
                break;
            case R.id.tv_tempchart_4:
                tv_tempchart_1.setBackgroundResource(R.mipmap.jc_tab);
                tv_tempchart_2.setBackgroundResource(R.mipmap.jc_tab);
                tv_tempchart_3.setBackgroundResource(R.mipmap.jc_tab);
                tv_tempchart_4.setBackgroundResource(R.mipmap.jc_tab1);
                if (type != 4) {
                    getData("custon", "1");
                    type = 4;
                }
                break;
            default:
                break;
        }
    }
    public void getData(String flag, String num) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("RoomId", RoomId);
        hashMap.put("flag", flag);
        hashMap.put("num", num);
        hashMap.put("deviceNum", deviceNum);
        MyAsyncTast myAsyncTast = new MyAsyncTast(TempChartActivity.this, hashMap, "", App.getInstance().getToken(), new MyAsyncTast.Callback() {
            @Override
            public void send(String result) {
//                initLineChart(lcv_xgtension, bean.getData().getVaList(), bean.getData().getVbList(), bean.getData().getVcList(), xList, maxXg);
            }

            @Override
            public void onError(String result) {

            }
        });
        myAsyncTast.execute();
    }
}
