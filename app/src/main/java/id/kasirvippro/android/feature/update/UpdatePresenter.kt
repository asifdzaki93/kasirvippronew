package id.kasirvippro.android.feature.update

import android.content.Context
import id.kasirvippro.android.base.BasePresenter

class UpdatePresenter(val context: Context, val view: UpdateContract.View) : BasePresenter<UpdateContract.View>(),
    UpdateContract.Presenter, UpdateContract.InteractorOutput {

    override fun onViewCreated() {

    }
}