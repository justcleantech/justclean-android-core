package com.justclean.core.ui

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.justclean.core.base.BaseActivity
import com.justclean.core.custom.JCEditText
import com.justclean.core.R
import com.justclean.core.base.LayoutRes
import com.justclean.core.custom.JCTextView
import com.justclean.core.heplers.startActivity
import com.justclean.core.ui.viewmodels.SampleViewModel
import kotlinx.android.synthetic.main.activity_button.*
import org.koin.androidx.viewmodel.ext.android.viewModel


@LayoutRes(layout = R.layout.activity_button)
class ButtonActivity : BaseActivity() {
    private val pattern = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$"
    private val errorMessage = "email format is wrong"
    private val minErrorMsg = "min error"
    private val maxErrorMsg = "max error"

    private val viewModel: SampleViewModel by viewModel()

    override fun onActivityReady(savedInstanceState: Bundle?) {
        jcBtn.setLifeCycleScope(this)
        jcBtn.setOnClickListener {
            //  jcBtn.setJcEnabled(false)
            jcBtn.withProgress(false)
            jcBtn.setJcText("Text Changed")
            jcBtn.setJcSecondary()
            showToast("event clicked")

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
        showToast(viewModel.test)



        setJcTextView()
    }


    fun setJcTextView(){
        txtGoToSheet.setJCTextShadow(-20,20,R.color.colorPrimary,20)
        txtGoToSheet.setJcTextSize(16)
        txtGoToSheet.setJcRotationX(8)
        txtGoToSheet.setSpannableStyle(3,15,JCTextView.JcTextSpannableStyle.BOLD_ITALIC,R.color.colorPrimaryLight)
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
}
