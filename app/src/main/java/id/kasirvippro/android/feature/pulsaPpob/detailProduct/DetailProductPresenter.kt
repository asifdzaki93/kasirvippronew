package id.kasirvippro.android.feature.pulsaPpob.detailProduct

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.productPpob.ProductPpob
import id.kasirvippro.android.models.pulsaPpob.DetailPpob
import id.kasirvippro.android.models.pulsaPpob.PulsaPpobRestModel
import id.kasirvippro.android.utils.AppConstant

class DetailProductPresenter(val context: Context, val view: DetailProductContract.View) : BasePresenter<DetailProductContract.View>(),
    DetailProductContract.Presenter, DetailProductContract.InteractorOutput {


    private var interactor: DetailProductInteractor = DetailProductInteractor(this)
    private var data:ProductPpob? = null
    private var pulsappobRestModel = PulsaPpobRestModel(context)
    private var restModel = PulsaPpobRestModel(context)

    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as ProductPpob
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }
        data?.let {
            view.setData(it.category,it.brand)
        }
    }


    override fun onCheck(pin: String) {
        val hargajual = view.getPay()
        val phone = view.getPhone()
        val sku = view.getSku()
        val ref_id = view.getRef()
        val hargaagent = view.getHa()
        interactor.callOrderAPI(context,restModel,phone,sku,hargajual.toString(),pin,ref_id,hargaagent)

    }

    override fun searchPrefix(search: String) {
        val category = view.getJenis()
        val brand = view.getBrand()
        if(search.isEmpty() || search.isBlank()){

        }else{
            interactor.callPrefixAPI(context,pulsappobRestModel, brand,search)
        }

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessOrder(msg: String?) {
        view.showMessage(200,msg)
        //view.onClose(Activity.RESULT_OK)
        view.openSuccessPage(msg!!)
    }

    override fun onSuccessPrefix(list: List<DetailPpob>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

}