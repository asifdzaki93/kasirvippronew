package id.kasirvippro.android.feature.manageOrder.main

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter

class ManageOrderPresenter(val context: Context, val view: ManageOrderContract.View) : BasePresenter<ManageOrderContract.View>(),
    ManageOrderContract.Presenter,
    ManageOrderContract.InteractorOutput {

    private var interactor = ManageOrderInteractor(this)


    override fun onViewCreated(intent: Intent) {
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
}