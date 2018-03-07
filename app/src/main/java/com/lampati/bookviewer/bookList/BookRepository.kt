package com.lampati.bookviewer.bookList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.lampati.bookviewer.base.RepositoryBase
import com.lampati.bookviewer.bookList.daos.BookDao
import com.lampati.bookviewer.bookList.entities.Book
import com.lampati.bookviewer.bookList.web.BookWebService
import com.lampati.bookviewer.commons.ResourcesService
import com.lampati.bookviewer.commons.SharedPreferencesService
import com.lampati.bookviewer.commons.ToastService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
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


    fun getBooks(): LiveData<List<Book>> {
        return bookDao.getAll()
    }

    fun getLastRefreshTime(): Date? {
       return sharedPreferencesService.getLastBookFetchTime()
    }



    fun refeshBookList() : Single<Boolean> {
        val service = getRetrofit().create(BookWebService::class.java)

        return service.getBooks("application/json")
                .subscribeOn(Schedulers.io())
                .flatMap {
                    validateHttpResponse(it)
                }
                .flatMap {
                    refreshBooksOnDatabase(it.toTypedArray())
                }
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun refreshBooksOnDatabase( elements: Array<Book>): Single<Boolean> {
        return Single.fromCallable {
            bookDao.truncateAndInsert(*elements)
            sharedPreferencesService.putLastBookFetchTime(Date())
            //if no exception we assume it ended ok
            true
        }
    }
}