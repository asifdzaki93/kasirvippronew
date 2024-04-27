package id.kasirvippro.android.feature.manage.recipe.add

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.choose.rawMaterial.ChooseRawMaterialActivity
import id.kasirvippro.android.feature.manage.recipe.list.RecipeListActivity
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.models.recipe.Recipe
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.*
import kotlinx.android.synthetic.main.activity_add_recipe.et_rawmaterial
import kotlinx.android.synthetic.main.activity_add_recipe.btn_save
import kotlinx.android.synthetic.main.activity_add_recipe.et_stock
import kotlinx.android.synthetic.main.activity_add_recipe.et_name_product
import java.util.regex.PatternSyntaxException

class AddRecipeActivity : BaseActivity<AddRecipePresenter, AddRecipeContract.View>(), AddRecipeContract.View {

    private val CODE_OPEN_SCAN = 1001
    private val CODE_OPEN_ADD = 1003
    private val CODE_OPEN_CHOOSE_MATERIAL = 1005

    override fun createPresenter(): AddRecipePresenter {
        return AddRecipePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_add_recipe
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){
        val decimal = AppConstant.DECIMAL.getDecimalData()
        btn_save.setOnClickListener {
            showLoadingDialog()
            if(decimal=="No Decimal") {
                val stock    = et_stock.text.toString().trim()
                getPresenter()?.onCheck(stock)
            }else{
                val stock = et_stock.text.toString().trim()

                getPresenter()?.onCheck(stock)
            }

        }

            et_stock.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)

        et_rawmaterial.setOnClickListener {
            openChooseRawmaterial()
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
            title = getString(R.string.lbl_recipe)
            elevation = 0f

        }

    }

    override fun getIdProduct(): String? {
        val idproduct = getIntent().getStringExtra("id_product")
        return idproduct
    }

    override fun getDetail(): String? {
        val detail = getIntent().getStringExtra("detail")
        return detail
    }

    @SuppressLint("SetTextI18n")
    override fun setProduct() {
        val detail = getIntent().getStringExtra("detail")
        et_name_product.setText(detail)
       // Log.d("detail", detail!!)
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
        val id = getIntent().getStringExtra("id_product")
        val detail = getIntent().getStringExtra("detail")
        val intent = Intent(this, RecipeListActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        intent.putExtra("id_product", id);
        intent.putExtra("detail", detail);
        startActivityForResult(intent,CODE_OPEN_ADD)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_OPEN_CHOOSE_MATERIAL && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this,"Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"Data not found")
                return
            }
            val rawmaterial = data.getSerializableExtra(AppConstant.DATA) as RawMaterial
            if (rawmaterial.id_raw_material == null) {
                toast(this,"Data not found")
            } else {
                getPresenter()?.updateRawMaterial(rawmaterial)
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    public fun hideLoading(){
        hideLoadingDialog()
    }

    override fun openEditPage(product: Recipe) {
        intent.putExtra(AppConstant.DATA,product)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    override fun onPremiumPage(isPremium: Boolean) {
        if(isPremium){

        }
        else{

        }
    }





}
