package id.kasirvippro.android.feature.manage.role.edit

import android.app.AlertDialog
import android.os.Bundle
import android.view.MenuItem
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.rest.entity.RestException
import kotlinx.android.synthetic.main.activity_edit_role.*
import kotlinx.android.synthetic.main.activity_edit_role.et_name

class EditRoleActivity : BaseActivity<EditRolePresenter, EditRoleContract.View>(), EditRoleContract.View {


    override fun createPresenter(): EditRolePresenter {
        return EditRolePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_role
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){



    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun setInfo(role: Role) {
       et_name.text = ""
        role.name_role?.let {
            if (it ==  "kasir"){
                val jobs = "Cashier"
                et_name.text = jobs
            }else if (it ==  "other"){
                val jobs = "Accounting"
                et_name.text = jobs
            }else{
                val jobs = it
                et_name.text = jobs
            }
        }


        role.menu_order?.let {
            if("YES" == it){
                rg_menu_order.check(R.id.rb_menu_order_yes)
            }
            else{
                rg_menu_order.check(R.id.rb_menu_order_no)
            }
        }

        role.menu_purchase?.let {
            if("YES" == it){
                rg_menu_purchase.check(R.id.rb_menu_purchase_yes)
            }
            else{
                rg_menu_purchase.check(R.id.rb_menu_purchase_no)
            }
        }

        role.menu_spending?.let {
            if("YES" == it){
                rg_menu_spending.check(R.id.rb_menu_spending_yes)
            }
            else{
                rg_menu_spending.check(R.id.rb_menu_spending_no)
            }
        }

        role.menu_transaction?.let {
            if ("YES" == it) {
                rg_menu_transaction.check(R.id.rb_menu_transaction_yes)
            } else {
                rg_menu_transaction.check(R.id.rb_menu_transaction_no)
            }
        }

        role.menu_manageorder?.let {
            if("YES" == it){
                rg_menu_manageorder.check(R.id.rb_menu_manageorder_yes)
            }
            else{
                rg_menu_manageorder.check(R.id.rb_menu_manageorder_no)
            }
        }

        role.menu_debt?.let {
            if("YES" == it){
                rg_menu_debt.check(R.id.rb_menu_debt_yes)
            }
            else{
                rg_menu_debt.check(R.id.rb_menu_debt_no)
            }
        }

        role.menu_printlabel?.let {
            if("YES" == it){
                rg_menu_printlabel.check(R.id.rb_menu_printlabel_yes)
            }
            else{
                rg_menu_printlabel.check(R.id.rb_menu_printlabel_no)
            }
        }

        role.menu_managestock?.let {
            if("YES" == it){
                rg_menu_managestock.check(R.id.rb_menu_managestock_yes)
            }
            else{
                rg_menu_managestock.check(R.id.rb_menu_managestock_no)
            }
        }

        role.menu_return?.let {
            if("YES" == it){
                rg_menu_return.check(R.id.rb_menu_return_yes)
            }
            else{
                rg_menu_return.check(R.id.rb_menu_return_no)
            }
        }

        role.menu_addon?.let {
            if("YES" == it){
                rg_menu_addon.check(R.id.rb_menu_addon_yes)
            }
            else{
                rg_menu_addon.check(R.id.rb_menu_addon_no)
            }
        }

        role.menu_othermenu?.let {
            if("YES" == it){
                rg_menu_othermenu.check(R.id.rb_menu_othermenu_yes)
            }
            else{
                rg_menu_othermenu.check(R.id.rb_menu_othermenu_no)
            }
        }

        role.add_product?.let {
            if("YES" == it){
                rg_add_product.check(R.id.rb_add_product_yes)
            }
            else{
                rg_add_product.check(R.id.rb_add_product_no)
            }
        }

        role.add_category?.let {
            if("YES" == it){
                rg_add_category.check(R.id.rb_add_category_yes)
            }
            else{
                rg_add_category.check(R.id.rb_add_category_no)
            }
        }

        role.add_supplier?.let {
            if("YES" == it){
                rg_add_supplier.check(R.id.rb_add_supplier_yes)
            }
            else{
                rg_add_supplier.check(R.id.rb_add_supplier_no)
            }
        }


        role.add_customer?.let {
            if("YES" == it){
                rg_add_customer.check(R.id.rb_add_customer_yes)
            }
            else{
                rg_add_customer.check(R.id.rb_add_customer_no)
            }
        }


        role.add_discount?.let {
            if("YES" == it){
                rg_add_discount.check(R.id.rb_add_discount_yes)
            }
            else{
                rg_add_discount.check(R.id.rb_add_discount_no)
            }
        }


        role.report_product?.let {
            if("YES" == it){
                rg_report_product.check(R.id.rb_report_product_yes)
            }
            else{
                rg_report_product.check(R.id.rb_report_product_no)
            }
        }


        role.report_sumary?.let {
            if("YES" == it){
                rg_report_sumary.check(R.id.rb_report_sumary_yes)
            }
            else{
                rg_report_sumary.check(R.id.rb_report_sumary_no)
            }
        }


        role.report_daily?.let {
            if("YES" == it){
                rg_report_daily.check(R.id.rb_report_daily_yes)
            }
            else{
                rg_report_daily.check(R.id.rb_report_daily_no)
            }
        }


        role.report_accounting?.let {
            if("YES" == it){
                rg_report_accounting.check(R.id.rb_report_accounting_yes)
            }
            else{
                rg_report_accounting.check(R.id.rb_report_accounting_no)
            }
        }






    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun showMessage(code: Int, msg: String?) {
        hideLoadingDialog()
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> {
                msg?.let {
                    //toast(this,it)
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Notification")
                    builder.setMessage(it)
                    builder.setCancelable(false)
                    builder.setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    builder.show()


                }
            }

        }

    }

    override fun loadData() {
        getPresenter()?.loadData()
    }

    override fun getIdRole(): String? {
        val idrole = getIntent().getStringExtra("id_role")
        return idrole
    }


    override fun setProfile(name_role: String?, menu_order: String?, menu_purchase: String?, menu_spending: String?, menu_transaction: String?, menu_debt: String?, menu_printlabel: String?, menu_manageorder: String?, menu_managestock: String?, menu_return: String?, menu_addon: String?, menu_othermenu: String?, add_product: String?, add_category: String?, add_supplier: String?, add_customer: String?, add_discount: String?, report_product: String?, report_sumary: String?, report_daily: String?, report_accounting: String?) {

        et_name.text = ""
        name_role?.let {
            if (it ==  "kasir"){
                val jobs = "Cashier"
                et_name.text = jobs
            }else if (it ==  "other"){
                val jobs = "Accounting"
                et_name.text = jobs
            }else{
                val jobs = it
                et_name.text = jobs
            }
        }

        menu_order?.let {
            if("YES" == it){
                rg_menu_order.check(R.id.rb_menu_order_yes)
            }
            else{
                rg_menu_order.check(R.id.rb_menu_order_no)
            }
        }

        menu_purchase?.let {
            if("YES" == it){
                rg_menu_purchase.check(R.id.rb_menu_purchase_yes)
            }
            else{
                rg_menu_purchase.check(R.id.rb_menu_purchase_no)
            }
        }

        menu_spending?.let {
            if("YES" == it){
                rg_menu_spending.check(R.id.rb_menu_spending_yes)
            }
            else{
                rg_menu_spending.check(R.id.rb_menu_spending_no)
            }
        }

        menu_transaction?.let {
            if ("YES" == it) {
                rg_menu_transaction.check(R.id.rb_menu_transaction_yes)
            } else {
                rg_menu_transaction.check(R.id.rb_menu_transaction_no)
            }
        }

        menu_manageorder?.let {
            if("YES" == it){
                rg_menu_manageorder.check(R.id.rb_menu_manageorder_yes)
            }
            else{
                rg_menu_manageorder.check(R.id.rb_menu_manageorder_no)
            }
        }

        menu_debt?.let {
            if("YES" == it){
                rg_menu_debt.check(R.id.rb_menu_debt_yes)
            }
            else{
                rg_menu_debt.check(R.id.rb_menu_debt_no)
            }
        }

        menu_printlabel?.let {
            if("YES" == it){
                rg_menu_printlabel.check(R.id.rb_menu_printlabel_yes)
            }
            else{
                rg_menu_printlabel.check(R.id.rb_menu_printlabel_no)
            }
        }

        menu_managestock?.let {
            if("YES" == it){
                rg_menu_managestock.check(R.id.rb_menu_managestock_yes)
            }
            else{
                rg_menu_managestock.check(R.id.rb_menu_managestock_no)
            }
        }

        menu_return?.let {
            if("YES" == it){
                rg_menu_return.check(R.id.rb_menu_return_yes)
            }
            else{
                rg_menu_return.check(R.id.rb_menu_return_no)
            }
        }

        menu_addon?.let {
            if("YES" == it){
                rg_menu_addon.check(R.id.rb_menu_addon_yes)
            }
            else{
                rg_menu_addon.check(R.id.rb_menu_addon_no)
            }
        }

        menu_othermenu?.let {
            if("YES" == it){
                rg_menu_othermenu.check(R.id.rb_menu_othermenu_yes)
            }
            else{
                rg_menu_othermenu.check(R.id.rb_menu_othermenu_no)
            }
        }

        add_product?.let {
            if("YES" == it){
                rg_add_product.check(R.id.rb_add_product_yes)
            }
            else{
                rg_add_product.check(R.id.rb_add_product_no)
            }
        }

        add_category?.let {
            if("YES" == it){
                rg_add_category.check(R.id.rb_add_category_yes)
            }
            else{
                rg_add_category.check(R.id.rb_add_category_no)
            }
        }

        add_supplier?.let {
            if("YES" == it){
                rg_add_supplier.check(R.id.rb_add_supplier_yes)
            }
            else{
                rg_add_supplier.check(R.id.rb_add_supplier_no)
            }
        }


        add_customer?.let {
            if("YES" == it){
                rg_add_customer.check(R.id.rb_add_customer_yes)
            }
            else{
                rg_add_customer.check(R.id.rb_add_customer_no)
            }
        }


        add_discount?.let {
            if("YES" == it){
                rg_add_discount.check(R.id.rb_add_discount_yes)
            }
            else{
                rg_add_discount.check(R.id.rb_add_discount_no)
            }
        }


        report_product?.let {
            if("YES" == it){
                rg_report_product.check(R.id.rb_report_product_yes)
            }
            else{
                rg_report_product.check(R.id.rb_report_product_no)
            }
        }


        report_sumary?.let {
            if("YES" == it){
                rg_report_sumary.check(R.id.rb_report_sumary_yes)
            }
            else{
                rg_report_sumary.check(R.id.rb_report_sumary_no)
            }
        }


        report_daily?.let {
            if("YES" == it){
                rg_report_daily.check(R.id.rb_report_daily_yes)
            }
            else{
                rg_report_daily.check(R.id.rb_report_daily_no)
            }
        }


        report_accounting?.let {
            if("YES" == it){
                rg_report_accounting.check(R.id.rb_report_accounting_yes)
            }
            else{
                rg_report_accounting.check(R.id.rb_report_accounting_no)
            }
        }


        val idrole = getIntent().getStringExtra("id_role")
        rg_menu_order.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_menu_order_yes -> getPresenter()?.setActive("YES", idrole,"menu_order")
                R.id.rb_menu_order_no -> getPresenter()?.setActive("NO", idrole,"menu_order")
            }
        }

        rg_menu_purchase.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_menu_purchase_yes -> getPresenter()?.setActive("YES", idrole,"menu_purchase")
                R.id.rb_menu_purchase_no -> getPresenter()?.setActive("NO", idrole,"menu_purchase")
            }
        }

        rg_menu_spending.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_menu_spending_yes -> getPresenter()?.setActive("YES", idrole,"menu_spending")
                R.id.rb_menu_spending_no -> getPresenter()?.setActive("NO", idrole,"menu_spending")
            }
        }

        rg_menu_transaction.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_menu_transaction_yes -> getPresenter()?.setActive("YES", idrole,"menu_transaction")
                R.id.rb_menu_transaction_no -> getPresenter()?.setActive("NO", idrole,"menu_transaction")
            }
        }

        rg_menu_manageorder.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_menu_manageorder_yes -> getPresenter()?.setActive("YES", idrole,"menu_manageorder")
                R.id.rb_menu_manageorder_no -> getPresenter()?.setActive("NO", idrole,"menu_manageorder")
            }
        }

        rg_menu_debt.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_menu_debt_yes -> getPresenter()?.setActive("YES", idrole,"menu_debt")
                R.id.rb_menu_debt_no -> getPresenter()?.setActive("NO", idrole,"menu_debt")
            }
        }

        rg_menu_printlabel.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_menu_printlabel_yes -> getPresenter()?.setActive("YES", idrole,"menu_printlabel")
                R.id.rb_menu_printlabel_no -> getPresenter()?.setActive("NO", idrole,"menu_printlabel")
            }
        }

        rg_menu_managestock.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_menu_managestock_yes -> getPresenter()?.setActive("YES", idrole,"menu_managestock")
                R.id.rb_menu_managestock_no -> getPresenter()?.setActive("NO", idrole,"menu_managestock")
            }
        }

        rg_menu_return.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_menu_return_yes -> getPresenter()?.setActive("YES", idrole,"menu_return")
                R.id.rb_menu_return_no -> getPresenter()?.setActive("NO", idrole,"menu_return")
            }
        }

        rg_menu_addon.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_menu_addon_yes -> getPresenter()?.setActive("YES", idrole,"menu_addon")
                R.id.rb_menu_addon_no -> getPresenter()?.setActive("NO", idrole,"menu_addon")
            }
        }

        rg_menu_othermenu.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_menu_othermenu_yes -> getPresenter()?.setActive("YES", idrole,"menu_othermenu")
                R.id.rb_menu_othermenu_no -> getPresenter()?.setActive("NO", idrole,"menu_othermenu")
            }
        }

        rg_add_product.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_add_product_yes -> getPresenter()?.setActive("YES", idrole,"add_product")
                R.id.rb_add_product_no -> getPresenter()?.setActive("NO", idrole,"add_product")
            }
        }

        rg_add_category.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_add_category_yes -> getPresenter()?.setActive("YES", idrole,"add_category")
                R.id.rb_add_category_no -> getPresenter()?.setActive("NO", idrole,"add_category")
            }
        }


        rg_add_supplier.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_add_supplier_yes -> getPresenter()?.setActive("YES", idrole,"add_supplier")
                R.id.rb_add_supplier_no -> getPresenter()?.setActive("NO", idrole,"add_supplier")
            }
        }


        rg_add_customer.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_add_customer_yes -> getPresenter()?.setActive("YES", idrole,"add_customer")
                R.id.rb_add_customer_no -> getPresenter()?.setActive("NO", idrole,"add_customer")
            }
        }


        rg_add_discount.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_add_discount_yes -> getPresenter()?.setActive("YES", idrole,"add_discount")
                R.id.rb_add_discount_no -> getPresenter()?.setActive("NO", idrole,"add_discount")
            }
        }


        rg_report_product.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_report_product_yes -> getPresenter()?.setActive("YES", idrole,"report_product")
                R.id.rb_report_product_no -> getPresenter()?.setActive("NO", idrole,"report_product")
            }
        }


        rg_report_sumary.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_report_sumary_yes -> getPresenter()?.setActive("YES", idrole,"report_sumary")
                R.id.rb_report_sumary_no -> getPresenter()?.setActive("NO", idrole,"report_sumary")
            }
        }


        rg_report_daily.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_report_daily_yes -> getPresenter()?.setActive("YES", idrole,"report_daily")
                R.id.rb_report_daily_no -> getPresenter()?.setActive("NO", idrole,"report_daily")
            }
        }


        rg_report_accounting.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_report_accounting_yes -> getPresenter()?.setActive("YES", idrole,"report_accounting")
                R.id.rb_report_accounting_no -> getPresenter()?.setActive("NO", idrole,"report_accounting")
            }
        }


    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_edit_role)
            elevation = 0f
           // setBackgroundDrawable(ContextCompat.getDrawable(this@EditRoleActivity,R.drawable.gradient_blue))
        }

    }
}