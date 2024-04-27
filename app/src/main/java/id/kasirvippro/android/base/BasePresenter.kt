package id.kasirvippro.android.base

import id.kasirvippro.android.BuildConfig

open class BasePresenter<T : BaseViewImpl> : BasePresenterImpl<T> {

    private var view: T? = null

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    fun getView(): T? {
        return this.view
    }

    fun checkedViewAttached() {
        if (!isViewAttached() && BuildConfig.DEBUG) {
            throw ViewNotAttachedException()
        }
    }

    fun isViewAttached(): Boolean {
        return view != null
    }

    companion object {
        class ViewNotAttachedException :
                RuntimeException("Please call Presenter.attachView(view) before" +
                        " requesting data to the Presenter")
    }

}