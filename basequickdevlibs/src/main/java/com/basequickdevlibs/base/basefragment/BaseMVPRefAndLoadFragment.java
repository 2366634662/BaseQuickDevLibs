package com.basequickdevlibs.base.basefragment;

import android.view.View;
import android.widget.Toast;

import com.basequickdevlibs.base.basedelegate.UpdateDataDelegate;
import com.basequickdevlibs.base.baseinterface.IBaseStatusView;
import com.basequickdevlibs.base.baseinterface.IUpdateDataView;
import com.basequickdevlibs.base.basepresenter.BasePresenter;
import com.basequickdevlibs.base.baseview.CustomLoadMoreView;
import com.basequickdevlibs.utils.StringUtils;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;

/**
 * Created by Du_Li on 2017/3/8 14:19.
 * Function:
 * Desc:
 */

public abstract class BaseMVPRefAndLoadFragment<V, T extends BasePresenter<V>> extends BaseQuickFragment implements IUpdateDataView, IBaseStatusView {
	/**
	 * refresh and load more delegate
	 */
	private UpdateDataDelegate mDelegate;
	/**
	 * to attach view
	 */
	protected T mPresenter;

	/**
	 * contact presenter
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
		/**
		 * create delegate
		 */
		mPresenter = initPresenter();
		/**
		 * create contact
		 */
		mPresenter.attachView((V) this);
		/**
		 * init adapter and refresh
		 */
		mDelegate = new UpdateDataDelegate(convertView);
		mDelegate.initPTR(this, getRefreshHeader());
		mDelegate.initLoad(this, getAdapter(), getLayoutManager(), getLoadMoreViewr());
	}

	/**
	 * check this activity can do refresh
	 *
	 * @param frame
	 * @param content
	 * @param header
	 * @return true can refresh
	 */
	@Override
	public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
		return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
	}

	/**
	 * Obtain the drop-down refresh style, if you don't rewrite the method subclass, use the default style
	 *
	 * @return
	 */
	@Override
	public PtrUIHandler getRefreshHeader() {
		return new PtrClassicDefaultHeader(mContext);
	}

	/**
	 * Get loaded more variety, if you don't rewrite the method subclass, use the default style
	 *
	 * @return
	 */
	@Override
	public LoadMoreView getLoadMoreViewr() {
		return new CustomLoadMoreView();
	}

	/**
	 * Call when there is no network
	 */
	@Override
	public void noNet() {
		if (easyStatusView != null) {
			easyStatusView.noNet();
		}
	}

	/**
	 * Call when there is no data
	 */
	@Override
	public void empty() {
		if (easyStatusView != null) {
			easyStatusView.empty();
		}
	}

	/**
	 * Call when there is loading
	 */
	@Override
	public void loading() {
		if (easyStatusView != null) {
			easyStatusView.loading();
		}
	}

	/**
	 * Call when there is load error
	 *
	 * @param msg tip
	 */
	@Override
	public void error(String msg) {
		if (easyStatusView != null) {
			easyStatusView.error();
		}
		if (StringUtils.isNotEmpty(msg))
			Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();


	}

	/**
	 * show the content when load success
	 */
	@Override
	public void content() {
		if (easyStatusView != null) {
			easyStatusView.content();
		}
	}
}
