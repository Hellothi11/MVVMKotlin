package com.mvvmdemo.mangalist

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by thint on 5/27/18.
 */
class MangaItem(val imageUrl: String, val title: String) {

    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

}