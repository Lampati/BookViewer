package com.lampati.bookviewer.bookList.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.lampati.bookviewer.bookList.entities.Book

/**
 * Created by FEDE on 6/3/2018.
 */

@Dao
abstract class BookDao {

    @Query("SELECT title, imageURL, author FROM Book")
    abstract fun getAll(): LiveData<List<Book>>

    @Query("DELETE FROM Book")
    abstract fun truncate()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract  fun insert( vararg  elem: Book)

    @Transaction
    open fun truncateAndInsert( vararg  elem: Book){
        truncate()
        insert(*elem)
    }

}