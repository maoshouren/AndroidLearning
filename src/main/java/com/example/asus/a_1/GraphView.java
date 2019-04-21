package com.example.asus.a_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import org.litepal.LitePal;

public class GraphView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    //绘图的Canvas
    private Canvas mCanvas;

    //子线程标志位
    private boolean mIsDrawing;
    private Path path;
    private float currentTime;
    private float temperature;
    private float temperaturePosition;
    private float x = 0,y=0;

    private static final String TAG = "GraphView";

    Paint paint = new Paint();


    public GraphView (Context context) {
        super(context);
        init();
    }

    public GraphView (Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraphView (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //在三个参数的构造方法中完成初始化操作



        //绘制正弦曲线
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        path = new Path();
        //路径起始点(0, 100)
        path.moveTo(0, 100);



        init();
    }

    //初始化view
    private void init() {
        Log.d(TAG, "GraphView init");

        mHolder = getHolder();
        //注册回调的方法
        mHolder.addCallback(this);
        //设置一些参数方便以后绘图
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);

    }

    //创建子线程
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        //开启子线程
        new Thread(this).start();
    }

    //改变
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    //销毁
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        int action = event.getAction();
        float x = event.getX();
//        float y = event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            default:
                break;
        }
        return true;
    }

    //绘图逻辑
    private void draw() {
        try {
            //获取canvas对象
            mCanvas = mHolder.lockCanvas();
            //绘制背景
            mCanvas.drawColor(Color.WHITE);

//            temperaturePosition = 400 - 4 * temperature;
////            path.moveTo(x, temperaturePosition);
////            paint.setColor(Color.BLUE);
            mCanvas.drawPath(path, paint);

            //绘图
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                //释放canvas对象并提交画布
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    @Override
    public void run() {
        path = new Path();
        while (mIsDrawing) {
            EnvData data = LitePal.findLast(EnvData.class);
            //没有数据，不能够绘图
//            currentTime = Float.parseFloat(data.getTime());
//            temperature = Float.parseFloat(data.getTemperature());
//            x += 4;



            x += 1;
            y = (int)(100 * Math.sin(2 * x * Math.PI / 180) + 400);
            //加入新的坐标点
            path.lineTo(x, y);





            draw();
        }
    }
}
