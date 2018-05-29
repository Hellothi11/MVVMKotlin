package com.mvvmdemo.common

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mvvmdemo.data.source.MangaRepository
import com.mvvmdemo.data.source.UserRepository
import com.mvvmdemo.data.source.remote.manga.MangaApi
import com.mvvmdemo.data.source.remote.user.UserApi
import com.mvvmdemo.login.LoginViewModel
import com.mvvmdemo.mangalist.MangaListViewModel

/**
 * Created by thint on 5/28/18.
 */
/**
 * A creator is used to inject the product ID into the ViewModel
 *
 *
 * This creator is to showcase how to inject dependencies into ViewModels. It's not
 * actually necessary in this case, as the product ID can be passed in a public method.
 */
class ViewModelFactory private constructor(
        private val application: Application,
        private val mangaRepository: MangaRepository,
        private val userRepository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(MangaListViewModel::class.java) ->
                        MangaListViewModel(application, mangaRepository)
                    isAssignableFrom(LoginViewModel::class.java) ->
                            LoginViewModel(application, userRepository)

                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        /**
         * Return singleton instance
         */
        fun getInstance(application: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(application,
                            RepositoryInjection.provideMangaRepository(),
                            RepositoryInjection.provideUserRepository())
                            .also { INSTANCE = it }
                }
    }
}

object RepositoryInjection {

    fun provideMangaRepository(): MangaRepository {
        val mangaApi = MangaApi.create()
        return MangaRepository.getInstance(mangaApi)
    }

    fun provideUserRepository(): UserRepository {
        val userApi = UserApi.create()
        return UserRepository.getInstance(userApi)
    }
}