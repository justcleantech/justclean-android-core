package com.justclean.core.heplers

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.location.LocationManager
import android.net.Uri
import android.os.SystemClock
import android.provider.Settings
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.akexorcist.localizationactivity.core.LanguageSetting
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.justclean.core.R
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

fun LanguageSetting.getLangCode(): String {
    return LanguageSetting.getDefaultLanguage().language
}

fun Context.showLongToast(msg: String) {
    android.widget.Toast.makeText(this, msg, android.widget.Toast.LENGTH_LONG).show()
}

fun Context.showShortToast(msg: String) {
    android.widget.Toast.makeText(this, msg, android.widget.Toast.LENGTH_SHORT).show()
}

fun Context.showLongSnackBar(rootView: View, msg: String) {
    Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG).show()
}

fun Context.showShortSnackBar(rootView: View, msg: String) {
    Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG).show()
}

// price format
fun Float.formatPrice(locale: Locale): String {
    val currency = Currency.getInstance(locale)
    val format = NumberFormat.getCurrencyInstance(locale)
    format.currency = currency
    if (locale.country == Constants.Country.KUWAIT.code || locale.country == Constants.Country.BAHRAIN.code)
        format.minimumFractionDigits = 3
    else
        format.minimumFractionDigits = 2
    format.minimumIntegerDigits = 1
    return replaceNonstandardDigits(
        format.format(this).toString()
    ).toString().replace("٫", ".")
}

fun Float.formatMinusPrice(locale: Locale): String {
    val currency = Currency.getInstance(locale)
    val format = NumberFormat.getCurrencyInstance(locale)
    format.currency = currency
    if (locale.country == Constants.Country.KUWAIT.code || locale.country == Constants.Country.BAHRAIN.code)
        format.minimumFractionDigits = 3
    else
        format.minimumFractionDigits = 2
    format.minimumIntegerDigits = 1
    return replaceNonstandardDigits(
        "-" + format.format(
            this
        ).toString()
    ).toString().replace("٫", ".")
}

fun Float.formatShortPrice(locale: Locale): String {
    val currency = Currency.getInstance(locale)
    val format = NumberFormat.getCurrencyInstance(locale)
    format.currency = currency
    format.minimumFractionDigits = 0
    format.minimumIntegerDigits = 1
    return replaceNonstandardDigits(
        format.format(this).toString()
    ).toString().replace("٫", ".")
}

fun getCurrency(locale: Locale): String {
    val currency = Currency.getInstance(locale)
    return currency.getSymbol(locale)
}

fun Float.formatShortPrice2(locale: Locale): String {
    val currency = Currency.getInstance(locale)
    val format = NumberFormat.getCurrencyInstance(locale)
    format.currency = currency
    format.minimumFractionDigits = 2
    format.minimumIntegerDigits = 1
    return replaceNonstandardDigits(
        format.format(this).toString()
    ).toString().replace("٫", ".")
}

fun Float.formatPriceWithoutCurrency(): String {
    val format = NumberFormat.getNumberInstance()
    format.minimumFractionDigits = 2
    format.minimumIntegerDigits = 1
    return replaceNonstandardDigits(
        format.format(this).toString()
    ).toString().replace("٫", ".")
}

fun Float.formatPriceWithoutCurrencyWallet(locale: Locale): String {
    val format = NumberFormat.getNumberInstance()
    if (locale.country == Constants.Country.KUWAIT.code || locale.country == Constants.Country.BAHRAIN.code) {
        format.maximumFractionDigits = 3
        format.minimumFractionDigits = 3
    } else {
        format.maximumFractionDigits = 2
        format.minimumFractionDigits = 2
    }
    format.minimumIntegerDigits = 1
    return replaceNonstandardDigits(
        format.format(this).toString()
    ).toString().replace("٫", ".")
}

fun replaceNonstandardDigits(input: String?): String? {
    if (input == null || input.isEmpty()) {
        return input
    }

    val builder = StringBuilder()
    for (i in 0 until input.length) {
        val ch = input[i]
        if (isNonstandardDigit(ch)) {
            val numericValue = Character.getNumericValue(ch)
            if (numericValue >= 0) {
                builder.append(numericValue)
            }
        } else {
            builder.append(ch)
        }
    }
    return builder.toString()
}

fun replaceNonstandardDigits2(input: String?): String? {
    if (input == null || input.isEmpty()) {
        return input
    }

    val builder = StringBuilder()
    for (i in 0 until input.length) {
        val ch = input[i]
        if (isNonstandardDigit(ch)) {
            val numericValue = Character.getNumericValue(ch)
            if (numericValue >= 0) {
                builder.append(numericValue)
            }
        } else {
            builder.append(ch)
        }
    }
    return builder.toString()
}

private fun isNonstandardDigit(ch: Char): Boolean {
    return Character.isDigit(ch) && !(ch >= '0' && ch <= '9')
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.changeToGoneVisibility(visibility: Boolean, vararg views: View) {
    views.map { view ->
        if (visibility) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }
}

fun View.changeToInvisibaleVisibility(visibility: Boolean, vararg views: View) {
    views.map { view ->
        if (visibility) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }
    }
}

fun Context.wakeUpEveryTime(timeForAlarm: Long, receiver: Class<*>): AlarmManager {
    val intent = Intent(this.applicationContext, receiver)
    val pendingIntent = PendingIntent.getBroadcast(this, 1000, intent, FLAG_ONE_SHOT)
    val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + timeForAlarm * 60 * 1000, (timeForAlarm * 60 * 1000), pendingIntent)

    Log.d(receiver.simpleName, "alarm set at ${SystemClock.elapsedRealtime() + 15000}")
    return alarmManager
}

fun Context.getAlarmManager(): AlarmManager {
    return this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
}

fun String.formatDate(dateFormat: String): String {
    return AppDateTimeHelper.getHumanDateTime(dateFormat, this)
}



fun Context.openGooglePlay() {
    val appPackageName = packageName
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
    } catch (a: android.content.ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
    }

}

fun Activity.openPermissionSettings() {
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    startActivityForResult(intent, 3000)
}

fun Context.isGpsOpen(): Boolean {
    val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
}

inline fun <reified T> Activity.startActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T> Activity.startActivity(data: Intent) {
    startActivity(Intent(this, T::class.java).putExtras(data))
}

inline fun <reified T> Activity.startActivityForResult(data: Intent, code: Int) {
    startActivityForResult(Intent(this, T::class.java).putExtras(data), code)
}

inline fun <reified T> Activity.startActivityForResult(code: Int) {
    startActivityForResult(Intent(this, T::class.java), code)
}

fun Context.checkPermissions(arrays: ArrayList<String>): Boolean {
    val isGranted = arrayListOf<Boolean>()
    arrays.map { data ->
        isGranted.add(ContextCompat.checkSelfPermission(this, data) != PackageManager.PERMISSION_GRANTED)
    }
    return isGranted.find { data -> !data } != null
}

fun EditText.validate(message: String): Boolean {
    return if (this.text.toString().isEmpty()) {
        if (this.parent.parent is TextInputLayout) {
            (this.parent.parent as TextInputLayout).error = message
        } else {
            error = message
        }
        false
    } else {
        if (this.parent.parent is TextInputLayout) {
            (this.parent.parent as TextInputLayout).isErrorEnabled = false
        } else {
            error = null
        }
        true
    }
}

fun removeLastChar(s: String): String {
    return if (s == "" || s.isBlank())
        ""
    else
        s.substring(0, s.length - 1)
}

fun isNotNullOrEmpty(s: String?): Boolean {
    return when (!s.isNullOrEmpty() && s != "[]" && s != "" && s != "null" && s != null) {
        true -> true
        false -> false
    }
}

fun removeQuotation(s: String): String {
    return s.replace("\"", "")
}

fun trimComma(s: String): String {
    return s.replace(", ", ",")
}

fun String.decode(): String {
    return android.util.Base64.decode(this, android.util.Base64.DEFAULT).toString(charset("UTF-8"))
}

fun String.encode(): String {
    return android.util.Base64.encodeToString(this.toByteArray(charset("UTF-8")), android.util.Base64.DEFAULT)
}

fun setFontSizeForPath(spannable: Spannable, path: String, fontSizeInPixel: Int) {
    val startIndexOfPath = spannable.toString().indexOf(path)
    spannable.setSpan(
        AbsoluteSizeSpan(fontSizeInPixel), startIndexOfPath,
        startIndexOfPath + path.length, 0)
}

fun Regex.checkValidInput(txt: String): Boolean {
    var regex = Regex("[!@#\$%^&*(),.?\":{}_|<>]")
    return regex.containsMatchIn(txt)
}

fun Regex.checkValidNumber(txt: String): Boolean {
    var regex = Regex("^[0-9]*\$")
    return regex.containsMatchIn(txt)
}

fun Regex.checkValidMail(txt: String): Boolean {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return EMAIL_REGEX.toRegex().matches(txt)
}

fun checkValidMail(txt: String): Boolean {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return EMAIL_REGEX.toRegex().matches(txt)
}

fun TextInputEditText.clear(input: TextInputEditText) {
    input.setText("")
}

fun setTextWithSpan(textView: TextView, text: String, spanTextBold1: String, spanTextBold2: String, spanTextLight1: String, spanTextLight2: String, style: StyleSpan) {

    val sb = SpannableStringBuilder(text)

    val start = text.indexOf(spanTextBold1)
    val end = start + spanTextBold1.length
    sb.setSpan(style, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    sb.setSpan(RelativeSizeSpan(1.0f), start, end, 0)

    val start2 = text.indexOf(spanTextBold2)
    val end2 = start2 + spanTextBold1.length
    sb.setSpan(style, start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    sb.setSpan(RelativeSizeSpan(1.0f), start2, end2, 0)

    val startTwo = text.indexOf(spanTextLight1)
    val endTwo = startTwo + spanTextLight1.length
    sb.setSpan(ForegroundColorSpan(Color.LTGRAY), startTwo, endTwo, 0)
    sb.setSpan(RelativeSizeSpan(0.7f), startTwo, endTwo, 0)

    val startTwo2 = text.indexOf(spanTextLight2)
    val endTwo2 = startTwo2 + spanTextLight2.length
    sb.setSpan(ForegroundColorSpan(Color.LTGRAY), startTwo2, endTwo2, 0)
    sb.setSpan(RelativeSizeSpan(0.7f), startTwo2, endTwo2, 0)

    textView.text = sb
}

inline fun <reified T> Intent.getObject(): T? {
    return if (getSerializableExtra(T::class.java.simpleName) != null) {
        getSerializableExtra(T::class.java.simpleName) as T
    } else {
        null
    }
}


fun View.startAnimation(animationName: String, duration: Long, vararg movement: Float) {
    val animation: ObjectAnimator = ObjectAnimator.ofFloat(this, animationName, *movement)
    animation.duration = duration
    animation.start()
}

fun Activity.getScreenHeight(): Int {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.heightPixels
}

fun Activity.getScreenWidth(): Int {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}


fun convertDbToPixel(context: Context, dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()

fun TextView.changeTypeFace(activity: Activity, id: Int) {
    this.typeface = Typeface.createFromAsset(activity.assets, activity.getString(id))
}

fun Activity.setMediumFont(vararg textViews: TextView) {
    for (text in textViews) {
        text.changeTypeFace(this, R.font.roboto_medium)
    }
}

fun Activity.setRegularFont(vararg textViews: TextView) {
    for (text in textViews) {
        text.changeTypeFace(this, R.font.dubai_regular)
    }
}

inline fun View.fadeIn(durationMillis: Long = 250) {
    this.startAnimation(AlphaAnimation(0F, 1F).apply {
        duration = durationMillis
        fillAfter = true
    })
}

inline fun View.fadeOut(durationMillis: Long = 250) {
    this.startAnimation(AlphaAnimation(1F, 0F).apply {
        duration = durationMillis
        fillAfter = false
    })
}

fun String.measureTextWidth(): Int {
    val paint = Paint()
    val bounds = Rect()
    paint.getTextBounds(this, 0, this.length, bounds)
    return bounds.width()
}

fun increaseViewHeightWithAnimation(view: View, height: Int) {
    val anim: ValueAnimator = ValueAnimator.ofInt(view.measuredHeight, height)
    anim.addUpdateListener { valueAnimator ->
        val `val` = valueAnimator.animatedValue as Int
        val layoutParams: ViewGroup.LayoutParams = view.layoutParams
        layoutParams.height = `val`
        view.layoutParams = layoutParams
    }
    anim.duration = 400
    anim.start()
}

fun decreaseViewHeightWithAnimation(view: View, height: Int, isToEnd: Boolean) {
    val anim: ValueAnimator = ValueAnimator.ofInt(if (isToEnd) 0 else height, view.measuredHeight)
    anim.addUpdateListener {
        val params: ViewGroup.LayoutParams = view.layoutParams
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        view.layoutParams = params
        view.layoutParams = params
    }
    anim.duration = 400
    anim.start()
}

fun View.toggleSlideViewFromTopToBottom(show: Boolean, root: ViewGroup) {
    val transition: Transition = Slide(Gravity.TOP)
    transition.duration = 400
    transition.addTarget(this)
    TransitionManager.beginDelayedTransition(root, transition)
    this.visibility = if (show) View.VISIBLE else View.GONE
}