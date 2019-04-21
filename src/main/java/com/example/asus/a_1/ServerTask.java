package com.example.asus.a_1;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class ServerTask implements Runnable {

    private static final String TAG = "ServerTask";

    private int port;

    private Handler handler;//主线程handle——用于将从服务器获取的消息显示出来
//    Handler dataHandler;


    ServerTask(int port, Handler handler) {
//        this.ip = ip;
        this.port = port;
        this.handler = handler;
    }

    @Override
    //开启线程并且执行线程
    public void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Log.e(TAG, "waiting...");
            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),
                    "GBK"));//创建输入流对象InputStreamReader和输入流对象读取器
            // BufferedReader并且传入输入流对象，该对象的作用是：获取服务器返回的对象
            Log.e(TAG, "connected");

            Message message;
            String receiveValue;
            String value;

            HashMap<String, String> data = new HashMap<>();

            while (true) {
                String readLine = br.readLine();//通过输入流读取对象，接收服务器发送过来的数据
                if (readLine != null) {
                    Log.e(TAG, "receive" + readLine);

                    receiveValue = readLine;
                    if (receiveValue.equals("close")) {
                        break;
                    }
                    String subMsg = receiveValue.substring(0, 2);
                    switch (subMsg) {
                        case "温度":
                            value = receiveValue.substring(3);
                            data.put("temperature", value);
                            message = new Message();
                            message.what = MainActivity.UPDATE_TEMPERATURE;
                            message.obj = value;
                            handler.sendMessage(message);
                            break;
                        case "湿度":
                            value = receiveValue.substring(3);
                            data.put("humidity", value);
                            message = new Message();
                            message.what = MainActivity.UPDATE_HUMIDITY;
                            message.obj = value;
                            handler.sendMessage(message);
                            break;
                        case "大气":
                            value = receiveValue.substring(4);
                            data.put("atmosphere_pressure", value);
                            message = new Message();
                            message.what = MainActivity.UPDATE_PRESSURE;
                            message.obj = value;
                            handler.sendMessage(message);
                            break;
                        case "光照":
                            value = receiveValue.substring(5);
                            data.put("light_intensity", value);
                            message = new Message();
                            message.what = MainActivity.UPDATE_LIGHT;
                            message.obj = value;
                            handler.sendMessage(message);
                            break;
                        case "氨气":
                            value = receiveValue.substring(5);
                            data.put("ammonia_concentration", value);
                            message = new Message();
                            message.what = MainActivity.UPDATE_AMMONIA;
                            message.obj = value;
                            handler.sendMessage(message);
                            break;
                        case "二氧":
                            value = receiveValue.substring(5);
                            data.put("carbon_dioxide", value);
                            message = new Message();
                            message.what = MainActivity.UPDATE_CARBON;
                            message.obj = value;
                            handler.sendMessage(message);
                            break;
                        case "氧气":
                            value = receiveValue.substring(3);
                            data.put("oxygen", value);
                            message = new Message();
                            message.what = MainActivity.UPDATE_OXYGEN;
                            message.obj = value;
                            handler.sendMessage(message);
                            break;
                        case "PM":
                            value = receiveValue.substring(6);
                            data.put("pm25", value);
                            message = new Message();
                            message.what = MainActivity.UPDATE_PM;
                            message.obj = value;
                            handler.sendMessage(message);
                            break;
                        case "噪声":
                            value = receiveValue.substring(3);
                            data.put("noise", value);
                            message = new Message();
                            message.what = MainActivity.UPDATE_NOISE;
                            message.obj = value;
                            handler.sendMessage(message);
                            break;
                        case "Lo":
                            value = receiveValue.substring(10);
                            data.put("longitude", value);
                            message = new Message();
                            message.what = MainActivity.UPDATE_LONGITUDE;
                            message.obj = value;
                            handler.sendMessage(message);
                            break;
                        case "La":
                            value = receiveValue.substring(9);
                            data.put("latitude", value);
                            message = new Message();
                            message.what = MainActivity.UPDATE_LATITUDE;
                            message.obj = value;
                            handler.sendMessage(message);
                            break;
                        case "时间":
                            value = receiveValue.substring(3);
                            data.put("time", value);
                            message = new Message();
                            message.what = MainActivity.UPDATE_TIME;
                            message.obj = value;
                            handler.sendMessage(message);
                            break;
                        case "日期":
                            value = receiveValue.substring(3);
                            data.put("date", value);
                            message = new Message();
                            message.what = MainActivity.UPDATE_DATE;
                            message.obj = receiveValue.substring(3);
                            handler.sendMessage(message);

                            EnvData envData = new EnvData();
                            envData.setTemperature(data.get("temperature"));
                            envData.setHumidity(data.get("humidity"));
                            envData.setAtmospherePressure(data.get("atmosphere_pressure"));
                            envData.setLightIntensity(data.get("light_intensity"));
                            envData.setAmmoniaConcentration(data.get("ammonia_concentration"));
                            envData.setCarbonDioxide(data.get("carbon_dioxide"));
                            envData.setOxygen(data.get("oxygen"));
                            envData.setPm25(data.get("pm25"));
                            envData.setNoise(data.get("noise"));
                            envData.setLongitude(data.get("longitude"));
                            envData.setLatitude(data.get("latitude"));
                            envData.setTime(data.get("time"));
                            envData.setDate(data.get("date"));
                            envData.save();
                            break;

                    }
                }
            }
            socket.close();//关闭socket的连接
            serverSocket.close();
        } catch (Exception e) {
            Log.e(TAG, "connect error");
            e.printStackTrace();
        }
    }

}
