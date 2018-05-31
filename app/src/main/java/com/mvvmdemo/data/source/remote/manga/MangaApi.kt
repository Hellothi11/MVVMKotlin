package com.mvvmdemo.data.source.remote.manga

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by thint on 5/27/18.
 */
interface MangaApi {

    @GET("/v2/5b0f714b3000006f00115080")
    fun getListManga()
            : Call<MangaListResponse>

    companion object {
        fun create(): MangaApi {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://www.mocky.io")
                    .build()

            return retrofit.create(MangaApi::class.java)
        }
    }
}

