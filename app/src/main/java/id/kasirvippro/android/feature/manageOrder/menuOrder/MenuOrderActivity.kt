package id.kasirvippro.android.feature.manageOrder.menuOrder

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.manageOrder.kitchen.main.DataOrderActivity
import id.kasirvippro.android.feature.manageOrder.joinTable.list.JoinTableListActivity
import id.kasirvippro.android.feature.manageOrder.main.ManageOrderActivity
import id.kasirvippro.android.feature.manageOrder.moveTable.list.MoveTableListActivity


import id.kasirvippro.android.feature.webview.WebViewActivity
import id.kasirvippro.android.utils.AppConstant


import kotlinx.android.synthetic.main.fragment_menu_order.*


class MenuOrderActivity : BaseActivity<MenuOrderPresenter, MenuOrderContract.View>(),
    MenuOrderContract.View {

    override fun createPresenter(): MenuOrderPresenter {
        return MenuOrderPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.fragment_menu_order
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){


        btn_movetable.setOnClickListener {
            openMovetablePage()
        }
        btn_jointable.setOnClickListener {
            openJointablePage()
        }
        btn_kitchen.setOnClickListener {
            openKitchenPage()
        }

        btn_editorder.setOnClickListener {
            openEditOrderPage()
        }



    }


    override fun openMovetablePage() {
        startActivity(Intent(this, MoveTableListActivity::class.java))
    }
    override fun openJointablePage() {
        startActivity(Intent(this, JoinTableListActivity::class.java))
    }
    override fun openKitchenPage() {
        startActivity(Intent(this, DataOrderActivity::class.java))
    }
    override fun openEditOrderPage() {
        startActivity(Intent(this, ManageOrderActivity::class.java))
    }

    override fun openWebviewPage(title:String,url: String) {
        val browserIntent = Intent(this, WebViewActivity::class.java)
        browserIntent.putExtra(AppConstant.Webview.TITLE,title)
        browserIntent.putExtra(AppConstant.Webview.URL,url)
        startActivity(browserIntent)
    }

    override fun onMasterPage(isPremium: Boolean) {
        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store"){

            btn_kitchen.visibility = View.GONE
            btn_movetable.visibility = View.GONE
            btn_jointable.visibility = View.GONE
            btn_editorder.visibility = View.VISIBLE


        }else if(typestore=="Service products" || typestore=="Healthcare") {

            btn_kitchen.visibility = View.VISIBLE
            btn_movetable.visibility = View.GONE
            btn_jointable.visibility = View.GONE
            btn_editorder.visibility = View.VISIBLE

        }else if(typestore=="Culinary") {

            btn_kitchen.visibility = View.VISIBLE
            btn_movetable.visibility = View.VISIBLE
            btn_jointable.visibility = View.VISIBLE
            btn_editorder.visibility = View.VISIBLE
        }

    }

    override fun onAdminPage() {
        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store"){

            btn_kitchen.visibility = View.GONE
            btn_movetable.visibility = View.GONE
            btn_jointable.visibility = View.GONE
            btn_editorder.visibility = View.VISIBLE


        }else {
            btn_kitchen.visibility = View.VISIBLE
            btn_movetable.visibility = View.VISIBLE
            btn_jointable.visibility = View.VISIBLE
            btn_editorder.visibility = View.VISIBLE

        }

    }

    override fun onSalesPage() {

        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store"){

            btn_kitchen.visibility = View.GONE
            btn_movetable.visibility = View.GONE
            btn_jointable.visibility = View.GONE
            btn_editorder.visibility = View.VISIBLE


        }else {
            btn_kitchen.visibility = View.VISIBLE
            btn_movetable.visibility = View.VISIBLE
            btn_jointable.visibility = View.VISIBLE
            btn_editorder.visibility = View.VISIBLE

        }

    }

    override fun onWaiterPage() {
        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store"){

            btn_kitchen.visibility = View.GONE
            btn_movetable.visibility = View.GONE
            btn_jointable.visibility = View.GONE
            btn_editorder.visibility = View.VISIBLE


        }else {
            btn_kitchen.visibility = View.VISIBLE
            btn_movetable.visibility = View.VISIBLE
            btn_jointable.visibility = View.VISIBLE
            btn_editorder.visibility = View.VISIBLE

        }

    }

    override fun onKitchenPage() {


        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store"){

            btn_kitchen.visibility = View.GONE
            btn_movetable.visibility = View.GONE
            btn_jointable.visibility = View.GONE
            btn_editorder.visibility = View.VISIBLE


        }else {
            btn_kitchen.visibility = View.VISIBLE
            btn_movetable.visibility = View.GONE
            btn_jointable.visibility = View.GONE
            btn_editorder.visibility = View.VISIBLE

        }

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_manage_order
            )
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
