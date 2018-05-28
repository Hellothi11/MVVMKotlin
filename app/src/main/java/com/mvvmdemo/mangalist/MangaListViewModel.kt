package com.mvvmdemo.mangalist

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.mvvmdemo.common.SingleLiveEvent
import com.mvvmdemo.data.source.MangaRepository

/**
 * Created by thint on 5/27/18.
 */
class MangaListViewModel(
        context: Application,
        private val mangaRepository: MangaRepository
) : AndroidViewModel(context) {
    internal val clickedManga = SingleLiveEvent<MangaItem>()
}