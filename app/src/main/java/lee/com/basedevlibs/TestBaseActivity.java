package lee.com.basedevlibs;

import com.basequickdevlibs.base.basepresenter.BasePresenter;
import com.basequickdevlibs.base.baseactivity.BaseMVPRefAndLoadActivity;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

import in.srain.cube.views.ptr.PtrUIHandler;
import lee.com.basedevlibs.lodamoreview.MyCustomLoadMoreView;
import lee.com.basedevlibs.refreshheader.RefreshHeader;

/**
 * Created by Du_Li on 2017/3/9 14:51.
 * Function:
 * Desc:
 */

public abstract class TestBaseActivity<V, T extends BasePresenter<V>> extends BaseMVPRefAndLoadActivity<V, T> {

	@Override
	public PtrUIHandler getRefreshHeader() {
		return new RefreshHeader(this);
	}

	@Override
	public LoadMoreView getLoadMoreViewr() {
		return new MyCustomLoadMoreView(this);
	}

}
