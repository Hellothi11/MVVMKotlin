package com.mvvmdemo.login

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.text.TextUtils
import android.widget.AutoCompleteTextView
import com.example.nguyentamthi.mvvmdemo.R
import com.mvvmdemo.common.MyApplication
import com.mvvmdemo.data.source.UserRepository
import com.mvvmdemo.data.source.remote.user.LoginResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

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

    val errorEmail:PublishSubject<String> = PublishSubject.create()
    val errorPassword:PublishSubject<String> = PublishSubject.create()
    val isShowProgress:PublishSubject<Boolean> = PublishSubject.create()
    val bindEmailFocus:PublishSubject<Boolean> = PublishSubject.create()
    val bindPasswordFocus:PublishSubject<Boolean> = PublishSubject.create()

    val loginTask:Observable<LoginResponse> =

    fun onLoginClicked() {
        attemptLogin()
    }

    fun attemptLogin() {
        errorEmail.onNext("")
        errorPassword.onNext("")

        val emailStr = email.get()
        val passwordStr = password.get()

        var cancel = false

        var bindFocus:PublishSubject<Boolean>? = null
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            errorPassword.onNext(MyApplication.getApplicationContext()?.getString(R.string.error_invalid_password))
            bindFocus = bindPasswordFocus
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            errorEmail.onNext(MyApplication.getApplicationContext()?.getString(R.string.error_field_required))
            bindFocus = bindEmailFocus
            cancel = true
        } else if (!isEmailValid(emailStr)) {
            errorEmail.onNext(MyApplication.getApplicationContext()?.getString(R.string.error_invalid_email))
            bindFocus = bindEmailFocus
            cancel = true
        }

        if (cancel) {
            bindFocus?.onNext(true)
        } else {
            isShowProgress.onNext(true)
        }
    }

    fun requestLogin(email:String, password: String) {
    }

    private fun isEmailValid(email: String?): Boolean {
        email?.let {
            return it.contains("@")
        }
        return false
    }

    private fun isPasswordValid(password: String?): Boolean {
        password?.let {
            return password.length > 4
        }
        return false
    }


}