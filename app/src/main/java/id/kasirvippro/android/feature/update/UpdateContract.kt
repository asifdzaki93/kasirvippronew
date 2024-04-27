package id.kasirvippro.android.feature.update

import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl

interface UpdateContract {

    interface View : BaseViewImpl {
        fun onClose()
        fun openPlaystore()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()

    }

    interface Interactor : BaseInteractorImpl {

    }

    interface InteractorOutput : BaseInteractorOutputImpl {

    }


}