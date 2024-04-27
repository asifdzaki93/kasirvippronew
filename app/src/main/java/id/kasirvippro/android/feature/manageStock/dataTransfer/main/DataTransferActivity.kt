package id.kasirvippro.android.feature.manageStock.dataTransfer.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dataTransfer.transactionReturn.TransactionReturnFragment
import id.kasirvippro.android.feature.dialog.BottomDialog
import id.kasirvippro.android.feature.manageStock.dataTransfer.transferOut.TransferOutFragment
import id.kasirvippro.android.models.TabModel
import id.kasirvippro.android.ui.ViewPagerAdapter
import id.kasirvippro.android.feature.manageStock.dataTransfer.transferIn.TransferInFragment
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.fragment_history.*


class DataTransferActivity : BaseActivity<DataTransferPresenter, DataTransferContract.View>(), DataTransferContract.View,
    TransferInFragment.Listener,TransferOutFragment.Listener, TransactionReturnFragment.Listener, BottomDialog.Listener {

    private var mFragmentManager : FragmentManager ?= null
    private var viewPagerAdapter : ViewPagerAdapter ?= null
    private val transferInFragment = TransferInFragment.newInstance()
    private val transferOutFragment = TransferOutFragment.newInstance()
    private val transferReturnFragment = TransactionReturnFragment.newInstance()

    override fun createPresenter(): DataTransferPresenter {
        return DataTransferPresenter(this, this)
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
        tab1.fragment = transferInFragment
        tab1.title = getString(R.string.lbl_transfer_in)
        val tab2 = TabModel()
        tab2.fragment = transferOutFragment
        tab2.title = getString(R.string.lbl_transfer_out)
        val tab3 = TabModel()
        tab3.fragment = transferReturnFragment
        tab3.title = getString(R.string.transfer_return)
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
            transferInFragment.reloadData()
        }
        else if(position == 1){
            transferOutFragment.reloadData()
        }
    }

    override fun openFilterByStatusDialog(title: String, list: List<DialogModel>, selected: DialogModel?, type: Int) {
        val dialog = BottomDialog.newInstance()
        dialog.setData(title,type,list,selected)
        dialog.show(this.supportFragmentManager, BottomDialog.TAG)
    }

    override fun onItemClicked(data: DialogModel, type: Int) {
        if(type == AppConstant.Code.CODE_TRANSACTION_CUSTOMER){
            transferInFragment.onFilterStatusSelected(data)
        }
        else{
            transferOutFragment.onFilterStatusSelected(data)
        }
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_data_transfer_title)
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
