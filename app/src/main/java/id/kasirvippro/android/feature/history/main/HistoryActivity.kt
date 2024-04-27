package id.kasirvippro.android.feature.history.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.BottomDialog
import id.kasirvippro.android.feature.history.kulakan.KulakanFragment
import id.kasirvippro.android.feature.history.pengeluaran.SpendFragment
import id.kasirvippro.android.models.TabModel
import id.kasirvippro.android.ui.ViewPagerAdapter
import id.kasirvippro.android.feature.history.transaction.TransactionFragment
import id.kasirvippro.android.feature.history.transactionReturn.TransactionReturnFragment
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryActivity : BaseActivity<HistoryPresenter, HistoryContract.View>(), HistoryContract.View,
    TransactionFragment.Listener,KulakanFragment.Listener,TransactionReturnFragment.Listener, BottomDialog.Listener {

    private var mFragmentManager : FragmentManager ?= null
    private var viewPagerAdapter : ViewPagerAdapter ?= null
    private val transactionFragment = TransactionFragment.newInstance()
    private val buyingFragment = KulakanFragment.newInstance()
    private val transactionReturnFragment = TransactionReturnFragment.newInstance()
    private val spendFragment = SpendFragment.newInstance()

    override fun createPresenter(): HistoryPresenter {
        return HistoryPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.fragment_history
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView(){
        mFragmentManager = supportFragmentManager
        viewPagerAdapter = ViewPagerAdapter(mFragmentManager)

        setupTab()

    }

    private fun setupTab(){
        val type : ArrayList<TabModel> = ArrayList()
        val tab1 = TabModel()
        tab1.fragment = transactionFragment
        tab1.title = getString(R.string.sales)
        val tab2 = TabModel()
        tab2.fragment = buyingFragment
        tab2.title = getString(R.string.wholesale)
        val tab3 = TabModel()
        tab3.fragment = transactionReturnFragment
        tab3.title = getString(R.string.sales_return)
        val tab4 = TabModel()
        tab4.fragment = spendFragment
        tab4.title = getString(R.string.spending)
        type.add(tab1)
        type.add(tab2)
        type.add(tab3)
        type.add(tab4)
        setupViewPager(type)
    }

    private fun setupViewPager(list: List<TabModel>) {
        for (type in list) {
            viewPagerAdapter?.addFragment(type.fragment, type.title)
        }
        viewpager.adapter = viewPagerAdapter
        tabs.tabMode = TabLayout.MODE_FIXED
        tabs.setupWithViewPager(viewpager)
        viewpager.offscreenPageLimit = 4
    }

    override fun setSelectTab(position: Int) {
        tabs.getTabAt(position)?.select()
    }

    override fun checkTab(position: Int) {
        setSelectTab(position)
        if(position == 0){
            transactionFragment.reloadData()
        }
        else if(position == 1){
            buyingFragment.reloadData()
        }
        else if(position == 3){
            transactionReturnFragment.reloadData()
        }

    }

    override fun openFilterByStatusDialog(title: String, list: List<DialogModel>, selected: DialogModel?, type: Int) {
        val dialog = BottomDialog.newInstance()
        dialog.setData(title,type,list,selected)
        dialog.show(this.supportFragmentManager, BottomDialog.TAG)
    }

    override fun onItemClicked(data: DialogModel, type: Int) {
        if(type == AppConstant.Code.CODE_TRANSACTION_CUSTOMER){
            transactionFragment.onFilterStatusSelected(data)
        }
        else{
            buyingFragment.onFilterStatusSelected(data)
        }
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.transaction_history)
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

}
