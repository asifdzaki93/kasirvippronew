package id.kasirvippro.android.feature.addOn.medicalHistory.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.utils.PermissionUtil

class MedicalHistoryListPresenter(val context: Context, val view: MedicalHistoryListContract.View) : BasePresenter<MedicalHistoryListContract.View>(),
    MedicalHistoryListContract.Presenter, MedicalHistoryListContract.InteractorOutput {

    private var interactor: MedicalHistoryListInteractor = MedicalHistoryListInteractor(this)
    private var restModel = CustomerRestModel(context)
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission: PermissionCallback
    private var sort = false

    override fun onViewCreated() {
        loadProducts()
    }
    override fun onCheckScan() {
        permissionUtil.checkCameraPermission(cameraPermission)
    }

    override fun loadProducts() {
        interactor.callGetProductsAPI(context,restModel)
    }

    override fun searchProduct(search: String) {
        interactor.onRestartDisposable()
        if(search.isNullOrEmpty() || search.isNullOrBlank()){
            interactor.callGetProductsAPI(context,restModel)
        }
        else{
            interactor.callSearchProductAPI(context,restModel,search)
        }
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetProducts(list: List<Customer>) {
        view.setProducts(list)
    }

    fun setProduct(list: List<Customer>){
        view.setProducts(list)
    }
    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}