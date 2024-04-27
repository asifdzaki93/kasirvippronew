package id.kasirvippro.android.feature.manageStock.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.manageStock.dataTransfer.main.DataTransferActivity
import id.kasirvippro.android.feature.manageStock.stockHistory.list.StockHistoryListActivity
import id.kasirvippro.android.feature.manageStock.stockOpname.list.StockOpnameListActivity
import id.kasirvippro.android.feature.manageStock.stockRawMaterial.list.StockRawMaterialListActivity
import id.kasirvippro.android.feature.purchaseMaterial.main.PurchaseMaterialActivity
import id.kasirvippro.android.feature.transfer.main.TransferActivity
import id.kasirvippro.android.feature.transferReturn.main.TransferReturnActivity
import kotlinx.android.synthetic.main.fragment_manage_stock.*


class ManageStockActivity : BaseActivity<ManageStockPresenter, ManageStockContract.View>(),
    ManageStockContract.View {

    override fun createPresenter(): ManageStockPresenter {
        return ManageStockPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.fragment_manage_stock
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){
        btn_product.setOnClickListener {
            openStockOpnamePage()
        }

        btn_history.setOnClickListener {
            openStockHistoryPage()
        }

        btn_transfer.setOnClickListener {
            openTransferPage()
        }

        btn_datatransfer.setOnClickListener {
            openDataTransferPage()
        }

        btn_transfer_return.setOnClickListener {
            openReturnTransferPage()
        }

        btn_purchasestock.setOnClickListener {
            openPurchaseStockPage()
        }

        btn_rawmaterial.setOnClickListener {
            openRawMaterialPage()
        }
    }

    override fun openStockOpnamePage() {
        startActivity(Intent(this, StockOpnameListActivity::class.java))
    }

    override fun openStockHistoryPage() {
        startActivity(Intent(this, StockHistoryListActivity::class.java))
    }

    override fun openTransferPage() {
        startActivity(Intent(this, TransferActivity::class.java))
    }

    override fun openDataTransferPage() {
        startActivity(Intent(this, DataTransferActivity::class.java))
    }

    override fun openReturnTransferPage() {
        startActivity(Intent(this, TransferReturnActivity::class.java))
    }

    override fun openPurchaseStockPage() {
        startActivity(Intent(this, PurchaseMaterialActivity::class.java))
    }

    override fun openRawMaterialPage() {
        startActivity(Intent(this, StockRawMaterialListActivity::class.java))
    }

    override fun onMasterPage(isPremium: Boolean) {
        btn_product.visibility = View.VISIBLE
        btn_transfer.visibility = View.VISIBLE
        btn_history.visibility = View.VISIBLE
    }

    override fun onAdminPage() {
        btn_product.visibility = View.VISIBLE
        btn_transfer.visibility = View.VISIBLE
        btn_history.visibility = View.VISIBLE
    }

    override fun onSalesPage() {
        btn_product.visibility = View.VISIBLE
        btn_transfer.visibility = View.VISIBLE
        btn_history.visibility = View.VISIBLE

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_manage_stock)
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
