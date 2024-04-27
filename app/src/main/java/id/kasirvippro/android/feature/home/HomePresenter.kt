package id.kasirvippro.android.feature.home

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.category.CategoryRestModel
import id.kasirvippro.android.models.news.News
import id.kasirvippro.android.models.news.NewsRestModel
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel
import id.kasirvippro.android.models.transaction.TransactionRestModel

class HomePresenter(val context: Context, val view: HomeContract.View) : BasePresenter<HomeContract.View>(),
    HomeContract.Presenter, HomeContract.InteractorOutput {

    private var interactor: HomeInteractor = HomeInteractor(this)
    private var newsrestModel = NewsRestModel(context)
    private var restModel = StoreRestModel(context)
    private var linkrestModel = TransactionRestModel(context)
    private var categoryrestModel = CategoryRestModel(context)
    private var premium = false

    override fun onViewCreated() {
        loadNews()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    private fun menu(){

        val menu1 = News()
        menu1.id_news = 1
        menu1.title = "Management"
        menu1.img = "R.drawable.ic_menu_manajemen"

        val menu2 = News()
        menu2.id_news = 2
        menu2.title = "Transaction History"
        menu2.img = "R.drawable.clock"


        val list = ArrayList<News>()
                list.add(menu2)
                list.add(menu1)
        view.setData(list)
    }

    override fun loadNews() {
        interactor.callGetNewsAPI(context,newsrestModel)
    }

    override fun loadStore() {
        interactor.callGetStoreAPI(context,restModel)
    }

    override fun loadLink() {
        interactor.callGetlinkAPI(context,linkrestModel)
    }

    override fun loadCategory() {
        interactor.callGetCategoriesAPI(context,categoryrestModel)
    }


    override fun onSuccessGetNews(list: List<News>) {
        view.setData(list)
    }

    override fun onSuccessGetStore(list: List<Store>) {
        if(list.isEmpty()){
            onFailedAPI(999,"Store not found ")
            return
        }

        val user = list[0]
        premium = "premium" == user.type
        view.setStore(user.name_store,user.address,user.nohp,user.omset,user.number_store,user.transaksi,user.order,premium,user.level,user.photo,user.initial,user.shift,user.menu_order,user.menu_purchase,user.menu_spending,user.menu_transaction,user.menu_debt,user.menu_printlabel,user.menu_manageorder,user.menu_managestock,user.menu_return,user.menu_addon,user.menu_othermenu)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}