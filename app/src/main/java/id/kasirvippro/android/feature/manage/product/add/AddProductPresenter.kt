package id.kasirvippro.android.feature.manage.product.add

import android.app.Activity
import android.app.Service
import android.content.Context
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
import id.kasirvippro.android.sqlite.Model.ProductSQLAdd
import id.kasirvippro.android.utils.AppSession
import id.kasirvippro.android.utils.PermissionUtil


class AddProductPresenter(val context: Context, val view: AddProductContract.View) : BasePresenter<AddProductContract.View>(),
    AddProductContract.Presenter, AddProductContract.InteractorOutput {

    private var interactor = AddProductInteractor(this)
    private var restModel = ProductRestModel(context)
    private var categoryRestModel = CategoryRestModel(context)
    private var categories:ArrayList<DialogModel> = ArrayList()
    private var category:DialogModel? = null
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

    override fun onCheck(name: String, unit: String, buy: String, sell: String, stok: String, minstok: String, desc: String, barcode:String,hargagrosir:String,tax:String,alertstock:String) {
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
        if(grosir == 1){

            if(hargagrosir.isBlank() || hargagrosir.isEmpty() || "0" == hargagrosir){
                view.showMessage(999,context.getString(R.string.err_empty_grosir_price))
                return
            }

            if ( connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                        interactor.callAddProductAPI(context,restModel,name,unit,barcode,category?.id!!,sell,buyText,stok,minstok,photoPath,desc,grosir.toString(),haveStock.toString(),hargagrosir,tax,alertstock)
                        return
                    }
                }
            }
            val productAdd =
                    ProductSQLAdd(
                        "0",
                        key!!,
                        name,
                        unit,
                        barcode,
                        category?.id!!,
                        buyText,
                        sell,
                        stok,
                        minstok,
                        desc,
                        grosir.toString(),
                        haveStock.toString(),
                        hargagrosir,
                        tax,
                        alertstock,
                        photoPath.toString()
                    )
            val product =
                ProductSQL(
                    "0",
                    "0",
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
                    grosir.toString(),
                    haveStock.toString(),
                    hargagrosir,
                    tax,
                    alertstock,
                    photoPath.toString(),
                )
            var result = dataManager.addProductAdd(productAdd!!)
            var result2 = dataManager.addProduct(product!!)
            Toast.makeText(context,"Tambah Local",Toast.LENGTH_SHORT).show()
            (context as AddProductActivity).hideLoading()
            view.onClose(Activity.RESULT_OK)
            (context as Activity).finish()
        }
        else{
            if(haveStock == 1){

            }

            if ( connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                        interactor.callAddProductAPI(
                            context,
                            restModel,
                            name,
                            unit,
                            barcode,
                            category?.id!!,
                            sell,
                            buyText,
                            stok,
                            minstok,
                            photoPath,
                            desc,
                            grosir.toString(),
                            haveStock.toString(),
                            "",
                            tax,
                            alertstock)
                        return
                    }
                }
            }
            val productAdd =
                    ProductSQLAdd(
                        "0",
                        key!!,
                        name,
                        unit,
                        barcode,
                        category?.id!!,
                        buyText,
                        sell,
                        stok,
                        minstok,
                        desc,
                        grosir.toString(),
                        haveStock.toString(),
                        grosir.toString(),
                        tax.toString(),
                        alertstock,
                        photoPath.toString()
                    )
            val product =
                ProductSQL(
                    "0",
                    "0",
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
                    grosir.toString(),
                    haveStock.toString(),
                    grosir.toString(),
                    tax.toString(),
                    alertstock.toString(),
                    photoPath.toString()
                )
            var result = dataManager.addProductAdd(productAdd!!)
            var result2 = dataManager.addProduct(product!!)
            Toast.makeText(context,"Add Local",Toast.LENGTH_SHORT).show()
            (context as AddProductActivity).hideLoading()
            view.onClose(Activity.RESULT_OK)
            (context as Activity).finish()
        }
    }

    override fun onSuccessAddProduct(msg: String?) {
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
            view.openCategories("Select Category",categories!!,category)
        }
    }

    override fun onSuccessGetCategories(list: List<Category>) {
        if(list.isEmpty()){
            view.showMessage(999,"There are no categories yet")
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
        view.openCategories("Select Category",categories!!,category)
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