package id.kasirvippro.android.feature.manage.packages.edit

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.aminography.choosephotohelper.ChoosePhotoHelper
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.models.unit.Unit
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_edit_packages.*
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.NumberTextWatcher
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.ImageCompression
import id.kasirvippro.android.utils.ImageUtil
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.activity_edit_packages.btn_save
import kotlinx.android.synthetic.main.activity_edit_packages.et_name
import kotlinx.android.synthetic.main.activity_edit_packages.*
import kotlinx.android.synthetic.main.activity_edit_packages.btn_camera
import kotlinx.android.synthetic.main.activity_edit_packages.iv_camera
import kotlinx.android.synthetic.main.activity_edit_packages.iv_photo
import java.io.File
import java.util.regex.PatternSyntaxException


class EditPackagesActivity : BaseActivity<EditPackagesPresenter, EditPackagesContract.View>(), EditPackagesContract.View {
    private lateinit var choosePhotoHelper: ChoosePhotoHelper
    override fun createPresenter(): EditPackagesPresenter {
        return EditPackagesPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_packages
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
                val name = et_name.text.toString().trim()
                val price = et_price.text.toString().trim().replace(",","")
                getPresenter()?.onCheck(name, price)
            }else{
                val name = et_name.text.toString().trim()
                val price = et_price.text.toString().trim()
                getPresenter()?.onCheck(name, price)
            }
        }

        btn_camera.setOnClickListener {
            getPresenter()?.onCheckPhoto()
        }

        if(decimal=="No Decimal") {
            et_price.addTextChangedListener(NumberTextWatcher(et_price))
        }else{
            et_price.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
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
                    object : ImageCompression(this@EditPackagesActivity) {
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
            title = getString(R.string.menu_edit_packages)
            elevation = 0f

        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        choosePhotoHelper.onActivityResult(requestCode, resultCode, data)
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

    override fun setPackagesName(name: String?,price: String?) {
        name?.let {
            et_name.setText(it)
        }
        price?.let {
            et_price.setText(it)
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
