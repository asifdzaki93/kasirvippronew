package id.kasirvippro.android.feature.menu

import android.content.Context
import android.util.Log
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.category.CategoryRestModel
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.models.discount.DiscountRestModel
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.models.store.StoreRestModel
import id.kasirvippro.android.models.supplier.SupplierRestModel
import id.kasirvippro.android.models.table.TableRestModel
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.utils.AppConstant

class MenuPresenter(val context: Context, val view: MenuContract.View) : BasePresenter<MenuContract.View>(),
    MenuContract.Presenter, MenuContract.InteractorOutput {

    private var interactor = MenuInteractor(this)
    private var userRestModel = UserRestModel(context)
    private var premium = false

    private var restModel = StoreRestModel(context)
    private var linkrestModel = TransactionRestModel(context)
    private var categoryrestModel = CategoryRestModel(context)
    private var discountrestModel = DiscountRestModel(context)
    private var tablerestModel = TableRestModel(context)
    private var productrestModel = ProductRestModel(context)
    private var customerrestModel = CustomerRestModel(context)
    private var supplierrestModel = SupplierRestModel(context)

    override fun onViewCreated() {
        val login = interactor.getSessionLogin(context)
        premium = "1" == login?.packages
        when(login?.level){
            "master" -> {
                view.onMasterPage()
                view.onPremium(premium)
            }

            "admin" -> view.onAdminPage()
            "kasir" -> view.onSalesPage()
        }

        loadProfile()
    }

    override fun loadProfile() {
        interactor.callGetProfileAPI(context,userRestModel)
    }

    override fun onLogout() {
        interactor.onLogout()
    }

    override fun onLogoutSuccess() {
        view.restartLoginActivity()
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

    override fun loadDiscount() {
        interactor.callGetDiscountAPI(context,discountrestModel)
    }
    override fun loadTable() {
        interactor.callGetTableAPI(context,tablerestModel)
    }

    override fun loadProduct() {
        interactor.callGetProductsAPI(context,productrestModel, 1)
    }

    override fun loadCustomer() {
        interactor.callGetCustomersAPI(context,customerrestModel)
    }

    override fun loadSupplier() {
        interactor.callGetSupplierAPI(context,supplierrestModel)
    }



    override fun onDestroy(){
        interactor.onDestroy()
    }


    override fun onSuccessGetProfile(list: List<User>) {
        if(list.isEmpty()){
            onFailedAPI(999,"Akun tidak ditemukan")
            return
        }

        val user = list[0]
        interactor.saveUser(user)
        view.setProfile(user.full_name,user.phone_number,user.img)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

    override fun getPremiumUrl(): String {
        val key = interactor.getToken(context)
        val url = AppConstant.URL.PREMIUM+key
        Log.d("getPremiumUrl",url)
        return url
    }

    override fun getToken(): String {
        return  interactor.getToken(context)
    }

}