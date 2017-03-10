package lee.com.basedevlibs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.basequickdevlibs.view.EasyStatusView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MainActivity extends TestBaseActivity<TestView, TestPresenter> implements TestView {


	@BindView(R.id.rv_content)
	RecyclerView rvContent;
	@BindView(R.id.ptr_layout)
	PtrFrameLayout ptrLayout;
	@BindView(R.id.esv_layout)
	EasyStatusView esv_layout;

	private MyAdapter adapter;

	private int refresh = 0;
	private int loadmore = (int) (Math.random() * 100);

	/**
	 * 初始化 presenter
	 *
	 * @return
	 */
	@Override
	protected TestPresenter initPresenter() {
		return new TestPresenter();
	}

	/**
	 * 初始化布局
	 *
	 * @return
	 */
	@Override
	public int getLayout() {
		return R.layout.activity_main;
	}

	/**
	 * 初始化控件
	 *
	 * @param savedInstanceState
	 */
	@Override
	public void initView(Bundle savedInstanceState) {
		setEasyStatusView(esv_layout);
		rvContent.setAdapter(adapter);
		loading();
	}

	/**
	 * 初始化一些数据，，比如获取数据
	 */
	@Override
	public void initDatas() {

	}

	/**
	 * 加载网络数据
	 */
	@Override
	public void loadData() {
		ptrLayout.postDelayed(new Runnable() {
			@Override
			public void run() {
				mPresenter.getData();
				loadmore = (int) (Math.random() * 100);
				Log
						.e("tag", "" + loadmore);
			}
		}, 2000);

	}

	/**
	 * 返回你写的adapter
	 *
	 * @return
	 */
	@Override
	public BaseQuickAdapter getAdapter() {
		adapter = new MyAdapter();
		return adapter;
	}

	/**
	 * 返回recyclerView的布局管理器
	 *
	 * @return
	 */
	@Override
	public RecyclerView.LayoutManager getLayoutManager() {
		return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
	}

	/**
	 * 加载更多
	 */
	@Override
	public void onLoadMoreRequested() {
//		mPresenter.getData();

		ptrLayout.postDelayed(new Runnable() {
			@Override
			public void run() {
				mPresenter.getData();
				loadmore = (int) (Math.random() * 100);
			}
		}, 2000);

	}

	/**
	 * 下拉刷新
	 *
	 * @param frame
	 */
	@Override
	public void onRefreshBegin(PtrFrameLayout frame) {
//		mPresenter.getData();

		frame.postDelayed(new Runnable() {
			@Override
			public void run() {
				mPresenter.getData();

				refresh = (int) (Math.random() * 100);
				adapter.loadMoreComplete();
			}
		}, 2000);

	}

	/**
	 * 网络请求的回调
	 *
	 * @param itemEntity
	 */
	@Override
	public void getItemEntity(List<ItemEntity> itemEntity) {
		if (refresh >= 0 && refresh < 50) {
			ptrLayout.refreshComplete();
			adapter.setNewData(itemEntity);
		} else if (refresh > 50 && refresh < 100) {
			ptrLayout.refreshComplete();
			Toast.makeText(mContext, "刷新失败。。。", Toast.LENGTH_SHORT).show();
		}
		if (loadmore >= 0 && loadmore < 30) {
			adapter.loadMoreEnd();
			adapter.addData(itemEntity);
		} else if (loadmore > 35 && loadmore < 70) {
			adapter.loadMoreComplete();
			adapter.addData(itemEntity);
		} else {
			adapter.loadMoreFail();
		}
	}

}
