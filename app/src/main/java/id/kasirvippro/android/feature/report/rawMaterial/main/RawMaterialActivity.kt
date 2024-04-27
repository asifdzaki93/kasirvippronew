package id.kasirvippro.android.feature.report.rawMaterial.main

import android.os.Bundle
import android.view.MenuItem
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.BottomDialog
import id.kasirvippro.android.models.TabModel
import id.kasirvippro.android.ui.ViewPagerAdapter
import id.kasirvippro.android.feature.report.rawMaterial.daily.ListRawMaterialFragment
import id.kasirvippro.android.models.DialogModel
import kotlinx.android.synthetic.main.fragment_history.*


class RawMaterialActivity : BaseActivity<RawMaterialPresenter, RawMaterialContract.View>(), RawMaterialContract.View,
    ListRawMaterialFragment.Listener,BottomDialog.Listener {


    private val fragmentManager = supportFragmentManager
    private val viewPagerAdapter = ViewPagerAdapter(fragmentManager)

    private val transactionFragment = ListRawMaterialFragment.newInstance()

    override fun createPresenter(): RawMaterialPresenter {
        return RawMaterialPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.fragment_manage_order
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
        tab1.fragment = ListRawMaterialFragment.newInstance()
        tab1.title = getString(R.string.raw_material_report)
        type.add(tab1)
        setupViewPager(type)
    }



    private fun setupViewPager(list: List<TabModel>) {
        for (type in list) {
            viewPagerAdapter.addFragment(type.fragment, type.title)
        }
        viewpager.setAdapter(viewPagerAdapter)

    }

    override fun openFilterByStatusDialog(title: String, list: List<DialogModel>, selected: DialogModel?, type: Int) {
        val dialog = BottomDialog.newInstance()
        dialog.setData(title,type,list,selected)
        dialog.show(this.supportFragmentManager, BottomDialog.TAG)
    }

    override fun onItemClicked(data: DialogModel, type: Int) {
            transactionFragment.onFilterStatusSelected(data)
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.raw_material_report)
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item!!)
    }

}
