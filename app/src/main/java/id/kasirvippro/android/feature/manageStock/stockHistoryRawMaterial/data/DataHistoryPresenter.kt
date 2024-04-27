package id.kasirvippro.android.feature.manageStock.stockHistoryRawMaterial.data

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.transaction.DetailHistory
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.utils.ImageHelper
import id.kasirvippro.android.utils.PermissionUtil

class DataHistoryPresenter(val context: Context, val view: DataHistoryContract.View) : BasePresenter<DataHistoryContract.View>(),
    DataHistoryContract.Presenter, DataHistoryContract.InteractorOutput {

    private var interactor: DataHistoryInteractor = DataHistoryInteractor(this)
    private var restModel = TransactionRestModel(context)
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission: PermissionCallback
    private var sort = false
    private var firstDate: CalendarDay?= null
    private var lastDate: CalendarDay?= null
    private var today: CalendarDay?= null
    private lateinit var downloadPermission: PermissionCallback
    private var id = String()


    override fun onViewCreated() {
        downloadPermission = object :PermissionCallback{
            override fun onSuccess() {
                ImageHelper.downloadScreenshot(context,view.getParentLayout())
            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_write_external))
            }
        }

        val now = org.threeten.bp.LocalDate.now()
        today = CalendarDay.from(now)
        val yesterday = today?.date!!.minusDays(1)
        firstDate =  CalendarDay.from(yesterday)
        lastDate = today

       loadProducts()
    }
    override fun loadProducts() {
        val idproduct = view.getIdProduct()
        interactor.callGetProductsAPI(context,restModel,idproduct!!,firstDate?.date.toString(),lastDate?.date.toString())
    }

    override fun onChangeDate(firstDate: CalendarDay?, lastDate: CalendarDay?) {
        firstDate?.let {
            this.firstDate = it
        }

        lastDate?.let {
            this.lastDate = it
        }

        loadProducts()
    }

    override fun getToday(): CalendarDay? {
        return today
    }

    override fun getFirstDate(): CalendarDay? {
        return firstDate
    }

    override fun getLastDate(): CalendarDay? {
        return lastDate
    }

    override fun onCheckDownload() {
        permissionUtil.checkWriteExternalPermission(downloadPermission)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetProducts(list: List<DetailHistory>) {
        sort = false
        view.setProducts(list)
    }

    fun setProduct(list: List<DetailHistory>){
        view.setProducts(list)
    }

    override fun onSuccessDeleteProduct(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

}