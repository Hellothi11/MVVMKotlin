package com.mvvmdemo.data.source.remote.manga

import com.mvvmdemo.mangalist.MangaItem

/**
 * Created by thint on 5/27/18.
 */
class MangaListResponse (
    val children:List<MangaItem>,
    val after:String?,
    val before:String?
)