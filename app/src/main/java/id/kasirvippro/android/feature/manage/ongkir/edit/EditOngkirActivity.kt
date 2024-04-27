package id.kasirvippro.android.feature.manage.ongkir.edit

import android.os.Bundle
import android.text.InputFilter
import android.view.MenuItem
import android.widget.EditText
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_edit_ongkir.*
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.NumberTextWatcher
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.activity_add_ongkir.*
import kotlinx.android.synthetic.main.activity_edit_ongkir.btn_save
import kotlinx.android.synthetic.main.activity_edit_ongkir.et_name
import kotlinx.android.synthetic.main.activity_edit_ongkir.et_nominal
import java.util.regex.PatternSyntaxException


class EditOngkirActivity : BaseActivity<EditOngkirPresenter, EditOngkirContract.View>(), EditOngkirContract.View {

    override fun createPresenter(): EditOngkirPresenter {
        return EditOngkirPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_ongkir
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView(){
        val decimal = AppConstant.DECIMAL.getDecimalData()

        btn_save.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            if(decimal=="No Decimal") {
                val name_ongkir = et_name.text.toString().trim()
                val nominal = et_nominal.text.toString().trim().replace(",","")
                getPresenter()?.onCheck(name_ongkir, nominal)
            }else{
                val name_ongkir = et_name.text.toString().trim()
                val nominal = et_nominal.text.toString().trim()
                getPresenter()?.onCheck(name_ongkir, nominal)
            }

            if(decimal=="No Decimal") {
                et_nominal.addTextChangedListener(NumberTextWatcher(et_nominal))
            }else{
                et_nominal.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
            }
        }
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
            title = getString(R.string.lbl_ongkir)
            elevation = 0f

        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun showMessage(code:Int, msg:String?) {
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

    override fun setName(value: String?) {
        value.let {
            et_name.setText(it)
        }
    }
    override fun setNominal(value: String?) {
        value.let {
            et_nominal.setText(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }


}
