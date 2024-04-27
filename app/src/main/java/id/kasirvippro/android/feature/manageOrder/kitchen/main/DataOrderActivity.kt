package id.kasirvippro.android.feature.manageOrder.kitchen.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.BottomDialog
import id.kasirvippro.android.models.TabModel
import id.kasirvippro.android.ui.ViewPagerAdapter
import id.kasirvippro.android.feature.manageOrder.kitchen.transaction.TransactionFragment
import id.kasirvippro.android.feature.manageOrder.kitchen.transactionPreorder.PreorderFragment
import id.kasirvippro.android.feature.manageOrder.kitchen.transactionSuccess.TransactionSuccessFragment
import id.kasirvippro.android.models.DialogModel
import kotlinx.android.synthetic.main.fragment_history.*


class DataOrderActivity : BaseActivity<DataOrderPresenter, DataOrderContract.View>(), DataOrderContract.View,
    TransactionFragment.Listener, PreorderFragment.Listener, BottomDialog.Listener {

    private var mFragmentManager : FragmentManager ?= null
    private var viewPagerAdapter : ViewPagerAdapter ?= null
    private val transactionFragment = TransactionFragment.newInstance()
    private val preorderFragment = PreorderFragment.newInstance()
    private val spendFragment = TransactionSuccessFragment.newInstance()

    override fun createPresenter(): DataOrderPresenter {
        return DataOrderPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.fragment_data_order
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
        tab1.title = getString(R.string.new_order)
        val tab2 = TabModel()
        tab2.fragment = preorderFragment
        tab2.title = getString(R.string.pre_order)
        val tab3 = TabModel()
        tab3.fragment = spendFragment
        tab3.title = getString(R.string.finish_cocking)
        type.add(tab1)
        type.add(tab2)
        type.add(tab3)
        setupViewPager(type)
    }

    private fun setupViewPager(list: List<TabModel>) {
        for (type in list) {
            viewPagerAdapter?.addFragment(type.fragment, type.title)
        }
        viewpager.adapter = viewPagerAdapter
        tabs.tabMode = TabLayout.MODE_FIXED
        tabs.setupWithViewPager(viewpager)
        viewpager.offscreenPageLimit = 3
    }

    override fun setSelectTab(position: Int) {
        tabs.getTabAt(position)?.select()
    }

    override fun checkTab(position: Int) {
        setSelectTab(position)
        if(position == 0){
            transactionFragment.reloadData()
        }

    }

    override fun openFilterByStatusDialog(title: String, list: List<DialogModel>, selected: DialogModel?, type: Int) {
        val dialog = BottomDialog.newInstance()
        dialog.setData(title,type,list,selected)
        dialog.show(this.supportFragmentManager, BottomDialog.TAG)
    }

    override fun onItemClicked(data: DialogModel, type: Int) {

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
