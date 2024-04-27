package id.kasirvippro.android.feature.spend.add

import android.app.Activity
import android.os.Bundle
import android.text.InputFilter
import android.view.MenuItem
import android.widget.EditText
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.models.transaction.RequestSpend
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.NumberTextWatcher
import id.kasirvippro.android.utils.*
import kotlinx.android.synthetic.main.activity_add_spend.*
import java.util.regex.PatternSyntaxException


class AddActivity : BaseActivity<AddPresenter, AddContract.View>(), AddContract.View {

    override fun createPresenter(): AddPresenter {
        return AddPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_add_spend
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){
        val decimal = AppConstant.DECIMAL.getDecimalData()
        btn_save.setOnClickListener {
            showLoadingDialog()
            val name = et_name.text.toString().trim()
            val buy = et_buy.text.toString().trim()
            getPresenter()?.onCheck(name, buy)
        }


        if(decimal=="No Decimal") {
            et_buy.addTextChangedListener(NumberTextWatcher(et_buy))
        }else{
            et_buy.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
        }

        et_buy.addTextChangedListener(NumberTextWatcher(et_buy))
    }
    fun EditText.inputFilterDecimal(
        // maximum digits including point and without decimal places
        maxDigitsIncludingPoint: Int,
        maxDecimalPlaces: Int // maximum decimal places

    ){

        try {

            filters = arrayOf<InputFilter>(

                Helper.DecimalDigitsInputFilter(maxDigitsIncludingPoint, maxDecimalPlaces)

            )

        }catch (e: PatternSyntaxException){

            isEnabled = false

            hint = e.message

        }

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Add Expenses"
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
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

    override fun onSuccess(data: RequestSpend.Barang) {
        val newIntent = intent
        newIntent.putExtra(AppConstant.DATA,data)
        setResult(Activity.RESULT_OK,newIntent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }
}
