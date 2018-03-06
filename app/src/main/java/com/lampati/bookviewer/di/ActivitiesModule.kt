package com.lampati.bookviewer.di

import com.lampati.bookviewer.bookList.BookListActivity
import dagger.Module


/**
 * Created by FEDE on 6/3/2018.
 */
@Module
abstract class ActivitiesModule {

    @ActivityScope
    abstract fun bindBookListActivity(): BookListActivity
}