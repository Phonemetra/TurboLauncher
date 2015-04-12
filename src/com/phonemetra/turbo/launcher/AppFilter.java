package com.phonemetra.turbo.launcher;

import android.content.ComponentName;
import android.text.TextUtils;

public abstract class AppFilter {

	public abstract boolean shouldShowApp(ComponentName app);

	public static AppFilter loadByName(String className) {
		if (TextUtils.isEmpty(className))
			return null;

		try {
			Class<?> cls = Class.forName(className);
			return (AppFilter) cls.newInstance();
		} catch (ClassNotFoundException e) {

			return null;
		} catch (InstantiationException e) {

			return null;
		} catch (IllegalAccessException e) {

			return null;
		} catch (ClassCastException e) {

			return null;
		}
	}
}
