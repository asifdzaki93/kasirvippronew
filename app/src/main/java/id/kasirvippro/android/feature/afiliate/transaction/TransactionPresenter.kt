package id.kasirvippro.android.feature.afiliate.transaction

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.models.transaction.*
import id.kasirvippro.android.utils.AppConstant
import org.threeten.bp.LocalDate

class TransactionPresenter(val context: Context, val view: TransactionContract.View) : BasePresenter<TransactionContract.View>(),
    TransactionContract.Presenter, TransactionContract.InteractorOutput {


    private var interactor = TransactionInteractor(this)
    private var transactionRestModel = TransactionRestModel(context)
    private var today: CalendarDay?= null
    private var filterSelected:DialogModel ?= null
    private var filters:ArrayList<DialogModel> ?= null
    private var selectedDate:FilterDialogDate?=null


    override fun onViewCreated() {
        val now = LocalDate.now()
        today = CalendarDay.from(now)
        val yesterday = today?.date!!.minusDays(1)
        val firstDate =  CalendarDay.from(yesterday)
        val lastDate = today
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

    override fun onSuccessGetHistory(list:List<Comission>?) {
        list?.let {
            if(it.isEmpty()){
                view.showErrorMessage(999,"Data not found")
                return
            }
            val transactions = ArrayList<DetailCOmmision>()
            for(history in it){
                history.detail?.let {detail->
                    if(detail.isNotEmpty()){
                        val header = DetailCOmmision()
                        header.date = history.date
                        header.type = "header"
                        header.amount = history.totalorderall
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
            val user = list[0]
            view.setCommision(user.totalcommision)

        }
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

        val lunas = DialogModel()
        lunas.id = "paid off"
        lunas.value = "Paid off"
        filters!!.add(lunas)

        val batal = DialogModel()
        batal.id = "cancel"
        batal.value = "Cancel"
        filters!!.add(batal)

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

}