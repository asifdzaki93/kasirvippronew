package id.kasirvippro.android.feature.sell.addDiscount

import android.app.Activity
import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.discount.DiscountRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.DiscountSQL
import id.kasirvippro.android.sqlite.Model.DiscountSQLAdd
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession

class AddPresenter(val context: Context, val view: AddContract.View) : BasePresenter<AddContract.View>(),
    AddContract.Presenter, AddContract.InteractorOutput {

    private var interactor = AddInteractor(this)
    private var restModel = DiscountRestModel(context)
    private var jenis:String = AppConstant.CURRENCY.getCurrencyData()


    override fun onViewCreated() {

    }

    override fun onCheck(code: String,desc:String,nominal:String) {
        if(code.isBlank() || code.isEmpty()){
            view.showMessage(999,"The discount code must be filled in")
            return
        }

        if(desc.isBlank() || desc.isEmpty()){
            view.showMessage(999,"Information on discount must be filled in")
            return
        }

        if(nominal.isBlank() || nominal.isEmpty()){
            view.showMessage(999,"The discount amount must be filled in")
            return
        }

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
                    interactor.callAddAPI(context,restModel,code, desc, jenis, nominal)
                    return
                }
            }
        }
        val discountAdd =
            DiscountSQLAdd(
                "0",
                key!!,
                code,
                desc,
                jenis,
                nominal
            )
        val discount =
            DiscountSQL(
                "0",
                "0",
                key!!,
                code,
                desc,
                jenis,
                nominal
            )
        var result = dataManager.addDiscountAdd(discountAdd!!)
        var result2 = dataManager.addDiscount(discount!!)
        Toast.makeText(context,"Add Discount Local", Toast.LENGTH_SHORT).show()
        (context as AddActivity).hideLoading()
        view.onClose(Activity.RESULT_OK)
        (context as Activity).finish()

    }

    override fun onSuccessAdd(data:List<Discount>) {
        if(data.isEmpty()){
            onFailedAPI(999,"There is an error")
            return
        }
        view.onSuccess(data[0])
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    /* override fun onSuccessAdd(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }*/

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun setJenis(data: String) {
        jenis = data
    }

}