package com.lampati.bookviewer.bookList

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lampati.bookviewer.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class BookListActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var bookListViewModel: BookListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        //bookListViewModel = ViewModelProviders.of(this, viewModelFactory).get(BookListViewModel::class.java)

        setContentView(R.layout.activity_book_list)
    }
}
