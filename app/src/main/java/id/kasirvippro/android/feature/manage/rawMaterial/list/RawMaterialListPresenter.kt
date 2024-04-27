package id.kasirvippro.android.feature.manage.rawMaterial.list

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.models.rawMaterial.RawMaterialRestModel
import id.kasirvippro.android.utils.PermissionUtil

class RawMaterialListPresenter(val context: Context, val view: RawMaterialListContract.View) : BasePresenter<RawMaterialListContract.View>(),
    RawMaterialListContract.Presenter, RawMaterialListContract.InteractorOutput {

    private var interactor: RawMaterialListInteractor = RawMaterialListInteractor(this)
    private var restModel = RawMaterialRestModel(context)
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission: PermissionCallback
    private var sort = false

    override fun onViewCreated() {
        loadProducts()
    }

    override fun loadProducts() {
        interactor.callGetProductsAPI(context,restModel)
    }

    override fun deleteProduct(id: String,position:Int,increment:String) {

        interactor.callDeleteProductAPI(context,restModel,id)
    }

    override fun searchProduct(search: String) {
        interactor.onRestartDisposable()
        interactor.callGetProductsAPI(context,restModel)
    }
    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetProducts(list: List<RawMaterial>) {
        sort = false
        view.setProducts(list)
    }

    fun setProduct(list: List<RawMaterial>){
        view.setProducts(list)
    }

    override fun onSuccessDeleteProduct(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

}