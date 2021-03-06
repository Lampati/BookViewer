package com.lampati.bookviewer.commons

import android.content.Context

/**
 * Created by FEDE on 6/3/2018.
 */
class ResourcesService(private val mContext: Context) {

    fun getString(id: Int): String {
        return mContext.getString(id)
    }

    open fun getString( id: Int, extra1: Any?): String{
        return mContext.getString(id,extra1)
    }
}