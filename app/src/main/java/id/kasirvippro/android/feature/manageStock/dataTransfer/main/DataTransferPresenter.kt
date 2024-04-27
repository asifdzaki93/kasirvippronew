package id.kasirvippro.android.feature.manageStock.dataTransfer.main

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.utils.AppConstant

class DataTransferPresenter(val context: Context, val view: DataTransferContract.View) : BasePresenter<DataTransferContract.View>(),
    DataTransferContract.Presenter,
    DataTransferContract.InteractorOutput {

    private var interactor = DataTransferInteractor(this)


    override fun onViewCreated(intent: Intent) {
        val position = intent.getIntExtra(AppConstant.POSITION,0)
        view.setSelectTab(position)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
}