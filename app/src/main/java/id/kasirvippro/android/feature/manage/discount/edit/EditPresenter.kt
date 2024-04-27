package id.kasirvippro.android.feature.manage.discount.edit

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.discount.DiscountRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.DiscountSQL
import id.kasirvippro.android.sqlite.Model.DiscountSQLUpdate
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession

class EditPresenter(val context: Context, val view: EditContract.View) : BasePresenter<EditContract.View>(),
    EditContract.Presenter, EditContract.InteractorOutput {

    private var interactor = EditInteractor(this)
    private var restModel = DiscountRestModel(context)
    private var jenis:String = AppConstant.CURRENCY.getCurrencyData()
    private var discount:Discount ?= null


    override fun onViewCreated(intent: Intent) {
        discount = intent.getSerializableExtra(AppConstant.DATA) as Discount
        view.setData(discount)
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

        val value = nominal.replace(".","")


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
                    interactor.callEditAPI(context,restModel,discount?.id_discount!!,code, desc, jenis, value)
                    return
                }
            }
        }
        val discountUpdate = DiscountSQLUpdate(
            discount?.inc!!,
            key!!,
            discount?.id_discount!!,
            code,
            desc,
            jenis,
            value
        )
        val discount =
            DiscountSQL(
                discount?.inc!!,
                key!!,
                discount?.id_discount!!,
                code,
                desc,
                jenis,
                value
            )
        var result = dataManager.addDiscountUpdate(discountUpdate!!)
        var result2 = dataManager.updateDiscount(discount!!)
        Toast.makeText(context,"Update Local", Toast.LENGTH_SHORT).show()
        (context as EditActivity)
        view.onClose(Activity.RESULT_OK)
        (context as Activity).finish()

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessEdit(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun setJenis(jenis: String) {
        this.jenis = jenis
    }

}