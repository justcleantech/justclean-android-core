package com.justclean.uikit.validation

import android.os.Handler
import android.os.Looper
import android.util.Patterns
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.justclean.uikit.models.ValidationField

class ValidationObserver(private val phoneLength: Int = 8, private val passwordLength: Int = 6) {

    private val fieldsMap = HashMap<String, ValidationField>()
    var shouldEnable = ObservableBoolean(false)
    var countryCode: String = "KW"

    /**
     * Register new edit text with the id and type to observe it's changes
     * Save the field inside hashmap with it's id as a key
     * Add property change listener to know when content is updated
     */
    @JvmOverloads
    fun registerField(id: String, type: String? = null): ObservableField<String> {
        if (fieldsMap.containsKey(id)) return fieldsMap[id]!!.field
        val field = ObservableField<String>()
        fieldsMap[id] = ValidationField(field)
        addPropertyChangedCallback(id, type, field)
        return field
    }

    /**
     * Register property change listener for the field
     * Update the valid state value inside the hashmap when content changes
     * Trigger the button observer to check and update it's state
     */
    private fun addPropertyChangedCallback(
        id: String,
        type: String?,
        field: ObservableField<String>
    ) {
        field.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                field.get()?.let {
                    fieldsMap[id]!!.valid = isValidField(it, type)
                    shouldEnable.set(getFieldsValidation())
                }
            }
        })
    }

    /**
     * Check if field content is valid based on field's type
     */
    private fun isValidField(value: String, type: String?): Boolean {
        return when (type) {
            "email" -> isMailValid(value)
            "phone" -> isPhoneValid(value)
            "password" -> isPasswordValid(value)
            else -> value.isNotBlank()
        }
    }

    /**
     * Return false if any value in the hashmap isn't valid
     * Return true if all fields is valid and ready to submit form
     */
    private fun getFieldsValidation(): Boolean {
        fieldsMap.forEach { (_, field) -> if (!field.valid) return false }
        return true
    }

    /**
     * Validate the phone base on length assigned in the constructor
     */
    fun isPhoneValid(phone: String): Boolean {
        return when(countryCode) {
            "KW" -> matchesPattern(phone, "569")
            "AE", "SA" -> matchesPattern(phone, "5", 9)
            "BH" -> matchesPattern(phone, "36")
            "QA" -> matchesPattern(phone, "3567")
            else -> true
        }
    }

    fun setFieldText(id: String, text: String) {
        Handler(Looper.getMainLooper()).postDelayed({
            fieldsMap[id]?.field?.set(text)
        }, 100)
    }

    private fun matchesPattern(phone: String, pattern: String, length: Int= 8): Boolean {
        val regex = "^[$pattern]".toRegex()
        return regex.containsMatchIn(phone) && phone.length == length
    }

    /**
     * Validate the password based on length assigned in the constructor
     */
    private fun isPasswordValid(password: String) = password.length >= passwordLength

    /**
     * Validate the email address based on matching this format a@a.a as a is any alphanumeric
     */
    private fun isMailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

}