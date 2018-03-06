package com.lampati.bookviewer.di

import com.lampati.bookviewer.BookViewerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by FEDE on 6/3/2018.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, ActivitiesModule::class, AndroidInjectionModule::class))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BookViewerApplication): Builder

        fun build(): AppComponent
    }
    fun inject(application: BookViewerApplication)
}