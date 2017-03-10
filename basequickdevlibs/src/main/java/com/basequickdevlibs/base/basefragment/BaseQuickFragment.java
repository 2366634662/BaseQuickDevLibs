package com.basequickdevlibs.base.basefragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basequickdevlibs.view.EasyStatusView;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Du_Li on 2017/3/8 14:50.
 * Function:
 * Desc:
 */

public abstract class BaseQuickFragment extends Fragment {
	private boolean isVisible = false;
	private boolean isInitView = false;
	private boolean isFirstLoad = true;

	public View convertView;
	private SparseArray<View> mViews;
	private Unbinder mUnbinder;

	public EasyStatusView easyStatusView;

	public Activity mContext;

	public void setEasyStatusView(EasyStatusView easyStatusView) {
		this.easyStatusView = easyStatusView;
	}

	/**
	 * EventBus  default is true
	 */
	public boolean isNeedEventBus() {
		return true;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (convertView == null) {
			convertView = inflater.inflate(getLayoutId(), container, false);
			mUnbinder = ButterKnife.bind(this, convertView);
			mViews = new SparseArray<>();
			mContext = getActivity();
			beforeInitView();
			try {
				if (isNeedEventBus())
					EventBus.getDefault().register(this);
			} catch (Exception e) {

			}
			initView(convertView, savedInstanceState);
			isInitView = true;
			initData();
			lazyLoadData();
		}
		ViewGroup parent = (ViewGroup) convertView.getParent();
		if (parent != null) {
			parent.removeView(convertView);
		}
		return convertView;
	}

	/**
	 * do something  bifore init view
	 */
	protected void beforeInitView() {

	}


	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);

		if (!hidden) {
			isVisible = true;
			lazyLoadData();
		} else {
			isVisible = false;
		}
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		if (isVisibleToUser) {
			isVisible = true;
			lazyLoadData();
		} else {
			isVisible = false;
		}
		super.setUserVisibleHint(isVisibleToUser);
	}

	private void lazyLoadData() {
		if (!isFirstLoad || !isVisible || !isInitView) {
			return;
		}
		getData();
		isFirstLoad = false;
	}

	/**
	 * 加载页面布局ID
	 *
	 * @return
	 */
	protected abstract int getLayoutId();

	/**
	 * 初始化控件
	 */
	protected abstract void initView(View convertView, Bundle savedInstanceState);

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 获取网络数据
	 */
	protected abstract void getData();

	/**
	 * @param viewId
	 * @param <T>
	 * @return
	 */
	protected <T extends View> T findView(int viewId) {
		if (convertView != null) {
			T view = (T) mViews.get(viewId);
			if (view == null) {
				view = (T) convertView.findViewById(viewId);
				mViews.put(viewId, view);
			}
			return view;
		}
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mUnbinder.unbind();
		if (isNeedEventBus())
			EventBus.getDefault().unregister(this);
	}
}
