package com.example.framelayout;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.TextView;


public class MainActivity extends Activity {
	private int[]textIds=new int[]{R.id.text01,R.id.text02,R.id.text03,R.id.text04,R.id.text05};//定义一个数组，用于储存所有TextView的ID
	private int[]colors=new int[]{Color.RED,Color.MAGENTA,Color.GREEN,Color.YELLOW,Color.BLUE};//定义一个数组，用于储存5种颜色
	//定义一个数组，数组元素为TextView,数组的长度由前面的数组决定
	private TextView[]views=new TextView[textIds.length];
	private Handler mHandler;
	private int current=0;//记录从哪个颜色开始
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		for (int i=0;i<textIds.length;i++){
			views[i]=(TextView)findViewById(textIds[i]);
		}//循环遍历ID数组，根据ID获取控件，然后将控件赋给TextView数组中的元素
		//创建Handler对象，用于接收消息并处理
		mHandler=new Handler(){
			public void handleMessage(Message msg){//处理消息的方法
				if(msg.what==0x11){//判断消息是否为指定的消息
					//循环设置TextView的背景颜色
					for (int i=0;i<views.length;i++){
						views[i].setBackgroundColor(colors[(i+current)%colors.length]);
					}
					current=(current+1)%colors.length;
					//使开始颜色的序号加1，如果已经是最后一个，则从第一个开始
				}
			}
		};
		Timer timer=new Timer();//创建定时器对象
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				mHandler.sendEmptyMessage(0x11);
			}
		},0,3000);//开启定时任务，每个3000ms发送一次消息
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
