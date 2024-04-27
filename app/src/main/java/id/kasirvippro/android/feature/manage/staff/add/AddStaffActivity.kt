package id.kasirvippro.android.feature.manage.staff.add

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.aminography.choosephotohelper.ChoosePhotoHelper
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.BottomDialog
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.NumberTextWatcher
import id.kasirvippro.android.utils.ImageCompression
import id.kasirvippro.android.utils.ImageUtil
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.activity_add_staff.*
import java.io.File


class AddStaffActivity : BaseActivity<AddStaffPresenter, AddStaffContract.View>(), AddStaffContract.View,BottomDialog.Listener {

    private lateinit var choosePhotoHelper: ChoosePhotoHelper
    private val storeDialog = BottomDialog.newInstance()

    override fun createPresenter(): AddStaffPresenter {
        return AddStaffPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_add_staff
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){

        choosePhotoHelper = ChoosePhotoHelper.with(this)
            .asFilePath()
            .build(ChoosePhotoCallback { photo ->
                if(photo.isNullOrEmpty() || photo.isNullOrBlank()){
                    getPresenter()?.setImagePhotoPath(null)
                    loadPhoto("")
                }
                else{
//                    val tmpBitmap = BitmapFactory.decodeFile(photo)
//                    val uri = Uri.fromFile(File(photo))
//                    val bitmap = ImageHelper.rotateImageIfRequired(tmpBitmap, uri)
//                    val uriImage = ImageUtil.getImageUri(this, bitmap)
//                    val path = FilePathUtil.getPath(this@AddCustomerActivity,uriImage)
                    val imageUtil = @SuppressLint("StaticFieldLeak")
                    object : ImageCompression(this@AddStaffActivity) {
                        override fun onPostExecute(imagePath: String) {
                            super.onPostExecute(imagePath)
                            val compressedImageFile = File(imagePath)
                            if(compressedImageFile.exists()){
                                val compressedSize = ImageUtil.getSizeFile(imagePath)
                                Log.d("choosePhotoHelper compressed size",""+compressedSize)
                                getPresenter()?.setImagePhotoPath(imagePath)
                                loadPhoto(imagePath)

                            }else{
                                getPresenter()?.setImagePhotoPath(null)
                                loadPhoto("")
                                showMessage(999,"Foto tidak ditemukan")
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
            val gaji = et_gaji.text.toString().trim().replace(",","")
            val komisi = et_komisi.text.toString().trim().replace(",","")
            val tunjangan = et_tunjangan.text.toString().trim().replace(",","")
            val potongan = et_potongan.text.toString().trim().replace(",","")
            getPresenter()?.onCheck(name,email,phone,address,gaji,komisi,tunjangan,potongan)
        }

        iv_photo.setOnClickListener {
            getPresenter()?.onCheckPhoto()
        }

        rg_job.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_admin -> getPresenter()?.setLevel("admin")
                R.id.rb_kasir -> getPresenter()?.setLevel("kasir")
                R.id.rb_other -> getPresenter()?.setLevel("other")
                R.id.rb_manager -> getPresenter()?.setLevel("manager")
            }
        }

        rg_job.check(R.id.rb_admin)

        et_gaji.addTextChangedListener(NumberTextWatcher(et_gaji))
        et_komisi.addTextChangedListener(NumberTextWatcher(et_komisi))
        et_potongan.addTextChangedListener(NumberTextWatcher(et_potongan))
        et_tunjangan.addTextChangedListener(NumberTextWatcher(et_tunjangan))

        et_store.setOnClickListener {
            getPresenter()?.onCheckStore()
        }

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Add Employees"
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

    override fun openImageChooser() {
        choosePhotoHelper.showChooser()
    }
    override fun loadPhoto(path: String) {
        GlideApp.with(this)
            .load(path)
            .error(R.drawable.ic_user)
            .transform(CenterCrop(), CircleCrop())
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

    override fun openStores(title: String, list: List<DialogModel>, selected: DialogModel?) {
        hideLoadingDialog()
        if (storeDialog.dialog != null && storeDialog.dialog!!.isShowing) {

        } else {
            storeDialog.setData(title,1, list,selected)
            storeDialog.show(supportFragmentManager, "storeDialog")
        }
    }

    override fun onItemClicked(data: DialogModel, type: Int) {
        getPresenter()?.setSelectedStore(data)
    }

    override fun setStoreName(value: String) {
        et_store.text = value
    }


}
