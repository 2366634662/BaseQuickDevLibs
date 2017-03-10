package com.basequickdevlibs.base.baseview;

import com.basequickdevlibs.R;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

/**
 * Created by Du_Li on 2017/3/8 15:30.
 * Function:
 * Desc:
 */

public class CustomLoadMoreView extends LoadMoreView {

	@Override
	public int getLayoutId() {
		return R.layout.view_load_more;
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

}
