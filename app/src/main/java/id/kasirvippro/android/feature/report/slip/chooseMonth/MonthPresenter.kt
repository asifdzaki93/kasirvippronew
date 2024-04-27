package id.kasirvippro.android.feature.report.slip.chooseMonth

import android.content.Context
import android.content.Intent
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.models.slip.Absent
import id.kasirvippro.android.models.slip.Slip
import id.kasirvippro.android.models.slip.SlipRestModel
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.utils.AppConstant
import org.threeten.bp.LocalDate

class MonthPresenter(val context: Context, val view: MonthContract.View) : BasePresenter<MonthContract.View>(),
    MonthContract.Presenter, MonthContract.InteractorOutput {

    private var interactor = MonthInteractor(this)
    private var restModel = SlipRestModel(context)
    private var selected:FilterDialogDate? = null
    private val today = LocalDate.now()
    private val maxYear = today.year
    private var selectedYear = maxYear
    private var id:String? = null
    private var name:String? = null
    private var codeKaryawan = AppConstant.Code.CODE_KARYAWAN_GAJI

    override fun onViewCreated(intent: Intent) {
        id = intent.getStringExtra(AppConstant.DATA)
        name = intent.getStringExtra(AppConstant.USER)
        codeKaryawan = intent.getIntExtra(AppConstant.CODE,AppConstant.Code.CODE_KARYAWAN_GAJI)
        selected = FilterDialogDate()
        setDate()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun setSelectedDate(date: FilterDialogDate?) {
        selected = date
    }

    override fun getSelectedDate(): FilterDialogDate? {
        return selected
    }

    override fun getDates(year:Int): List<FilterDialogDate> {
        val list = ArrayList<FilterDialogDate>()
        for(i in 1..12){
            val model = generateModel(year,i)
            list.add(model)
        }
        return list
    }

    override fun setDate() {
        view.setYear(selectedYear.toString())
        view.setList(getDates(selectedYear),selected!!)
    }

    override fun onNextYear() {
        if(selectedYear < maxYear){
            selectedYear++
            setDate()
        }
    }

    override fun onPrevYear() {
        selectedYear--
        setDate()
    }

    private fun generateModel(year:Int,month:Int):FilterDialogDate{
        val first = CalendarDay.from(year,month,1)
        val max = first.date.lengthOfMonth()
        val last = CalendarDay.from(year,month,max)

        val model = FilterDialogDate()
        model.id = AppConstant.FilterDate.MONTHLY
        model.firstDate = first
        model.lastDate = last

        return model
    }

    override fun onCheck() {
        if(selected == null){
            onFailedAPI(999,"Choose the date first")
            return
        }
        if(selected?.id == null){
            onFailedAPI(999,"Choose the date first")
            return
        }
        if(selected?.firstDate == null){
            onFailedAPI(999,"Choose the date first")
            return
        }
        view.showLoadingDialog()
        if(AppConstant.Code.CODE_KARYAWAN_ABSENSI == codeKaryawan){
            interactor.callGetAbsenAPI(context,restModel,id,selected!!)
        }
        else{
            interactor.callGetSlipAPI(context,restModel,id,selected!!)
        }

    }

    override fun onSuccessAbsent(list: List<Absent>?) {
        view.hideLoadingDialog()
        if(list.isNullOrEmpty()){
            onFailedAPI(999,"No data available")
        }
        else{
            val staff = Staff()
            staff.key = id
            staff.full_name = name
            view.openAbsentPage(selected!!, ArrayList(list),staff)
        }
    }

    override fun onSuccessPaySlip(list: List<Slip>?) {
        view.hideLoadingDialog()
        if(!list.isNullOrEmpty()){
            val data = list[0]
            view.openSlipPage(selected!!,data)
        }
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }



}