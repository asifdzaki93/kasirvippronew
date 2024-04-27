package id.kasirvippro.android.feature.spend.main

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.transaction.RequestSpend
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.SpendingDataSQL
import id.kasirvippro.android.sqlite.Model.SpendingSQL
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import id.kasirvippro.android.utils.PermissionUtil
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class SpendPresenter(val context: Context, val view: SpendContract.View) : BasePresenter<SpendContract.View>(),
    SpendContract.Presenter, SpendContract.InteractorOutput {

    private var interactor = SpendInteractor(this)
    private var transactionRestModel = TransactionRestModel(context)
    private var carts:HashMap<String,RequestSpend.Barang> = HashMap()
    private var date:CalendarDay?=null
    private var req = RequestSpend()
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var photoPermission: PermissionCallback
    private var photoPath:String? = null

    override fun onViewCreated() {
        photoPermission = object : PermissionCallback {
            override fun onSuccess() {
                view.openImageChooser()
            }

            override fun onFailed() {
                view.showMessage(999,context.getString(R.string.reason_permission_camera))
            }
        }
        carts = HashMap()
        count()
    }

    override fun add(data: RequestSpend.Barang) {
        if(carts.containsKey(data.id)){
            val cart = carts[data.id]
            val nominal = cart?.nominal!! + data.nominal!!
            cart.nominal = nominal
            carts[data.id!!] = cart
            view.add(cart)

        }
        else{
            carts[data.id!!] = data
            view.add(data)
        }
        count()
    }

    

    override fun delete(data: RequestSpend.Barang,position:Int) {
        carts.remove(data.id)
        view.delete(position)
        count()
    }

    override fun onCheckPhoto() {
        permissionUtil.checkCameraPermission(photoPermission)
    }

    override fun setImagePhotoPath(path: String?) {
        photoPath = path
    }

    override fun count() {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(carts.size == 0){
            view.setNominalText("0","Rp 0")
            view.showErrorView("click add icon to start")
            return
        }

        var total = 0.0

        for(cart:RequestSpend.Barang in carts.values){
            val sell = cart.nominal!!.toDouble()
            total += sell
        }

        if(carts.size > 99){
            if(decimal=="No Decimal") {
                view.setNominalText(">99",AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(total))
            }else{
                view.setNominalText(">99",AppConstant.CURRENCY.getCurrencyData() + total)
            }
        }
        else{
            if(decimal=="No Decimal") {
                view.setNominalText(Helper.convertToCurrency(carts.size.toString()),AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(total))
            }else{
                view.setNominalText(Helper.convertToCurrency(carts.size.toString()),AppConstant.CURRENCY.getCurrencyData() + total)
            }
        }
        view.showContentView()
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.hideLoadingDialog()
        view.showMessage(code,msg)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
    
    override fun setSelectedDate(date: CalendarDay?) {
        this.date = date
    }

    override fun getSelectedDate(): CalendarDay? {
        return date
    }

    private fun getBarang(): List<RequestSpend.Barang> {
        val list = ArrayList<RequestSpend.Barang>()
        if(carts.size == 0){
            return list
        }
        for(cart in carts.values){
            list.add(cart)
        }
        return list
    }

    override fun onSuccessOrder() {
        view.hideLoadingDialog()
        view.openSuccessPage()
    }

    override fun getSize():Int {
        return carts.size
    }

    fun OrderOffline(){
        val dataManager = DataManager (context)

        req.key = AppSession().getToken(context)

        val key = req.key

        val dt = dataManager.store("1")
        val user = dataManager.userKey(key!!)


        if (user!! != null){
            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("ddmmss")
            val formatted = current.format(formatter)

            var dates = formatted

            var nama = dt!!.name_store
            var arr = nama.split(" ").toTypedArray()
            var singkatan = ""

            for (kata in arr) {
                singkatan = singkatan + kata.first().toString()
            }

            var kode = singkatan.toUpperCase()
            var no_invoice = kode + "-" + "OFF" + dates

            if(carts.size == 0){
                onFailedAPI(999,"No data yet")
                return
            }
            val request = RequestSpend()
            request.spending = getBarang()
            var amount = view.getTotalValue()
            var tgl = date?.date.toString()

                var i = 1
                val list: ArrayList<SpendingSQL> = ArrayList()
                for (pro in request.spending!!){
                    var id_spending             = pro.id
                    var name_spending           = pro.name_spending
                    var nominal                 = pro.nominal

                    var spending = SpendingSQL(
                        increment = i.toString(),
                        id_spending = id_spending!!,
                        name_spending = name_spending!!,
                        user = user.phone,
                        no_invoice = no_invoice,
                        nominal = nominal.toString(),
                        date = tgl!!
                    )
                    dataManager.addSpending(spending);
                    list.add(spending)
                    i++
                }
                var spendingData = SpendingDataSQL(
                    increment = "0",
                    id_spending_data = "0",
                    user = user.phone,
                    id_store = user.id_store,
                    no_invoice = no_invoice.toString(),
                    date = tgl!!,
                    totalnominal = amount.toString(),
                    operator = user.name
                )
                dataManager.addSpendingData(spendingData)



            view.hideLoadingDialog()
            view.openSuccessPage()


        }else{
            Toast.makeText(context,"User Not Found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun check() {
        if(carts.size == 0){
            onFailedAPI(999,"No data yet")
            return
        }
        if(date == null){
            onFailedAPI(999,"Date not filled")
            return
        }

        val request = RequestSpend()
        request.amount = view.getTotalValue()
        request.date = date?.date.toString()
        request.spending = getBarang()
        request.img = photoPath

        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        info = connectivity.activeNetworkInfo
        if (info != null) {
            if (info.state == NetworkInfo.State.CONNECTED) {
                interactor.callOrderAPI(context,transactionRestModel,request,photoPath)
            }else{
                OrderOffline()
            }
        }else{
            OrderOffline()
        }
    }

}