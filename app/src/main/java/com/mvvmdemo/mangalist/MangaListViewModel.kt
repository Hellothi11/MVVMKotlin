package com.mvvmdemo.mangalist

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.mvvmdemo.common.SingleLiveEvent
import com.mvvmdemo.data.source.MangaRepository
import com.mvvmdemo.data.source.remote.manga.MangaListResponse
import com.mvvmdemo.data.source.remote.user.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by thint on 5/27/18.
 */
class MangaListViewModel(
        context: Application,
        private val mangaRepository: MangaRepository
) : AndroidViewModel(context) {
    internal val clickedManga = SingleLiveEvent<MangaItem>()
    val items: ObservableList<MangaItem> = ObservableArrayList()

    fun start() {
        if (items.size <= 0) {
            loadMangas()
        }
    }

    fun loadMangas() {
        val call = mangaRepository.getMangaList()
        call.enqueue(object : Callback<MangaListResponse> {
            override fun onFailure(call: Call<MangaListResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<MangaListResponse>?, response: Response<MangaListResponse>?) {
                response?.body()?.let {
                    items.addAll(it.children)
                }
            }

        })
    }
}