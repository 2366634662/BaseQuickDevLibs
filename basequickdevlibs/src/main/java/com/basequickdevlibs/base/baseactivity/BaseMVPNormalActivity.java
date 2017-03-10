package com.basequickdevlibs.base.baseactivity;

import android.widget.Toast;

import com.basequickdevlibs.base.basepresenter.BasePresenter;
import com.basequickdevlibs.base.baseinterface.IBaseStatusView;
import com.basequickdevlibs.utils.StringUtils;

/**
 * Created by Du_Li on 2017/3/8 20:09.
 * Function:
 * Desc:
 */

public abstract class BaseMVPNormalActivity<V, T extends BasePresenter<V>> extends BaseQuickActivty implements IBaseStatusView {

	protected T mPresenter;

	/**
	 * 关联presenter
	 *
	 * @return
	 */
	protected abstract T initPresenter();

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mPresenter != null) {
			mPresenter.detacheView();
		}
	}

	@Override
	protected void beforeInitView() {
		//创建代理
		mPresenter = initPresenter();
		//创建关联
		mPresenter.attachView((V) this);
	}

	/**
	 * 没网时调用
	 */
	@Override
	public void noNet() {
		if (easyStatusView != null) {
			easyStatusView.noNet();
		}
	}

	@Override
	public void empty() {
		if (easyStatusView != null) {
			easyStatusView.empty();
		}
	}

	@Override
	public void loading() {
		if (easyStatusView != null) {
			easyStatusView.loading();
		}
	}

	@Override
	public void error(String msg) {
		if (easyStatusView != null) {
			easyStatusView.error();
		}
		if (StringUtils.isNotEmpty(msg))
			Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void content() {
		if (easyStatusView != null) {
			easyStatusView.content();
		}
	}
}
