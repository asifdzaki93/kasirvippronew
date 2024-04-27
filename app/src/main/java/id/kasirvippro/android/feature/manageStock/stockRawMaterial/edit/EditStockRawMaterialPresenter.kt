package id.kasirvippro.android.feature.manageStock.stockRawMaterial.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.models.rawMaterial.RawMaterialRestModel
import id.kasirvippro.android.utils.AppConstant


class EditStockRawMaterialPresenter(val context: Context, val view: EditStockRawMaterialContract.View) : BasePresenter<EditStockRawMaterialContract.View>(),
    EditStockRawMaterialContract.Presenter, EditStockRawMaterialContract.InteractorOutput {

    private var interactor = EditStockRawMaterialInteractor(this)
    private var restModel = RawMaterialRestModel(context)
    private var data : RawMaterial ?= null
    private var newdata : RawMaterial ?= null
    private var premium:Boolean = false


    override fun onViewCreated(intent:Intent) {

        data = intent.getSerializableExtra(AppConstant.DATA) as RawMaterial
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
    override fun onCheck(stok: String) {

        if(stok.isBlank() || stok.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_stock))
            return
        }

        newdata = RawMaterial()
        newdata?.stock = stok

        interactor.callEditProductAPI(context,restModel,data?.id_raw_material!!, stok)
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