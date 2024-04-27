package id.kasirvippro.android.feature.sell.add

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.cart.CartRestModel
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.utils.AppConstant

class AddPresenter(val context: Context, val view: AddContract.View) : BasePresenter<AddContract.View>(),
    AddContract.Presenter, AddContract.InteractorOutput {

    private var interactor = AddInteractor(this)
    private var cartRestModel = CartRestModel(context)
    private var barcode:String ?= null

    override fun onViewCreated(intent: Intent) {
        barcode = intent.getStringExtra(AppConstant.DATA)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onCheck(name: String, buy: String, sell: String) {
        if(name.isBlank() || name.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_product_name))
            return
        }

        if(buy.isBlank() || buy.isEmpty() || "0" == buy){
            view.showMessage(999,context.getString(R.string.err_empty_buy))
            return
        }

        if(sell.isBlank() || sell.isEmpty() || "0" == sell){
            view.showMessage(999,context.getString(R.string.err_empty_sell))
            return
        }


        if(barcode.isNullOrEmpty() || barcode.isNullOrBlank()){
            interactor.callAddAPI(context,cartRestModel,name,buy,sell)
        }
        else{
            interactor.callAddWithBarodeAPI(context,cartRestModel,name,barcode!!,buy,sell)
        }
    }

    override fun onSuccessAdd(data: List<Product>) {
        if(data.isEmpty()){
            onFailedAPI(999,"There is an error")
            return
        }
        view.onSuccess(data[0])
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }
}