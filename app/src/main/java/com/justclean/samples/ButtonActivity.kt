package com.justclean.samples

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.justclean.core.JCEditText
import com.justclean.core.R
import kotlinx.android.synthetic.main.activity_button.*


class ButtonActivity : AppCompatActivity() {
    private val pattern = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$"
    private val errorMessage = "email format is wrong"
    private val minErrorMsg = "min error"
    private val maxErrorMsg = "max error"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        jcBtn.setLifeCycleScope(this)
        jcBtn.setOnClickListener {
            //  jcBtn.setJcEnabled(false)
            jcBtn.withProgress(false)
            jcBtn.setJcText("Text Changed")
            jcBtn.setJcSecondary()
            showToast("event clicked")

        }
        setJcInput(jcTextInput,jcTextInputTwo)

        txtGoToSheet.setOnClickListener {
            jcTextInput.clearFocus()
            jcTextInputTwo.clearFocus()
            txtGoToSheet.hideKeyboard()
            startActivity(Intent(this,BottomSheetActivity::class.java))
        }

    }

    private fun setJcInput(vararg inputs:JCEditText){
        for (input in inputs){
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
            input.withRegex(false,pattern,errorMessage)
            input.setMinChar(7,minErrorMsg)
            input.setMaxChar(8,maxErrorMsg)
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
