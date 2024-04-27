package id.kasirvippro.android.feature.report.preOrder.daily

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.models.report.HistoryPreOrder
import id.kasirvippro.android.models.report.ReportPreOrder
import id.kasirvippro.android.models.report.ReportRestModel
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.ImageHelper
import id.kasirvippro.android.utils.PermissionUtil
import org.threeten.bp.LocalDate

class ListPreOrderPresenter(val context: Context, val view: ListPreOrderContract.View) : BasePresenter<ListPreOrderContract.View>(),
    ListPreOrderContract.Presenter, ListPreOrderContract.InteractorOutput {


    private var interactor = ListPreOrderInteractor(this)
    private var transactionRestModel = ReportRestModel(context)
    private var today: CalendarDay?= null
    private var filterSelected:DialogModel ?= null
    private var filters:ArrayList<DialogModel> ?= null
    private var selectedDate:FilterDialogDate?=null
    private val permissionUtil = PermissionUtil(context)
    private lateinit var storagePermission: PermissionCallback

    override fun onViewCreated() {

        storagePermission = object :PermissionCallback{
            override fun onSuccess() {
                ImageHelper.takeScreenshot(context,view.getParentLayout())
            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_write_external))
            }
        }
        val now = LocalDate.now()
        today = CalendarDay.from(now)
        val yesterday = today?.date!!.minusDays(1)
        val firstDate =  CalendarDay.from(yesterday)
        val nextday = today?.date!!.plusDays(7)
        val lastDate =  CalendarDay.from(nextday)
        selectedDate = FilterDialogDate()
        selectedDate?.id = AppConstant.FilterDate.DAILY
        selectedDate?.firstDate = firstDate
        selectedDate?.lastDate = lastDate
        generateFilter()
        loadTransaction()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun loadTransaction() {
        interactor.callGetHistoryAPI(context,transactionRestModel,selectedDate?.firstDate?.date.toString(),selectedDate?.lastDate?.date.toString(),filterSelected!!.id.toString())

    }

    override fun onSuccessGetHistory(list:List<HistoryPreOrder>?) {
        list?.let {
            if(it.isEmpty()){
                view.showErrorMessage(999,"Data not found")
                return
            }
            val transactions = ArrayList<ReportPreOrder>()
            for(history in it){
                history.detail?.let {detail->
                    if(detail.isNotEmpty()){
                        val header = ReportPreOrder()
                        header.date = history.date
                        header.type = "header"
                        header.totalsales = history.totalsales
                        for(trx in detail){
                            transactions.add(0,trx)
                        }

                        transactions.add(0,header)

                    }
                }
            }
            if(transactions.isEmpty()){
                view.showErrorMessage(999,"No data available")
                return
            }
            view.setList(transactions)
        }
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

    override fun onSearch(id: String) {
        if(id.isEmpty()){
            loadTransaction()
        }
        else{
            interactor.callGetSearchAPI(context,transactionRestModel,id)
        }
    }

    override fun onSuccessGetSearch(list: List<ReportPreOrder>?) {
        if(list == null){
            view.showErrorMessage(999,"Data not found")
            return
        }
        if(list.isEmpty()){
            view.showErrorMessage(999,"Data not found")
            return
        }
        view.setList(list)
    }

    private fun generateFilter(){
        filters = ArrayList()

        val all = DialogModel()
        all.id = ""
        all.value = "All"
        filters!!.add(all)
        filterSelected = all

        val hutang = DialogModel()
        hutang.id = "berkurang"
        hutang.value = "Transaksi Keluar"
        filters!!.add(hutang)

        val lunas = DialogModel()
        lunas.id = "bertambah"
        lunas.value = "Transaksi Masuk"
        filters!!.add(lunas)


    }

    override fun getFilterSelected(): DialogModel {
        return filterSelected!!
    }

    override fun getFilters(): ArrayList<DialogModel> {
        return filters!!
    }

    override fun onChangeStatus(selected: DialogModel?) {
        selected?.let {
            filterSelected = it
        }

        loadTransaction()
    }

    override fun setFilterDateSelected(data: FilterDialogDate?) {
        selectedDate = data
        loadTransaction()
    }

    override fun getFilterDateSelected(): FilterDialogDate? {
        return selectedDate
    }

    override fun onCheckShare() {
        permissionUtil.checkWriteExternalPermission(storagePermission)
    }

}