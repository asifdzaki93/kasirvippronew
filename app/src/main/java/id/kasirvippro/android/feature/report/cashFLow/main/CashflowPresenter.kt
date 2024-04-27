package id.kasirvippro.android.feature.report.cashflow.main

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter

class CashflowPresenter(val context: Context, val view: CashflowContract.View) : BasePresenter<CashflowContract.View>(),
    CashflowContract.Presenter,
    CashflowContract.InteractorOutput {

    private var interactor = CashflowInteractor(this)


    override fun onViewCreated(intent: Intent) {
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
}