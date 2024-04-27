package id.kasirvippro.android.feature.manage.customer.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.utils.AppConstant

class CustomerDetailPresenter(val context: Context, val view: CustomerDetailContract.View) : BasePresenter<CustomerDetailContract.View>(),
    CustomerDetailContract.Presenter,
    CustomerDetailContract.InteractorOutput {

    private var interactor = CustomerDetailInteractor(this)
    private var title = ""
    private var data : Customer?= null


    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as Customer
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        data?.let {
            title = it.name_customer!!
            view.setCustomer(it.name_customer,it.email,it.telephone,it.address)
        }
    }

    override fun getTitleName(): String {
        return title
    }

    override fun setCustomerData(dt: Customer) {
        data = dt
        data?.let {
            title = it.name_customer!!
            view.setCustomer(it.name_customer,it.email,it.telephone,it.address)
        }
    }

    override fun getCustomerData(): Customer? {
        return data
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
}