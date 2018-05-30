package com.mvvmdemo.data.source.remote.user

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by thint on 5/29/18.
 */
interface UserApi {
    @POST("/v2/5b0e9d053200002700c19b51")
    fun login(@Body body:HashMap<String, String>)
            : Observable<LoginResponse>

    companion object {
        fun create(): UserApi {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://www.mocky.io")
                    .build()

            return retrofit.create(UserApi::class.java)
        }
    }
}