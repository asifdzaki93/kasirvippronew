package id.kasirvippro.android.feature.manageStock.dataHistory

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.transaction.DetailHistory
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.utils.PermissionUtil

class DataHistoryPresenter(val context: Context, val view: DataHistoryContract.View) : BasePresenter<DataHistoryContract.View>(),
    DataHistoryContract.Presenter, DataHistoryContract.InteractorOutput {

    private var interactor: DataHistoryInteractor = DataHistoryInteractor(this)
    private var restModel = TransactionRestModel(context)
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission: PermissionCallback
    private var sort = false
    private var id = String()

    override fun onViewCreated() {
       loadProducts()
    }
    override fun loadProducts() {
        val idproduct = view.getIdProduct()
        interactor.callGetProductsAPI(context,restModel,idproduct!!)
    }
    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetProducts(list: List<DetailHistory>) {
        sort = false
        view.setProducts(list)
    }

    fun setProduct(list: List<DetailHistory>){
        view.setProducts(list)
    }

    override fun onSuccessDeleteProduct(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

}