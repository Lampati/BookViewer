package com.lampati.bookviewer.base

import com.lampati.bookviewer.commons.ResourcesService
import com.lampati.bookviewer.commons.SharedPreferencesService
import com.lampati.bookviewer.commons.ToastService

/**
 * Created by FEDE on 6/3/2018.
 */
abstract class RepositoryBase(protected val sharedPreferencesService: SharedPreferencesService,
                              protected val toastService: ToastService,
                              protected val resourcesService: ResourcesService) {
}