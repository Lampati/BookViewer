package com.lampati.bookviewer.di

import android.arch.persistence.room.Room
import android.content.Context
import com.lampati.bookviewer.BookViewerApplication
import com.lampati.bookviewer.bookList.BookRepository
import com.lampati.bookviewer.bookList.daos.BookDao
import com.lampati.bookviewer.commons.ResourcesService
import com.lampati.bookviewer.commons.SharedPreferencesService
import com.lampati.bookviewer.commons.ToastService
import com.lampati.bookviewer.room.BookViewerDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by FEDE on 6/3/2018.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun providesAppContext(application: BookViewerApplication) = application.applicationContext

    @Provides @Singleton
    fun providesSharedPreferences(application: BookViewerApplication) =   SharedPreferencesService(application)

    @Provides @Singleton
    fun providesToastService(application: BookViewerApplication) =   ToastService(application)

    @Provides @Singleton
    fun providesResourcesService(application: BookViewerApplication) =   ResourcesService(application)

    @Provides @Singleton
    fun providesBookDao(database: BookViewerDatabase) = database.bookDao()

    @Provides @Singleton
    fun providesAppDatabase(context: Context): BookViewerDatabase =
            Room.databaseBuilder(context, BookViewerDatabase::class.java, "book-viewer").build()

    @Provides @Singleton
    fun providesBookRepository(bookDao: BookDao,
                               sharedPreferencesService: SharedPreferencesService,
                               toastService: ToastService,
                               resourcesService: ResourcesService)
            = BookRepository(bookDao,sharedPreferencesService,toastService,resourcesService)
}