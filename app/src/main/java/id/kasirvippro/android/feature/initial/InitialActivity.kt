package id.kasirvippro.android.feature.initial

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.choose.sift.ChooseSiftActivity
import id.kasirvippro.android.models.sift.Sift
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.feature.sell.main.SellActivity
import id.kasirvippro.android.ui.NumberTextWatcher
import kotlinx.android.synthetic.main.activity_initial.*
import kotlinx.android.synthetic.main.activity_initial.btn_save


class InitialActivity : BaseActivity<InitialPresenter, InitialContract.View>(), InitialContract.View {

    private val CODE_OPEN_CHOOSE_SIFT = 1005

    override fun createPresenter(): InitialPresenter {
        return InitialPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_initial
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }



    private fun renderView(){



        btn_save.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val modal_awal = et_modal_awal.text.toString().trim().replace(",","")
            val sift = et_sift.text.toString().trim()
            getPresenter()?.onCheck(modal_awal,sift)
        }

        et_sift.setOnClickListener {
            openChooseSift()
        }

        et_modal_awal.addTextChangedListener(NumberTextWatcher(et_modal_awal))
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Initial"
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

    override fun setData(
        modal_awal: String?,
        sift: String?
    ) {
        modal_awal?.let {
            et_modal_awal.setText(it)
        }

        sift?.let {
            et_sift.setText(it)
        }

    }

    override fun onClose(status: Int) {
        setResult(status,intent)
        restartMainActivity()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> restartMainActivity()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun openChooseSift() {
        val intent = Intent(this, ChooseSiftActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_SIFT)
    }

    override fun openSuccessPage() {
        val intent = Intent(this, SellActivity::class.java)
        startActivity(intent)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_OPEN_CHOOSE_SIFT && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this,"Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"Data not found")
                return
            }
            val toko = data.getSerializableExtra(AppConstant.DATA) as Sift
            if (toko.name_sift == null) {
                toast(this,"Data tidak ditemukan")
            } else {
                et_sift.text = toko.name_sift
            }
        }
    }


}
