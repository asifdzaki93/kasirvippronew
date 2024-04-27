package id.kasirvippro.android.feature.manage.productVariant.add

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.utils.PermissionUtil


class AddProductVariantPresenter(val context: Context, val view: AddProductVariantContract.View) : BasePresenter<AddProductVariantContract.View>(),
    AddProductVariantContract.Presenter, AddProductVariantContract.InteractorOutput {

    private var interactor = AddProductVariantInteractor(this)
    private var restModel = ProductRestModel(context)
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission:PermissionCallback
    private lateinit var photoPermission:PermissionCallback
    private var photoPath:String? = null
    private var haveStock = 0
    private var grosir = 0
    private var premium:Boolean = false

    override fun onViewCreated() {
        cameraPermission = object : PermissionCallback{
            override fun onSuccess() {
                view.openScanPage()
            }

            override fun onFailed() {
                view.showMessage(999,context.getString(R.string.reason_permission_camera))
            }
        }

        photoPermission = object : PermissionCallback{
            override fun onSuccess() {
                view.openImageChooser()
            }

            override fun onFailed() {
                view.showMessage(999,context.getString(R.string.reason_permission_camera))
            }
        }
        premium = "1" == interactor.getUserPaket(context)
        //view.onPremiumPage(premium)

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onCheckScan() {
        permissionUtil.checkCameraPermission(cameraPermission)
    }

    override fun onCheckPhoto() {
        permissionUtil.checkCameraPermission(photoPermission)
    }

    override fun setImagePhotoPath(path: String?) {
        photoPath = path
    }

    override fun onCheck(name: String, buy: String, sell: String, stok: String, minstok: String, desc: String, barcode:String,hargagrosir:String,tax:String,alertstock:String) {
        if(name.isBlank() || name.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_product_name))
            return
        }

        if(sell.isBlank() || sell.isEmpty() || "0" == sell){
            view.showMessage(999,context.getString(R.string.err_empty_sell))
            return
        }

        val buyText = if(buy.isBlank() || buy.isEmpty()){
            "0"
        } else{
            buy
        }
        val idproduct = view.getIdProduct()

        if(grosir == 1){

            if(hargagrosir.isBlank() || hargagrosir.isEmpty() || "0" == hargagrosir){
                view.showMessage(999,context.getString(R.string.err_empty_grosir_price))
                return
            }

            interactor.callAddProductAPI(context,restModel,name,barcode,idproduct!!,sell,buyText,stok,minstok,photoPath,desc,grosir.toString(),haveStock.toString(),hargagrosir,tax,alertstock)
        }
        else{
            if(haveStock == 1){
                if(buy.isBlank() || buy.isEmpty() || "0" == buy){
                    view.showMessage(999,context.getString(R.string.err_empty_buy))
                    return
                }
            }
            interactor.callAddProductAPI(context,restModel,name,barcode,idproduct!!,sell,buyText,stok,minstok,photoPath,desc,grosir.toString(),haveStock.toString(),"",tax,alertstock)
        }


    }

    override fun onSuccessAddProduct(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }


    override fun searchByBarcode(search:String)
    {
        interactor.callSearchByBarcodeAPI(context,restModel,search)
    }

    override fun onSuccessByBarcode(list: List<Product>) {
        view.hideLoadingDialog()
        if(list.isNotEmpty()){
            val data = list[0]
            view.openEditPage(data)
        }


    }

    override fun onFailedByBarcode(code: Int, msg: String) {
        view.hideLoadingDialog()
    }
    override fun setHaveStock(value: Int) {
        haveStock = value
    }

    override fun setGrosir(value: Int) {
        grosir = value
    }




}