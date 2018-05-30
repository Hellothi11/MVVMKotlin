package com.mvvmdemo.login

import android.Manifest.permission.READ_CONTACTS
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.app.LoaderManager.LoaderCallbacks
import android.content.CursorLoader
import android.content.Loader
import android.content.pm.PackageManager
import android.database.Cursor
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.nguyentamthi.mvvmdemo.R
import com.example.nguyentamthi.mvvmdemo.databinding.ActivityLoginBinding
import com.mvvmdemo.common.obtainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.reactivestreams.Subscriber
import java.util.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    private lateinit var viewDataBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).apply {
            viewmodel = obtainViewModel(LoginViewModel::class.java)
        }

        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                viewDataBinding.viewmodel?.attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        viewDataBinding.viewmodel?.isShowProgress
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ value ->
                    showProgress(value)
                })

        viewDataBinding.viewmodel?.bindEmailFocus
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ value ->
                    email.requestFocus()
                })

        viewDataBinding.viewmodel?.bindPasswordFocus
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ value ->
                    password.requestFocus()
                })

        viewDataBinding.viewmodel?.errorEmail
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ value ->
                    if (!value.isEmpty()) {
                        email.error = value
                    } else {
                        email.error = null
                    }
                })

        viewDataBinding.viewmodel?.errorPassword
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ value ->
                    if (!value.isEmpty()) {
                        password.error = value
                    } else {
                        password.error = null
                    }
                })
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            login_form.visibility = if (show) View.GONE else View.VISIBLE
            login_form.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_form.visibility = if (show) View.GONE else View.VISIBLE
                        }
                    })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 1 else 0).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_progress.visibility = if (show) View.VISIBLE else View.GONE
                        }
                    })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

}
