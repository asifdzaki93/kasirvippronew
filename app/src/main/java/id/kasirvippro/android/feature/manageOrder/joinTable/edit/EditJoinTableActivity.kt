package id.kasirvippro.android.feature.manageOrder.joinTable.edit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.choose.tableActive.ChooseTableActivity
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_move_table.*
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_join_table.et_table
import kotlinx.android.synthetic.main.activity_join_table.btn_delete_table


class EditJoinTableActivity : BaseActivity<EditJoinTablePresenter, EditJoinTableContract.View>(), EditJoinTableContract.View {

    private val codeOpenChooseTable = 1007

    override fun createPresenter(): EditJoinTablePresenter {
        return EditJoinTablePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_join_table
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView(){
        btn_save.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val name = et_name.text.toString().trim()
            val id_table = et_table.text.toString().trim()
            getPresenter()?.onCheck(name,id_table)
        }
        et_table.setOnClickListener {
            openChooseTable()
        }

        btn_delete_table.setOnClickListener {
            getPresenter()?.updateTable(null)
        }

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_manage_jointable)
            elevation = 0f

        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun showMessage(code:Int, msg:String?) {
        hideLoadingDialog()
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> {
                msg?.let {
                    toast(this,it)}
            }

        }

    }

    override fun setJoinTableName(data: Table?) {
        et_table.text = ""
        btn_delete_table.visibility = View.GONE
        data?.let {
            et_table.text = it.name_table
            btn_delete_table.visibility = View.VISIBLE
        }
    }

    override fun openChooseTable() {
        val intent = Intent(this, ChooseTableActivity::class.java)
        startActivityForResult(intent, codeOpenChooseTable)
    }

    override fun onClose(status: Int) {
        setResult(status,intent)
        finish()
    }

    override fun setTableName(name: String?) {
        name?.let {
            et_name.setText(it)
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == codeOpenChooseTable && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this, "Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this, "Data not found")
                return
            }
            val table = data.getSerializableExtra(AppConstant.DATA) as Table
            if (table.id_table == null) {
                toast(this, "Data not found")
            } else {
                getPresenter()?.updateTable(table)
            }
        }
    }


}
