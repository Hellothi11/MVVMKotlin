package com.mvvmdemo.login

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.text.TextUtils
import android.widget.AutoCompleteTextView
import com.example.nguyentamthi.mvvmdemo.R
import com.mvvmdemo.common.MyApplication
import com.mvvmdemo.data.source.UserRepository
import com.mvvmdemo.data.source.remote.user.LoginResponse
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    val errorEmail:BehaviorSubject<String> = BehaviorSubject.create()
    val errorPassword: BehaviorSubject<String> = BehaviorSubject.create()
    val isShowProgress:BehaviorSubject<Boolean> = BehaviorSubject.create()
    val bindEmailFocus:BehaviorSubject<Boolean> = BehaviorSubject.create()
    val bindPasswordFocus:BehaviorSubject<Boolean> = BehaviorSubject.create()
    val isLoginSuccess:BehaviorSubject<Boolean> = BehaviorSubject.create()

    fun onLoginClicked() {
        attemptLogin()
    }

    fun attemptLogin() {
        errorEmail.onNext("")
        errorPassword.onNext("")

        val emailStr = email.get()
        val passwordStr = password.get()

        var cancel = false

        var bindFocus:BehaviorSubject<Boolean>? = null
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
            requestLogin(emailStr!!, passwordStr!!)
        }
    }

    fun requestLogin(email:String, password: String) {
        val call = userRepository.login(email, password)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                isLoginSuccess.onNext(false)
            }

            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                isLoginSuccess.onNext(true)
            }

        })
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