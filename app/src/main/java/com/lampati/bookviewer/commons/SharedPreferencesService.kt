package com.lampati.bookviewer.commons

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


/**
 * Created by FEDE on 6/3/2018.
 */
class SharedPreferencesService(mContext: Context) {

    var mSharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)


}