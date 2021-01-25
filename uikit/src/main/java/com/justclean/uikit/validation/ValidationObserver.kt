package com.justclean.uikit.validation

import android.util.Patterns
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

class ValidationObserver(private val phoneLength: Int = 8, private val passwordLength: Int = 6) {

    private val fieldsValidationMap = HashMap<String, Boolean>()
    private val fieldsValueMap = HashMap<String, ObservableField<String>>()
    var shouldEnable = ObservableBoolean(false)

    @JvmOverloads
    fun registerField(id: String, type: String? = null): ObservableField<String> {
        if (fieldsValueMap.containsKey(id)) return fieldsValueMap[id]!!
        val field = ObservableField<String>()
        addPropertyChangedCallback(id, type, field)
        fieldsValidationMap[id] = false
        fieldsValueMap[id] = field
        return field
    }

    private fun addPropertyChangedCallback(id: String, type: String?, field: ObservableField<String>) {
        field.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                field.get()?.let {
                    fieldsValidationMap[id] = isValidField(it, type)
                    checkFieldsAndUpdateButton()
                }
            }
        })
    }

    private fun isValidField(value: String, type: String?): Boolean {
        return when (type) {
            "email" -> isMailValid(value)
            "phone" -> isPhoneValid(value)
            "password" -> isPasswordValid(value)
            else -> value.isNotBlank()
        }
    }

    private fun checkFieldsAndUpdateButton() {
        shouldEnable.set(false)
        fieldsValidationMap.forEach { (_, v) -> if (!v) return }
        shouldEnable.set(true)
    }

    private fun isPhoneValid(phone: String) = phone.length >= phoneLength

    private fun isPasswordValid(password: String) = password.length >= passwordLength

    private fun isMailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

}