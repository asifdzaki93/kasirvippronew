package id.kasirvippro.android.feature.manage.productVariant.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.PermissionUtil


class EditProductVariantPresenter(val context: Context, val view: EditProductVariantContract.View) : BasePresenter<EditProductVariantContract.View>(),
    EditProductVariantContract.Presenter, EditProductVariantContract.InteractorOutput {

    private var interactor = EditProductVariantInteractor(this)
    private var restModel = ProductRestModel(context)
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission:PermissionCallback
    private lateinit var photoPermission:PermissionCallback
    private var photoPath:String? = null
    private var data : Product ?= null
    private var haveStock = 0
    private var grosirOnline = 0
    private var premium:Boolean = false


    override fun onViewCreated(intent:Intent) {
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

        data = intent.getSerializableExtra(AppConstant.DATA) as Product
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        checkProduct()
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

    override fun onCheck(name: String, unit: String, buy: String, sell: String, stok: String, minstok: String, desc: String, barcode:String,grosir:String,tax:String,alertstock:String) {
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


        if(grosirOnline == 1){

            if(grosir.isBlank() || grosir.isEmpty() || "0" == grosir){
                view.showMessage(999,context.getString(R.string.err_empty_grosir_price))
                return
            }

            interactor.callEditProductAPI(context,restModel,data?.id_product!!,name,unit,barcode,data?.id_category!!,sell,buyText,stok,minstok,photoPath,desc,grosirOnline.toString(),haveStock.toString(),grosir,tax,alertstock)
        }
        else{
            interactor.callEditProductAPI(context,restModel,data?.id_product!!,name,unit,barcode,data?.id_category!!,sell,buyText,stok,minstok,photoPath,desc,grosirOnline.toString(),haveStock.toString(),"",tax,alertstock)
        }
    }

    override fun onSuccessEditProduct(msg: String?) {
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
            val product = list[0]
            this.data = product
            checkProduct()
        }


    }

    override fun onFailedByBarcode(code: Int, msg: String) {
        view.hideLoadingDialog()
    }

    private fun checkProduct(){
        data?.let {
            val cat = DialogModel()
            if(!it.id_category.isNullOrBlank() && !it.id_category.isNullOrEmpty() && it.id_category != "0" &&
                !it.name_category.isNullOrBlank() && !it.name_category.isNullOrEmpty()){
                cat.id = it.id_category
                cat.value = it.name_category
            }
            view.loadPhoto(it.img!!)
            view.setProductName(it.name_product!!)
            view.setBuyPrice(it.purchase_price!!)
            view.setSellPrice(it.selling_price!!)
            view.setTax(it.tax!!)
            view.setStock(it.stock!!)
            view.setAlertstock(it.alertstock!!)
            view.setMinStock(it.minimalstock!!)
            view.setDescription(it.description!!)
            view.setBarcode(it.codeproduct!!)

            if("0" == it.have_stock){
                view.setHaveStock(false)
            }
            else{
                view.setHaveStock(true)
            }

            if("0" == it.online){
                view.setGrosir(false)
               view.setGrosirPrice("")
            }
            else{
                view.setGrosir(true)
               view.setGrosirPrice(it.wholesale_price!!)

            }
        }
    }

    override fun setHaveStock(value: Int) {
        haveStock = value
    }

    override fun setGrosir(value: Int) {
        grosirOnline = value
    }


}