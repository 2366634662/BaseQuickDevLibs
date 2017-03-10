package com.basequickdevlibs.base.basedelegate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;

/**
 * Created by Du_Li on 2017/3/8 15:06.
 * Function:
 * Desc:
 */

public class UpdateDataDelegate {
	private PtrFrameLayout mPtrLayout;
	private RecyclerView mRecyclerView;
	private View mRootView;

	public UpdateDataDelegate(View rootView) {
		mRootView = rootView;
	}

	//初始化RecyclerView控件
	public void initLoad(BaseQuickAdapter.RequestLoadMoreListener listener,
	                     BaseQuickAdapter adapter, RecyclerView.LayoutManager manager, LoadMoreView loadMoreView) {
		getRecyclerView(mRootView);

		if (null != manager && null != mRecyclerView)
			mRecyclerView.setLayoutManager(manager);

		setAdapter(listener, adapter, loadMoreView);
	}

	//初始化PrtFrameLayout控件
	public void initPTR(PtrHandler ptrHandler, PtrUIHandler header) {
		getPtrLayout(mRootView);
		if (null != mPtrLayout) {
			mPtrLayout.setPtrHandler(ptrHandler);
			//解决与viewpager横向滑动冲突
			mPtrLayout.disableWhenHorizontalMove(true);
			/**
			 * 如果直接使用PtrFrameLayout的时候就需要在代码中去配置刷新头，
			 * 但是建议直接继承PtrFrameLayout，然后根据项目修改刷新头，类似PtrClassicFrameLayout的做法
			 */
			mPtrLayout.setHeaderView((View) header);
			mPtrLayout.addPtrUIHandler(header);
		}
	}

	//根据当前Fragment布局获取PtrFrameLayout对象
	private void getPtrLayout(View rootView) {
		if (isPTR(rootView) && mPtrLayout == null) {
			mPtrLayout = (PtrFrameLayout) rootView;
		} else if (rootView instanceof ViewGroup) {
			ViewGroup contentView = (ViewGroup) rootView;
			int childCount = contentView.getChildCount();
			for (int i = 0; i < childCount; i++) {
				View childView = contentView.getChildAt(i);
				getPtrLayout(childView);
			}
		}
	}

	//根据当前Fragment布局获取RecyclerView对象
	private void getRecyclerView(View rootView) {
		if (isRV(rootView) && mRecyclerView == null) {
			mRecyclerView = (RecyclerView) rootView;
		} else if (rootView instanceof ViewGroup) {
			ViewGroup contentView = (ViewGroup) rootView;
			int childCount = contentView.getChildCount();
			for (int i = 0; i < childCount; i++) {
				View childView = contentView.getChildAt(i);
				getRecyclerView(childView);
			}
		}
	}

	//设置RecyclerView适配器
	private void setAdapter(BaseQuickAdapter.RequestLoadMoreListener listener, BaseQuickAdapter adapter, LoadMoreView loadMoreView) {
		if (null != adapter && null != mRecyclerView) {
			adapter.setLoadMoreView(loadMoreView);
			adapter.setOnLoadMoreListener(listener);
			mRecyclerView.setAdapter(adapter);
		}
	}

	//判断布局是否是PtrFrameLayout
	private boolean isPTR(View view) {
		return view instanceof PtrFrameLayout;
	}

	//判断布局是否是RecyclerView
	private boolean isRV(View view) {
		return view instanceof RecyclerView;
	}
}
