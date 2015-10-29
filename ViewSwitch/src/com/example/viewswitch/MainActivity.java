package com.example.viewswitch;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;


public class MainActivity extends Activity {
	private ViewSwitcher switcher;
	private float touchDownX , touchUpX;
	private LayoutInflater inflater;
	private int count = 0;
//	private List<DataItem> dataItems = new ArrayList<DataItem>();
//	private int currentScreenNo = -1;// 当前屏数
//	private int screenCount = 0;// 总屏数
//	private final int perScreenCount = 12;// 每一屏显示数量

//	private BaseAdapter adapter = new GridViewAdapter();

//	int[] images = { R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inflater = LayoutInflater.from(this);

		switcher = (ViewSwitcher) findViewById(R.id.switchview);
		// 设置每一屏显示的视图，这里设置为GridView
		switcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				return inflater.inflate(R.layout.switchiew, null);
			}
		});
		
		switcher.setOnTouchListener(new OnTouchListener()
		{
			public boolean onTouch(View arg0, MotionEvent arg1) 
			{
				if (arg1.getAction() == MotionEvent.ACTION_DOWN)
				{
					touchDownX = arg1.getX();
					return true;
				}
				else if(arg1.getAction() == MotionEvent.ACTION_UP)
				{
					touchUpX = arg1.getX();
					if (touchDownX - touchUpX > 100)
					{
						prev();
					}
					else
					{
						next();
					}
					return true;
				}
				return false;
			}
		});

		next();// 页面加载时显示第一页
	}

	// 下一屏
	public void next() {
		// 设置视图切换动画
		switcher.setInAnimation(this, R.anim.slide_in_right);// 自定义动画效果
		switcher.setOutAnimation(this, R.anim.slide_out_left);// 自定义动画效果
		count++;
		LinearLayout l = ((LinearLayout) switcher.getNextView());
		TextView text = (TextView)l.findViewById(R.id.text);
		text.setText("第"+count+"个");
		switcher.showNext();
	}

	// 上一屏
	public void prev() {
		// 设置视图切换动画
		switcher.setInAnimation(this, android.R.anim.slide_in_left);// android系统自带的动画效果
		switcher.setOutAnimation(this, android.R.anim.slide_out_right);// android系统自带的动画效果
		count--;
		LinearLayout l = ((LinearLayout) switcher.getNextView());
		TextView text = (TextView)l.findViewById(R.id.text);
		text.setText("第"+count+"个");
		switcher.showNext();
	}
//
//	static class DataItem {
//		String lable;
//		Drawable drawable;
//	}

//	class GridViewAdapter extends BaseAdapter {
//
//		/**
//		 * 当前屏的数量
//		 */
//		@Override
//		public int getCount() {
//			if (currentScreenNo < screenCount - 1) {
//				return perScreenCount;
//			}
//			return dataItems.size() - (screenCount - 1) * perScreenCount;
//		}
//
//		/**
//		 * 数据
//		 */
//		@Override
//		public Object getItem(int position) {
//			int index = currentScreenNo * perScreenCount + position;
//			return dataItems.get(index);
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return position;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			if (convertView == null) {
//				convertView = inflater.inflate(R.layout.lable_icon, null);
//			}
//			ImageView imageView = (ImageView) convertView
//					.findViewById(R.id.imageView1);
//			DataItem dataItem = (DataItem) getItem(position);
//			imageView.setImageDrawable(dataItem.drawable);
//			TextView textView = (TextView) convertView
//					.findViewById(R.id.textView1);
//			textView.setText(dataItem.lable);
//			return convertView;
//		}
//
//	}

}
