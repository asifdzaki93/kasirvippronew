package id.kasirvippro.android.feature.manage.priceVariant.edit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.MenuItem
import android.widget.EditText
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.manage.priceVariant.list.PriceVariantListActivity
import id.kasirvippro.android.models.priceVariant.PriceVariant
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.NumberTextWatcher
import id.kasirvippro.android.utils.*
import kotlinx.android.synthetic.main.activity_edit_pricevariant.*
import java.util.regex.PatternSyntaxException


class EditPriceVariantActivity : BaseActivity<EditPriceVariantPresenter, EditPriceVariantContract.View>(), EditPriceVariantContract.View {

    private val CODE_OPEN_ADD = 1003
    override fun createPresenter(): EditPriceVariantPresenter {
        return EditPriceVariantPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_pricevariant
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView() {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        btn_save.setOnClickListener {
            showLoadingDialog()
            if(decimal=="No Decimal") {
                val minimal = et_minimal.text.toString().trim()
                val nominal = et_nominal.text.toString().trim().replace(",","")
                getPresenter()?.onCheck(minimal, nominal)
            }else{
                val minimal = et_minimal.text.toString().trim()
                val nominal = et_nominal.text.toString().trim()
                getPresenter()?.onCheck(minimal, nominal)
            }

        }

        if(decimal=="No Decimal") {
            et_minimal.addTextChangedListener(NumberTextWatcher(et_minimal))
            et_nominal.addTextChangedListener(NumberTextWatcher(et_nominal))
        }else{
            et_minimal.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
            et_nominal.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
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
            title = getString(R.string.menu_edit_variant_price)
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

    override fun onClose(status: Int,data: PriceVariant?) {
        val id = data?.id_product
        val detail = data?.detail
        val intent = Intent(this, PriceVariantListActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        intent.putExtra("id_product", id);
        intent.putExtra("detail", detail);
        startActivityForResult(intent,CODE_OPEN_ADD)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun setProductName(value: String) {
        et_minimal.setText(value)
    }
    override fun setBuyPrice(value: String) {
        et_nominal.setText(value)
    }

    override fun setProduct(value: String) {
        et_name_product.setText(value)
    }


    override fun onPremiumPage(isPremium: Boolean) {
        if(isPremium){
        }
        else{

        }
    }


}
