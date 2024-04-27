package id.kasirvippro.android.feature.history.pengeluaran

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.models.transaction.HistoryTransaction
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.utils.AppConstant
import org.threeten.bp.LocalDate

class SpendPresenter(val context: Context, val view: SpendContract.View) : BasePresenter<SpendContract.View>(),
    SpendContract.Presenter, SpendContract.InteractorOutput {

    private var interactor = SpendInteractor(this)
    private var transactionRestModel = TransactionRestModel(context)
    private var today: CalendarDay?= null
    private var selectedDate: FilterDialogDate?=null


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
        loadTransaction()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun loadTransaction() {
        interactor.callGetHistoryAPI(context,transactionRestModel,selectedDate?.firstDate?.date.toString(),selectedDate?.lastDate?.date.toString())

    }

    override fun onSuccessGetHistory(list:List<HistoryTransaction>?) {
        list?.let {
            if(it.isEmpty()){
                view.showErrorMessage(999,"Data tidak ditemukan")
                return
            }
            val transactions = ArrayList<Transaction>()
            for(history in it){
                history.detail?.let {detail->
                    if(detail.isNotEmpty()){
                        val header = Transaction()
                        header.date = history.date
                        header.type = "header"
                        header.totalorder = history.totalnominal
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

    override fun onSuccessGetSearch(list: List<Transaction>?) {
        if(list == null){
            view.showErrorMessage(999,"Data tidak ditemukan")
            return
        }
        if(list.isEmpty()){
            view.showErrorMessage(999,"Data tidak ditemukan")
            return
        }
        view.setList(list)
    }

    override fun setFilterDateSelected(data: FilterDialogDate?) {
        selectedDate = data
        loadTransaction()
    }

    override fun getFilterDateSelected(): FilterDialogDate? {
        return selectedDate
    }

}