package com.lampati.bookviewer.di

import android.arch.lifecycle.ViewModel
import com.lampati.bookviewer.bookList.BookListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by FEDE on 6/3/2018.
 */
@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(BookListViewModel::class)
    abstract fun bindBookListViewModel(bookListViewModel: BookListViewModel): ViewModel
}