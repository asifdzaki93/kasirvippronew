package id.kasirvippro.android.feature.label.confirmation

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.transaction.*
import id.kasirvippro.android.utils.AppConstant
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ConfirmationLabelPresenter(val context: Context, val view: ConfirmationLabelContract.View) : BasePresenter<ConfirmationLabelContract.View>(),
    ConfirmationLabelContract.Presenter, ConfirmationLabelContract.InteractorOutput {

    private var interactor = ConfirmationLabelInteractor(this)
    private var transactionRestModel = TransactionRestModel(context)

    private var carts:HashMap<String,Cart> = HashMap()
    private var req = RequestTransaction()
    private var order:Order?=null


    override fun onViewCreated(intent: Intent) {
        carts = intent.getSerializableExtra(AppConstant.DATA) as HashMap<String,Cart>
        view.setCart(ArrayList(carts.values))
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.hideLoadingDialog()
        view.showMessage(code,msg)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }


    override fun checkLabel() {
        req = RequestTransaction()
        req.product = getBarang()

        interactor.callOrderAPI(context,transactionRestModel,req,note = String())
    }

    private fun getBarang():List<RequestTransaction.Barang>{
        val list = ArrayList<RequestTransaction.Barang>()
        if(carts.size == 0){
            return list
        }
        for(cart in carts.values){
            val barang = RequestTransaction.Barang()
            barang.id_product = cart.product?.id_product
            barang.amount_product = cart.count?.toInt()
            barang.notes = cart.note.toString()
            if(cart.new_price == "0"){
                barang.new_price = cart.product?.selling_price!!
            }else{
                barang.new_price = cart.new_price.toString()
            }
            list.add(barang)
        }
        return list
    }


    override fun onSuccessOrder(order: Order) {
        view.hideLoadingDialog()
        if(order.invoice == null){
            view.showMessage(999,"Invoice number not found")
            return
        }
        this.order = order
        view.openSuccessPage(order.invoice!!)


    }

    override fun getCartsSize():Int {
        return carts.size
    }


}