package id.kasirvippro.android.feature.manage.staff.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.feature.manage.staff.add.AddStaffActivity
import id.kasirvippro.android.feature.manage.staff.edit.EditStaffActivity
import kotlinx.android.synthetic.main.activity_list_staff.btn_add
import kotlinx.android.synthetic.main.activity_list_staff.rv_list
import kotlinx.android.synthetic.main.activity_list_staff.sw_refresh


class StaffListActivity : BaseActivity<StaffListPresenter, StaffListContract.View>(),
    StaffListContract.View {

    val adapter = StaffListAdapter()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val CODE_OPEN_ADD = 1001
    private val CODE_OPEN_EDIT = 1002

    override fun createPresenter(): StaffListPresenter {
        return StaffListPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_list_staff
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView() {
        sw_refresh.setOnRefreshListener {
            scrollListener.resetState()
            reloadData()
        }

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onFirstItemVisible(isFirstItem: Boolean) {
                sw_refresh.isEnabled = isFirstItem && adapter.itemCount > 0
            }

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {

            }
        }
        rv_list.addOnScrollListener(scrollListener)

        adapter.callback = object : StaffListAdapter.ItemClickCallback{
            override fun onClick(data: Staff) {
                openEditPage(data)
            }

            override fun onDelete(data: Staff) {
                showLoadingDialog()
                getPresenter()?.delete(data.phone_number!!)
            }
        }

        btn_add.setOnClickListener {
            openAddPage()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_one, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.lbl_search_staff)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener  {

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.clearAdapter()
                sw_refresh.isRefreshing = true
                getPresenter()?.search(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_manage_staff_title)
            elevation = 0f
        }

    }

    override fun setData(list: List<Staff>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        adapter.setItems(list)
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun showErrorMessage(code: Int, msg: String) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> toast(this,msg)
        }

    }

    override fun showSuccessMessage(msg: String?) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        msg?.let {
            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
        }
        reloadData()

    }

    override fun reloadData() {
        sw_refresh.isRefreshing = true
        adapter.clearAdapter()
        getPresenter()?.loadData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            reloadData()
        }
    }

    override fun openAddPage() {
        val intent = Intent(this,AddStaffActivity::class.java)
        startActivityForResult(intent,CODE_OPEN_ADD)
    }

    override fun openEditPage(data: Staff) {
        val intent = Intent(this,EditStaffActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

}
