package id.kasirvippro.android.feature.manage.productVariant.edit

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
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.activity_edit_variant_product.*
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.NumberTextWatcher
import id.kasirvippro.android.utils.*
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.activity_edit_product.*
import kotlinx.android.synthetic.main.activity_edit_variant_product.btn_camera
import kotlinx.android.synthetic.main.activity_edit_variant_product.btn_save
import kotlinx.android.synthetic.main.activity_edit_variant_product.btn_scan
import kotlinx.android.synthetic.main.activity_edit_variant_product.cb_dropship
import kotlinx.android.synthetic.main.activity_edit_variant_product.cb_stock
import kotlinx.android.synthetic.main.activity_edit_variant_product.et_alertstock
import kotlinx.android.synthetic.main.activity_edit_variant_product.et_barcode
import kotlinx.android.synthetic.main.activity_edit_variant_product.et_buy
import kotlinx.android.synthetic.main.activity_edit_variant_product.et_desc
import kotlinx.android.synthetic.main.activity_edit_variant_product.et_dropship_sell
import kotlinx.android.synthetic.main.activity_edit_variant_product.et_minstock
import kotlinx.android.synthetic.main.activity_edit_variant_product.et_name
import kotlinx.android.synthetic.main.activity_edit_variant_product.et_sell
import kotlinx.android.synthetic.main.activity_edit_variant_product.et_stok
import kotlinx.android.synthetic.main.activity_edit_variant_product.et_tax
import kotlinx.android.synthetic.main.activity_edit_variant_product.iv_camera
import kotlinx.android.synthetic.main.activity_edit_variant_product.iv_photo
import kotlinx.android.synthetic.main.activity_edit_variant_product.ll_alertstock
import kotlinx.android.synthetic.main.activity_edit_variant_product.ll_dropship
import kotlinx.android.synthetic.main.activity_edit_variant_product.ll_stock
import java.io.File
import java.util.regex.PatternSyntaxException


class EditProductVariantActivity : BaseActivity<EditProductVariantPresenter, EditProductVariantContract.View>(), EditProductVariantContract.View {

    private lateinit var choosePhotoHelper: ChoosePhotoHelper
    private val CODE_OPEN_SCAN = 1001

    override fun createPresenter(): EditProductVariantPresenter {
        return EditProductVariantPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_variant_product
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
                val unit = et_name.text.toString().trim()
                val buy = et_buy.text.toString().trim().replace(",","")
                val sell = et_sell.text.toString().trim().replace(",","")
                val stok = et_stok.text.toString().trim()
                val minstok = et_minstock.text.toString().trim()
                val desc = et_desc.text.toString().trim()
                val barcode = et_barcode.text.toString().trim()
                val grosir = et_dropship_sell.text.toString().trim().replace(",","")
                val tax = et_tax.text.toString().trim().replace(",","")
                val alertstock = et_alertstock.text.toString().trim()
                getPresenter()?.onCheck(name, unit, buy, sell, stok, minstok, desc, barcode,grosir,tax,alertstock)
            }else{
                val name = et_name.text.toString().trim()
                val unit = et_name.text.toString().trim()
                val buy = et_buy.text.toString().trim()
                val sell = et_sell.text.toString().trim()
                val stok = et_stok.text.toString().trim()
                val minstok = et_minstock.text.toString().trim()
                val desc = et_desc.text.toString().trim()
                val barcode = et_barcode.text.toString().trim()
                val grosir = et_dropship_sell.text.toString().trim()
                val tax = et_tax.text.toString().trim()
                val alertstock = et_alertstock.text.toString().trim()
                getPresenter()?.onCheck(name, unit, buy, sell, stok, minstok, desc, barcode,grosir,tax,alertstock)
            }

        }

        cb_stock.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                getPresenter()?.setHaveStock(1)
                ll_stock.visibility = View.VISIBLE
                ll_alertstock.visibility = View.VISIBLE
            }
            else{
                getPresenter()?.setHaveStock(0)
                ll_stock.visibility = View.GONE
                ll_alertstock.visibility = View.GONE
            }
        }

        cb_dropship.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                getPresenter()?.setGrosir(1)
                ll_dropship.visibility = View.VISIBLE
            }
            else{
                getPresenter()?.setGrosir(0)
                ll_dropship.visibility = View.GONE
            }
        }

        btn_scan.setOnClickListener {
            getPresenter()?.onCheckScan()
        }

        btn_camera.setOnClickListener {
            getPresenter()?.onCheckPhoto()
        }

        if(decimal=="No Decimal") {
            et_sell.addTextChangedListener(NumberTextWatcher(et_sell))
            et_buy.addTextChangedListener(NumberTextWatcher(et_buy))
            et_stok.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
            et_alertstock.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
            et_dropship_sell.addTextChangedListener(NumberTextWatcher(et_dropship_sell))
        }else{
            et_sell.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
            et_buy.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
            et_stok.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
            et_alertstock.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
            et_dropship_sell.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
        }

        choosePhotoHelper = ChoosePhotoHelper.with(this)
            .asFilePath()
            .build(ChoosePhotoCallback { photo ->
                if (photo.isNullOrEmpty() || photo.isNullOrBlank()) {
                    getPresenter()?.setImagePhotoPath(null)
                    loadPhoto("")
                    iv_camera.visibility = View.VISIBLE
                } else {
//                    val tmpBitmap = BitmapFactory.decodeFile(photo)
//                    val uri = Uri.fromFile(File(photo))
//                    val bitmap = ImageHelper.rotateImageIfRequired(tmpBitmap, uri)
//                    val uriImage = ImageUtil.getImageUri(this, bitmap)
//                    val path = FilePathUtil.getPath(this@EditProductActivity,uriImage)
                    val imageUtil = @SuppressLint("StaticFieldLeak")
                    object : ImageCompression(this@EditProductVariantActivity) {
                        override fun onPostExecute(imagePath: String) {
                            super.onPostExecute(imagePath)
                            val compressedImageFile = File(imagePath)
                            if(compressedImageFile.exists()){
                                val compressedSize = ImageUtil.getSizeFile(imagePath)
                                Log.d("choosePhotoHelper compressed size",""+compressedSize)
                                getPresenter()?.setImagePhotoPath(imagePath)
                                loadPhoto(imagePath)
                                iv_camera.visibility = View.GONE
                            }else{
                                getPresenter()?.setImagePhotoPath(null)
                                loadPhoto("")
                                iv_camera.visibility = View.VISIBLE
                                showMessage(999,"Photo not found")
                            }
                        }
                    }
                    //imageUtil.execute(path!!)
                    imageUtil.execute(photo)

                }

            })

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
            title = getString(R.string.menu_edit_variant_product)
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

    override fun openScanPage() {
        val intent = Intent(this, ScanCodeActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_SCAN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        choosePhotoHelper.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_OPEN_SCAN && resultCode == Activity.RESULT_OK) {
            val code = data?.getStringExtra(AppConstant.DATA)
            if(code.isNullOrBlank() || code.isNullOrEmpty()){
                setBarcode("")
            }
            else{
                setBarcode(code)
                showLoadingDialog()
                getPresenter()?.searchByBarcode(code)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        choosePhotoHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun openImageChooser() {
        choosePhotoHelper.showChooser()
    }

    override fun loadPhoto(path: String) {
        if (path.isEmpty() || path.isBlank()) {
            iv_camera.visibility = View.VISIBLE
        } else {
            iv_camera.visibility = View.GONE
        }
        GlideApp.with(this)
            .load(path)
            .transform(CenterCrop(), RoundedCorners(4))
            .into(iv_photo)

    }

    override fun setProductName(value: String) {
        et_name.setText(value)
    }

    override fun setStock(value: String) {
        et_stok.setText(value)
    }

    override fun setAlertstock(value: String) {
        et_alertstock.setText(value)
    }

    override fun setMinStock(value: String) {
        et_minstock.setText(value)
    }

    override fun setSellPrice(value: String) {
        et_sell.setText(value)
    }

    override fun setBuyPrice(value: String) {
        et_buy.setText(value)
    }

    override fun setDescription(value: String) {
        et_desc.setText(value)
    }

    override fun setBarcode(value: String) {
        et_barcode.setText(value)
    }


    override fun setGrosir(value: Boolean) {
        cb_dropship.isChecked = value
    }

    override fun setHaveStock(value: Boolean) {
        cb_stock.isChecked = value
    }
    override fun setGrosirPrice(value: String) {
        et_dropship_sell.setText(value)
    }

    override fun setTax(value: String) {
        et_tax.setText(value)
    }

    override fun onPremiumPage(isPremium: Boolean) {
        if(isPremium){
        }
        else{

        }
    }


}
