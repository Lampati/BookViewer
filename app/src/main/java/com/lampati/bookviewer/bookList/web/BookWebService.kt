package com.lampati.bookviewer.bookList.web

import com.lampati.bookviewer.bookList.entities.Book
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Created by FEDE on 6/3/2018.
 */
interface BookWebService {
    @GET("books.json")
    fun getBooks(@Header("Content-Type") contentType: String): Single<Response<List<Book>>>
}