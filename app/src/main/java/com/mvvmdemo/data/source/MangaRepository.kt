package com.mvvmdemo.data.source

import com.mvvmdemo.data.source.remote.manga.MangaApi
import com.mvvmdemo.mangalist.MangaItem
import io.reactivex.Observable

/**
 * Created by thint on 5/27/18.
 */

class MangaRepository(val mangaApi: MangaApi) {

    fun getMangas(): Observable<List<MangaItem>> {
        return mangaApi.getListManga(null, null).map { response -> response.children }
    }
}