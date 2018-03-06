package com.lampati.bookviewer.bookList.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity

/**
 * Created by FEDE on 6/3/2018.
 */
@Entity(primaryKeys = ["title"])
data class Book(
        @ColumnInfo(name = "title") var title: String,
        @ColumnInfo(name = "imageURL") var imageURL: String,
        @ColumnInfo(name = "author") var author: String)