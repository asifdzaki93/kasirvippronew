package id.kasirvippro.android.feature.hutangpiutang.main

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.tabs.TabLayout
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.hutangpiutang.hutang.HutangFragment
import id.kasirvippro.android.feature.hutangpiutang.piutang.PiutangFragment
import id.kasirvippro.android.models.TabModel
import id.kasirvippro.android.ui.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_hutang_piutang.*

class MainActivity : BaseActivity<MainPresenter, MainContract.View>(),
    MainContract.View {

    private val fragmentManager = supportFragmentManager
    private val viewPagerAdapter = ViewPagerAdapter(fragmentManager)


    override fun createPresenter(): MainPresenter {
        return MainPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_hutang_piutang
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
        tab1.fragment = HutangFragment.newInstance()
        tab1.title = getString(R.string.supplier_payable)
        val tab2 = TabModel()
        tab2.fragment = PiutangFragment.newInstance()
        tab2.title = getString(R.string.receivables_customer)
        type.add(tab1)
        type.add(tab2)
        setupViewPager(type)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_hutang_piutang)
            elevation = 0f
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }



    private fun setupViewPager(list: List<TabModel>) {
        for (type in list) {
            if (type != null) {
                viewPagerAdapter.addFragment(type.fragment, type.title)
            }
        }
        viewpager.setAdapter(viewPagerAdapter)
        tabs.tabMode = TabLayout.MODE_FIXED
        tabs.setupWithViewPager(viewpager)

    }

}
