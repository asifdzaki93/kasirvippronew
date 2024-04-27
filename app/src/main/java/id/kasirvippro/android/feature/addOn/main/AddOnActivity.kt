package id.kasirvippro.android.feature.addOn.main

import android.app.Service
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.addOn.linkStore.LinkStoreActivity
import id.kasirvippro.android.feature.addOn.medicalHistory.list.MedicalHistoryListActivity
import id.kasirvippro.android.feature.addOn.recipe.main.RecipeActivity
import id.kasirvippro.android.feature.addOn.workManagement.list.StaffListActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.fragment_addon_menu.*


class AddOnActivity : BaseActivity<AddOnPresenter, AddOnContract.View>(),
    AddOnContract.View {

    override fun createPresenter(): AddOnPresenter {
        return AddOnPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.fragment_addon_menu
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){


        btn_link.setOnClickListener {
            openLinkPage()
        }


        btn_work.setOnClickListener {
            openWorkPage()
        }

        btn_medical.setOnClickListener {
            openMedicalHistoryPage()
        }

        btn_recipe.setOnClickListener {
            openRecipePage()
        }
    }

    override fun openLinkPage() {
        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = this.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    startActivity(Intent(this, LinkStoreActivity::class.java))
                }else{
                    Toast.makeText(this,"Your connection is not available", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Your connection is not available", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Your connection is not available", Toast.LENGTH_SHORT).show()
        }
    }

    override fun openWorkPage() {
        startActivity(Intent(this, StaffListActivity::class.java))
    }

    override fun openMedicalHistoryPage() {
        startActivity(Intent(this, MedicalHistoryListActivity::class.java))
    }

    override fun openRecipePage() {
        startActivity(Intent(this, RecipeActivity::class.java))
    }

    override fun onMasterPage(isPremium: Boolean) {
        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store") {
            btn_work.visibility = View.GONE
            btn_medical.visibility = View.GONE
            btn_link.visibility = View.VISIBLE
            btn_recipe.visibility = View.GONE
        }else if(typestore=="Service products") {
            btn_work.visibility = View.VISIBLE
            btn_medical.visibility = View.GONE
            btn_link.visibility = View.VISIBLE
            btn_recipe.visibility = View.GONE
        }else if(typestore=="Healthcare") {
            btn_work.visibility = View.GONE
            btn_medical.visibility = View.VISIBLE
            btn_link.visibility = View.VISIBLE
            btn_recipe.visibility = View.VISIBLE
        }else{
            btn_work.visibility = View.VISIBLE
            btn_medical.visibility = View.GONE
            btn_link.visibility = View.VISIBLE
            btn_recipe.visibility = View.GONE
        }

    }

    override fun onAdminPage() {
        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store") {
            btn_work.visibility = View.GONE
            btn_medical.visibility = View.GONE
            btn_link.visibility = View.VISIBLE
            btn_recipe.visibility = View.GONE
        }else if(typestore=="Service products") {
            btn_work.visibility = View.VISIBLE
            btn_medical.visibility = View.GONE
            btn_link.visibility = View.VISIBLE
            btn_recipe.visibility = View.GONE
        }else if(typestore=="Healthcare") {
            btn_work.visibility = View.GONE
            btn_medical.visibility = View.VISIBLE
            btn_link.visibility = View.VISIBLE
            btn_recipe.visibility = View.VISIBLE
        }else{
            btn_work.visibility = View.VISIBLE
            btn_medical.visibility = View.GONE
            btn_link.visibility = View.VISIBLE
            btn_recipe.visibility = View.GONE
        }
    }

    override fun onSalesPage() {
        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store") {
            btn_work.visibility = View.GONE
            btn_medical.visibility = View.GONE
            btn_link.visibility = View.VISIBLE
            btn_recipe.visibility = View.GONE
        }else if(typestore=="Service products") {
            btn_work.visibility = View.VISIBLE
            btn_medical.visibility = View.GONE
            btn_link.visibility = View.VISIBLE
            btn_recipe.visibility = View.GONE
        }else if(typestore=="Healthcare") {
            btn_work.visibility = View.GONE
            btn_medical.visibility = View.VISIBLE
            btn_link.visibility = View.VISIBLE
            btn_recipe.visibility = View.VISIBLE
        }else{
            btn_work.visibility = View.VISIBLE
            btn_medical.visibility = View.GONE
            btn_link.visibility = View.VISIBLE
            btn_recipe.visibility = View.GONE
        }

    }

    override fun loadProfile() {
        getPresenter()?.loadProfile()
    }

    override fun showErrorMessage(code: Int, msg: String) {
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> toast(this,msg)
        }

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_tools)
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }
}
