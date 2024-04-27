package id.kasirvippro.android.feature.home

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.category.CategoryRestModel
import id.kasirvippro.android.models.news.News
import id.kasirvippro.android.models.news.NewsRestModel
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel
import id.kasirvippro.android.models.transaction.TransactionRestModel

interface HomeContract {

    interface View : BaseViewImpl {
        fun setData(list:List<News>)
        fun setStore(name:String?,address:String?,phone:String?,omset:String?,storeNumber:String?,transaksi:String?,order:String?,premium:Boolean,level:String?,photo: String?,initial: String?,shift: String?,menu_order: String?,menu_purchase: String?,menu_spending: String?,menu_transaction: String?,menu_debt: String?,menu_printlabel: String?,menu_manageorder: String?,menu_managestock: String?,menu_return: String?,menu_addon: String?,menu_othermenu: String?)
        fun reloadData()
        fun showErrorMessage(code: Int, msg: String)
        fun openSellingPage()
        fun openBuyingPage()
        fun openlabelPage()
        fun openSpendingPage()
        fun openManagePage()
        fun openHistoryPage()
        fun openReportPage()
        fun openCreditPage()
        fun openManageOrderPage()
        fun openFastMenu()
        fun openInitialPage()
        fun openSalesreturnPage()
        fun openViewNewsPage(data: News)
        fun openProfilePage()
        fun openOrderPage()
        fun openManageStockPage()
        fun openAddOnPage()
    }


    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadNews()
        fun loadStore()
        fun loadLink()
        fun loadCategory()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserLevel(context: Context):String?
        fun getUserPackage(context: Context):String?
        fun callGetNewsAPI(context: Context, restModel: NewsRestModel)
        fun callGetStoreAPI(context: Context, restModel: StoreRestModel)
        fun callGetlinkAPI(context: Context, restModel: TransactionRestModel)
        fun callGetCategoriesAPI(context: Context, restModel: CategoryRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetNews(list:List<News>)
        fun onSuccessGetStore(list:List<Store>)
        fun onFailedAPI(code:Int,msg:String)

    }


}