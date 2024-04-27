package id.kasirvippro.android.feature.spend.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aminography.choosephotohelper.ChoosePhotoHelper
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.SingleDateDialog
import id.kasirvippro.android.feature.spend.add.AddActivity
import id.kasirvippro.android.models.transaction.RequestSpend
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.ImageCompression
import id.kasirvippro.android.utils.ImageUtil
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.activity_spend.*
import org.threeten.bp.LocalDate
import java.io.File

class SpendActivity : BaseActivity<SpendPresenter,SpendContract.View>(), SpendContract.View,
SingleDateDialog.Listener{
    private lateinit var choosePhotoHelper: ChoosePhotoHelper
    private val TAG = SpendActivity::class.java.simpleName

    private val CODE_OPEN_ADD_MANUAL = 1003

    private val adapter = SpendAdapter()

    override fun createPresenter(): SpendPresenter {
        return SpendPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_spend
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.spending)
            elevation = 0f
        }

    }

    private fun renderView() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        adapter.callback = object : SpendAdapter.ItemClickCallback {

            override fun onDelete(data: RequestSpend.Barang, position: Int) {
                getPresenter()?.delete(data, position)

            }
        }

        btn_date.setOnClickListener {
            val now = LocalDate.now()
            openSingleDatePickerDialog(getPresenter()?.getSelectedDate(),null,now,AppConstant.Code.CODE_FILTER_DATE_SELL)
        }


        btn_camera.setOnClickListener {
            getPresenter()?.onCheckPhoto()
        }

        btn_bayar.setOnClickListener {
            showLoadingDialog()
            getPresenter()?.check()
        }

        choosePhotoHelper = ChoosePhotoHelper.with(this)
            .asFilePath()
            .build(ChoosePhotoCallback { photo ->
                if(photo.isNullOrEmpty() || photo.isNullOrBlank()){
                    getPresenter()?.setImagePhotoPath(null)
                    loadPhoto("")
                    iv_camera.visibility = View.VISIBLE
                }
                else{
//                    val tmpBitmap = BitmapFactory.decodeFile(photo)
//                    val uri = Uri.fromFile(File(photo))
//                    val bitmap = ImageHelper.rotateImageIfRequired(tmpBitmap, uri)
//                    val uriImage = ImageUtil.getImageUri(this, bitmap)
//                    val path = FilePathUtil.getPath(this@AddProductActivity,uriImage)
//                    val size = ImageUtil.getSizeFile(path!!)
//                    Log.d("choosePhotoHelper",""+size)
                    val imageUtil = @SuppressLint("StaticFieldLeak")
                    object : ImageCompression(this@SpendActivity) {
                        @SuppressLint("LongLogTag")
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
            R.id.action_add -> openAddPage()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        choosePhotoHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun openImageChooser() {
        choosePhotoHelper.showChooser()
    }
    override fun loadPhoto(path: String) {
        Log.d("choosePhotoHelper",""+path)
        GlideApp.with(this)
            .load(path)
            .transform(CenterCrop(), RoundedCorners(4))
            .into(iv_photo)

    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        choosePhotoHelper.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_OPEN_ADD_MANUAL && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this,"No Data")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"No Data")
                return
            }
            val product = data.getSerializableExtra(AppConstant.DATA) as RequestSpend.Barang
            if (product.id == null) {
                toast(this,"No Data")
            } else {
                getPresenter()?.add(product)
            }
        }

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

    override fun openAddPage() {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_ADD_MANUAL)
    }

    override fun openSuccessPage() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Success")
        builder.setMessage("Expenses saved successfully!")
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            restartMainActivity()
        }
        builder.show()
    }

    override fun showContentView() {
        ll_content.visibility = View.VISIBLE
        ll_error.visibility = View.GONE
    }

    override fun showErrorView(err: String) {
        ll_content.visibility = View.GONE
        ll_error.visibility = View.VISIBLE
        tv_error.text = err
    }

    override fun setNominalText(count: String, nominal: String) {
        tv_number.text = count
        tv_total.text = nominal
    }

    override fun add(data: RequestSpend.Barang) {
        adapter.addItem(data)
    }

    override fun getTotalValue(): Double {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(decimal=="No Decimal") {
            val value = tv_total.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(",", "")
            if (value.isBlank() || value.isEmpty()) {
                return 0.0
            }
            return value.toDouble()
        }else{
            val value = tv_total.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "")
            if (value.isBlank() || value.isEmpty()) {
                return 0.0
            }
            return value.toDouble()
        }




    }

    override fun delete(position: Int) {
        adapter.deleteItem(position)
    }

    override fun openSingleDatePickerDialog(selected: CalendarDay?, minDate: LocalDate?, maxDate: LocalDate?, type: Int) {
        val dateDialog = SingleDateDialog.newInstance()
        dateDialog.setData(selected,minDate,maxDate,-1)
        dateDialog.show(this.supportFragmentManager, SingleDateDialog.TAG)
    }

    override fun onDateClicked(selected: CalendarDay?, type: Int) {
        getPresenter()?.setSelectedDate(selected)
        if (selected == null) {
            et_date.text = ""
        } else {
            et_date.text = Helper.getDateFormat(this, selected.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
        }
    }

}
