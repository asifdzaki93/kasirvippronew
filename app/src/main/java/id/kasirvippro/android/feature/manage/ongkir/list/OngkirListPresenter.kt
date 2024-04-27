package id.kasirvippro.android.feature.manage.ongkir.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.ongkir.Ongkir
import id.kasirvippro.android.models.ongkir.OngkirRestModel

class OngkirListPresenter(val context: Context, val view: OngkirListContract.View) : BasePresenter<OngkirListContract.View>(),
    OngkirListContract.Presenter, OngkirListContract.InteractorOutput {

    private var interactor: OngkirListInteractor = OngkirListInteractor(this)
    private var categoryRestModel = OngkirRestModel(context)

    override fun onViewCreated() {
        load()
    }

    override fun load() {
        interactor.callGetAPI(context,categoryRestModel)
    }

    fun setCategory(list: List<Ongkir>){
        view.set(list)
    }

    override fun delete(id: String,position:Int) {
        interactor.callDeleteAPI(context,categoryRestModel,id)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGet(list: List<Ongkir>) {
        view.setData(list)
    }

    override fun onSuccessDelete(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}