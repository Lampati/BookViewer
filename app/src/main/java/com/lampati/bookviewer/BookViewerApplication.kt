package com.lampati.bookviewer

import android.app.Activity
import android.app.Application
import com.lampati.bookviewer.di.AppComponent
import com.lampati.bookviewer.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by FEDE on 6/3/2018.
 */
class BookViewerApplication : Application(), HasActivityInjector{

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponent: AppComponent

    companion object {
        val TAG = "BookViewerApplication"
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}