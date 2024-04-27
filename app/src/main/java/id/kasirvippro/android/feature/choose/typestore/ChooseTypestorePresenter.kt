package id.kasirvippro.android.feature.choose.typestore

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.typestore.TypeStoreRestModel
import id.kasirvippro.android.models.typestore.TypeStore

class ChooseTypestorePresenter(val context: Context, val view: ChooseTypestoreContract.View) : BasePresenter<ChooseTypestoreContract.View>(),
    ChooseTypestoreContract.Presenter, ChooseTypestoreContract.InteractorOutput {

    private var interactor  = ChooseTypestoreInteractor(this)
    private var restModel = TypeStoreRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetDataAPI(context,restModel)
    }
    override fun onDestroy() {
        interactor.onDestroy()
    }

    fun setCustomer(list: List<TypeStore>){
        view.setData(list)
    }

    override fun onSuccessGetData(list: List<TypeStore>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}