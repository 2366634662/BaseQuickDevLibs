package com.basequickdevlibs.base.baseinterface;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;

/**
 * Created by Du_Li on 2017/3/8 15:07.
 * Function:
 * Desc:
 */

public interface IUpdateDataView extends BaseQuickAdapter.RequestLoadMoreListener, PtrHandler {
	/**
	 * 使用BaseRecyclerViewAdapterHelper作为上拉加载的实现方式
	 * 如果使用ListView或GridView等需要自己去实现上拉加载更多的逻辑
	 *
	 * @return BaseRecyclerViewAdapterHelper的实现类
	 */
	BaseQuickAdapter getAdapter();

	/**
	 * 获取RecyclerView的布局管理器对象，根据自己业务实际情况返回
	 *
	 * @return RecyclerView的布局管理器对象
	 */
	RecyclerView.LayoutManager getLayoutManager();

	/**
	 * 获取下拉刷新头布局
	 *
	 * @return 下拉刷新头
	 */
	PtrUIHandler getRefreshHeader();

	/**
	 * 获取加载更多布局
	 *
	 * @return
	 */
	LoadMoreView getLoadMoreViewr();
}
