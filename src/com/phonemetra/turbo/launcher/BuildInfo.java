package com.phonemetra.turbo.launcher;

import android.text.TextUtils;

public class BuildInfo {
	
	public static BuildInfo loadByName(String className) {
		if (TextUtils.isEmpty(className))
			return new BuildInfo();

		try {
			Class<?> cls = Class.forName(className);
			return (BuildInfo) cls.newInstance();
		} catch (ClassNotFoundException e) {

		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		} catch (ClassCastException e) {

		}
		return new BuildInfo();
	}
}
