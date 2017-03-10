package lee.com.basedevlibs;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Du_Li on 2017/3/5 21:23.
 * Function:
 * Desc:
 */

public class MyAdapter extends BaseQuickAdapter<ItemEntity, BaseViewHolder> {
	public MyAdapter() {
		super(R.layout.item_content);
	}

	@Override
	protected void convert(BaseViewHolder helper, ItemEntity item) {

		helper.setText(R.id.tv_name, item.name);
		helper.setText(R.id.tv_age, item.age);

	}
}
