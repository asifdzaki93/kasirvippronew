package id.kasirvippro.android.feature.manageOrder.table.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.manage.table.edit.EditTableActivity
import id.kasirvippro.android.feature.manage.table.transaction.TableTransactionFragment
import id.kasirvippro.android.models.TabModel
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.ui.ViewPagerAdapter
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_table_detail.*

class TableDetailActivity : BaseActivity<TableDetailPresenter, TableDetailContract.View>(),
    TableDetailContract.View {

    private val fragmentManager = supportFragmentManager
    private val viewPagerAdapter = ViewPagerAdapter(fragmentManager)
    private val codeOpenEdit = 102
    private var status = Activity.RESULT_CANCELED


    override fun createPresenter(): TableDetailPresenter {
        return TableDetailPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_table_detail
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
        setupTab()
    }

    private fun renderView() {
        setupToolbar()

    }

    private fun setupTab(){
        val type : ArrayList<TabModel> = ArrayList()
        val tab1 = TabModel()
        tab1.fragment = TableTransactionFragment.newInstance(getPresenter()?.getTableData())
        tab1.title=getString(R.string.lbl_manage_order)
        type.add(tab1)
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
            title = getString(R.string.lbl_manage_order)
            elevation = 0f
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }


    private fun setupViewPager(list: List<TabModel>) {
        for (type in list) {
            viewPagerAdapter.addFragment(type.fragment, type.title)
        }
        viewpager.setAdapter(viewPagerAdapter)

    }

    override fun setTable(name: String?)
    {

    }

    override fun onClose(status: Int) {
        setResult(status,intent)
        finish()
    }

    override fun openEditPage() {
        val intent = Intent(this, EditTableActivity::class.java)
        intent.putExtra(AppConstant.DATA,getPresenter()?.getTableData())
        startActivityForResult(intent,codeOpenEdit)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == codeOpenEdit) {
            status = Activity.RESULT_OK
            val dt = data?.getSerializableExtra(AppConstant.DATA) as Table
            dt.let {
                getPresenter()?.setTableData(dt)
            }
        }
    }

    override fun onBackPressed() {
        onClose(status)
    }

}
