package id.kasirvippro.android.rest.callback

interface ResponseCallback<T> {

    fun onRequestSuccess(data: T)
    fun onRequestFailed(errorCode: Int, message: String?)
}