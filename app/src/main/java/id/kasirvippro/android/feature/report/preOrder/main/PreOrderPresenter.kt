package id.kasirvippro.android.feature.report.preOrder.main

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter

class PreOrderPresenter(val context: Context, val view: PreOrderContract.View) : BasePresenter<PreOrderContract.View>(),
    PreOrderContract.Presenter,
    PreOrderContract.InteractorOutput {

    private var interactor = PreOrderInteractor(this)


    override fun onViewCreated(intent: Intent) {
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
}