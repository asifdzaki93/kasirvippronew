package id.kasirvippro.android.feature.scan

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter

class ScanCodePresenter(val context: Context, val view: ScanCodeContract.View) : BasePresenter<ScanCodeContract.View>(),
    ScanCodeContract.Presenter, ScanCodeContract.InteractorOutput {

    private val interactor = ScanCodeInteractor(this)

    override fun onViewCreated(intent: Intent) {
    }

    override fun onDestroy() {
        interactor.destroy()
    }
}