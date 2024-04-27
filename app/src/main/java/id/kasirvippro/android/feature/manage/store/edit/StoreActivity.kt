package id.kasirvippro.android.feature.manage.store.edit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.aminography.choosephotohelper.ChoosePhotoHelper
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.BottomDialog
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.ImageCompression
import id.kasirvippro.android.utils.ImageUtil
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.activity_edit_store.*
import kotlinx.android.synthetic.main.activity_edit_store.btn_save
import kotlinx.android.synthetic.main.activity_edit_store.et_address
import kotlinx.android.synthetic.main.activity_edit_store.et_email
import kotlinx.android.synthetic.main.activity_edit_store.et_name
import kotlinx.android.synthetic.main.activity_edit_store.et_phone
import kotlinx.android.synthetic.main.activity_edit_store.iv_camera
import java.io.File

class StoreActivity : BaseActivity<StorePresenter, StoreContract.View>(),
    StoreContract.View, BottomDialog.Listener {
    private lateinit var choosePhotoHelper: ChoosePhotoHelper
    private val currencyDialog = BottomDialog.newInstance()


    override fun createPresenter(): StorePresenter {
        return StorePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_store
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView(){

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
//                    val path = FilePathUtil.getPath(this@StoreActivity,uriImage)
                    val imageUtil = @SuppressLint("StaticFieldLeak")
                    object : ImageCompression(this@StoreActivity) {
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

        btn_save.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val name = et_name.text.toString().trim()
            val email = et_email.text.toString().trim()
            val phone = et_phone.text.toString().trim()
            val address = et_address.text.toString().trim()
            val tax = et_tax.text.toString().trim()
            val service = et_service.text.toString().trim()
            val currency = et_currency.text.toString().trim()
            val footer = et_footer.text.toString().trim()
            getPresenter()?.onCheck(name,email,phone,address,tax,service,currency,footer)
        }

        rg_shift.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_not_active -> getPresenter()?.setActive("0")
                R.id.rb_active -> getPresenter()?.setActive("1")

            }
        }

        btn_camera.setOnClickListener {
            getPresenter()?.onCheckPhoto()
        }

        et_currency.setOnClickListener {
            showLoadingDialog()
            getPresenter()?.onCheckCurrency()
        }

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Edit Store"
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        choosePhotoHelper.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        choosePhotoHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
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

    override fun onItemClicked(data: DialogModel, type: Int) {
        getPresenter()?.setSelectedCurrency(data)
    }

    override fun openCurrencies(title: String, list: List<DialogModel>, selected: DialogModel?) {
        hideLoadingDialog()
        if (currencyDialog.dialog != null && currencyDialog.dialog!!.isShowing) {

        } else {
            currencyDialog.setData(title,1, list,selected)
            currencyDialog.show(supportFragmentManager, "currency")
        }
    }

    override fun setCurrencyName(name: String) {
        et_currency.text = name
    }

    override fun setStoreName(value: String?) {
        et_name.setText(value)
    }
    override fun setNohp(value: String?) {
        et_phone.setText(value)
    }

    override fun setAdress(value: String?) {
        et_address.setText(value)
    }
    override fun setEmail(value: String?) {
        et_email.setText(value)
    }
    override fun setTaxt(value: String?) {
        et_tax.setText(value)
    }

    override fun setSc(value: String?) {
        et_service.setText(value)
    }
    override fun setCurrency(value: String?) {
        et_currency.setText(value)
    }
    override fun setFooter(value: String?) {
        et_footer.setText(value)
    }

    override fun setShift(value: String?) {
        if("0" == value){
            rg_shift.check(R.id.rb_not_active)
        }
        else{
            rg_shift.check(R.id.rb_active)
        }
    }




}
