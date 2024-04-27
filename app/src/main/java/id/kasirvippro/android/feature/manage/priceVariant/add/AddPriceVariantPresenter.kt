package id.kasirvippro.android.feature.manage.priceVariant.add

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.priceVariant.PriceVariantRestModel


class AddPriceVariantPresenter(val context: Context, val view: AddPriceVariantContract.View) : BasePresenter<AddPriceVariantContract.View>(),
    AddPriceVariantContract.Presenter, AddPriceVariantContract.InteractorOutput {

    private var interactor = AddPriceVariantInteractor(this)
    private var restModel = PriceVariantRestModel(context)
    private var premium:Boolean = false

    override fun onViewCreated() {
        premium = "1" == interactor.getUserPaket(context)
        //view.onPremiumPage(premium)
        checkProduct()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
    override fun onCheck(minimal: String, nominal: String) {
        if(minimal.isBlank() || minimal.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_grosir_price))
            return
        }

        if(nominal.isBlank() || nominal.isEmpty() || "0" == nominal){
            view.showMessage(999,context.getString(R.string.err_empty_sell))
            return
        }
        val idproduct = view.getIdProduct()
        interactor.callAddProductAPI(context,restModel,minimal,nominal,idproduct!!)
    }

    override fun onSuccessAddProduct(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    private fun checkProduct(){
        val detail = view.getDetail()
        view.setProduct(detail!!)
    }



}