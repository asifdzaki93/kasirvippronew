package id.kasirvippro.android.feature.manage.main

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
import id.kasirvippro.android.feature.etalase.kelolatoko.list.KelolatokoListActivity
import id.kasirvippro.android.feature.manage.product.list.ProductListActivity
import id.kasirvippro.android.feature.manage.store.list.StoreListActivity
import id.kasirvippro.android.feature.manage.category.list.CategoryListActivity
import id.kasirvippro.android.feature.manage.discount.list.DiscountListActivity
import id.kasirvippro.android.feature.manage.customer.list.CustomerListActivity
import id.kasirvippro.android.feature.manage.divisi.list.DivisiListActivity
import id.kasirvippro.android.feature.manage.ongkir.list.OngkirListActivity
import id.kasirvippro.android.feature.manage.packages.list.PackagesListActivity
import id.kasirvippro.android.feature.manage.packages.main.PackagesActivity
import id.kasirvippro.android.feature.manage.rawMaterial.list.RawMaterialListActivity
import id.kasirvippro.android.feature.manage.role.list.RoleListActivity
import id.kasirvippro.android.feature.manage.supplier.list.SupplierListActivity
import id.kasirvippro.android.feature.manage.staff.list.StaffListActivity
import id.kasirvippro.android.feature.manage.table.list.TableListActivity
import id.kasirvippro.android.feature.webview.WebViewActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant


import kotlinx.android.synthetic.main.fragment_manage.*
import kotlinx.android.synthetic.main.fragment_manage.btn_kelolatoko


class ManageActivity : BaseActivity<ManagePresenter, ManageContract.View>(),
    ManageContract.View {

    override fun createPresenter(): ManagePresenter {
        return ManagePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.fragment_manage
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){
        btn_product.setOnClickListener {
            openProductPage()
        }
        btn_packages.setOnClickListener {
            openPackagesPage()
        }

        btn_store.setOnClickListener {
            openStorePage()
        }

        btn_rawmaterial.setOnClickListener {
            openRawMaterialPage()
        }

        btn_kelolatoko.setOnClickListener {
            openKelolatokoPage()
        }

        btn_category.setOnClickListener {
            openCategoryPage()
        }


        btn_customer.setOnClickListener {
            openCustomerPage()
        }

        btn_supplier.setOnClickListener {
            openSupplierPage()
        }

        btn_role.setOnClickListener {
            openRolePage()
        }

        btn_staff.setOnClickListener {
            getPresenter()?.onCheckStaff()
        }

        btn_discount.setOnClickListener {
            openDiscountPage()
        }

        btn_ongkir.setOnClickListener {
            openOngkirPage()
        }

        btn_divisi.setOnClickListener {
            openDivisiPage()
        }

        btn_table.setOnClickListener {
            openTablePage()
        }
    }

    override fun setRole(name_role: String?, add_product: String?, add_category: String?, add_supplier: String?, add_customer: String?, add_discount: String?) {
        name_role?.let {
            if(it == "master"){
                btn_product.visibility = View.VISIBLE
                btn_category.visibility = View.VISIBLE
                btn_supplier.visibility = View.VISIBLE
                btn_staff.visibility = View.VISIBLE
                btn_role.visibility = View.VISIBLE
                btn_customer.visibility = View.VISIBLE
                btn_discount.visibility = View.VISIBLE
                btn_store.visibility = View.VISIBLE
                btn_kelolatoko.visibility = View.VISIBLE
                val typestore = AppConstant.TYPESTORE.getTypeData()

                if(typestore=="General store" || typestore=="Service products" || typestore=="Healthcare") {
                    btn_table.visibility = View.GONE
                }else{
                    btn_table.visibility = View.VISIBLE
                }
            }else if(it == "kasir"){
                if(add_product=="YES") {
                    btn_product.visibility = View.VISIBLE
                }else{
                    btn_product.visibility = View.GONE
                }
                if(add_category=="YES") {
                    btn_category.visibility = View.VISIBLE
                }else{
                    btn_category.visibility = View.GONE
                }
                if(add_supplier=="YES") {
                    btn_supplier.visibility = View.VISIBLE
                }else{
                    btn_supplier.visibility = View.GONE
                }
                if(add_customer=="YES") {
                    btn_customer.visibility = View.VISIBLE
                }else{
                    btn_customer.visibility = View.GONE
                }
                if(add_discount=="YES") {
                    btn_discount.visibility = View.VISIBLE
                }else{
                    btn_discount.visibility = View.GONE
                }
                btn_role.visibility = View.GONE
                btn_kelolatoko.visibility = View.GONE
                btn_staff.visibility = View.GONE
                btn_store.visibility = View.GONE

                val typestore = AppConstant.TYPESTORE.getTypeData()
                if(typestore=="General store" || typestore=="Service products" || typestore=="Healthcare") {
                    btn_table.visibility = View.GONE
                }else{
                    btn_table.visibility = View.VISIBLE
                }

            }else if(it == "admin"){
                if(add_product=="YES") {
                    btn_product.visibility = View.VISIBLE
                }else{
                    btn_product.visibility = View.GONE
                }
                if(add_category=="YES") {
                    btn_category.visibility = View.VISIBLE
                }else{
                    btn_category.visibility = View.GONE
                }
                if(add_supplier=="YES") {
                    btn_supplier.visibility = View.VISIBLE
                }else{
                    btn_supplier.visibility = View.GONE
                }
                if(add_customer=="YES") {
                    btn_customer.visibility = View.VISIBLE
                }else{
                    btn_customer.visibility = View.GONE
                }
                if(add_discount=="YES") {
                    btn_discount.visibility = View.VISIBLE
                }else{
                    btn_discount.visibility = View.GONE
                }
                btn_role.visibility = View.GONE
                btn_staff.visibility = View.GONE
                btn_kelolatoko.visibility = View.GONE
                btn_store.visibility = View.GONE

                val typestore = AppConstant.TYPESTORE.getTypeData()
                if(typestore=="General store" || typestore=="Service products" || typestore=="Healthcare") {
                    btn_table.visibility = View.GONE
                }else{
                    btn_table.visibility = View.VISIBLE
                }

            }else if(it == "other"){
                if(add_product=="YES") {
                    btn_product.visibility = View.VISIBLE
                }else{
                    btn_product.visibility = View.GONE
                }
                if(add_category=="YES") {
                    btn_category.visibility = View.VISIBLE
                }else{
                    btn_category.visibility = View.GONE
                }
                if(add_supplier=="YES") {
                    btn_supplier.visibility = View.VISIBLE
                }else{
                    btn_supplier.visibility = View.GONE
                }
                if(add_customer=="YES") {
                    btn_customer.visibility = View.VISIBLE
                }else{
                    btn_customer.visibility = View.GONE
                }
                if(add_discount=="YES") {
                    btn_discount.visibility = View.VISIBLE
                }else{
                    btn_discount.visibility = View.GONE
                }
                btn_role.visibility = View.GONE
                btn_staff.visibility = View.GONE
                btn_kelolatoko.visibility = View.GONE
                btn_store.visibility = View.GONE

                val typestore = AppConstant.TYPESTORE.getTypeData()
                if(typestore=="General store" || typestore=="Service products" || typestore=="Healthcare") {
                    btn_table.visibility = View.GONE
                }else{
                    btn_table.visibility = View.VISIBLE
                }

            }else if(it == "manager"){
                if(add_product=="YES") {
                    btn_product.visibility = View.VISIBLE
                }else{
                    btn_product.visibility = View.GONE
                }
                if(add_category=="YES") {
                    btn_category.visibility = View.VISIBLE
                }else{
                    btn_category.visibility = View.GONE
                }
                if(add_supplier=="YES") {
                    btn_supplier.visibility = View.VISIBLE
                }else{
                    btn_supplier.visibility = View.GONE
                }
                if(add_customer=="YES") {
                    btn_customer.visibility = View.VISIBLE
                }else{
                    btn_customer.visibility = View.GONE
                }
                if(add_discount=="YES") {
                    btn_discount.visibility = View.VISIBLE
                }else{
                    btn_discount.visibility = View.GONE
                }
                btn_role.visibility = View.GONE
                btn_staff.visibility = View.GONE
                btn_kelolatoko.visibility = View.GONE
                btn_store.visibility = View.GONE

                val typestore = AppConstant.TYPESTORE.getTypeData()
                if(typestore=="General store" || typestore=="Service products" || typestore=="Healthcare") {
                    btn_table.visibility = View.GONE
                }else{
                    btn_table.visibility = View.VISIBLE
                }

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

    override fun openProductPage() {
        startActivity(Intent(this, ProductListActivity::class.java))

    }

    override fun openPackagesPage() {
        startActivity(Intent(this, PackagesListActivity::class.java))

    }

    override fun openStorePage() {
        startActivity(Intent(this, StoreListActivity::class.java))
    }

    override fun openRawMaterialPage() {
        startActivity(Intent(this, RawMaterialListActivity::class.java))
    }

    override fun openKelolatokoPage() {
        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = this.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    startActivity(Intent(this, KelolatokoListActivity::class.java))
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

    override fun openCategoryPage() {
        startActivity(Intent(this, CategoryListActivity::class.java))
    }

    override fun openDiscountPage() {
        startActivity(Intent(this, DiscountListActivity::class.java))
    }

    override fun openOngkirPage() {
        startActivity(Intent(this, OngkirListActivity::class.java))
    }

    override fun openDivisiPage() {
        startActivity(Intent(this, DivisiListActivity::class.java))
    }
    override fun openTablePage() {
        startActivity(Intent(this, TableListActivity::class.java))
    }

    override fun openCustomerPage() {
        startActivity(Intent(this, CustomerListActivity::class.java))
    }

    override fun openSupplierPage() {
        startActivity(Intent(this, SupplierListActivity::class.java))
    }


    override fun openRolePage() {
        startActivity(Intent(this, RoleListActivity::class.java))
    }

    override fun openStaffPage() {
        startActivity(Intent(this, StaffListActivity::class.java))
    }

    override fun openWebviewPage(title:String,url: String) {
        val browserIntent = Intent(this, WebViewActivity::class.java)
        browserIntent.putExtra(AppConstant.Webview.TITLE,title)
        browserIntent.putExtra(AppConstant.Webview.URL,url)
        startActivity(browserIntent)
    }

    /*override fun onMasterPage(isPremium: Boolean) {
        btn_product.visibility = View.VISIBLE
        btn_category.visibility = View.VISIBLE
        btn_supplier.visibility = View.VISIBLE
        btn_staff.visibility = View.VISIBLE
        btn_role.visibility = View.VISIBLE
        btn_customer.visibility = View.VISIBLE
        btn_discount.visibility = View.VISIBLE
        btn_store.visibility = View.VISIBLE

        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store" || typestore=="Service products" || typestore=="Healthcare") {
            btn_table.visibility = View.GONE
        }else{
            btn_table.visibility = View.VISIBLE
        }

        if(isPremium){
            btn_lock.visibility = View.GONE
        }
        else{
            btn_lock.visibility = View.VISIBLE
        }
    }

    override fun onAdminPage() {
        btn_product.visibility = View.VISIBLE
        btn_category.visibility = View.VISIBLE
        btn_supplier.visibility = View.VISIBLE
        btn_role.visibility = View.GONE
        btn_staff.visibility = View.GONE
        btn_customer.visibility = View.VISIBLE
        btn_discount.visibility = View.VISIBLE
        btn_lock.visibility = View.GONE
        btn_store.visibility = View.GONE

        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store" || typestore=="Service products" || typestore=="Healthcare") {
            btn_table.visibility = View.GONE
        }else{
            btn_table.visibility = View.VISIBLE
        }

    }

    override fun onSalesPage() {
        btn_product.visibility = View.GONE
        btn_category.visibility = View.GONE
        btn_supplier.visibility = View.VISIBLE
        btn_role.visibility = View.GONE
        btn_staff.visibility = View.GONE
        btn_customer.visibility = View.VISIBLE
        btn_discount.visibility = View.GONE
        btn_lock.visibility = View.GONE
        btn_store.visibility = View.GONE

        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store" || typestore=="Service products" || typestore=="Healthcare") {
            btn_table.visibility = View.GONE
        }else{
            btn_table.visibility = View.VISIBLE
        }
    } */

    override fun loadRole() {
        getPresenter()?.loadRole()
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Management"
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
