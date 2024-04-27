package id.kasirvippro.android.feature.spend.main

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.transaction.RequestSpend
import id.kasirvippro.android.models.transaction.TransactionRestModel
import org.threeten.bp.LocalDate

interface SpendContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun openAddPage()
        fun showContentView()
        fun showErrorView(err:String)
        fun setNominalText(count:String,nominal:String)
        fun add(data: RequestSpend.Barang)
        fun delete(position: Int)
        fun openSuccessPage()
        fun openSingleDatePickerDialog(selected: CalendarDay?, minDate: LocalDate?, maxDate: LocalDate?, type:Int)
        fun getTotalValue(): Double
        fun openImageChooser()
        fun loadPhoto(path:String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun add(data:RequestSpend.Barang)
        fun delete(data: RequestSpend.Barang,position:Int)
        fun count()
        fun setSelectedDate(date:CalendarDay?)
        fun getSelectedDate():CalendarDay?
        fun getSize():Int
        fun check()
        fun onCheckPhoto()
        fun setImagePhotoPath(path:String?)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestSpend,img:String?)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessOrder()
        fun onFailedAPI(code:Int,msg:String)
    }


}