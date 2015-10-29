package com.cc.ma.welcome;

import com.cc.ma.MainActivity;
import com.cc.welcome.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity{
	
	private final String SP_NAME = "splash";
	private static final int GOTO_MAIN = 1000;
	private static final int GOTO_GUIDE = 1001;
	
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case GOTO_GUIDE:
			{
				goGuide();
			}
				break;
			case GOTO_MAIN:
			{
				goHome();
			}
				break;
			default:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		init();
	}
	
	private void init(){
		// 读取SharedPreferences中需要的数据
		// 使用SharedPreferences来记录程序的使用次数
		SharedPreferences sp=  getSharedPreferences(SP_NAME, MODE_PRIVATE);
		boolean bFirstInstall = sp.getBoolean("First", true);
		if(!bFirstInstall){
			
			mHandler.sendEmptyMessage(GOTO_MAIN);
		}else{
			mHandler.sendEmptyMessage(GOTO_GUIDE);
		}
	}
	private void goHome() {
		Intent intent = new Intent(SplashActivity.this, MainActivity.class);
		SplashActivity.this.startActivity(intent);
		SplashActivity.this.finish();
    }
		
    private void goGuide() {
        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }
}
