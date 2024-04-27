package id.kasirvippro.android.feature.manage.rawMaterial.edit

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.scan.ScanCodeActivity
import id.kasirvippro.android.ui.ext.toast
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback
import com.aminography.choosephotohelper.ChoosePhotoHelper
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.feature.choose.unit.ChooseUnitActivity
import id.kasirvippro.android.feature.dialog.BottomDialog
import id.kasirvippro.android.feature.manage.productVariant.list.ProductVariantListActivity
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.unit.Unit
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.activity_edit_material.*
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.NumberTextWatcher
import id.kasirvippro.android.utils.*
import java.io.File
import java.util.regex.PatternSyntaxException


class EditRawMaterialActivity : BaseActivity<EditRawMaterialPresenter, EditRawMaterialContract.View>(), EditRawMaterialContract.View,
    BottomDialog.Listener {

    private val CODE_OPEN_ADD = 101
    private val CODE_OPEN_CHOOSE_UNIT = 1005

    private val categoryDialog = BottomDialog.newInstance()

    private lateinit var choosePhotoHelper: ChoosePhotoHelper
    private val CODE_OPEN_SCAN = 1001

    override fun createPresenter(): EditRawMaterialPresenter {
        return EditRawMaterialPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_material
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
                val name = et_name.text.toString().trim()
                val unit = et_unit.text.toString().trim()
                val sell = et_sell.text.toString().trim().replace(",","")
                val stok = et_stok.text.toString().trim()
                val desc = et_desc.text.toString().trim()
                getPresenter()?.onCheck(name, unit, sell, stok, desc)
            }else{
                val name = et_name.text.toString().trim()
                val unit = et_unit.text.toString().trim()
                val sell = et_sell.text.toString().trim()
                val stok = et_stok.text.toString().trim()
                val desc = et_desc.text.toString().trim()
                getPresenter()?.onCheck(name, unit, sell, stok,desc)
            }
        }

        et_unit.setOnClickListener {
            openChooseUnit()
        }

        if(decimal=="No Decimal") {
            et_sell.addTextChangedListener(NumberTextWatcher(et_sell))
            et_stok.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
        }else{
            et_sell.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
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
            title = getString(R.string.menu_edit_material)
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

    override fun onClose(status: Int) {
        setResult(status, intent)
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

    override fun openChooseUnit() {
        val intent = Intent(this, ChooseUnitActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_UNIT)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_OPEN_CHOOSE_UNIT && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this,"Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"Data not found")
                return
            }
            val unit = data.getSerializableExtra(AppConstant.DATA) as Unit
            if (unit.name_unit == null) {
                toast(this,"Data not found")
            } else {
                et_unit.text = unit.name_unit
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        choosePhotoHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onItemClicked(data: DialogModel, type: Int) {
    }

    override fun setUnitName(value: String) {
        et_unit.setText(value)
    }

    override fun setProductName(value: String) {
        et_name.setText(value)
    }

    override fun setStock(value: String) {
        et_stok.setText(value)
    }

    override fun setSellPrice(value: String) {
        et_sell.setText(value)
    }

    override fun setDescription(value: String) {
        et_desc.setText(value)
    }
    override fun onPremiumPage(isPremium: Boolean) {
        if(isPremium){
        }
        else{

        }
    }


}
