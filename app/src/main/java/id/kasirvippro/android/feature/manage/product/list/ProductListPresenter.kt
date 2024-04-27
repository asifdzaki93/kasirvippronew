package id.kasirvippro.android.feature.manage.product.list

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.ProductSQLDelete
import id.kasirvippro.android.utils.AppSession
import id.kasirvippro.android.utils.PermissionUtil

class ProductListPresenter(val context: Context, val view: ProductListContract.View) : BasePresenter<ProductListContract.View>(),
    ProductListContract.Presenter, ProductListContract.InteractorOutput {

    private var interactor: ProductListInteractor = ProductListInteractor(this)
    private var restModel = ProductRestModel(context)
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission: PermissionCallback
    private var sort = false

    override fun onViewCreated() {
        cameraPermission = object : PermissionCallback{
            override fun onSuccess() {
                view.openScanPage()
            }

            override fun onFailed() {
                view.showErrorMessage(999,context.getString(R.string.reason_permission_camera))
            }
        }
        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val dataManager = DataManager (context)
        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {

                    loadProducts(1)

                    dataManager.clearCategoryAll()
                    dataManager.clearProductAll()
                    return
                }else{
                    var allproduct = dataManager.allProduct()

                    var productList = mutableListOf<Product>()
                    for (item in allproduct){
                        var product = Product();
                        product.set(item.id_product,item.nama,item.unit,
                            item.img,item.kode,
                            item.idkategori,item.namakategori,
                            item.beli, item.jual,
                            item.stok, item.minstok,
                            item.deskripsi, item.online,
                            item.havestok, item.grosir,
                            item.increment, item.alertstock
                        )
                        productList.add(product)
                    }
                    setProduct(productList);
                }
            }else{
                var allproduct = dataManager.allProduct()

                var productList = mutableListOf<Product>()
                for (item in allproduct){
                    var product = Product();
                    product.set(item.id_product,item.nama,item.unit,
                        item.img,item.kode,
                        item.idkategori,item.namakategori,
                        item.beli, item.jual,
                        item.stok, item.minstok,
                        item.deskripsi, item.online,
                        item.havestok, item.grosir,
                        item.increment, item.alertstock
                    )
                    productList.add(product)
                }
                setProduct(productList);
            }
        }else{
            var allproduct = dataManager.allProduct()

            var productList = mutableListOf<Product>()
            for (item in allproduct){
                var product = Product();
                product.set(item.id_product,item.nama,item.unit,
                    item.img,item.kode,
                    item.idkategori,item.namakategori,
                    item.beli, item.jual,
                    item.stok, item.minstok,
                    item.deskripsi, item.online,
                    item.havestok, item.grosir,
                    item.increment, item.alertstock
                )
                productList.add(product)
            }
            setProduct(productList);
        }
    }
    override fun onCheckScan() {
        permissionUtil.checkCameraPermission(cameraPermission)
    }

    override fun loadProducts(page: Int?) {
       interactor.callGetProductsAPI(context,restModel,page)
        Log.d("Halamanya", page.toString())
    }

    override fun deleteProduct(id: String,position:Int,increment:String) {

        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = context?.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val dataManager = DataManager (context)
        val appSession = AppSession()
        val key = appSession.getToken(context)

        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    interactor.callDeleteProductAPI(context,restModel,id)
                }else{
                    val product = ProductSQLDelete(increment,key!!,id)
                    var result1 = dataManager.addProductDelete(product!!)
                    var result = dataManager.clearProduct(increment)
                }
            }else{
                val product = ProductSQLDelete(increment,key!!,id)
                var result1 = dataManager.addProductDelete(product)
                var result = dataManager.clearProduct(increment)
            }
        }else{
            val product = ProductSQLDelete(increment,key!!,id)
            var result1 = dataManager.addProductDelete(product!!)
            var result = dataManager.clearProduct(increment)
        }
        Toast.makeText(context,"Hapus Local", Toast.LENGTH_SHORT).show()
        (context as ProductListActivity).list2.removeAt(position)
        (context as ProductListActivity).setList()
        (context as ProductListActivity).hideLoading()
    }

    override fun searchProduct(search: String) {
        interactor.onRestartDisposable()
        if(search.isNullOrEmpty() || search.isNullOrBlank()){
            interactor.callGetProductsAPI(context,restModel, 1)
        }
        else{


            var connectivity : ConnectivityManager? = null
            var info : NetworkInfo? = null
            connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            val dataManager = DataManager (context)
            if ( connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                        interactor.callSearchProductAPI(context,restModel,search)
                    }else{
                        var searchproduct = dataManager.searchProduct(search)

                        var productList = mutableListOf<Product>()
                        for (item in searchproduct){
                            var product = Product();
                            product.set(item.id_product,item.nama,item.unit,
                                item.img,item.kode,
                                item.idkategori,item.namakategori,
                                item.beli, item.jual,
                                item.stok, item.minstok,
                                item.deskripsi, item.online,
                                item.havestok, item.grosir,
                                item.increment, item.alertstock
                            )
                            productList.add(product)
                        }
                        setProduct(productList);
                    }
                }else{
                    var searchproduct = dataManager.searchProduct(search)

                    var productList = mutableListOf<Product>()
                    for (item in searchproduct){
                        var product = Product();
                        product.set(item.id_product,item.nama,item.unit,
                            item.img,item.kode,
                            item.idkategori,item.namakategori,
                            item.beli, item.jual,
                            item.stok, item.minstok,
                            item.deskripsi, item.online,
                            item.havestok, item.grosir,
                            item.increment, item.alertstock
                        )
                        productList.add(product)
                    }
                    setProduct(productList);
                }
            }else{
                var searchproduct = dataManager.searchProduct(search)

                var productList = mutableListOf<Product>()
                for (item in searchproduct){
                    var product = Product();
                    product.set(item.id_product,item.nama,item.unit,
                        item.img,item.kode,
                        item.idkategori,item.namakategori,
                        item.beli, item.jual,
                        item.stok, item.minstok,
                        item.deskripsi, item.online,
                        item.havestok, item.grosir,
                        item.increment, item.alertstock
                    )
                    productList.add(product)
                }
                setProduct(productList);
            }
        }
    }

    override fun searchByBarcode(search: String) {
        interactor.callSearchByBarcodeAPI(context,restModel,search)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetProducts(list: List<Product>) {
        sort = false
        view.setData(list)
    }

    fun setProduct(list: List<Product>){
        view.setProducts(list)
    }

    override fun onSuccessDeleteProduct(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

    override fun onSuccessByBarcode(list: List<Product>) {
        if(list.isEmpty()){
            view.showErrorMessage(999,"Produk tidak ditemukan")
            return
        }
        val data = list[0]
        view.openEditPage(data)
    }

    override fun onCheckSort() {
        interactor.onRestartDisposable()
        if(sort){
            interactor.callGetProductsAPI(context,restModel,1)
        }
        else{
            interactor.callSortProductsAPI(context,restModel)
        }
    }

    override fun onSuccessSort(list: List<Product>) {
        sort = true
        view.setProducts(list)
    }


}