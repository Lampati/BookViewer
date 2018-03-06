package com.lampati.bookviewer.bookList

import com.lampati.bookviewer.base.RepositoryBase
import com.lampati.bookviewer.bookList.daos.BookDao
import com.lampati.bookviewer.commons.ResourcesService
import com.lampati.bookviewer.commons.SharedPreferencesService
import com.lampati.bookviewer.commons.ToastService
import javax.inject.Singleton

/**
 * Created by FEDE on 6/3/2018.
 */

@Singleton
class BookRepository(private val bookDao: BookDao,
                     sharedPreferencesService: SharedPreferencesService,
                     toastService: ToastService,
                     resourcesService: ResourcesService):
        RepositoryBase(sharedPreferencesService, toastService, resourcesService) {
}