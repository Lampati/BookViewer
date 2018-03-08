package com.lampati.bookviewer.base

import com.lampati.bookviewer.R
import com.lampati.bookviewer.commons.ResourcesService
import com.lampati.bookviewer.commons.SharedPreferencesService
import com.lampati.bookviewer.commons.ToastService
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

/**
 * Created by FEDE on 6/3/2018.
 */
abstract class RepositoryBase(protected val sharedPreferencesService: SharedPreferencesService,
                              protected val toastService: ToastService,
                              protected val resourcesService: ResourcesService) {


    fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(" http://de-coding-test.s3.amazonaws.com")
                .build()
    }

    fun <T> validateHttpResponse(reponse: Response<T>): Single<T> {

        return Single.fromCallable {
            if (reponse.isSuccessful) {
                reponse.body()
            } else {
                when (reponse.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> throw Exception(resourcesService.getString(R.string.error_404))
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> throw Exception(resourcesService.getString(R.string.error_500))
                    else -> throw Exception(resourcesService.getString(R.string.error_unknown))
                }

            }
        }
    }
}