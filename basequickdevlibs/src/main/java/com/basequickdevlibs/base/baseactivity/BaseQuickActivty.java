package com.basequickdevlibs.base.baseactivity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.basequickdevlibs.base.baseinterface.IPermissionsLinstener;
import com.basequickdevlibs.tools.AppManager;
import com.basequickdevlibs.view.EasyStatusView;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Du_Li on 2017/3/8 14:18.
 * Function:
 * Desc:
 */

public abstract class BaseQuickActivty extends AppCompatActivity {
	/**
	 * get activity layout
	 */
	@LayoutRes
	public abstract int getLayout();

	/**
	 * init activity view
	 *
	 * @param savedInstanceState
	 */
	public abstract void initView(Bundle savedInstanceState);

	/**
	 * init data
	 */
	public abstract void initDatas();

	/*
	 * load data in onResume
	 */
	public abstract void loadData();

	/**
	 * Context
	 */
	protected Activity mContext;
	/**
	 * load data once
	 */
	public boolean mIsFirstShow = true;
	/**
	 * Binder  or  UnBinder  ButterKnife
	 */
	private Unbinder mUnbinder;

	/**
	 * EventBus  default is true
	 */
	public boolean isNeedEventBus() {
		return true;
	}

	/**
	 * @param easyStatusView MutiType page
	 */
	public void setEasyStatusView(EasyStatusView easyStatusView) {
		this.easyStatusView = easyStatusView;
	}

	public EasyStatusView easyStatusView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		AppManager.getInstance().addActivity(this);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(getLayout());
		mUnbinder = ButterKnife.bind(this);
		try {
			if (isNeedEventBus())
				EventBus.getDefault().register(this);
		} catch (Exception e) {

		}
		beforeInitView();
		initView(savedInstanceState);
		initDatas();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		/**
		 * remove this when activity destory
		 */
		AppManager.getInstance().remove(this);
		if (isNeedEventBus())
			EventBus.getDefault().unregister(this);
		mUnbinder.unbind();
	}

	@Override
	protected void onResume() {
		if (mIsFirstShow) {
			mIsFirstShow = false;
			loadData();
		}
		super.onResume();
	}

	/**
	 * init some other bifore init view
	 */
	protected void beforeInitView() {

	}

	/**
	 * Request  Presmision
	 */

	public static final int REQUEST_PERMISSION_CODE = 0x000001;
	private static IPermissionsLinstener mPermissionsLinstener;

	public static void requestPresmision(IPermissionsLinstener permissionsLinstener, String... permission) {
		mPermissionsLinstener = permissionsLinstener;
		if (permissionsLinstener == null) {
			return;
		}
		List<String> permissionList = new ArrayList<>();
		for (String p : permission) {
			if (ContextCompat.checkSelfPermission(AppManager.getInstance().getTopActivity(), p) != PackageManager.PERMISSION_GRANTED) {
				permissionList.add(p);
			}
		}
		if (!permissionList.isEmpty()) {
			ActivityCompat.requestPermissions(AppManager.getInstance().getTopActivity(), permissionList.toArray(new String[permissionList.size()]), REQUEST_PERMISSION_CODE);
		} else {
			mPermissionsLinstener.permissionSuccess();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
			case REQUEST_PERMISSION_CODE:
				if (grantResults.length > 0) {
					List<String> deniedPermissionsList = new ArrayList<>();
					for (int i = 0; i < grantResults.length; i++) {
						int grantResult = grantResults[i];
						String deniedPer = permissions[i];
						if (grantResult != PackageManager.PERMISSION_GRANTED) {
							deniedPermissionsList.add(deniedPer);
						}
						if (deniedPermissionsList.isEmpty()) {
							mPermissionsLinstener.permissionSuccess();
						} else {
							mPermissionsLinstener.permissionDenied(deniedPermissionsList);
						}
					}
				}
				break;
		}
	}

	/**
	 * show or dismiss  keyBoard
	 *
	 * @param isShow
	 */

	public static void showKeyboard(boolean isShow) {
		InputMethodManager imm = (InputMethodManager) AppManager.getInstance().currentActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		if (isShow) {
			if (AppManager.getInstance().currentActivity().getCurrentFocus() == null) {
				imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
			} else {
				imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
			}
		} else {
			if (AppManager.getInstance().currentActivity().getCurrentFocus() != null) {
				imm.hideSoftInputFromWindow(AppManager.getInstance().currentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			} else {
				imm.hideSoftInputFromWindow(AppManager.getInstance().currentActivity().getCurrentFocus().getWindowToken(), 0);
			}
		}
	}
}
