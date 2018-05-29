package com.mvvmdemo.login

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.widget.AutoCompleteTextView
import com.mvvmdemo.data.source.UserRepository

/**
 * Created by thint on 5/29/18.
 */

@BindingAdapter("app:bindRequestFocus")
fun AutoCompleteTextView.bindRequestFocus(isFocus:Boolean) {
    if (isFocus) {
        requestFocus()
    }
}

class LoginViewModel(
        context: Application,
        private val userRepository: UserRepository
) : AndroidViewModel(context) {

    val email:ObservableField<String> = ObservableField<String>()
    val password:ObservableField<String> = ObservableField<String>()
    val errorEmail:ObservableField<String> = ObservableField<String>()
    val errorPassword:ObservableField<String> = ObservableField<String>()
    val isShowProgress:ObservableBoolean = ObservableBoolean(false)

    val bindEmailFocus:ObservableBoolean = ObservableBoolean(false)
    val bindPasswordFocus:ObservableBoolean = ObservableBoolean(false)

    fun onLoginClicked() {

    }
}