package id.kasirvippro.android.feature.manage.supplier.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.utils.AppConstant

class DetailPresenter(val context: Context, val view: DetailContract.View) : BasePresenter<DetailContract.View>(),
    DetailContract.Presenter,
    DetailContract.InteractorOutput {

    private var interactor = DetailInteractor(this)
    private var title = ""
    private var data : Supplier?= null


    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as Supplier
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        data?.let {
            title = it.name_supplier!!
            view.setCustomer(it.name_supplier,it.email,it.telephone,it.address)
        }
    }

    override fun getTitleName(): String {
        return title
    }

    override fun setSupplierData(dt: Supplier) {
        data = dt
        data?.let {
            title = it.name_supplier!!
            view.setCustomer(it.name_supplier,it.email,it.telephone,it.address)
        }
    }

    override fun getSupplierData(): Supplier? {
        return data
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
}