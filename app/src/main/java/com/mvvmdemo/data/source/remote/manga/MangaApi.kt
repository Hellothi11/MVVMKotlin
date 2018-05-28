package com.mvvmdemo.data.source.remote.manga

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by thint on 5/27/18.
 */
interface MangaApi {

    @GET("/v2/5185415ba171ea3a00704eed")
    fun getListManga(@Query("after") after: String?,
               @Query("limit") limit: String?)
            : Observable<MangaListResponse>

    companion object {
        fun create(): MangaApi {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://mocky.io")
                    .build()

            return retrofit.create(MangaApi::class.java)
        }
    }
}

