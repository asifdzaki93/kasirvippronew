package id.kasirvippro.android.feature.report.rawMaterial.main

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter

class RawMaterialPresenter(val context: Context, val view: RawMaterialContract.View) : BasePresenter<RawMaterialContract.View>(),
    RawMaterialContract.Presenter,
    RawMaterialContract.InteractorOutput {

    private var interactor = RawMaterialInteractor(this)


    override fun onViewCreated(intent: Intent) {
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
}