package id.kasirvippro.android.feature.manageOrder.kitchen.transactionPreorder

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.models.transaction.HistoryTransaction
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.utils.AppConstant
import org.threeten.bp.LocalDate

class PreorderPresenter(val context: Context, val view: PreorderContract.View) : BasePresenter<PreorderContract.View>(),
    PreorderContract.Presenter, PreorderContract.InteractorOutput {


    private var interactor = PreorderInteractor(this)
    private var transactionRestModel = TransactionRestModel(context)
    private var today: CalendarDay?= null
    private var selectedDate:FilterDialogDate?=null
    private var filterSelected:DialogModel ?= null
    private var filters:ArrayList<DialogModel> ?= null

    override fun onViewCreated() {
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
        interactor.callGetHistoryAPI(context,transactionRestModel,selectedDate?.firstDate?.date.toString(),selectedDate?.lastDate?.date.toString())

    }

    override fun onSearch(id: String) {
        if(id.isEmpty()){
            loadTransaction()
        }
        else{
            interactor.callGetSearchAPI(context,transactionRestModel,id)
        }
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

    override fun onSuccessGetHistory(list:List<HistoryTransaction>?) {
        list?.let {
            if(it.isEmpty()){
                view.showErrorMessage(999,"Data not found")
                return
            }
            val transactions = ArrayList<Transaction>()
            for(history in it){
                history.detail?.let {detail->
                    if(detail.isNotEmpty()){
                        val header = Transaction()
                        header.date = history.date
                        header.type = "header"
                        header.totalorder = history.totalorderall
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

    override fun onSuccessGetSearch(list: List<Transaction>?) {
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

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

    private fun generateFilter(){
        filters = ArrayList()

        val all = DialogModel()
        all.id = ""
        all.value = "All"
        filters!!.add(all)
        filterSelected = all

        val hutang = DialogModel()
        hutang.id = "debt"
        hutang.value = "Debt"
        filters!!.add(hutang)

        val billing = DialogModel()
        billing.id = "billing"
        billing.value = "Billing"
        filters!!.add(billing)

        val process = DialogModel()
        process.id = "process"
        process.value = "Process"
        filters!!.add(process)

        val preorder = DialogModel()
        preorder.id = "pre order"
        preorder.value = "Pre Order"
        filters!!.add(preorder)

        val lunas = DialogModel()
        lunas.id = "paid off"
        lunas.value = "Paid off"
        filters!!.add(lunas)

        val batal = DialogModel()
        batal.id = "cancel"
        batal.value = "Cancel"
        filters!!.add(batal)

    }





}