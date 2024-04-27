package id.kasirvippro.android.feature.choose.customer

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.customer.CustomerRestModel

class ChooseCustomerPresenter(val context: Context, val view: ChooseCustomerContract.View) : BasePresenter<ChooseCustomerContract.View>(),
    ChooseCustomerContract.Presenter, ChooseCustomerContract.InteractorOutput {

    private var interactor  = ChooseCustomertInteractor(this)
    private var restModel = CustomerRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetDataAPI(context,restModel)
    }

    override fun search(search: String) {
        interactor.onRestartDisposable()
        if(search.isNullOrEmpty() || search.isNullOrBlank()){
            interactor.callGetDataAPI(context,restModel)
        }
        else{
            interactor.callSearchAPI(context,restModel,search)
        }
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    fun setCustomer(list: List<Customer>){
        view.setData(list)
    }

    override fun onSuccessGetData(list: List<Customer>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}