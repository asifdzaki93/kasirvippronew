package id.kasirvippro.android.feature.manageStock.stockRawMaterial.edit

import android.app.Activity
import android.os.Bundle
import android.text.InputFilter
import android.view.MenuItem
import android.widget.EditText
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_edit_stock.*
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.*
import java.util.regex.PatternSyntaxException


class EditStockRawMaterialActivity : BaseActivity<EditStockRawMaterialPresenter, EditStockRawMaterialContract.View>(), EditStockRawMaterialContract.View {



    override fun createPresenter(): EditStockRawMaterialPresenter {
        return EditStockRawMaterialPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_rawmaterial
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView() {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        btn_save.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            if(decimal=="No Decimal") {
                val stok = et_stok.text.toString().trim()
                getPresenter()?.onCheck(stok)
            }else{
                val stok = et_stok.text.toString().trim()
                getPresenter()?.onCheck(stok)
            }
        }

        if(decimal=="No Decimal") {
            et_stok.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
        }else{
            et_stok.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
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
            title = getString(R.string.lbl_manage_raw_materialstock_title)
            elevation = 0f
        }

    }

    public fun hideLoading(){
        hideLoadingDialog()
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

    override fun onClose(status: Int,data:RawMaterial?) {
        val newIntent = intent
        if(Activity.RESULT_OK == status){
            newIntent.putExtra(AppConstant.DATA,data)
        }
        setResult(status,intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun setStock(value: String) {
        et_stok.setText(value)
    }

    override fun onPremiumPage(isPremium: Boolean) {
        if(isPremium){
        }
        else{

        }
    }


}
