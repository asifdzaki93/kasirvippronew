package id.kasirvippro.android.feature.manage.priceVariant.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.priceVariant.PriceVariant
import id.kasirvippro.android.models.priceVariant.PriceVariantRestModel
import id.kasirvippro.android.utils.AppConstant


class EditPriceVariantPresenter(val context: Context, val view: EditPriceVariantContract.View) : BasePresenter<EditPriceVariantContract.View>(),
    EditPriceVariantContract.Presenter, EditPriceVariantContract.InteractorOutput {

    private var interactor = EditPriceVariantInteractor(this)
    private var restModel = PriceVariantRestModel(context)
    private var data : PriceVariant ?= null
    private var premium:Boolean = false


    override fun onViewCreated(intent:Intent) {


        data = intent.getSerializableExtra(AppConstant.DATA) as PriceVariant
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


    override fun onCheck(minimal: String, nominal: String) {
        if(minimal.isBlank() || minimal.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_grosir_price))
            return
        }

        if(nominal.isBlank() || nominal.isEmpty() || "0" == nominal){
            view.showMessage(999,context.getString(R.string.err_empty_sell))
            return
        }

        interactor.callEditProductAPI(context,restModel, data?.id_pricevariant!!,minimal,nominal)
    }

    override fun onSuccessEditProduct(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK,data)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    private fun checkProduct(){
        data?.let {
            view.setProductName(it.minimal!!)
            view.setBuyPrice(it.price!!)
            view.setProduct(it.detail!!)
        }
    }


}