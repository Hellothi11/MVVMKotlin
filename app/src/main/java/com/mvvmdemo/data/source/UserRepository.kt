package com.mvvmdemo.data.source

import com.mvvmdemo.data.source.remote.user.LoginResponse
import com.mvvmdemo.data.source.remote.user.UserApi
import io.reactivex.Observable

/**
 * Created by thint on 5/29/18.
 */
class UserRepository(val userApi: UserApi) {

    companion object {

        private var INSTANCE: UserRepository? = null

        @JvmStatic fun getInstance(userApi: UserApi) =
                INSTANCE ?: synchronized(UserRepository::class.java) {
                    INSTANCE ?: UserRepository(userApi)
                            .also { INSTANCE = it }
                }
    }

    fun login(username:String, password:String): Observable<LoginResponse> {
        val map = HashMap<String, String>()
        map["username"] = username
        map["password"] = password
        return userApi.login(map)
    }
}