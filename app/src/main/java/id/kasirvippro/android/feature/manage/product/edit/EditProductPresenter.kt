package id.kasirvippro.android.feature.manage.product.edit

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.gson.Gson
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.category.CategoryRestModel
import android.util.Log
import android.widget.Toast
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.category.Category
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.ProductSQL
import id.kasirvippro.android.sqlite.Model.ProductSQLUpdate
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import id.kasirvippro.android.utils.PermissionUtil


class EditProductPresenter(val context: Context, val view: EditProductContract.View) : BasePresenter<EditProductContract.View>(),
    EditProductContract.Presenter, EditProductContract.InteractorOutput {

    private var interactor = EditProductInteractor(this)
    private var restModel = ProductRestModel(context)
    private var categoryRestModel = CategoryRestModel(context)
    private var categories:ArrayList<DialogModel> = ArrayList()
    private var category:DialogModel? = null
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

    override fun onVariant() {
        view.openVariantPage(data?.id_product!!)
    }

    override fun onRecipe() {
        val detail = data?.name_product!!
        view.openRecipePage(data?.id_product!!, detail)
    }

    override fun onPriceVariant() {
        val detail = data?.name_product + " (" + data?.unit + ")"
        view.openPriceVariantPage(data?.id_product!!, detail)
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

        if(category == null){
            view.showMessage(999,context.getString(R.string.err_empty_product_category))
            return
        }

        if(unit.isBlank() || unit.isEmpty() || "0" == unit){
            view.showMessage(999,context.getString(R.string.err_empty_unit))
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

        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = context?.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val dataManager = DataManager (context)
        val appSession = AppSession()
        val key = appSession.getToken(context)

        if(grosirOnline == 1){

            if(grosir.isBlank() || grosir.isEmpty() || "0" == grosir){
                view.showMessage(999,context.getString(R.string.err_empty_grosir_price))
                return
            }

            if ( connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                        interactor.callEditProductAPI(
                            context,restModel,data?.id_product!!,
                            name,unit,barcode,category?.id!!,sell,buyText,
                            stok,minstok,photoPath,desc,grosirOnline.toString(),
                            haveStock.toString(),grosir,tax,alertstock)
                        return
                    }
                }
            }
            var productUpdate = ProductSQLUpdate(
                data?.inc!!,
                key!!,
                data?.id_product!!,
                name,
                unit,
                barcode,
                category?.id!!,
                buyText,
                sell,
                stok,
                minstok,
                desc,
                grosirOnline.toString(),
                haveStock.toString(),
                grosir,
                tax,
                alertstock,
                photoPath.toString()
            )
            val product =
                ProductSQL(
                    data?.inc!!,
                    data?.id_product!!,
                    key!!,
                    name,
                    unit,
                    barcode,
                    category?.id!!,
                    category?.value!!,
                    "",
                    buyText,
                    sell,
                    stok,
                    minstok,
                    desc,
                    grosirOnline.toString(),
                    haveStock.toString(),
                    grosir,
                    tax,
                    alertstock,
                    photoPath.toString()
                )
            var result = dataManager.addProductUpdate(productUpdate!!)
            var result2 = dataManager.updateProduct(product!!)
            Toast.makeText(context,"Update Local", Toast.LENGTH_SHORT).show()
            (context as EditProductActivity).hideLoading()
            view.onClose(Activity.RESULT_OK)
            (context as Activity).finish()

        }
        else{
            if ( connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                        interactor.callEditProductAPI(context,restModel,data?.id_product!!,
                            name,unit,barcode,category?.id!!,sell,buyText,
                            stok,minstok,photoPath,desc,grosirOnline.toString(),
                            haveStock.toString(),"",tax,alertstock)
                        return
                    }
                }
            }
            val productUpdate = ProductSQLUpdate(
                        data?.inc!!,
                        key!!,
                        data?.id_product!!,
                        name,
                        unit,
                        barcode,
                        category?.id!!,
                        buyText,
                        sell,
                        stok,
                        minstok,
                        desc,
                        grosirOnline.toString(),
                        haveStock.toString(),
                        grosir.toString(),
                        tax.toString(),
                        alertstock,
                        photoPath.toString()
                    )
            val product =
                ProductSQL(
                    data?.inc!!,
                    data?.id_product!!,
                    key!!,
                    name,
                    unit,
                    barcode,
                    category?.id!!,
                    category?.value!!,
                    "",
                    buyText,
                    sell,
                    stok,
                    minstok,
                    desc,
                    grosirOnline.toString(),
                    haveStock.toString(),
                    grosir.toString(),
                    tax.toString(),
                    alertstock,
                    photoPath.toString()
                )
            var result = dataManager.addProductUpdate(productUpdate!!)
            var result2 = dataManager.updateProduct(product!!)
            Toast.makeText(context,"Update Local",Toast.LENGTH_SHORT).show()
            (context as EditProductActivity).hideLoading()
            view.onClose(Activity.RESULT_OK)
            (context as Activity).finish()

        }
    }

    override fun onSuccessEditProduct(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun onCheckCategory() {
        if(categories.isEmpty()){
            interactor.callGetCategoriesAPI(context,categoryRestModel)
        }
        else{
            view.openCategories("Select Category",categories,category)
        }
    }

    override fun onSuccessGetCategories(list: List<Category>) {
        if(list.isEmpty()){
            view.showMessage(999,"No categories yet")
            return
        }
        categories = ArrayList()
        for(cat in list){
            val model = DialogModel()
            model.id = cat.id_category
            model.value = cat.name_category
            categories.add(model)
        }
        Log.d("categories",Gson().toJson(categories))
        view.openCategories("Select Category",categories,category)
    }

    override fun setSelectedCategory(data: DialogModel) {
        category = data
        view.setCategoryName(data.value!!)
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
            setSelectedCategory(cat)
            view.loadPhoto(it.img!!)
            view.setProductName(it.name_product!!)
            view.setUnitName(it.unit!!)
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