package id.kasirvippro.android.feature.etalase.kelolatoko.edit

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.choose.color.ChooseColorActivity
import id.kasirvippro.android.models.color.Color
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.aminography.choosephotohelper.ChoosePhotoHelper
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.utils.ImageCompression
import id.kasirvippro.android.utils.ImageUtil
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.activity_edit_kelolatoko.*
import java.io.File


class kelolatokoEditActivity : BaseActivity<kelolatokoEditPresenter, kelolatokoEditContract.View>(), kelolatokoEditContract.View {
    private lateinit var choosePhotoHelper: ChoosePhotoHelper
    private val CODE_OPEN_CHOOSE_COLOR = 1005

    override fun createPresenter(): kelolatokoEditPresenter {
        return kelolatokoEditPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_kelolatoko
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
                    object : ImageCompression(this@kelolatokoEditActivity) {
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

        et_subdomain.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                tvSample.setText(AppConstant.URL.STORE + "id-"+s)
            }
        })

        btn_save.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val nama_toko = et_nama_toko.text.toString().trim()
            val alamat_toko = et_alamat_toko.text.toString().trim()
            val warna_toko = et_warna_toko.text.toString().trim()
            val jam_operasional = et_jam_operasional.text.toString().trim()
            val linkfb = et_linkfb.text.toString().trim()
            val linkinstagram = et_linkinstagram.text.toString().trim()
            val nowa = et_nowa.text.toString().trim()
            val subdomain = et_subdomain.text.toString().trim()
            getPresenter()?.onCheck(nama_toko,alamat_toko,warna_toko,jam_operasional,linkfb,linkinstagram,nowa,subdomain)
        }


        btn_camera.setOnClickListener {
            getPresenter()?.onCheckPhoto()
        }


        et_warna_toko.setOnClickListener {
            openChooseColor()
        }
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Edit Online Store"
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


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        choosePhotoHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun setStoreName(value: String?) {
        et_nama_toko.setText(value)
    }
    override fun setAdress(value: String?) {
        et_alamat_toko.setText(value)
    }
    override fun setColor(value: String?) {
        et_warna_toko.setText(value)
    }
    override fun setJam(value: String?) {
        et_jam_operasional.setText(value)
    }
    override fun setFb(value: String?) {
        et_linkfb.setText(value)
    }
    override fun setIg(value: String?) {
        et_linkinstagram.setText(value)
    }
    override fun setNowa(value: String?) {
        et_nowa.setText(value)
    }
    override fun setSubdomain(value: String?) {
        et_subdomain.setText(value)
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

    override fun openChooseColor() {
        val intent = Intent(this, ChooseColorActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_COLOR)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        choosePhotoHelper.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_OPEN_CHOOSE_COLOR && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this,"Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"Data not found")
                return
            }
            val toko = data.getSerializableExtra(AppConstant.DATA) as Color
            if (toko.name_color == null) {
                toast(this,"Data tidak ditemukan")
            } else {
                et_warna_toko.text = toko.name_color
            }
        }
    }


}