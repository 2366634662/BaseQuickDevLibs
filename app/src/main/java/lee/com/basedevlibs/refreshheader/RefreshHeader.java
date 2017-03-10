package lee.com.basedevlibs.refreshheader;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import lee.com.basedevlibs.R;


/**
 * Created by Administrator on 2016/11/14.
 */

public class RefreshHeader extends LinearLayout implements PtrUIHandler {
	private View mContainer;
	private AnimationDrawable animationDrawable;
	private ImageView imageView;
	
	public RefreshHeader(Context context) {
		super(context);
		initView(context);
	}
	
	public RefreshHeader(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
	
	private void initView(Context context) {
		mContainer = View.inflate(context, R.layout.rabbits_lv_header, null);
		addView(mContainer);
		setGravity(Gravity.CENTER);
		imageView = (ImageView) mContainer.findViewById(R.id.headview);
		imageView.setImageResource(R.drawable.pull_to_refresh_header);
		animationDrawable = (AnimationDrawable) imageView.getDrawable();
	}
	
	public void setBag(String color) {
		setBackgroundColor(Color.parseColor(color));
		mContainer.setBackgroundColor(Color.parseColor(color));
	}
	
	public void setBag(int color) {
		setBackgroundColor(color);
		mContainer.setBackgroundColor(color);
	}
	
	@Override
	public void onUIReset(PtrFrameLayout frame) {
//		setVisiableHeight(250);
		animationDrawable.start();
	}
	
	@Override
	public void onUIRefreshPrepare(PtrFrameLayout frame) {
		animationDrawable.start();
	}
	
	@Override
	public void onUIRefreshBegin(PtrFrameLayout frame) {
		animationDrawable.start();
	}
	
	@Override
	public void onUIRefreshComplete(PtrFrameLayout frame) {
		animationDrawable.stop();
	}
	
	@Override
	public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
		
	}
}
