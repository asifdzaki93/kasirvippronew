package id.kasirvippro.android.feature.pulsaPpob.order

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AlertDialog
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.pulsaPpob.PulsaPpob
import id.kasirvippro.android.models.pulsaPpob.PulsaPpobRestModel
import id.kasirvippro.android.utils.AppConstant

class OrderPresenter(val context: Context, val view: OrderContract.View) : BasePresenter<OrderContract.View>(),
    OrderContract.Presenter, OrderContract.InteractorOutput {


    private var interactor: OrderInteractor = OrderInteractor(this)
    private var data:PulsaPpob? = null
    private var restModel = PulsaPpobRestModel(context)

    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as PulsaPpob
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }
        data?.let {
            view.setData(it.product_name,it.category,it.brand,it.price,it.buyer_sku_code,it.desc,it.phone,it.gbr,it.buyer_product_status,it.seller_product_status)
        }


    }


    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onCheck(pin:String) {
        val hargajual = view.getPay()
        val phone = view.getPhone()
        val sku = view.getSku()
        val product_name = view.getProductname()
        interactor.callOrderAPI(context,restModel,phone,sku,hargajual.toString(),pin,product_name)
    }

    override fun onCheckToken(phone:String, brand:String) {
        if(brand == "PLN"){
            interactor.callTokenAPI(context,restModel,phone)
        }else{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Info")
            builder.setMessage("Nomor yang akan Anda diisi " + phone)
            builder.setCancelable(false)
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            builder.show()
        }
    }

    override fun onSuccessOrder(msg: String?) {
        view.hideLoadingDialog()
        view.showMessage(200,msg)
        //view.onClose(Activity.RESULT_OK)
        Log.d("tes",msg!!)
        view.openSuccessPage(msg)
    }

    override fun onSuccessCek(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

}