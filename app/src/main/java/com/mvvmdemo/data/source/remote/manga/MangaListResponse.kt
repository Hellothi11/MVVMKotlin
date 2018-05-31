package com.mvvmdemo.data.source.remote.manga

import com.google.gson.annotations.SerializedName
import com.mvvmdemo.mangalist.MangaItem

/**
 * Created by thint on 5/27/18.
 */
class MangaListResponse (
        @SerializedName("children") val children:List<MangaItem>,
        @SerializedName("after") val after:String,
        @SerializedName("before") val before:String
)