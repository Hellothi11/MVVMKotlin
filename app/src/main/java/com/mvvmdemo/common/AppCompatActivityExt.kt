package com.mvvmdemo.common

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.BindingAdapter
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by thint on 5/28/18.
 */

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
        (this.context as AppCompatActivity)?.obtainViewModel(viewModelClass)
