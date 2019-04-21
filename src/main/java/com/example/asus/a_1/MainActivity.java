package com.example.asus.a_1;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Fragment> mFragmentList = new ArrayList<>();
    FragmentAdapter  mFragmentAdapter;
    GraphFragment graphFragment;
    SetFragment setFragment;
    private DataFragment dataFragment;

    private ViewPager viewPager;
    private TextView item_data;
    private TextView item_graph;
    private TextView item_set;

    public static float screenWidth;
    public static float screenHeight;

    private static final int PORT = 8686;

    public static final int UPDATE_TEMPERATURE = 1;
    public static final int UPDATE_HUMIDITY = 2;
    public static final int UPDATE_PRESSURE = 3;
    public static final int UPDATE_LIGHT = 4;
    public static final int UPDATE_AMMONIA = 5;
    public static final int UPDATE_CARBON = 6;
    public static final int UPDATE_OXYGEN = 7;
    public static final int UPDATE_PM = 8;
    public static final int UPDATE_NOISE = 9;
    public static final int UPDATE_LONGITUDE = 10;
    public static final int UPDATE_LATITUDE = 11;
    public static final int UPDATE_TIME = 12;
    public static final int UPDATE_DATE = 13;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //每一个WindowManager对象都和一个特定的 Display绑定
        //使用WindowManager可以在其他应用最上层，甚至手机桌面最上层显示窗口。
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        //设置类似微信的界面
        initConf();
        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(mFragmentAdapter);
        viewPager.setCurrentItem(0);
        changeTextColor(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
            @Override
            public void onPageSelected(int position) {
                changeTextColor(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        //设置线程操作
        class mHandler extends Handler {
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what) {
                    case UPDATE_TEMPERATURE:
                        dataFragment.text_temperature.setText(msg.obj.toString());
                        break;
                    case UPDATE_HUMIDITY:
                        dataFragment.text_humidity.setText(msg.obj.toString());
                        break;
                    case UPDATE_PRESSURE:
                        dataFragment.text_pressure.setText(msg.obj.toString());
                        break;
                    case UPDATE_LIGHT:
                        dataFragment.text_light.setText(msg.obj.toString());
                        break;
                    case UPDATE_AMMONIA:
                        dataFragment.text_ammonia.setText(msg.obj.toString());
                        break;
                    case UPDATE_CARBON:
                        dataFragment.text_carbon.setText(msg.obj.toString());
                        break;
                    case UPDATE_OXYGEN:
                        dataFragment.text_oxygen.setText(msg.obj.toString());
                        break;
                    case UPDATE_PM:
                        dataFragment.text_pm.setText(msg.obj.toString());
                        break;
                    case UPDATE_NOISE:
                        dataFragment.text_noise.setText(msg.obj.toString());
                        break;
                    case UPDATE_LONGITUDE:
                        dataFragment.text_longitude.setText(msg.obj.toString());
                        break;
                    case UPDATE_LATITUDE:
                        dataFragment.text_latitude.setText(msg.obj.toString());
                        break;
                    case UPDATE_TIME:
                        dataFragment.text_time.setText(msg.obj.toString());
                        break;
                    default:
                        break;
                }
            }
        }
        Handler handler = new mHandler();

        new Thread(new ServerTask(PORT, handler)).start();


       LitePal.getDatabase();
    }

    //背景的渐变色
    private void changeTextColor(int position) {
        if (position == 0) {
            item_data.setTextColor(Color.parseColor("#F0FFF0"));
            item_graph.setTextColor(Color.parseColor("#000000"));
            item_set.setTextColor(Color.parseColor("#000000"));
        } else if (position == 1) {
            item_data.setTextColor(Color.parseColor("#000000"));
            item_graph.setTextColor(Color.parseColor("#F0FFF0"));
            item_set.setTextColor(Color.parseColor("#000000"));
        } else {
            item_data.setTextColor(Color.parseColor("#000000"));
            item_graph.setTextColor(Color.parseColor("#000000"));
            item_set.setTextColor(Color.parseColor("#F0FFF0"));
        }
    }

    //设置界面的切换
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_data:
                viewPager.setCurrentItem(0, true);
                break;
            case R.id.item_graph:
                viewPager.setCurrentItem(1, true);
                break;
            case R.id.item_set:
                viewPager.setCurrentItem(2, true);
                break;
            default:
                break;
        }
    }

    //界面设置代码
    private void initConf() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        item_data = (TextView) findViewById(R.id.item_data);
        item_graph = (TextView) findViewById(R.id.item_graph);
        item_set = (TextView) findViewById(R.id.item_set);
        item_data.setOnClickListener(this);
        item_graph.setOnClickListener(this);
        item_set.setOnClickListener(this);

        viewPager = (ViewPager) findViewById(R.id.mainViewPager);
        viewPager.getBackground().setAlpha(50);

        dataFragment = new DataFragment();
        graphFragment = new GraphFragment();
        setFragment = new SetFragment();
        mFragmentList.add(dataFragment);
        mFragmentList.add(graphFragment);
        mFragmentList.add(setFragment);
    }
}
