package id.kasirvippro.android.feature.afiliate.mains

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter

class ManageAfiliatePresenter(val context: Context, val view: ManageAfiliateContract.View) : BasePresenter<ManageAfiliateContract.View>(),
    ManageAfiliateContract.Presenter,
    ManageAfiliateContract.InteractorOutput {

    private var interactor = ManageAfiliateInteractor(this)


    override fun onViewCreated(intent: Intent) {
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
}