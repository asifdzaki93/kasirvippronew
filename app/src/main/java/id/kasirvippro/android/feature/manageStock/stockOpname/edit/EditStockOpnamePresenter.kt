package id.kasirvippro.android.feature.manageStock.stockOpname.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.utils.AppConstant


class EditStockOpnamePresenter(val context: Context, val view: EditStockOpnameContract.View) : BasePresenter<EditStockOpnameContract.View>(),
    EditStockOpnameContract.Presenter, EditStockOpnameContract.InteractorOutput {

    private var interactor = EditStockOpnameInteractor(this)
    private var restModel = ProductRestModel(context)
    private var data : Product ?= null
    private var newdata : Product ?= null
    private var premium:Boolean = false


    override fun onViewCreated(intent:Intent) {

        data = intent.getSerializableExtra(AppConstant.DATA) as Product
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED,data)
            return
        }

        checkProduct()
        premium = "1" == interactor.getUserPaket(context)
        //view.onPremiumPage(premium)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
    override fun onCheck(stok: String, reason: String) {

        if(stok.isBlank() || stok.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_stock))
            return
        }

        newdata = Product()
        newdata?.stock = stok

        interactor.callEditProductAPI(context,restModel,data?.id_product!!, stok, reason)
        return
    }

    override fun onSuccessEditProduct(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK,newdata)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }
    private fun checkProduct(){
        data?.let {
            view.setStock(it.stock!!)
        }
    }

}