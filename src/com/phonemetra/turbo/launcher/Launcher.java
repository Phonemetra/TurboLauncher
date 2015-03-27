
/*
* Copyright (C) 2008 The Android Open Source Project
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




/**
* Default launcher application.
*/
public class Launcher extends Activity
              implements View.OnClickListener, OnLongClickListener, LauncherModel.Callbacks,
                         View.OnTouchListener, PageSwitchListener, LauncherProviderChangeListener {
          static final String TAG = "Launcher";
          
          private static final int REQUEST_CREATE_SHORTCUT = 1;
          private static final int REQUEST_CREATE_APPWIDGET = 5;
          private static final int REQUEST_PICK_SHORTCUT = 7;
          private static final int REQUEST_PICK_APPWIDGET = 9;
          private static final int REQUEST_PICK_WALLPAPER = 10;

          private static final int REQUEST_BIND_APPWIDGET = 11;
          private static final int REQUEST_RECONFIGURE_APPWIDGET = 12;
          
          public static final int REQUEST_TRANSITION_EFFECTS = 14;
          
          /**
           * IntentStarter uses request codes starting with this. This must be greater than all activity
           * request codes used internally.
           */
           protected static final int REQUEST_LAST = 100;
           
           static final String EXTRA_SHORTCUT_DUPLICATE = "duplicate";
           
           static final int SCREEN_COUNT = 5;
           static final int DEFAULT_SCREEN = 2;
           
           private static final String PREFERENCES = "launcher.preferences";
