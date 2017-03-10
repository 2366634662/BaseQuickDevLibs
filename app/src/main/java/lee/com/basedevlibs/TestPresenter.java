package lee.com.basedevlibs;

import com.basequickdevlibs.base.basepresenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Du_Li on 2017/3/8 20:13.
 * Function:
 * Desc:
 */

public class TestPresenter extends BasePresenter<TestView> {
	private List<ItemEntity> list = new ArrayList<>();

	public void getData() {
		if (getView() != null) {
			getView().loading();
		}
		for (int i = 0; i < 30; i++) {
			list.add(new ItemEntity("名字 " + i, "年龄 " + i));
		}
		getView().content();
		getView().getItemEntity(list);
//		new Timer().schedule(new TimerTask() {
//			@Override
//			public void run() {
//
//			}
//		}, 2000);


	}

}
