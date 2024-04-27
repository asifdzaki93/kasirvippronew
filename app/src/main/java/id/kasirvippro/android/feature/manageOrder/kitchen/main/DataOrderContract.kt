package id.kasirvippro.android.feature.manageOrder.kitchen.main

import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.DialogModel

interface DataOrderContract {

    interface View : BaseViewImpl {
        fun checkTab(position:Int)
        fun setSelectTab(position:Int)
        fun openFilterByStatusDialog(title: String, list: List<DialogModel>, selected: DialogModel?, type: Int)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onDestroy()
        fun onViewCreated(intent: Intent)

    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()

    }

    interface InteractorOutput : BaseInteractorOutputImpl {

    }


}