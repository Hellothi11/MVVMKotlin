package com.mvvmdemo.data.source

import com.mvvmdemo.data.source.remote.manga.MangaApi
import com.mvvmdemo.data.source.remote.manga.MangaListResponse
import com.mvvmdemo.mangalist.MangaItem
import io.reactivex.Observable
import retrofit2.Call

/**
 * Created by thint on 5/27/18.
 */

class MangaRepository(val mangaApi: MangaApi) {

    companion object {

        private var INSTANCE: MangaRepository? = null

        @JvmStatic fun getInstance(mangaApi: MangaApi) =
                INSTANCE ?: synchronized(MangaRepository::class.java) {
                    INSTANCE ?: MangaRepository(mangaApi)
                            .also { INSTANCE = it }
                }
    }

    fun getMangaList(): Call<MangaListResponse> {
        return mangaApi.getListManga()
    }
}