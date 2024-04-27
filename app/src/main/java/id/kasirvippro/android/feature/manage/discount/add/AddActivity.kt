package id.kasirvippro.android.feature.manage.discount.add

import android.os.Bundle
import android.text.InputFilter
import android.view.MenuItem
import androidx.core.content.ContextCompat
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.NumberTextWatcher
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_add_discount.btn_persen
import kotlinx.android.synthetic.main.activity_add_discount.btn_rp
import kotlinx.android.synthetic.main.activity_add_discount.btn_save
import kotlinx.android.synthetic.main.activity_add_discount.et_code
import kotlinx.android.synthetic.main.activity_add_discount.et_desc
import kotlinx.android.synthetic.main.activity_add_discount.et_nominal

class AddActivity : BaseActivity<AddPresenter, AddContract.View>(), AddContract.View {

    override fun createPresenter(): AddPresenter {
        return AddPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_add_discount
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){
        btn_save.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val code = et_code.text.toString().trim()
            val desc = et_desc.text.toString().trim()
            val nominal = et_nominal.text.toString().trim().replace(",","")
            getPresenter()?.onCheck(code,desc,nominal)
        }

        btn_rp.setOnClickListener {
            chooseRupiah()
        }

        btn_persen.setOnClickListener {
            choosePercent()
        }

        chooseRupiah()

        et_nominal.addTextChangedListener(NumberTextWatcher(et_nominal))
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Add Discount"
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }
    public fun hideLoading(){
        hideLoadingDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
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

    override fun onClose(status: Int) {
        setResult(status,intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun chooseRupiah() {
        getPresenter()?.setJenis(AppConstant.CURRENCY.getCurrencyData())
        btn_rp.background = ContextCompat.getDrawable(this,R.color.colorAccent)
        btn_rp.setTextColor(ContextCompat.getColor(this,R.color.white))
        btn_persen.background = ContextCompat.getDrawable(this,R.color.white)
        btn_persen.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))
        et_nominal.setText("")
        btn_rp.setText(AppConstant.CURRENCY.getCurrencyData())
        et_nominal.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(9))
    }

    override fun choosePercent() {
        getPresenter()?.setJenis("prosentase")
        btn_rp.background = ContextCompat.getDrawable(this,R.color.white)
        btn_rp.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))
        btn_persen.background = ContextCompat.getDrawable(this,R.color.colorAccent)
        btn_persen.setTextColor(ContextCompat.getColor(this,R.color.white))
        et_nominal.setText("")
        et_nominal.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(3))

    }


}
