package com.mvvmdemo.mangalist

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName

/**
 * Created by thint on 5/27/18.
 */

class MangaItem(@SerializedName("image")val image: String, @SerializedName("title") val title: String) {



}