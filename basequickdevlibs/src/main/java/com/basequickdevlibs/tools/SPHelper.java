package com.basequickdevlibs.tools;

import android.content.Context;

/**
 * Created by Du_Li on 2016/12/17.
 */

public class SPHelper extends SharePrefrenceUtils {
	private static volatile SPHelper sharePrefreceHelper;

	private SPHelper(Context context) {
		super(context);
	}

	public static SPHelper getInstence(Context context) {
		if (sharePrefreceHelper == null)
			synchronized (SPHelper.class) {
				if (sharePrefreceHelper == null)
					sharePrefreceHelper = new SPHelper(context);
			}
		return sharePrefreceHelper;
	}

}
