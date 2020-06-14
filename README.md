* this core module has some custom components  and BaseClasses you can use it with all features
    Components  1-JC_BottomSheet.
                2-JC_TextView.
                3-JC_EditText.
                4-JC_ToolBar.
                5-JC_SwitchButton.
                6-JC_Button.
                7-JC_RecycleWithMaxHeight
    BaseClasses
                1-BaseViewModel
                2-BaseActivity
                3-BaseDialog
                4-BaseFragment
                5-BaseApplication and Koin Integration included
* let's have a look for each component separatly
  ________________________________________________________________________________________________________________________
    # 1-JCBottomSheet
         * it is a custom class you can use it to make a bottom sheet with some features just all what you need to call it

        #XML CODE
            <com.justclean.core.custom.JCBottomSheet
                android:id="@+id/jcSheet"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                app:jc_behavior_peekHeight="100dp"
                app:jc_sheet_max_height="300dp"
                app:jc_sheet_min_height="200dp"
                app:jc_sheet_style="primary" />


            jc_behavior_peekHeight       => pass dimension resource
            jc_sheet_min_height          => pass dimension resource
            jc_sheet_max_height          => pass dimension resource
            jc_with_close_icon           => pass boolean
            jc_with_dash_icon            => pass boolean
            jc_close_icon                => pass drawable
            jc_dash_icon                 => pass drawable
            jc_with_title                => paaa boolean
            jc_set_sheet_title           => pass string resource
            jc_sheet_style               => primary,secondary


           -BottomSheet Features
                        1- You can Add Icon To close the sheet
                               # jcSheet.withCloseIcon(View.VISIBLE)
                               # jcSheet.setCloseIcon(R.drawable.close)
                        2- choose title
                               # jcSheet.withSheetTitle(true, "JustClean Sheet")
                        3- add dash icon or no as you wish
                               # jcSheet.withDashIcon(View.VISIBLE)
                               # jcSheet.setDashIcon(R.drawable.dashIcon)
                        4- to add you View to the bottomsheet just you need to pass List Of Items(FastItemAdapter from FastItem Laibray)
                               # jcSheet.setListAdapter(ITEMS)
                        5- and you can add all your click events to the sheet
                               # jcSheet.setEventsHokes(testClickOne, testClickTwo)
                        6- Add Max Height
                               # jcSheet.setMaxSheetHeight(800)
                        7- add peek height
                               # jcSheet.setPeekHeight(300)
                        8 add min height to the sheet
                               # jcSheet.setMinHeight(400)
                        9- change sheet state programatically
                               # jcSheet.setSheetState(BottomSheetBehavior.STATE_EXPANDED)
                        10- add custom style
                               # jcSheet.setSheetStyle(JCBottomSheet.SheetStyle.primary)
                        11- you can get sheet state ( # isCollapsed(), # isDragging(),# isHidden(),# isExpanded() )
    ________________________________________________________________________________________________________________________

    # 2-JCToolBar
            *by using this componeten you can add a toolbar with title and icon use it anywhere

            #XML CODE
                 <com.justclean.core.custom.JCToolBar
                    android:id="@+id/toolbar"
                    android:layout_height="?attr/actionBarSize"
                    android:layout_width="match_parent"
                    app:jc_toolbar_color="@color/white"
                    app:jc_toolbar_icon="@drawable/ic_close_normal"
                    app:jc_toolbar_text="@string/action_retry" />


            -ToolBar featuers
                    1- set tool bar text and get it
                            # toolbar.setToolbarText("Title")
                            # toolbar.setToolbarText(R.string.title)
                            # toolbar.getToolbarText()
                    2- set and get color for the toolbar
                            # toolbar.setToolbarColor(R.color.blue)
                            # toolbar.getToolbarColor()

                    3- set the icon for the toolbar and get it
                            # toolbar.setToolbarIcon(toolbarIcon: Drawable)
                            # toolbar.setToolbarIcon(toolbarIcon: Int)
                            # toolbar.getToolbarIcon()

    ________________________________________________________________________________________________________________________

    # 2-JCTextView
        * it is textview with many features like you can add spannable part to this text with colors
            just you need to set the range and the color and the style

        #XML CODE
            <com.justclean.core.custom.JCTextView
                android:id="@+id/txtGoToSheet"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                app:jc_spannable_start="8"
                app:jc_spannable_end="12"
                app:jc_spannable_color="@color/success_green"
                app:jc_spannable_style="bold"
            />

        # JCTextView features
                    1- choose start and end point to the part to change the color
                            # setSpannableStyle(start end: Int, style: JcTextSpannableStyle, color: Int)

                    2- change text theme
                            # setJCTextTheme(theme)
                    3- change text background
                            # setJcTextBackground(color)
                    4- change text background
                            # setJcTextBackgroundTint(color)
                    5- set start and end drwable
                            # setJcTextEndDrawable(drawable)
                            # setJcTextStartDrawable(drawable)
                    6- change elevation
                            # setJcTextElevation(elevation: Float = 0f)
                    7- change type face
                            # setJcTextTypeFace(type: Int = 0)
                    8- set maxlines
                            # setJcTextMaxLines(max: Int = 0)
                    9- set rotation
                            #setJcRotationX(rotation: Int = 0)
                    10- set a shadow for the text
                            # setJCTextShadow(
                                    txtShadowDx: Int = 0,
                                    txtShadowDy: Int = 0,
                                    txtShadowColor: Int = 0,
                                    txtShadowRadius: Int = 0)

                    11- set text
                            # setJcText(txt: String )
                    12- cahnge the color
                            # setJcTextColor(color: Int = 0)
                    13- set the size
                            #setJcTextSize(size: Int)
                    14- set padding for each side
                            # setJcTextPadding(
                                jcPadding: Int = 0,
                                jcPaddingStart: Int = 0,
                                jcPaddingEnd: Int = 0,
                                jcPaddingTop: Int = 0,
                                jcPaddingBottom: Int = 0)
                    15- set the gravity
                            #setJcTextGravity(gravity)
                    16- change the direction and aligment
                           # setJcTextDirection(direction: JcTextDirections? = null)
                           # setTxtAlignment(alignment: Int = 0)
    ________________________________________________________________________________________________________________________
    # 4-JC_EditText.
            * it is custom component you can use it with many feature like regex pattern to detect error
                and error color and end icon action and icon

            #XML CODE
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

            #JCEditText features

                    1- add text watcher to activate the regex and errors on this input
                            # setTextWatcher()
                            # getError()
                    2- set style for the TextInput
                            # setInputStyle(style: Int)
                    3- change the input hit color
                            # setHintColor(color: Int)
                    4- change the input text color
                            # setTextInputColor(color: Int)
                    5- set a theme for the edittext inside the inputlayout
                            # setEditTextTheme(editTextTheme: Int)
                    6- change input text size
                            # setJcTextSize(size: Int)
                    7- change end icond drawable and the cursor
                            # setEndIconDrawable(drawable: Int)
                            # setTextCursorDrawable(textCursorDrawable: Int)
                    8- set the hint text
                            # setInputHintText(inputHintText: Int)
                            # setInputHintText(inputHintText: String)
                    9- set the input type (text,password,email etc.....)
                            # setInputType(value: String)
                    10- change tint for the end icon
                            # setEndIconTint(endIconTint: Int)
                    11- set min and max for how many char you need in the edittext
                            # setMinChar(min: Int, minErrorMsg: String)
                            # setMaxChar(max: Int, maxErrorMsg: String)
                    12- set regex and pattern and it's message for the input
                            # withRegex(isRegex: Boolean, pattern: String, errorMessage: String)
    ________________________________________________________________________________________________________________________

    # 5-JC_Button
        * by using this jc button you can add different styls to it and progressbar to this button

        #XML CODE
                <com.justclean.core.custom.JCButton
                    android:id="@+id/jcBtn"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    app:jc_enabled="true"
                    app:jc_font="@font/roboto_italic"
                    app:jc_primary="true"
                    app:jc_progress="true"
                    app:jc_text="@string/app_name"
                    />


        #JCButton features
                1- add a progress loading to the button
                        # withProgress(Boolean)
                2- choose between justclean button styles
                        # setJcPrimary()
                        # setJcSecondary()
                3- add text
                        #setJcText(String)
                4- set custom background
                        # setJcBackground(res: Int)
                5- check if the button is enabled
                        # isJcEnabled()
    ________________________________________________________________________________________________________________________
    # 6-JC_SwitchButton
        * a custom switch button with off/on text and styles

        #XML CODE
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

        #JCSwitchButton features
                1- set a text for on state and off ,color
                        # setOffText(txt: Int)
                        # setOnText(txt: Int)
                        # setOnTextColor(color: Int)
                        # setOffTextColor(color: Int)
                2- set background drawables for off/on states and the thum cricle
                        # setOffTrackDrawable(drawable: Int)
                        # setOnTrackDrawable(drawable: Int)
                        # setOnThumbDrawable(drawable: Int)
    ________________________________________________________________________________________________________________________

    # 7-JC_RecycleWithMaxHeight
            * by using this custom component use can set max height for the recycler view

            #XML CODE
                <com.justclean.core.custom.MaxHeightRecyclerView
                    android:id="@+id/list"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:minHeight="300dp"
                />

             # to add max height just you need to write # list.maxHeight = YOUR_MAX_HEIGHT_VALUE
    ________________________________________________________________________________________________________________________

    # 1-BaseViewModel

            * By extending this viewmodel you can user some injected variables by koin and some global vars used in many placs
                    1- schedulerProvider   (koin)
                    2- compositeDisposable  (koin)
                    3- showMainScreen (livedata (true,false))
                    4- showProgress (livedata (true,false))
                        * you can use directly two functions for this mutable object
                            # showLoadingProgress()
                            # hideLoadingProgress()
                    5- showMessage (livedata (string))
                    6- forceLogout (livedata (string))
                    7- showError (livedata (string))
                    8- handleError(error:Model<*>)
                            * it's an abstract method you can use it to handel all diffrenet requests just pass the error resobnse
    ________________________________________________________________________________________________________________________

    # 2-BaseActivity
            * By extending this BaseActivity you have many function to inherit it in your activity

                    1- connectivityReceiver :ConnectivityReceiver
                                this is a global network check with dialog if network lost
                    2- to extend it (for layouts ) you need to anotate the child activity
                            with @LayoutRes(layout = R.layout.my_layout) then no need to setcontentview()
                    3-  then you can use onActivityReady() instead of onCreate in you child activity
    ________________________________________________________________________________________________________________________

    # 3-BaseDialog
            * By extending this dialog you can use
                    1- fun showDialogDismiss() you can show message to dismiss the dialog
                    2- show message based on action
                        * showMessage(message: String)
                        * showMessage(message: Int?)
                    3- Check network avialability -> isNetworkConnected()
                    4- hide user keybad -> hideKeyboard()
                    5- show snackbar and toast messages
                        * showSnackBar(res: Int)
                        * showSnackBar(res: String)
                        * showToast(res: Int)
                        * showToast(res: String)
                    6- you can use bindViews() as abstract method to bind your views inside it
                    7- you can use setContentView() as abstract method to set your selected view
                    8- dismissDialog(),show(fragmentManager: FragmentManager, tag: String?) to show and hide your dialog
    ________________________________________________________________________________________________________________________

    # 4-BaseFragment
            * By extending this basefragment you can use

                    1- you can show and hide any kind of progress based on ur need
                            *showLoading() , hideLoading()
                    2- show error message come from the api showErrorMessage()
                    3- check network case if is connected or not and listen if it changed
                            *isConnected()
                            *onNetworkChange()
                    4- you can use onFragmentReady() to start interact with the user instead of ovveriding defualt fragmetn method
                    5- to extend it (for layouts ) you need to anotate the child activity
                            with @LayoutRes(layout = R.layout.my_layout) then no need to setcontentview()
    ________________________________________________________________________________________________________________________

    # 5-BaseApplication
            * By extending this base class you garantee using of koin just all what you need
                    when you extend this class
                    1- ovverrid this abstract list  modules: List<Module> (koin module)
                        * override val modules: List<Module> get() = listOf(myCustomModule)
                No need to add anything else(no dependancies no )
    ________________________________________________________________________________________________________________________
