package com.mvvmdemo.mangalist

import com.mvvmdemo.common.SingleLiveEvent

/**
 * Created by thint on 5/27/18.
 */
class MangaListViewModel {
    internal val clickedManga = SingleLiveEvent<MangaItem>()
}