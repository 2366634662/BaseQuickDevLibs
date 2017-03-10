package lee.com.basedevlibs.lodamoreview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

import lee.com.basedevlibs.R;

/**
 * Created by Du_Li on 2017/3/5 21:49.
 * Function:
 * Desc:
 */

public final class MyCustomLoadMoreView extends LoadMoreView {

	View view;
	private FrameLayout flayout_loadmore;
	LoadingIndicatorView loadingIndicatorView;

	public MyCustomLoadMoreView(Context context) {
		view = View.inflate(context, getLayoutId(), null);
		flayout_loadmore = (FrameLayout) view.findViewById(R.id.flayout_loadmore);
		loadingIndicatorView = (LoadingIndicatorView) view.findViewById(R.id.load_more_loading_view);
	}
	
	@Override
	public int getLayoutId() {
		return R.layout.custom_load;
	}
	
	@Override
	protected int getLoadingViewId() {
		return R.id.load_more_loading_view;
	}
	
	@Override
	protected int getLoadFailViewId() {
		return R.id.load_more_load_fail_view;
	}
	
	@Override
	protected int getLoadEndViewId() {
		return R.id.load_more_load_end_view;
	}
	
	
	public void setBackGround(int color) {
		Log.e("tag", "设置背景了1");
		flayout_loadmore.setBackgroundColor(color);
		loadingIndicatorView.setBackgroundColor(color);
	}
	
	public void setBackGround(String color) {
		Log.e("tag", "设置背景了2");
		flayout_loadmore.setBackgroundColor(Color.parseColor(color));
		loadingIndicatorView.setBackgroundColor(Color.parseColor(color));
	}
	
}
