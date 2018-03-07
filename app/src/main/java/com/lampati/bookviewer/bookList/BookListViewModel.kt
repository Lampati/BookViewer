package com.lampati.bookviewer.bookList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.lampati.bookviewer.BookViewerApplication
import com.lampati.bookviewer.bookList.entities.Book
import com.lampati.bookviewer.commons.ToastService
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import javax.inject.Inject

/**
 * Created by FEDE on 6/3/2018.
 */
class BookListViewModel @Inject constructor(private val bookRepository: BookRepository) : ViewModel(){

    @Inject
    lateinit var toastService: ToastService

    lateinit var bookList: LiveData<List<Book>>

    val ultimaActualizacion : MutableLiveData<Date?> = MutableLiveData()

    fun bindInfo(){
        bookList = bookRepository.getBooks()
        ultimaActualizacion.value = bookRepository.getLastRefreshTime()
    }

    fun refreshList(){
        try{
            bookRepository.refeshBookList()
                    .subscribeWith( object: DisposableCompletableObserver(){
                        override fun onError(e: Throwable) {
                            Log.e(BookViewerApplication.TAG, "Error on refreshList", e)
                            e.message?.let { toastService.showShortToast(it) }

                        }
                        override fun onComplete() {
                            Log.i(BookViewerApplication.TAG, "Success on refreshList")
                            ultimaActualizacion.value = bookRepository.getLastRefreshTime()
                        }
                    })
        }catch(e: Exception){
            Log.e(BookViewerApplication.TAG, "Error on refreshList", e)
            e.message?.let { toastService.showShortToast(it) }
        }
    }
}