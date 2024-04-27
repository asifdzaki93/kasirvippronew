package id.kasirvippro.android.feature.manage.divisi.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.divisi.Divisi
import id.kasirvippro.android.models.divisi.DivisiRestModel

class DivisiListPresenter(val context: Context, val view: DivisiListContract.View) : BasePresenter<DivisiListContract.View>(),
    DivisiListContract.Presenter, DivisiListContract.InteractorOutput {

    private var interactor: DivisiListInteractor = DivisiListInteractor(this)
    private var categoryRestModel = DivisiRestModel(context)

    override fun onViewCreated() {
        load()
    }

    override fun load() {
        interactor.callGetAPI(context,categoryRestModel)
    }

    fun setCategory(list: List<Divisi>){
        view.set(list)
    }

    override fun delete(id: String,position:Int) {
        interactor.callDeleteAPI(context,categoryRestModel,id)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGet(list: List<Divisi>) {
        view.setData(list)
    }

    override fun onSuccessDelete(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}