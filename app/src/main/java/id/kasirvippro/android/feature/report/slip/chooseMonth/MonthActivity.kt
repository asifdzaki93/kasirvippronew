package id.kasirvippro.android.feature.report.slip.chooseMonth

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.report.absent.AbsentActivity
import id.kasirvippro.android.feature.report.slip.slip.SlipActivity
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.models.slip.Absent
import id.kasirvippro.android.models.slip.Slip
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.GridItemOffsetDecoration
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_slip_month.*


class MonthActivity : BaseActivity<MonthPresenter, MonthContract.View>(),
    MonthContract.View {

    private val adapter = MonthAdapter()

    override fun createPresenter(): MonthPresenter {
        return MonthPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_slip_month
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView(){
        val spaceItem = resources.getDimensionPixelSize(R.dimen.standard_margin)
        rv_list.addItemDecoration(GridItemOffsetDecoration(spaceItem))
        val layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        adapter.callback = object : MonthAdapter.ItemClickCallback{
            override fun onClick(data: FilterDialogDate) {
                getPresenter()?.setSelectedDate(data)
            }
        }

        btn_next.setOnClickListener {
            getPresenter()?.onNextYear()
        }

       btn_prev.setOnClickListener {
            getPresenter()?.onPrevYear()
        }

        btn_save.setOnClickListener {
            getPresenter()?.onCheck()
        }
    }

    override fun setYear(year: String) {
        tv_year.text = year
    }

    override fun setList(list: List<FilterDialogDate>, selected: FilterDialogDate) {
        adapter.setItems(list,selected)
    }

    override fun openSlipPage(date: FilterDialogDate, slip: Slip) {
        val intent = Intent(this,SlipActivity::class.java)
        intent.putExtra(AppConstant.DATE,date.firstDate?.date.toString())
        intent.putExtra(AppConstant.DATA,slip)
        startActivity(intent)
    }

    override fun openAbsentPage(date:FilterDialogDate,list:ArrayList<Absent>,data:Staff?) {
        val intent = Intent(this,AbsentActivity::class.java)
        intent.putExtra(AppConstant.DATE,date.firstDate?.date.toString())
        intent.putExtra(AppConstant.DATA,list)
        intent.putExtra(AppConstant.USER,data)
        startActivity(intent)
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Select Date"
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun showMessage(code: Int, msg: String?) {
        hideLoadingDialog()
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> {
                msg?.let {
                    toast(this,it)}
            }

        }

    }
}
