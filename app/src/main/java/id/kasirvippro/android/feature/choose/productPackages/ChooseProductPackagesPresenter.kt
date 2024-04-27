package id.kasirvippro.android.feature.choose.productPackages

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.utils.AppConstant

class ChooseProductPackagesPresenter(val context: Context, val view: ChooseProductPackagesContract.View) : BasePresenter<ChooseProductPackagesContract.View>(),
    ChooseProductPackagesContract.Presenter, ChooseProductPackagesContract.InteractorOutput {

    private var interactor  = ChooseProductPackagesInteractor(this)
    private var restModel = ProductRestModel(context)
    private var id = ""
    private var haveStok = ""
    private var product:Product? = null
    private var products:ArrayList<Product> = ArrayList()

    override fun onViewCreated(intent: Intent) {
        val check = intent.getBooleanExtra(AppConstant.DATA,true)
        id = if(check){
            ""
        }
        else{
            "1"
        }
        view.checkStockProducts(check)
        loadProducts(1)
    }

    override fun loadProducts(page: Int?) {
        interactor.callGetProductsAPI(context,restModel,id,page)
    }

    override fun searchProduct(search: String) {
        interactor.onRestartDisposable()
        if(search.isEmpty() || search.isBlank()){
            interactor.callGetProductsAPI(context,restModel,id,1)
        }
        else{
            interactor.callSearchProductAPI(context,restModel,search,id)
        }
    }

    override fun onCheckVariable(id_product:String) {
        if(products.isEmpty()){
            interactor.callGetProductsVariableAPI(context,restModel,id_product,haveStok)
        }
        else{
            interactor.callGetProductsVariableAPI(context,restModel,id_product,haveStok)
        }
    }

    override fun setSelectedProduct(data: Product) {
        view.onSelected(data)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetProducts(list: List<Product>) {
        view.setProducts(list)
    }

    override fun onSuccessGetProductsVariable(list: List<Product>) {
        if(list.isEmpty()){
            view.showMessage(999,"No product yet")
            return
        }
        products = ArrayList()
        for(cat in list){
            val model = Product()
            model.id_product = cat.id_product
            model.name_product = cat.name_product
            model.img = cat.img
            model.codeproduct = cat.codeproduct
            model.id_category = cat.id_category
            model.name_category = cat.name_category
            model.active = cat.active
            model.selling_price = cat.selling_price
            model.purchase_price = cat.purchase_price
            model.stock = cat.stock
            model.minimalstock = cat.minimalstock
            model.description = cat.description
            model.discount = cat.discount
            model.posisi = cat.posisi
            model.online = cat.online
            model.have_stock = cat.have_stock
            model.wholesale_price = cat.wholesale_price
            model.inc = cat.inc
            products.add(model)
        }
        Log.d("products", Gson().toJson(products))
        view.openProducts("Product Variant",products,product)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}