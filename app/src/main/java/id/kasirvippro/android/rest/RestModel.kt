package id.kasirvippro.android.rest

import android.content.Context


@Suppress("DUPLICATE_LABEL_IN_WHEN")
abstract class RestModel<T>(var context: Context?) {

    private val TAG_API_EXCEPTION_FRAGMENT = "TAG_API_EXCEPTION_FRAGMENT"
    private val TAG = RestModel::class.java.simpleName


    var restInterface: T

    init {
        restInterface = createRestInterface()

    }

    abstract fun createRestInterface(): T


}