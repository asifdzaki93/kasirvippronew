package id.kasirvippro.android.feature.scan

import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl


interface ScanCodeContract {
    interface View : BaseViewImpl {
        fun renderView()
        fun resumeCamera()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
    }

    interface Interactor : BaseInteractorImpl {
        fun destroy()

    }

    interface InteractorOutput :BaseInteractorOutputImpl {

    }
}