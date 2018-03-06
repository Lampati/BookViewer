package com.lampati.bookviewer.bookList.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.lampati.bookviewer.bookList.entities.Book

/**
 * Created by FEDE on 6/3/2018.
 */

@Dao
abstract class BookDao {

    @Query("SELECT * FROM Book")
    abstract fun getAll(): LiveData<List<Book>>

}