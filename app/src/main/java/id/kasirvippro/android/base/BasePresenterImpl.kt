package id.kasirvippro.android.base

interface BasePresenterImpl<V : BaseViewImpl> {

    fun attachView(view: V)

    fun detachView()
}