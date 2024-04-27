package id.kasirvippro.android.feature.manage.customer.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.manage.customer.credit.CustomerCreditFragment
import id.kasirvippro.android.feature.manage.customer.edit.EditCustomerActivity
import id.kasirvippro.android.feature.manage.customer.transaction.CustomerTransactionFragment
import id.kasirvippro.android.models.TabModel
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.ui.ViewPagerAdapter
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.activity_customer_detail.*
import kotlin.math.abs

class CustomerDetailActivity : BaseActivity<CustomerDetailPresenter, CustomerDetailContract.View>(),
    CustomerDetailContract.View {

    private val fragmentManager = supportFragmentManager
    private val viewPagerAdapter = ViewPagerAdapter(fragmentManager)
    private val CODE_OPEN_EDIT = 102
    private var status = Activity.RESULT_CANCELED


    override fun createPresenter(): CustomerDetailPresenter {
        return CustomerDetailPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_customer_detail
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
        setupTab()
    }

    private fun renderView() {
        setupToolbar()
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {

                var title = ""
                getPresenter()?.getTitleName()?.let {
                    title = it
                }
                ctl.title = title

            } else {
                ctl.title = ""

            }
        })
        ctl.title = ""

        ll_edit.setOnClickListener {
            openEditPage()
        }
    }

    private fun setupTab(){
        val type : ArrayList<TabModel> = ArrayList()
        val tab1 = TabModel()
        tab1.fragment = CustomerTransactionFragment.newInstance(getPresenter()?.getCustomerData())
        tab1.title = "Transaction"
        val tab2 = TabModel()
        tab2.fragment = CustomerCreditFragment.newInstance(getPresenter()?.getCustomerData())
        tab2.title = "Debt"
        type.add(tab1)
        type.add(tab2)
        setupViewPager(type)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            elevation = 0f
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun hideShowToolbar(isShow: Boolean) {
        appBar.setExpanded(isShow)
    }

    private fun setupViewPager(list: List<TabModel>) {
        for (type in list) {
            viewPagerAdapter.addFragment(type.fragment, type.title)
        }
        viewpager.setAdapter(viewPagerAdapter)
        tabs.tabMode = TabLayout.MODE_FIXED
        tabs.setupWithViewPager(viewpager)

    }

    override fun setCustomer(name: String?, email: String?, phone: String?, address: String?)
    {
        name?.let {
            tv_name.text = it
            tv_initial.text = Helper.getInisialName(it)
        }

        email?.let {
            tv_email.text = it
        }

        phone?.let {
            tv_phone.text = it
        }

        address?.let {
            tv_address.text = it
        }




    }

    override fun onClose(status: Int) {
        setResult(status,intent)
        finish()
    }

    override fun openEditPage() {
        val intent = Intent(this, EditCustomerActivity::class.java)
        intent.putExtra(AppConstant.DATA,getPresenter()?.getCustomerData())
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == CODE_OPEN_EDIT) {
            status = Activity.RESULT_OK
            val dt = data?.getSerializableExtra(AppConstant.DATA) as Customer
            dt.let {
                getPresenter()?.setCustomerData(dt)
            }
        }
    }

    override fun onBackPressed() {
        onClose(status)
    }

}
