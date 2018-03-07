package com.lampati.bookviewer.bookList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.lampati.bookviewer.BookViewerApplication
import com.lampati.bookviewer.bookList.entities.Book
import com.lampati.bookviewer.commons.ToastService
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import javax.inject.Inject

/**
 * Created by FEDE on 6/3/2018.
 */
class BookListViewModel @Inject constructor(private val bookRepository: BookRepository, private val toastService: ToastService) : ViewModel(){
    lateinit var bookList: LiveData<List<Book>>

    fun bindInfo(){
        bookList = bookRepository.getBooks()
    }

    fun lastFetchTime() : Date?{
        return bookRepository.getLastRefreshTime()
    }

    fun refreshList(){
        try{
            bookRepository.refeshBookList()
                    .subscribeWith( object: DisposableSingleObserver<Boolean>(){
                        override fun onError(e: Throwable) {
                            Log.e(BookViewerApplication.TAG, "Error on refreshList", e)
                            e.message?.let { toastService.showShortToast(it) }

                        }
                        override fun onSuccess(result: Boolean) {
                            Log.i(BookViewerApplication.TAG, "Success on refreshList")

                        }
                    })
        }catch(e: Exception){
            Log.e(BookViewerApplication.TAG, "Error on refreshList", e)
            e.message?.let { toastService.showShortToast(it) }
        }
    }
}