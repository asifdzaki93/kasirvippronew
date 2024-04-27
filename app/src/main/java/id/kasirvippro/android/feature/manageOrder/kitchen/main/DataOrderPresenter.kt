package id.kasirvippro.android.feature.manageOrder.kitchen.main

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.utils.AppConstant

class DataOrderPresenter(val context: Context, val view: DataOrderContract.View) : BasePresenter<DataOrderContract.View>(),
    DataOrderContract.Presenter,
    DataOrderContract.InteractorOutput {

    private var interactor = DataOrderInteractor(this)


    override fun onViewCreated(intent: Intent) {
        val position = intent.getIntExtra(AppConstant.POSITION,0)
        view.setSelectTab(position)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
}