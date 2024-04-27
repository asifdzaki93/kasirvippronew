package id.kasirvippro.android.feature.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseFragment
import id.kasirvippro.android.feature.dialog.MenuDialog
import id.kasirvippro.android.feature.history.main.HistoryActivity
import id.kasirvippro.android.feature.hutangpiutang.main.MainActivity
import id.kasirvippro.android.feature.kulakan.main.KulakanActivity
import id.kasirvippro.android.feature.manage.main.ManageActivity
import id.kasirvippro.android.feature.hotNews.view.ViewNewsActivity
import id.kasirvippro.android.feature.initial.InitialActivity
import id.kasirvippro.android.feature.label.main.LabelActivity
import id.kasirvippro.android.feature.manageOrder.menuOrder.MenuOrderActivity
import id.kasirvippro.android.feature.manageStock.main.ManageStockActivity
import id.kasirvippro.android.feature.order.main.OrderActivity
import id.kasirvippro.android.feature.pulsaPpob.main.PulsaPpobActivity
import id.kasirvippro.android.feature.report.main.ReportActivity
import id.kasirvippro.android.feature.sell.main.SellActivity
import id.kasirvippro.android.feature.sellReturn.main.SellReturnActivity
import id.kasirvippro.android.feature.setting.account.AccountActivity
import id.kasirvippro.android.feature.spend.main.SpendActivity
import id.kasirvippro.android.models.news.News
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.GridItemOffsetDecoration
import id.kasirvippro.android.ui.ext.htmlText
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home.view.tv_name
import kotlinx.android.synthetic.main.fragment_home.view.tv_phone
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession


class HomeFragment : BaseFragment<HomePresenter, HomeContract.View>(), HomeContract.View, MenuDialog.Listener {

    private lateinit var _view: View
    val adapter = MenuAdapter()
    var list2 = arrayListOf<News>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val codeProfile = 1001
    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun createPresenter(): HomePresenter {
        return HomePresenter(activity as Context, this)
    }

    override fun onCreateLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_home, container, false)

    }


    override fun initAction(view: View) {
        _view = view
        renderView()
        getPresenter()?.onViewCreated()
        reloadData()
    }

    private fun renderView(){

        val rotation = this.getResources().getConfiguration().orientation;
        val metrics = this.resources.displayMetrics

        val x = Math.pow((metrics.widthPixels / metrics.xdpi).toDouble(), 2.0)
        val y = Math.pow((metrics.heightPixels / metrics.ydpi).toDouble(), 2.0)
        val screenInches = Math.sqrt(x + y)
        val hasil = +Math.round(screenInches)

        if(hasil >= 7) {
            if (rotation == Configuration.ORIENTATION_PORTRAIT) {
                _view.ll_error.visibility = View.VISIBLE
                _view.ll_content.visibility = View.GONE
            } else {
                _view.ll_error.visibility = View.GONE
                _view.ll_content.visibility = View.VISIBLE
            }

        }else{

            if (rotation == Configuration.ORIENTATION_PORTRAIT) {
                _view.ll_error.visibility = View.GONE
                _view.ll_content.visibility = View.VISIBLE
            } else {
                _view.ll_error.visibility = View.VISIBLE
                _view.ll_content.visibility = View.GONE
            }

        }

        val spaceItem = resources.getDimensionPixelSize(R.dimen.standard_margin)
        _view.rv_list.addItemDecoration(GridItemOffsetDecoration(spaceItem))
        val layoutManager = GridLayoutManager(activity, 6)
        _view.rv_list.layoutManager = layoutManager
        _view.rv_list.adapter = adapter

        adapter.callback = object : MenuAdapter.ItemClickCallback{
            override fun onClick(data: News) {
                openViewNewsPage(data)
            }
        }






        _view.btn_sales.setOnClickListener {
            openSellingPage()
        }
        _view.btn_manage.setOnClickListener {
            openManagePage()
        }
        _view.btn_history.setOnClickListener {
            openHistoryPage()
        }
        _view.btn_order.setOnClickListener {
            openOrderPage()
        }
        _view.btn_credit.setOnClickListener {
            openCreditPage()
        }
        _view.btn_spending.setOnClickListener {
            openSpendingPage()
        }
        _view.btn_buying.setOnClickListener {
            openBuyingPage()
        }
        _view.btn_service.setOnClickListener {
            openAddOnPage()
        }

        _view.btn_label.setOnClickListener {
            openlabelPage()
        }

        _view.btn_manage_order.setOnClickListener {
            openManageOrderPage()
        }

        _view.btn_fast_menu.setOnClickListener {
            openFastMenu()
        }

       // _view.iv_photo.setOnClickListener {
       //     openProfilePage()
        //}

        _view.btn_initial.setOnClickListener {
            openInitialPage()
        }

        _view.btn_sales_return.setOnClickListener {
            openSalesreturnPage()
        }


        _view.btn_manage_stock.setOnClickListener {
            openManageStockPage()
        }


        _view.sw_refresh.setOnRefreshListener {
            reloadData()
        }


    }

    override fun setData(list: List<News>) {
        adapter.setItems(list)
    }



    @SuppressLint("SetTextI18n")
    override fun setStore(name:String?,address:String?,phone:String?,omset:String?,storeNumber:String?,transaksi:String?,order:String?,premium:Boolean,level:String?,photo: String?,initial:String?,shift:String?,menu_order:String?,menu_purchase:String?,menu_spending:String?,menu_transaction:String?,menu_debt:String?,menu_printlabel:String?,menu_manageorder:String?,menu_managestock:String?,menu_return:String?,menu_addon:String?,menu_othermenu:String?) {

        _view.iv_badge.visibility = View.GONE
        if(premium){
          _view.iv_badge.visibility = View.GONE
        }

        val appSession = AppSession()
        val currency = appSession.getCurrency(context!!)
        val typestore = appSession.getTypeStore(context!!)
        val decimal = appSession.getDecimal(context!!)

        if(level=="master"){
            if(shift=="1") {
                if (initial == "I") {
                    _view.btn_sales.visibility = View.VISIBLE
                    _view.btn_initial.visibility = View.GONE

                } else {
                    _view.btn_sales.visibility = View.GONE
                    _view.btn_initial.visibility = View.VISIBLE
                }
            }else{
                _view.btn_sales.visibility = View.VISIBLE
                _view.btn_initial.visibility = View.GONE
            }
            _view.btn_buying.visibility = View.VISIBLE
            _view.btn_spending.visibility = View.VISIBLE
            _view.btn_history.visibility = View.VISIBLE
            _view.btn_manage_order.visibility = View.VISIBLE
            if(typestore=="General store") {
                _view.btn_order.visibility = View.GONE
                _view.btn_manage.visibility = View.VISIBLE
            }else{
                _view.btn_order.visibility = View.VISIBLE
                _view.btn_manage.visibility = View.GONE
            }
            _view.btn_credit.visibility = View.VISIBLE
            // _view.btn_report.visibility = View.VISIBLE
            _view.btn_fast_menu.visibility = View.VISIBLE

            if(typestore=="General store" || typestore=="Culinary" ) {
                _view.btn_service.visibility = View.VISIBLE
            }else if(typestore=="Service products") {
                _view.btn_service.visibility = View.VISIBLE
            }else if(typestore=="Healthcare") {
                _view.btn_service.visibility = View.VISIBLE
            }
        }
        if(level=="admin"){
            if(menu_order=="YES") {
                if (shift == "1") {
                    if (initial == "I") {
                        _view.btn_sales.visibility = View.VISIBLE
                        _view.btn_initial.visibility = View.GONE
                    } else {
                        _view.btn_sales.visibility = View.GONE
                        _view.btn_initial.visibility = View.VISIBLE
                    }
                } else {
                    _view.btn_sales.visibility = View.VISIBLE
                    _view.btn_initial.visibility = View.GONE
                }
            }else{
                _view.btn_sales.visibility = View.GONE
                _view.btn_initial.visibility = View.GONE
            }



            if(menu_purchase=="YES") {
                _view.btn_buying.visibility = View.VISIBLE
            }else{
                _view.btn_buying.visibility = View.GONE
            }

            if(menu_spending=="YES") {
                _view.btn_spending.visibility = View.VISIBLE
            }else{
                _view.btn_spending.visibility = View.GONE
            }

            if(menu_transaction=="YES") {
                _view.btn_history.visibility = View.VISIBLE
            }else{
                _view.btn_history.visibility = View.GONE
            }

            if(menu_manageorder=="YES") {
                _view.btn_manage_order.visibility = View.VISIBLE
            }else{
                _view.btn_manage_order.visibility = View.GONE
            }

            if(menu_order=="YES") {
                if (typestore == "General store") {
                    _view.btn_order.visibility = View.GONE
                    _view.btn_manage.visibility = View.GONE
                } else {
                    _view.btn_order.visibility = View.VISIBLE
                    _view.btn_manage.visibility = View.GONE
                }
            }else{
                if (typestore == "General store") {
                    _view.btn_order.visibility = View.GONE
                    _view.btn_manage.visibility = View.GONE
                } else {
                    _view.btn_order.visibility = View.GONE
                    _view.btn_manage.visibility = View.GONE
                }
            }

            if(menu_debt=="YES") {
                _view.btn_credit.visibility = View.VISIBLE
            }else{
                _view.btn_credit.visibility = View.GONE
            }

            if(menu_printlabel=="YES") {
                _view.btn_label.visibility = View.VISIBLE
            }else{
                _view.btn_label.visibility = View.GONE
            }

            if(menu_managestock=="YES") {
                _view.btn_manage_stock.visibility = View.VISIBLE
            }else{
                _view.btn_manage_stock.visibility = View.GONE
            }

            if(menu_return=="YES") {
                _view.btn_sales_return.visibility = View.VISIBLE
            }else{
                _view.btn_sales_return.visibility = View.GONE
            }

            if(menu_othermenu=="YES") {
                _view.btn_fast_menu.visibility = View.VISIBLE
            }else{
                _view.btn_fast_menu.visibility = View.GONE
            }

            if(menu_addon=="YES") {
                if(typestore=="General store" || typestore=="Culinary" ) {
                    _view.btn_service.visibility = View.VISIBLE
                }else if(typestore=="Service products") {
                    _view.btn_service.visibility = View.VISIBLE
                }else if(typestore=="Healthcare") {
                    _view.btn_service.visibility = View.VISIBLE
                }
            }else{
                if(typestore=="General store" || typestore=="Culinary" ) {
                    _view.btn_service.visibility = View.GONE
                }else if(typestore=="Service products") {
                    _view.btn_service.visibility = View.GONE
                }else if(typestore=="Healthcare") {
                    _view.btn_service.visibility = View.GONE
                }
            }
        }
        if(level=="kasir"){
            if(menu_order=="YES") {
                if (shift == "1") {
                    if (initial == "I") {
                        _view.btn_sales.visibility = View.VISIBLE
                        _view.btn_initial.visibility = View.GONE
                    } else {
                        _view.btn_sales.visibility = View.GONE
                        _view.btn_initial.visibility = View.VISIBLE
                    }
                } else {
                    _view.btn_sales.visibility = View.VISIBLE
                    _view.btn_initial.visibility = View.GONE
                }
            }else{
                _view.btn_sales.visibility = View.GONE
                _view.btn_initial.visibility = View.GONE
            }



            if(menu_purchase=="YES") {
                _view.btn_buying.visibility = View.VISIBLE
            }else{
                _view.btn_buying.visibility = View.GONE
            }

            if(menu_spending=="YES") {
                _view.btn_spending.visibility = View.VISIBLE
            }else{
                _view.btn_spending.visibility = View.GONE
            }

            if(menu_transaction=="YES") {
                _view.btn_history.visibility = View.VISIBLE
            }else{
                _view.btn_history.visibility = View.GONE
            }

            if(menu_manageorder=="YES") {
                _view.btn_manage_order.visibility = View.VISIBLE
            }else{
                _view.btn_manage_order.visibility = View.GONE
            }

            if(menu_order=="YES") {
                if (typestore == "General store") {
                    _view.btn_order.visibility = View.GONE
                    _view.btn_manage.visibility = View.GONE
                } else {
                    _view.btn_order.visibility = View.VISIBLE
                    _view.btn_manage.visibility = View.GONE
                }
            }else{
                if (typestore == "General store") {
                    _view.btn_order.visibility = View.GONE
                    _view.btn_manage.visibility = View.GONE
                } else {
                    _view.btn_order.visibility = View.GONE
                    _view.btn_manage.visibility = View.GONE
                }
            }

            if(menu_debt=="YES") {
                _view.btn_credit.visibility = View.VISIBLE
            }else{
                _view.btn_credit.visibility = View.GONE
            }

            if(menu_printlabel=="YES") {
                _view.btn_label.visibility = View.VISIBLE
            }else{
                _view.btn_label.visibility = View.GONE
            }

            if(menu_managestock=="YES") {
                _view.btn_manage_stock.visibility = View.VISIBLE
            }else{
                _view.btn_manage_stock.visibility = View.GONE
            }

            if(menu_return=="YES") {
                _view.btn_sales_return.visibility = View.VISIBLE
            }else{
                _view.btn_sales_return.visibility = View.GONE
            }

            if(menu_othermenu=="YES") {
                _view.btn_fast_menu.visibility = View.VISIBLE
            }else{
                _view.btn_fast_menu.visibility = View.GONE
            }

            if(menu_addon=="YES") {
                if(typestore=="General store" || typestore=="Culinary" ) {
                    _view.btn_service.visibility = View.VISIBLE
                }else if(typestore=="Service products") {
                    _view.btn_service.visibility = View.VISIBLE
                }else if(typestore=="Healthcare") {
                    _view.btn_service.visibility = View.VISIBLE
                }
            }else{
                if(typestore=="General store" || typestore=="Culinary" ) {
                    _view.btn_service.visibility = View.GONE
                }else if(typestore=="Service products") {
                    _view.btn_service.visibility = View.GONE
                }else if(typestore=="Healthcare") {
                    _view.btn_service.visibility = View.GONE
                }
            }


        }
        if(level=="other"){
            if(menu_order=="YES") {
                if (shift == "1") {
                    if (initial == "I") {
                        _view.btn_sales.visibility = View.VISIBLE
                        _view.btn_initial.visibility = View.GONE
                    } else {
                        _view.btn_sales.visibility = View.GONE
                        _view.btn_initial.visibility = View.VISIBLE
                    }
                } else {
                    _view.btn_sales.visibility = View.VISIBLE
                    _view.btn_initial.visibility = View.GONE
                }
            }else{
                _view.btn_sales.visibility = View.GONE
                _view.btn_initial.visibility = View.GONE
            }



            if(menu_purchase=="YES") {
                _view.btn_buying.visibility = View.VISIBLE
            }else{
                _view.btn_buying.visibility = View.GONE
            }

            if(menu_spending=="YES") {
                _view.btn_spending.visibility = View.VISIBLE
            }else{
                _view.btn_spending.visibility = View.GONE
            }

            if(menu_transaction=="YES") {
                _view.btn_history.visibility = View.VISIBLE
            }else{
                _view.btn_history.visibility = View.GONE
            }

            if(menu_manageorder=="YES") {
                _view.btn_manage_order.visibility = View.VISIBLE
            }else{
                _view.btn_manage_order.visibility = View.GONE
            }

            if(menu_order=="YES") {
                if (typestore == "General store") {
                    _view.btn_order.visibility = View.GONE
                    _view.btn_manage.visibility = View.GONE
                } else {
                    _view.btn_order.visibility = View.VISIBLE
                    _view.btn_manage.visibility = View.GONE
                }
            }else{
                if (typestore == "General store") {
                    _view.btn_order.visibility = View.GONE
                    _view.btn_manage.visibility = View.GONE
                } else {
                    _view.btn_order.visibility = View.GONE
                    _view.btn_manage.visibility = View.GONE
                }
            }

            if(menu_debt=="YES") {
                _view.btn_credit.visibility = View.VISIBLE
            }else{
                _view.btn_credit.visibility = View.GONE
            }

            if(menu_printlabel=="YES") {
                _view.btn_label.visibility = View.VISIBLE
            }else{
                _view.btn_label.visibility = View.GONE
            }

            if(menu_managestock=="YES") {
                _view.btn_manage_stock.visibility = View.VISIBLE
            }else{
                _view.btn_manage_stock.visibility = View.GONE
            }

            if(menu_return=="YES") {
                _view.btn_sales_return.visibility = View.VISIBLE
            }else{
                _view.btn_sales_return.visibility = View.GONE
            }

            if(menu_othermenu=="YES") {
                _view.btn_fast_menu.visibility = View.VISIBLE
            }else{
                _view.btn_fast_menu.visibility = View.GONE
            }

            if(menu_addon=="YES") {
                if(typestore=="General store" || typestore=="Culinary" ) {
                    _view.btn_service.visibility = View.VISIBLE
                }else if(typestore=="Service products") {
                    _view.btn_service.visibility = View.VISIBLE
                }else if(typestore=="Healthcare") {
                    _view.btn_service.visibility = View.VISIBLE
                }
            }else{
                if(typestore=="General store" || typestore=="Culinary" ) {
                    _view.btn_service.visibility = View.GONE
                }else if(typestore=="Service products") {
                    _view.btn_service.visibility = View.GONE
                }else if(typestore=="Healthcare") {
                    _view.btn_service.visibility = View.GONE
                }
            }
        }
        if(level=="manager"){
            if(menu_order=="YES") {
                if (shift == "1") {
                    if (initial == "I") {
                        _view.btn_sales.visibility = View.VISIBLE
                        _view.btn_initial.visibility = View.GONE
                    } else {
                        _view.btn_sales.visibility = View.GONE
                        _view.btn_initial.visibility = View.VISIBLE
                    }
                } else {
                    _view.btn_sales.visibility = View.VISIBLE
                    _view.btn_initial.visibility = View.GONE
                }
            }else{
                _view.btn_sales.visibility = View.GONE
                _view.btn_initial.visibility = View.GONE
            }



            if(menu_purchase=="YES") {
                _view.btn_buying.visibility = View.VISIBLE
            }else{
                _view.btn_buying.visibility = View.GONE
            }

            if(menu_spending=="YES") {
                _view.btn_spending.visibility = View.VISIBLE
            }else{
                _view.btn_spending.visibility = View.GONE
            }

            if(menu_transaction=="YES") {
                _view.btn_history.visibility = View.VISIBLE
            }else{
                _view.btn_history.visibility = View.GONE
            }

            if(menu_manageorder=="YES") {
                _view.btn_manage_order.visibility = View.VISIBLE
            }else{
                _view.btn_manage_order.visibility = View.GONE
            }

            if(menu_order=="YES") {
                if (typestore == "General store") {
                    _view.btn_order.visibility = View.GONE
                    _view.btn_manage.visibility = View.GONE
                } else {
                    _view.btn_order.visibility = View.VISIBLE
                    _view.btn_manage.visibility = View.GONE
                }
            }else{
                if (typestore == "General store") {
                    _view.btn_order.visibility = View.GONE
                    _view.btn_manage.visibility = View.GONE
                } else {
                    _view.btn_order.visibility = View.GONE
                    _view.btn_manage.visibility = View.GONE
                }
            }

            if(menu_debt=="YES") {
                _view.btn_credit.visibility = View.VISIBLE
            }else{
                _view.btn_credit.visibility = View.GONE
            }

            if(menu_printlabel=="YES") {
                _view.btn_label.visibility = View.VISIBLE
            }else{
                _view.btn_label.visibility = View.GONE
            }

            if(menu_managestock=="YES") {
                _view.btn_manage_stock.visibility = View.VISIBLE
            }else{
                _view.btn_manage_stock.visibility = View.GONE
            }

            if(menu_return=="YES") {
                _view.btn_sales_return.visibility = View.VISIBLE
            }else{
                _view.btn_sales_return.visibility = View.GONE
            }

            if(menu_othermenu=="YES") {
                _view.btn_fast_menu.visibility = View.VISIBLE
            }else{
                _view.btn_fast_menu.visibility = View.GONE
            }

            if(menu_addon=="YES") {
                if(typestore=="General store" || typestore=="Culinary" ) {
                    _view.btn_service.visibility = View.VISIBLE
                }else if(typestore=="Service products") {
                    _view.btn_service.visibility = View.VISIBLE
                }else if(typestore=="Healthcare") {
                    _view.btn_service.visibility = View.VISIBLE
                }
            }else{
                if(typestore=="General store" || typestore=="Culinary" ) {
                    _view.btn_service.visibility = View.GONE
                }else if(typestore=="Service products") {
                    _view.btn_service.visibility = View.GONE
                }else if(typestore=="Healthcare") {
                    _view.btn_service.visibility = View.GONE
                }
            }
        }



        _view.tv_omset.text = currency + " 0"
        omset?.let {
            if(decimal=="No Decimal") {
                _view.tv_omset.text = currency + Helper.convertToCurrency(it)
            }else{
                _view.tv_omset.text = currency + it
            }
        }

        _view.tv_transaksi.text = currency + "0"
        transaksi?.let {
            if(decimal=="No Decimal") {
                _view.tv_transaksi.text = currency + Helper.convertToCurrency(it)
            }else{
                _view.tv_transaksi.text = currency + it
            }
        }


        _view.tv_order.text = "0"
        order?.let {
            _view.tv_order.text = Helper.convertToCurrency(it)
        }



        _view.tv_name.text = ""

        name?.let {
            _view.tv_name.text = it
        }

        _view.tv_address.text = ""

        address?.let {
            _view.tv_address.text = it
        }

        _view.tv_phone.text = ""

        phone?.let {
            _view.tv_phone.text = it
            storeNumber?.let {number ->
                _view.tv_phone.htmlText("$it &#8226; $number")
            }
        }

        _view.sw_refresh.isRefreshing = false

        //  Log.d("tes ajah",phone)

    }

    override fun onDetach() {
        super.onDetach()
        getPresenter()?.onDestroy()
    }

    override fun reloadData() {
        _view.sw_refresh.isRefreshing = true
        setStore("","","","0",null,"0","0",false,"","","","","","","","","","","","","","","")
        getPresenter()?.loadStore()

    }

    override fun openProfilePage() {
        startActivityForResult(Intent(activity, AccountActivity::class.java),codeProfile)
    }


    override fun showErrorMessage(code: Int, msg: String) {
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> {
                setStore("","","","0",null,"0","0",false,"","","","","","","","","","","","","","","")
            }
        }

    }

    override fun openManagePage() {
        startActivity(Intent(activity,ManageActivity::class.java))
    }

    override fun openOrderPage() {
        OrderActivity.data = ""
        startActivity(Intent(activity,OrderActivity::class.java))
    }

    override fun openSellingPage() {
        SellActivity.data = ""
        startActivity(Intent(activity,SellActivity::class.java))
    }

    override fun openBuyingPage() {
        startActivity(Intent(activity,KulakanActivity::class.java))
    }

    override fun openlabelPage() {
        LabelActivity.data = ""
        startActivity(Intent(activity,LabelActivity::class.java))
    }

    override fun openSpendingPage() {
        startActivity(Intent(activity,SpendActivity::class.java))
    }

    override fun openHistoryPage() {
        startActivity(Intent(activity,HistoryActivity::class.java))
    }

    override fun openReportPage() {
        startActivity(Intent(activity,ReportActivity::class.java))
    }

    override fun openCreditPage() {
        startActivity(Intent(activity,MainActivity::class.java))
    }

    override fun openAddOnPage() {
        startActivity(Intent(activity, PulsaPpobActivity::class.java))
    }

    override fun openManageOrderPage() {
        startActivity(Intent(activity, MenuOrderActivity::class.java))
    }

    override fun openInitialPage() {
        startActivity(Intent(activity, InitialActivity::class.java))
    }

    override fun openSalesreturnPage() {
        startActivity(Intent(activity, SellReturnActivity::class.java))
    }

    override fun openManageStockPage() {
        startActivity(Intent(activity, ManageStockActivity::class.java))
    }

    override fun openFastMenu() {
        val dialog = MenuDialog.newInstance()
        dialog.show((activity as FragmentActivity).supportFragmentManager, null)
    }

    override fun openViewNewsPage(data: News) {
        val newintent = Intent(activity, ViewNewsActivity::class.java)
        newintent.putExtra(AppConstant.DATA,data)
        startActivity(newintent)
    }


}
