package com.mvvmdemo.common

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mvvmdemo.mangalist.MangaItem
import com.mvvmdemo.mangalist.MangaListAdapter

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(context).load(url).into(this)
}

object BindingAdapters {

    @BindingAdapter("app:items")
    @JvmStatic fun setItems(listView: RecyclerView, items: List<MangaItem>) {
        with(listView.adapter as MangaListAdapter) {
            replaceData(items)
        }
    }
}
