# JC-Android Core Module
This core module has been created to accelerate the development process by reducing the replication and git rid of the boilerplate code and also help JC-Android developers to maximize the shared code between the apps

[![Platform](https://img.shields.io/badge/platform-Android-inactive.svg?style=flat)](https://github.com/Tap-Payments/TapQRCode-Android.git)
[![SDK Version](https://img.shields.io/badge/minSdkVersion-21-blue.svg)](https://stuff.mit.edu/afs/sipb/project/android/docs/reference/packages.html)
[![SDK Version](https://img.shields.io/badge/targetSdkVersion-29-informational.svg)](https://stuff.mit.edu/afs/sipb/project/android/docs/reference/packages.html)

# Table of Contents
---

1. [Requirements](#requirements)
2. [Features](#features)
   1. [Components](#components)
      1. [JCBottomSheet](#JCBottomSheet)
      2. [JCTextView](#JCTextView)
      3. [JCEditText](#JCEditText)
      4. [JCToolBar](#JCToolBar)
      5. [JCSwitchButton](#JCSwitchButton)
      6. [JCButton](#JCButton)
      7. [JCRecycleView](#JCRecycleView)
      8. [CustomBottomSheet](#CustomBottomSheet)
      
   2. [Base Classes](#base)
      1. [BaseViewModel](#BaseViewModel)
      1. [BaseActivity](#BaseActivity)
      1. [BaseDialog](#BaseDialog)
      1. [BaseFragment](#BaseFragment)
      1. [BaseApplication and Koin Integration included](#BaseApplication)
   
   
<a name="requirements"></a>
## Requirements

To use the SDK the following requirements must be met:

1. **Android Studio 3.6 or newer**
2. **Android SDK Tools 29.0.0 or newer**
3. **Android Platform Version: API 29: Android 10.0 (Q)**
4. **Android targetSdkVersion: 29**


<a name="features"></a>
## Features

------

`justclean-android-core` this core module has some custom components and BaseClasses you can use it with all features.

<a name="components"></a>
### Components


<a name="JCBottomSheet"></a>
#### 1. JCBottomSheet
 ```xml
   <com.justclean.core.custom.JCBottomSheet
       android:id="@+id/jcSheet"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       app:jc_behavior_peekHeight="100dp"
       app:jc_sheet_max_height="300dp"
       app:jc_sheet_min_height="200dp"
       app:jc_sheet_style="primary" />
```

Property Name            |   Accepted Value    |
------------------------ | ------------------- |
jc_behavior_peekHeight   | dimension resource  |
jc_sheet_min_height      | dimension resource  |
jc_sheet_max_height      | dimension resource  |
jc_with_close_icon       | boolean  |
jc_with_dash_icon        | boolean  |
jc_close_icon            | drawable |
jc_dash_icon             | drawable |
jc_with_title            | boolean  |
jc_set_sheet_title       | dimension resource  |
jc_sheet_style           | primary,secondary   |

 ```kotlin
   jcSheet.withCloseIcon(View.VISIBLE)
   jcSheet.setCloseIcon(R.drawable.close)
   jcSheet.withSheetTitle(true, "JustClean Sheet")
   jcSheet.withDashIcon(View.VISIBLE)
   jcSheet.setDashIcon(R.drawable.dashIcon)
   jcSheet.setListAdapter(ITEMS)
   jcSheet.setEventsHokes(testClickOne, testClickTwo)
   jcSheet.setMaxSheetHeight(800)
   jcSheet.setPeekHeight(300)
   jcSheet.setMinHeight(400)
   jcSheet.setSheetState(BottomSheetBehavior.STATE_EXPANDED)
   jcSheet.setSheetStyle(JCBottomSheet.SheetStyle.primary)
```
---

<a name="JCTextView"></a>
#### 2. JCTextView
 ```xml
   <com.justclean.core.custom.JCTextView
       android:id="@+id/txtGoToSheet"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       app:jc_spannable_start="8"
       app:jc_spannable_end="12"
       app:jc_spannable_color="@color/success_green"
       app:jc_spannable_style="bold" />
```
```kotlin
   setJCTextTheme(theme)
   setJcTextBackground(color)
   setJcTextBackgroundTint(color)
   setJcTextEndDrawable(drawable)
   setJcTextStartDrawable(drawable)
   setJcTextElevation(elevation: Float = 0f)
   setJcTextTypeFace(type: Int = 0)
   setJcTextMaxLines(max: Int = 0)
   setJcRotationX(rotation: Int = 0)
   setJCTextShadow(
                  txtShadowDx: Int = 0,
                  txtShadowDy: Int = 0,
                  txtShadowColor: Int = 0,
                  txtShadowRadius: Int = 0)
   setJcText(txt: String)
   setJcTextColor(color: Int = 0)
   setJcTextSize(size: Int)
   setJcTextPadding(
                    jcPadding: Int = 0,
                    jcPaddingStart: Int = 0,
                    jcPaddingEnd: Int = 0,
                    jcPaddingTop: Int = 0,
                    jcPaddingBottom: Int = 0)
   setJcTextGravity(gravity)
   setJcTextDirection(direction: JcTextDirections? = null)
   setTxtAlignment(alignment: Int = 0)
```
---

<a name="JCEditText"></a>
#### 3. JCEditText
 ```xml
   <com.justclean.core.custom.JCEditText
     android:id="@+id/jcTextInput"
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     app:jc_editText_theme="@style/Custom"
     app:jc_end_icon_drawable="@drawable/ic_create_black_24dp"
     app:jc_end_icon_mode="clear_text"
     app:jc_end_icon_tint="@color/body_dark"
     app:jc_input_hint_color="@color/error_red"
     app:jc_input_hint_text="@string/app_name"
     app:jc_input_style="@style/InputLayoutTheme"
     app:jc_input_text_color="@color/success_green"
     app:jc_input_type="textEmailAddress"
     app:jc_max_chars="9"
     app:jc_min_chars="8"
     app:jc_text_cursor_drawable="@drawable/cursor"
     app:jc_text_size="@dimen/x_sm_text_size" />
```
```kotlin
   setTextWatcher()
   getError()
   setInputStyle(style: Int)
   setHintColor(color: Int)
   setTextInputColor(color: Int)
   setEditTextTheme(editTextTheme: Int)
   setJcTextSize(size: Int)
   setEndIconDrawable(drawable: Int)
   setTextCursorDrawable(textCursorDrawable: Int)#
   setInputHintText(inputHintText: Int)
   setInputHintText(inputHintText: String)
   setInputType(value: String)
   setEndIconTint(endIconTint: Int)
   setMinChar(min: Int, minErrorMsg: String)
   setMaxChar(max: Int, maxErrorMsg: String)
   withRegex(isRegex: Boolean, pattern: String, errorMessage: String)
```
---

<a name="JCToolBar"></a>
#### 4. JCToolBar
 ```xml
   <com.justclean.core.custom.JCToolBar
     android:id="@+id/toolbar"
     android:layout_height="?attr/actionBarSize"
     android:layout_width="match_parent"
     app:jc_toolbar_color="@color/white"
     app:jc_toolbar_icon="@drawable/ic_close_normal"
     app:jc_toolbar_text="@string/action_retry" />
```
```kotlin
   toolbar.setToolbarText("Title")
   toolbar.setToolbarText(R.string.title)
   toolbar.getToolbarText()
   toolbar.setToolbarColor(R.color.blue)
   toolbar.getToolbarColor()
   toolbar.setToolbarIcon(toolbarIcon: Drawable)
   toolbar.setToolbarIcon(toolbarIcon: Int)
   toolbar.getToolbarIcon()
```
---

<a name="JCSwitchButton"></a>
#### 5. JCSwitchButton
 ```xml
   <com.justclean.core.custom.JCSwitchButton
         android:id="@+id/jcSwitch"
         android:layout_width="wrap_content"
         android:layout_height="50dp"
         app:jc_switchMinWidth="16"
         app:jc_text_off="@string/off"
         app:jc_text_off_color="@color/dark_purple"
         app:jc_text_on="@string/on"
         app:jc_text_on_color="@color/white"
         app:jc_thumb_drawable="@drawable/custom_thumb"
         app:jc_track_off_drawable="@drawable/custom_track_off"
         app:jc_track_on_drawable="@drawable/custom_track" />
```
```kotlin
   setOffText(txt: Int)
   setOnText(txt: Int)
   setOnTextColor(color: Int)
   setOffTextColor(color: Int)
   setOffTrackDrawable(drawable: Int)
   setOnTrackDrawable(drawable: Int)
   setOnThumbDrawable(drawable: Int)
```
---

<a name="JCButton"></a>
#### 6. JCButton
 ```xml
    <com.justclean.core.custom.JCButton
        android:id="@+id/jcBtn"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:jc_enabled="true"
        app:jc_font="@font/roboto_italic"
        app:jc_primary="true"
        app:jc_progress="true"
        app:jc_text="@string/app_name"/>
```
```kotlin
   withProgress(Boolean)
   setJcPrimary()
   setJcSecondary()
   setJcText(String)
   setJcBackground(res: Int)
   isJcEnabled()
```
---

<a name="JCRecycleView"></a>
#### 7. JCRecycleView
 ```xml
    <com.justclean.core.custom.JCRecyclerView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:minHeight="300dp"/>
```
```kotlin
   list.maxHeight = YOUR_MAX_HEIGHT_VALUE
```
---

<a name="CustomBottomSheet"></a>
#### 8. CustomBottomSheet
```kotlin
   CustomBottomSheet(getSheetContentView(), getSheetDataSource()).show(supportFragmentManager, null)
   
    private fun getSheetContentView(): View {
        val view = LayoutInflater.from(this).inflate(R.layout.bottom_custom_view, null)
        view.actionButton.setOnClickListener { showToast("Thanks For Using Me !") }
        return view
    }
    
    private fun getSheetDataSource() = BottomSheetDataSource(
        title = null,
        dimLevel = 0.5f,
        topLeftCorner = 50f,
        topRightCorner = 50f,
        bottomLeftCorner = 0f,
        bottomRightCorner = 0f,
        isDragVisible = false,
        isCloseVisible = false,
        closeIcon = R.drawable.ic_close_normal, 
        backgroundColor = android.graphics.Color.WHITE
    )
```
---

<a name="base"></a>
### Base Clases

<a name="BaseViewModel"></a>
#### 1. BaseViewModel
By extending this viewmodel you can user some injected variables by koin and some global vars used in many places
1. `schedulerProvider` Koin
2. `compositeDisposable` Koin
3. `showMainScreen (livedata (true,false))` 
4. `showProgress (livedata (true,false))`
   You can use directly two functions for this mutable object
   1. `showLoadingProgress()`
   2. `hideLoadingProgress()`
5. `showMessage (livedata (string))`
6. `forceLogout (livedata (string))`
7. `showError (livedata (string))`
8. `handleError(error:Model<*>)` it's an abstract method you can use it to handel all diffrenet requests just pass the error resobnse

---

<a name="BaseActivity"></a>
#### 2. BaseActivity
By extending this BaseActivity you have many function to inherit it in your activity
1. `connectivityReceiver :ConnectivityReceiver` this is a global network check with dialog if network lost
2. To extend it (for layouts ) you need to anotate the child activity with `@LayoutRes(layout = R.layout.my_layout)` then no need to `setcontentview()`
3. Then you can use `onActivityReady()` instead of `onCreate()` in you child activity

---

<a name="BaseDialog"></a>
#### 3. BaseDialog
By extending this dialog you can use
1. Function `showDialogDismiss()` you can show message to dismiss the dialog
2. Show message based on action
   1. `showMessage(message: String)`
   2. `showMessage(message: Int?)`
3. Check network avialability `isNetworkConnected()`
4. Hide user keybad `hideKeyboard()`
5. Show snackbar and toast messages
   1. `showSnackBar(res: Int)`
   2. `showSnackBar(res: String)`
   3. `showToast(res: Int)`
   4. `showToast(res: String)`
6. You can use `bindViews()` as abstract method to bind your views inside it
7. You can use `setContentView()` as abstract method to set your selected view
8. `dismissDialog()` , `show(fragmentManager: FragmentManager, tag: String?)` to show and hide your dialog

---

<a name="BaseFragment"></a>
#### 4. BaseFragment

By extending this basefragment you can use
1. You can show and hide any kind of progress based on ur need
   1. `showLoading()`
   2. `hideLoading()`
2. Show error message come from the api `showErrorMessage()`
3. Check network case if is connected or not and listen if it changed
   1. `isConnected()`
   2. `onNetworkChange()`
4. You can use `onFragmentReady()` to start interact with the user instead of ovveriding defualt fragmetn method
5. To extend it (for layouts) you need to anotate the child activity with `@LayoutRes(layout = R.layout.my_layout)` then no need to `setcontentview()`

---

<a name="BaseApplication"></a>
#### 5. BaseApplication

By extending this base class you garantee using of koin just all what you need when you extend this class
1. Overrid this abstract list `modules: List<Module>` (koin module)
2. Override `val modules: List<Module> get() = listOf(myCustomModule)`
