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
	private int[]textIds=new int[]{R.id.text01,R.id.text02,R.id.text03,R.id.text04,R.id.text05};//����һ�����飬���ڴ�������TextView��ID
	private int[]colors=new int[]{Color.RED,Color.MAGENTA,Color.GREEN,Color.YELLOW,Color.BLUE};//����һ�����飬���ڴ���5����ɫ
	//����һ�����飬����Ԫ��ΪTextView,����ĳ�����ǰ����������
	private TextView[]views=new TextView[textIds.length];
	private Handler mHandler;
	private int current=0;//��¼���ĸ���ɫ��ʼ
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		for (int i=0;i<textIds.length;i++){
			views[i]=(TextView)findViewById(textIds[i]);
		}//ѭ������ID���飬����ID��ȡ�ؼ���Ȼ�󽫿ؼ�����TextView�����е�Ԫ��
		//����Handler�������ڽ�����Ϣ������
		mHandler=new Handler(){
			public void handleMessage(Message msg){//������Ϣ�ķ���
				if(msg.what==0x11){//�ж���Ϣ�Ƿ�Ϊָ������Ϣ
					//ѭ������TextView�ı�����ɫ
					for (int i=0;i<views.length;i++){
						views[i].setBackgroundColor(colors[(i+current)%colors.length]);
					}
					current=(current+1)%colors.length;
					//ʹ��ʼ��ɫ����ż�1������Ѿ������һ������ӵ�һ����ʼ
				}
			}
		};
		Timer timer=new Timer();//������ʱ������
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				mHandler.sendEmptyMessage(0x11);
			}
		},0,3000);//������ʱ����ÿ��3000ms����һ����Ϣ
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
