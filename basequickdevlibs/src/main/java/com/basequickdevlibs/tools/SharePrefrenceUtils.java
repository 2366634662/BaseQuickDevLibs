package com.basequickdevlibs.tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.basequickdevlibs.utils.StringUtils;

/**
 * Created by Du_Li on 2016/12/17.
 */

public class SharePrefrenceUtils {
	private SharedPreferences sharedPreferences;

	private static final String SP_NAME = "share_file";

	protected SharePrefrenceUtils(Context context, String sp_name) {
		sharedPreferences = context.getSharedPreferences(StringUtils.isEmpty(sp_name) ? SP_NAME : sp_name, Context.MODE_PRIVATE);//Context.MODE_MULTI_PROCESS支持跨进程访问
	}

	protected SharePrefrenceUtils(Context context) {
		sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);//Context.MODE_MULTI_PROCESS支持跨进程访问
	}

	protected void putString(String key, String value) {

		sharedPreferences.edit().putString(key, value).commit();
	}

	protected String getString(String key, String defaultVaule) {
		return sharedPreferences.getString(key, defaultVaule);
	}

	protected String getString(String key) {
		return sharedPreferences.getString(key, "");
	}

	protected void setBoolean(String key, boolean vaule) {
		sharedPreferences.edit().putBoolean(key, vaule).commit();
	}

	protected boolean getBoolean(String key, boolean defaultVaule) {
		return sharedPreferences.getBoolean(key, defaultVaule);
	}

	/**
	 * 清空sharePrefrence文件
	 */
	public void clear() {
		sharedPreferences.edit().clear();
	}

}
