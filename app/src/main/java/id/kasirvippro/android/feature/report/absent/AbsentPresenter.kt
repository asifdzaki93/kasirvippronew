package id.kasirvippro.android.feature.report.absent

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.slip.Absent
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper

class AbsentPresenter(val context: Context, val view: AbsentContract.View) : BasePresenter<AbsentContract.View>(),
    AbsentContract.Presenter, AbsentContract.InteractorOutput {

    private var interactor = AbsentInteractor(this)
    private var date:String? = null
    private var data:ArrayList<Absent>? = null
    private var staff:Staff? = null

    override fun onViewCreated(intent: Intent) {

        date = intent.getStringExtra(AppConstant.DATE)
        data = intent.getSerializableExtra(AppConstant.DATA) as ArrayList<Absent>
        staff = intent.getSerializableExtra(AppConstant.USER) as Staff
        setData()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun setData() {
        data?.let {
            date?.let {dt->
                val tgl = Helper.getDateFormat(context,dt,"yyyy-MM-dd","MMMM yyyy")
                view.setInfo(staff?.full_name,tgl)
                view.setList(it)
            }
        }
    }

}