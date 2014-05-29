package com.epicport.glue.ui;

import android.app.Activity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.epicport.glue.GameMode;
import com.epicport.glue.GameModeChangeListener;
import com.epicport.glue.billing.BillingButton;
import com.epicport.glue.billing.BillingThread;

public class ControlBar extends LinearLayout implements GameModeChangeListener {

	private static ControlBar instance = null;

	public static void createFor(FrameLayout layout, Activity activity,
			BillingThread billingThread) {
		instance = new ControlBar(activity, billingThread);
		layout.addView(instance);
	}

	private final Activity activity;
	private final BillingButton billingButton;
	
	private ControlBar(Activity activity, BillingThread billingThread) {
		super(activity);
		this.activity = activity;
		this.billingButton = new BillingButton(activity, billingThread);
		
		LayoutParams layoutParams = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.LEFT | Gravity.TOP);

		setLayoutParams(layoutParams);
		addView(billingButton);

		GameMode.setGameModeChangeListener(this);
	}

	public static boolean dispatch(MotionEvent ev) {
		if (instance == null) {
			return false;
		}

		if (instance.billingButton.dispatch(ev)) {
			return true;
		}
		
		return false;
	}

	@Override
	public void onGameModeChanged(final int gameMode) {
		billingButton.onGameModeChanged(gameMode);
		
		activity.runOnUiThread(new Runnable() {
		 @Override
			public void run() {
				if (gameMode == GameMode.GM_IN_GAME) {
					setVisibility(View.VISIBLE);
				} else {
					setVisibility(View.GONE);
				}						
			}
		});
	}

}
