package id.kasirvippro.android.feature.report.slip.slip

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.slip.Slip
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.ImageHelper
import id.kasirvippro.android.utils.PermissionUtil

class SlipPresenter(val context: Context, val view: SlipContract.View) : BasePresenter<SlipContract.View>(),
    SlipContract.Presenter, SlipContract.InteractorOutput {

    private var interactor = SlipInteractor(this)
    private var date:String? = null
    private var data:Slip? = null
    private val permissionUtil = PermissionUtil(context)
    private lateinit var storagePermission: PermissionCallback
    private lateinit var downloadPermission: PermissionCallback
    private var premium:Boolean = false

    override fun onViewCreated(intent: Intent) {
        premium = "1" == interactor.getUserPaket(context)
        view.onPremiumPage(premium)

        storagePermission = object :PermissionCallback{
            override fun onSuccess() {
                ImageHelper.takeScreenshot(context,view.getParentLayout())
            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_write_external))
            }
        }

        downloadPermission = object :PermissionCallback{
            override fun onSuccess() {
                ImageHelper.downloadScreenshot(context,view.getParentLayout())
            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_write_external))
            }
        }

        date = intent.getStringExtra(AppConstant.DATE)
        data = intent.getSerializableExtra(AppConstant.DATA) as Slip

        setData()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun setData() {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        data?.let {
            view.setStore(it.name_store,it.address,it.phone_number)
            date?.let {dt->
                val tgl = Helper.getDateFormat(context,date!!,"yyyy-MM-dd","MMMM yyyy")
                view.setInfo(it.full_name!!,it.part!!,tgl)
                val gaji = if(it.salary_fixed.isNullOrBlank() || it.salary_fixed.isNullOrEmpty() || "0" == it.salary_fixed){
                    "0"
                } else{
                    if(decimal=="No Decimal") {
                        AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it.salary_fixed!!)
                    }else{
                        AppConstant.CURRENCY.getCurrencyData() + it.salary_fixed!!
                    }
                }
                val komisi = if(it.commission.isNullOrBlank() || it.commission.isNullOrEmpty() || "0" == it.commission){
                    null
                } else{
                    if(decimal=="No Decimal") {
                        AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it.commission!!)
                    }else{
                        AppConstant.CURRENCY.getCurrencyData() + it.commission!!
                    }
                }
                val tunjangan = if(it.allowance.isNullOrBlank() || it.allowance.isNullOrEmpty() || "0" == it.allowance){
                    null
                } else{
                    if(decimal=="No Decimal") {
                        AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it.allowance!!)
                    }else{
                        AppConstant.CURRENCY.getCurrencyData() + it.allowance!!
                    }
                }
                val potongan = if(it.piece.isNullOrBlank() || it.piece.isNullOrEmpty() || "0" == it.piece){
                    0.0
                } else{
                    it.piece!!.toDouble()
                }
                val potonganString = if(potongan > 0){
                    if(decimal=="No Decimal") {
                        AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(potongan)
                    }else{
                        AppConstant.CURRENCY.getCurrencyData() + potongan
                    }
                }
                else{
                    null
                }

                if(decimal=="No Decimal") {
                    val penjualan = Helper.convertToCurrency(it.total_sales!!)
                    view.setDetail(penjualan,gaji,komisi,tunjangan,potonganString,it.presence)
                }else{
                    val penjualan = it.total_sales!!
                    view.setDetail(penjualan,gaji,komisi,tunjangan,potonganString,it.presence)
                }
                val totalPotongan = if(potongan > 0){
                    if(decimal=="No Decimal") {
                        AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(potongan)
                    }else{
                        AppConstant.CURRENCY.getCurrencyData() + potongan
                    }
                }
                else{
                    "0"
                }
                if(decimal=="No Decimal") {
                    val total = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it.total_salary!!)
                    var totalPendapatan = total
                    if(potongan > 0){
                        val pendapatan = it.total_salary!!.toDouble() + potongan
                        totalPendapatan = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(pendapatan)
                    }
                    view.setTotal(totalPendapatan,totalPotongan,total)
                }else{
                    val total = AppConstant.CURRENCY.getCurrencyData() + it.total_salary!!
                    var totalPendapatan = total
                    if(potongan > 0){
                        val pendapatan = it.total_salary!!.toDouble() + potongan
                        totalPendapatan = AppConstant.CURRENCY.getCurrencyData() + pendapatan
                    }
                    view.setTotal(totalPendapatan,totalPotongan,total)
                }

            }
        }
    }

    override fun onCheckShare() {
        permissionUtil.checkWriteExternalPermission(storagePermission)
    }

    override fun onCheckDownload() {
        permissionUtil.checkWriteExternalPermission(downloadPermission)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

}