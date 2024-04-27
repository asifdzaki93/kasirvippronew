package id.kasirvippro.android.feature.sell.paymentNonTunai

import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl

interface WebViewContract {
    interface View : BaseViewImpl {
        fun renderView()
        fun loadUrl(url:String)
        fun openSuccessPage()
        fun onClose()
    }

    interface Presenter :
        BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun getInvoice():String?
        fun setUrl(data:String?)
    }

    interface Interactor : BaseInteractorImpl {
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
    }
}