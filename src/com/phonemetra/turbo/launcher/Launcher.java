
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
           
           // Type: int
           private static final String RUNTIME_STATE_CURRENT_SCREEN = "launcher.current_screen";
           // Type: int
           private static final String RUNTIME_STATE = "launcher.state";
           // Type: int
           private static final String RUNTIME_STATE_PENDING_ADD_CONTAINER = "launcher.add_container";
           // Type: int
           private static final String RUNTIME_STATE_PENDING_ADD_SCREEN = "launcher.add_screen";
           // Type: int
           private static final String RUNTIME_STATE_PENDING_ADD_CELL_X = "launcher.add_cell_x";
           // Type: int
           private static final String RUNTIME_STATE_PENDING_ADD_CELL_Y = "launcher.add_cell_y";
           // Type: boolean
           private static final String RUNTIME_STATE_PENDING_FOLDER_RENAME = "launcher.rename_folder";
           // Type: long
           private static final String RUNTIME_STATE_PENDING_FOLDER_RENAME_ID = "launcher.rename_folder_id";
           // Type: int
           private static final String RUNTIME_STATE_PENDING_ADD_SPAN_X = "launcher.add_span_x";
           // Type: int
           private static final String RUNTIME_STATE_PENDING_ADD_SPAN_Y = "launcher.add_span_y";
           // Type: parcelable
           private static final String RUNTIME_STATE_PENDING_ADD_WIDGET_INFO = "launcher.add_widget_info";
           
