package com.lampati.bookviewer.bookList

import android.arch.lifecycle.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lampati.bookviewer.R
import com.lampati.bookviewer.bookList.entities.Book
import com.lampati.bookviewer.commons.ToastService
import dagger.android.AndroidInjection
import javax.inject.Inject

class BookListActivity : AppCompatActivity(){

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject lateinit var toastService : ToastService

    private lateinit var bookListViewModel: BookListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        bookListViewModel = ViewModelProviders.of(this, viewModelFactory).get(BookListViewModel::class.java)

        bookListViewModel.bindInfo()

        bookListViewModel.bookList.reObserve(this,  Observer {
            it?.let {
                toastService.showLongToast(it.size.toString())
            }
        })

        bookListViewModel.ultimaActualizacion.reObserve(this,  Observer {
            if (it == null){
                bookListViewModel.refreshList()
            }
        })

        setContentView(R.layout.activity_book_list)
    }


    fun <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
        removeObserver(observer)
        observe(owner, observer)
    }

}
