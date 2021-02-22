# Welcome to JC-UI-Kit
Please use this guide to help you going through the kit and get familier with it's component and usage


# Table of Contents
---

1. [Atoms](#atoms)
    1. [JCButton](#button)
    2. [JCOTPView](#otp)

2. [Engines](#engines)
    1. [ValidationObserver](#validator)
    
<a name="atoms"></a>
### Atoms

------

<a name="button"></a>
#### 1. JCButton
This button have the ability to play lottie animation and manage state changes when clicked and reset
To enable the loading status you need to add it from xml like this 

```xml
        <com.justclean.uikit.atoms.JCButton
            android:id="@+id/jcBtn"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Click Me!"
            app:withLoading="true" />
```
Or from code like this 
```kotlin
        JCButton(context = this, withLoading = true)
```
And thanks for data binding we are able to control the enabled/disabled states from xml like this
```xml
        <com.justclean.uikit.atoms.JCButton
            android:id="@+id/jcBtn"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Click Me!"
            app:isEnabled="@{observer.shouldEnable}"
            app:withLoading="true" />
```
Using this binding adapter that trigger the public method `setButtonEnabled(enabled)`
```kotlin
      @BindingAdapter("isEnabled")
      fun setEnabledFromBinding(button: JCButton, enabled: Boolean){
          button.setButtonEnabled(enabled)
      }
```

<a name="otp"></a>
#### 2. JCOTPView
This OTP is a widget that configurable for the size of the digits from 4 to 6 and used to handling auto navigation between OTP digits and also facilate the process of setting and removing error style from all the components

```xml
        <com.justclean.uikit.atoms.JCOTPView
            android:id="@+id/otpView"
            app:digits="6"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="16dp"
            android:layout_marginEnd="16dp" />
```
And to listen for OTP Input End setup this listener 
```kotlin
        otpView.inputListeners = object : JCOTPView.OTPViewListeners {
            override fun onInputEnd(otp: String) {
                //TODO Use the otp result
            }
        }
```
You can set the error when the OTP entered is wrong using this code 
```kotlin
        otpView.setError("Your error message goes here")
```
Normally the error is removed automatically when you start typing but to do this manually use this method
```kotlin
        otpView.removeError()
```
At any point of time if you need to get the OTP from the view you can make use this
```kotlin
        otpView.getOTP()
```

<a name="engines"></a>
### Engines

------

<a name="validator"></a>
#### 1. ValidationObserver
This class is acting like validation engine that control any form input fields and button, all you need to make is register fields with their id and type as the follwing code, and also mark your button to start listin for state changing

```xml

        <EditText
            android:id="@+id/edt1"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Username"
            android:inputType="text"
            android:text='@={observer.registerField("edt1")}' />

        <EditText
            android:id="@+id/edt2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Email"
            android:inputType="textEmailAddress"
            android:text='@={observer.registerField("edt2", "email")}' />

        <EditText
            android:id="@+id/edt3"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Phone"
            android:inputType="phone"
            android:text='@={observer.registerField("edt3", "phone")}' />

        <EditText
            android:id="@+id/edt4"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Password"
            android:inputType="textPassword"
            android:text='@={observer.registerField("edt4", "password")}' />

        <com.justclean.uikit.atoms.JCButton
            android:id="@+id/jcBtn"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Submit"
            app:isEnabled="@{observer.shouldEnable}"
            app:withLoading="true" />

```

You also can control the validation variable for password length and mobile length when initiating the constructor, here is the paramters name and default values
```kotlin
        class ValidationObserver(private val phoneLength: Int = 8, private val passwordLength: Int = 6)
```

And the full usage using databinding in your activity is like this 
```kotlin
        val binding: ActivityUidemoBinding = DataBindingUtil.setContentView(this, R.layout.activity_uidemo)
        binding.observer = ValidationObserver()
```
