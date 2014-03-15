package com.example.clock;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;

public class ClockView extends View {
	// dimension of the clock is relative to the screen
	private int dimClock = Math.min( ClockActivity.xCenter*3/4,ClockActivity.yCenter*3/4); 
	// dimension of the hands are relative to the dimension of the clock
	private int dimMon = dimClock/4;   
	private Bitmap mMonkey;
	private Bitmap mClock;
	// the clock is centered in the middle of the screen (width)
	private float centerx = ClockActivity.xCenter/2;
	// and a 3 quarters to the bottom (height) 
	private float centery = ClockActivity.yCenter/4;
	// matrix - for hand, matrix2 - for clock
	Matrix matrix = new Matrix();
	Matrix matrix2 = new Matrix();
	
	public ClockView(Context context) {
		super(context);
		setBackgroundResource(R.drawable.background);
		matrix.reset();  
		matrix.preTranslate(centerx-dimMon/10, centery-dimMon/10);
		matrix2.reset();
		matrix2.preTranslate(centerx-dimClock/2, centery-dimClock/2);
		mClock =  makeScaled(BitmapFactory.decodeResource(context.getResources(), R.drawable.clock), dimClock,dimClock);
		mMonkey = makeScaled(BitmapFactory.decodeResource(context.getResources(), R.drawable.bar), dimMon,dimMon);
	}

	// keep the proportions of the screen, without changing the proportion of the clock
	private Bitmap makeScaled(Bitmap base, float height, float width) {
		float scale1 = width / base.getWidth();
		float scale2 = height / base.getHeight();
		float scale = Math.min(scale1, scale2);
		return Bitmap.createScaledBitmap(base, (int)(base.getWidth()*scale), (int)(base.getHeight()*scale), false);
	}

	public void onDraw(Canvas canvas) {  
			canvas.drawBitmap(mClock, matrix2 , null);
			canvas.drawBitmap(mMonkey, matrix, null);
	}
   
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		// calculate the angle between the center of the clock and the finger point
		float ung2 = (float) (Math.atan2(centerx-x, y-centery) * 180 / Math.PI);
		matrix.reset();
		matrix.preTranslate(centerx-dimMon/10, centery-dimMon/10);
		matrix.preRotate(ung2);
		invalidate();
		return true;
	}
}
