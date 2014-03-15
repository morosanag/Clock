package com.example.clock;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class ClockActivity extends Activity {
	static DisplayMetrics metrics;
	static int xCenter;
	static int yCenter;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_clock);

		Display  display= ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		xCenter = display.getWidth(); // get the Width of the screen
		yCenter = display.getHeight(); // get the Height of the screen

		LinearLayout top = (LinearLayout) findViewById(R.id.topcontainer);

		final ClockView monkey = new ClockView(this);
		monkey.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		top.addView(monkey);
	}
}
