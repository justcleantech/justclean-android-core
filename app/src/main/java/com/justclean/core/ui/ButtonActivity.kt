package com.justclean.core.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.justclean.core.base.BaseActivity
import com.justclean.core.custom.JCEditText
import com.justclean.core.R
import com.justclean.core.base.LayoutRes
import com.justclean.core.custom.JCTextView
import com.justclean.core.custom.bottomsheet.CustomBottomSheet
import com.justclean.core.custom.bottomsheet.BottomSheetDataSource
import com.justclean.core.heplers.startActivity
import com.justclean.core.network.MainActivity
import com.justclean.core.ui.viewmodels.SampleViewModel
import kotlinx.android.synthetic.main.activity_button.*
import org.koin.androidx.viewmodel.ext.android.viewModel

@LayoutRes(layout = R.layout.activity_button)
class ButtonActivity : BaseActivity() {
    private val pattern = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$"
    private val errorMessage = "email format is wrong"
    private val minErrorMsg = "min error"
    private val maxErrorMsg = "max error"
    private var loadingStarted = false

    private val viewModel: SampleViewModel by viewModel()

    override fun onActivityReady(savedInstanceState: Bundle?) {

        jcBtn.setOnClickListener {
            showToast("event clicked")
            jcBtn.startLoading()
        }
        setJcInput(jcTextInput, jcTextInputTwo)

        txtGoToSheet.setOnClickListener {
            jcTextInput.clearFocus()
            jcTextInputTwo.clearFocus()
            txtGoToSheet.hideKeyboard()
            startActivity(
                Intent(
                    this,
                    BottomSheetActivity::class.java
                )
            )
        }
        txtGoToFragment.setOnClickListener {
            startActivity<BaseSampleFragment>()
        }

        txtGoToMap.setOnClickListener {
            startActivity<MapsActivity>()
        }
        showToast(viewModel.test)

        setJcTextView()

        openRecycler.setOnClickListener {
            startActivity(Intent(this, RecyclerDemoActivity::class.java))
        }

        networkActivity.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


    fun setJcTextView() {
        txtGoToSheet.setJCTextShadow(-20, 20, R.color.colorPrimary, 20)
        txtGoToSheet.setJcTextSize(16)
        txtGoToSheet.setJcRotationX(8)
        txtGoToSheet.setSpannableStyle(
            3,
            15,
            JCTextView.JcTextSpannableStyle.BOLD_ITALIC,
            R.color.colorPrimaryLight
        )
    }

    private fun setJcInput(vararg inputs: JCEditText) {
        for (input in inputs) {
            input.lifecycleOwner = this
            input.setJcTextSize(20)
            input.setEditTextTheme(R.style.Custom)
            input.setEndIconDrawable(R.drawable.ic_create_black_24dp)
            input.setEndIconMode(JCEditText.TextInputMode.CLEAR_TEXT)
            input.setEndIconTint(R.color.success_green)
            input.setHintColor(R.color.error_red)
            input.setInputHintText("Hopaaaa")
            input.setInputStyle(R.style.TextInputLayoutStyle)
            input.setTextInputColor(R.color.normal_green)
            input.setInputType("text")
            input.getError()
            input.withRegex(false, pattern, errorMessage)
            input.setMinChar(7, minErrorMsg)
            input.setMaxChar(8, maxErrorMsg)
            input.error.postValue("")
            input.setTextWatcher()
        }

    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun Context.showToast(txt: String) {
        Toast.makeText(this, txt, Toast.LENGTH_LONG).show()
    }

    fun openCustomView(view: View) {
        CustomBottomSheet(getSheetContentView(), getSheetDataSource()).show(
            supportFragmentManager,
            null
        )
    }

    fun openCustomLayout(view: View) {
        ExampleBottomSheet(BottomSheetDataSource()).show(supportFragmentManager, null)
    }

    private fun getSheetContentView(): View {
        val view = LayoutInflater.from(this).inflate(R.layout.bottom_custom_view, null)
//        view.actionButton.setOnClickListener {
//            showToast("Thanks For Using Me !")
//        }
        return view
    }

    private fun getSheetDataSource() = BottomSheetDataSource(
        isDragVisible = false,
        isCloseVisible = true,
        title = "Jimmy Bottom Sheet",
        dimLevel = 0.75f
    )

    fun materialButtonClicked(view: View) {
        if (loadingStarted)
            materialButton.endLoading()
        else
            materialButton.startLoading()
        loadingStarted = !loadingStarted
    }

}
