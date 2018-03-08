package com.lampati.bookviewer.bookList

import android.arch.lifecycle.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lampati.bookviewer.R
import com.lampati.bookviewer.bookList.entities.Book
import com.lampati.bookviewer.commons.ResourcesService
import com.lampati.bookviewer.commons.ToastService
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_book_list.*

import java.text.SimpleDateFormat
import javax.inject.Inject
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.DividerItemDecoration




/**
 * Created by FEDE on 6/3/2018.
 */
class BookListActivity : AppCompatActivity(){

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject lateinit var toastService : ToastService

    @Inject lateinit var resourcesService : ResourcesService

    private lateinit var bookListViewModel: BookListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        bookListViewModel = ViewModelProviders.of(this, viewModelFactory).get(BookListViewModel::class.java)

        bookListViewModel.bindInfo()

        setContentView(R.layout.activity_book_list)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        activity_book_list_recycler_view_layout.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(activity_book_list_recycler_view_layout.context,
                linearLayoutManager.orientation)
        activity_book_list_recycler_view_layout.addItemDecoration(dividerItemDecoration)

        val lista = ArrayList<Book>()
        val adapter = BookAdapter(this, lista)
        activity_book_list_recycler_view_layout.adapter = adapter



        bookListViewModel.bookList.reObserve(this,  Observer {
            it?.let {
                (activity_book_list_recycler_view_layout.adapter as BookAdapter).refrehList(it)
            }
        })

        bookListViewModel.ultimaActualizacion.reObserve(this,  Observer {

            if (it == null){
                showLoading()
                bookListViewModel.refreshList({hideLoading()})
            }else{
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                activity_book_list_last_update.text = resourcesService.getString(R.string.last_update_time, sdf.format(it))
            }
        })

        activity_book_list_swipe_refresh_layout.setOnRefreshListener {
            showLoading()
            bookListViewModel.refreshList({hideLoading()})
        }
    }

    fun showLoading(){
        activity_book_list_loading_frame.visibility = View.VISIBLE
    }

    fun hideLoading(){
        if (activity_book_list_swipe_refresh_layout.isRefreshing){
            activity_book_list_swipe_refresh_layout.isRefreshing = false
        }

        activity_book_list_loading_frame.visibility = View.GONE
    }

    //reObserve so observers dont stack
    fun <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
        removeObserver(observer)
        observe(owner, observer)
    }

}
