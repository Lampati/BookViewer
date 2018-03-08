package com.lampati.bookviewer.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.lampati.bookviewer.bookList.daos.BookDao
import com.lampati.bookviewer.bookList.entities.Book

/**
 * Created by FEDE on 6/3/2018.
 */

@Database(entities = [(Book::class)],
        version = 1, exportSchema = false)
abstract class BookViewerDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao


}