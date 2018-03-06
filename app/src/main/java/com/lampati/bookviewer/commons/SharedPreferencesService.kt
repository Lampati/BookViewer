package com.lampati.bookviewer.commons

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import java.util.*


/**
 * Created by FEDE on 6/3/2018.
 */
class SharedPreferencesService(mContext: Context) {

    var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)

    val lastBookFetchTimeKey = "lastBookFetchTimeKey"

    fun putLastBookFetchTime(value: Date) {
        val editor = sharedPreferences.edit()


        editor.putLong(lastBookFetchTimeKey, value.time)
        editor.apply()
    }

    fun getLastBookFetchTime() : Date? {
        val millis = sharedPreferences.getLong(lastBookFetchTimeKey, 0)

        if (millis > 0){
            return Date(millis)
        }else{
            return null
        }
        
        
    }
}