package id.kasirvippro.android.feature.manageStock.stockHistoryRawMaterial.list

import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.models.rawMaterial.RawMaterialRestModel
import id.kasirvippro.android.utils.PermissionUtil

class StockHistoryListPresenter(val context: Context, val view: StockHistoryListContract.View) : BasePresenter<StockHistoryListContract.View>(),
    StockHistoryListContract.Presenter, StockHistoryListContract.InteractorOutput {

    private var interactor: StockHistoryListInteractor = StockHistoryListInteractor(this)
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

    override fun onSuccessGetProducts(list: List<RawMaterial>) {
        view.setProducts(list)
    }

    fun setProduct(list: List<RawMaterial>){
        view.setProducts(list)
    }
    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}