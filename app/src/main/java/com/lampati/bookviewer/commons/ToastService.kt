package com.lampati.bookviewer.commons

import android.content.Context
import android.widget.Toast

/**
 * Created by FEDE on 6/3/2018.
 */
class ToastService(private val mContext: Context) {

    fun showLongToast( mensaje: String){
        Toast.makeText(mContext,mensaje, Toast.LENGTH_LONG).show()
    }

    fun showShortToast( mensaje: String){
        Toast.makeText(mContext,mensaje, Toast.LENGTH_SHORT).show()
    }
}