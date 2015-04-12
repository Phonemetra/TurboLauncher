/*
* Copyright (C) 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.phonemetra.turbo.launcher;

public class LauncherAppState implements DeviceProfile.DeviceProfileCallbacks {
    private static final String TAG = "LauncherAppState";
    private static final String SHARED_PREFERENCES_KEY = "com.phonemetra.turbo.launcher.prefs";
    
    private final AppFilter mAppFilter;
    private final BuildInfo mBuildInfo;
    private LauncherModel mModel;
    private IconCache mIconCache;
    private WidgetPreviewLoader.CacheDb mWidgetPreviewCacheDb;
    private boolean mIsScreenLarge;
    private float mScreenDensity;
    private int mLongPressTimeout = 300;
    private boolean mWallpaperChangedSinceLastCheck;
    
    private static WeakReference<LauncherProvider> sLauncherProvider;
    private static Context sContext;
    
    private static LauncherAppState INSTANCE;
    
    private DynamicGrid mDynamicGrid;
    
    public static LauncherAppState getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LauncherAppState();
        }
        return INSTANCE;
    }
    
    public static LauncherAppState getInstanceNoCreate() {
        return INSTANCE;
    }
    
    public Context getContext() {
        return sContext;
    }
    
    public static void setApplicationContext(Context context) {
        sContext = context.getApplicationContext();
    }
    
    private LauncherAppState() {
        if (sContext == null) {
            throw new IllegalStateException("LauncherAppState inited before app context set");
        }
        
        mIsScreenLarge = isScreenLarge(sContext.getResources());
        mScreenDensity = sContext.getResources().getDisplayMetrics().density;
        
        recreateWidgetPreviewDb();
        mIconCache = new IconCache(sContext);
        
        mAppFilter = AppFilter.loadByName(sContext.getString(R.string.app_filter_class));
        mBuildInfo = BuildInfo.loadByName(sContext.getString(R.string.build_info_class));
        mModel = new LauncherModel(this, mIconCache, mAppFilter);
        
        // Register intent receivers
        
        // Register for changes to the favorites
        ContentResolver resolver = sContext.getContentResolver();
        resolver.registerContentObserver(LauncherSettings.Favorites.CONTENT_URI, true,
                mFavoritesObserver);
        
    }
    
    public void recreateWidgetPreviewDb() {
        if (mWidgetPreviewCacheDb != null) {
            mWidgetPreviewCacheDb.close();
        }
            mWidgetPreviewCacheDb = new WidgetPreviewLoader.CacheDb(sContext);
    }
    
}    
