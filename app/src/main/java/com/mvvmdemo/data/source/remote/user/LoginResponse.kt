package com.mvvmdemo.data.source.remote.user

import com.google.gson.annotations.SerializedName

/**
 * Created by thint on 5/29/18.
 */
class LoginResponse (
        @SerializedName("access_token") val accessToken:String,
        @SerializedName("expired_date")val expiredDate:String
)