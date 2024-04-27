package id.kasirvippro.android.feature.manage.recipe.edit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.MenuItem
import android.widget.EditText
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.choose.rawMaterial.ChooseRawMaterialActivity
import id.kasirvippro.android.feature.manage.recipe.list.RecipeListActivity
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.models.recipe.Recipe
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.*
import kotlinx.android.synthetic.main.activity_edit_recipe.*
import java.util.regex.PatternSyntaxException


class EditRecipeActivity : BaseActivity<EditRecipePresenter, EditRecipeContract.View>(), EditRecipeContract.View {

    private val CODE_OPEN_ADD = 1003
    private val CODE_OPEN_CHOOSE_MATERIAL = 1005
    override fun createPresenter(): EditRecipePresenter {
        return EditRecipePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_recipe
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
                val stock = et_stock.text.toString().trim()
                getPresenter()?.onCheck(stock)
            }else{
                val stock = et_stock.text.toString().trim()
                getPresenter()?.onCheck(stock)
            }

        }
            et_stock.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)


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
            title = getString(R.string.lbl_recipe)
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

    override fun onClose(status: Int,data: Recipe?) {
        val id = data?.id_product
        val detail = data?.name_product
        val intent = Intent(this, RecipeListActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        intent.putExtra("id_product", id);
        intent.putExtra("detail", detail);
        startActivityForResult(intent,CODE_OPEN_ADD)
    }

    override fun setStock(value: String) {
        et_stock.setText(value)
    }

    override fun setRawMaterial(value: String) {
        et_rawmaterial.setText(value)
    }

    override fun setProduct(value: String) {
        et_name_product.setText(value)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
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

    override fun onPremiumPage(isPremium: Boolean) {
        if(isPremium){
        }
        else{

        }
    }

    override fun openChooseRawmaterial() {
        val intent = Intent(this, ChooseRawMaterialActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_MATERIAL)
    }

    override fun setRawMaterialName(data: RawMaterial?) {
        et_rawmaterial.text = ""
        data?.let {
            et_rawmaterial.text = it.name
        }
    }


}
