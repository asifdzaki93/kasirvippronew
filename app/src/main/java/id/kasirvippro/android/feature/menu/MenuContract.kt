package id.kasirvippro.android.feature.menu

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.category.CategoryRestModel
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.models.discount.DiscountRestModel
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.models.store.StoreRestModel
import id.kasirvippro.android.models.supplier.SupplierRestModel
import id.kasirvippro.android.models.table.TableRestModel
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.models.user.Login
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel

interface MenuContract {

    interface View : BaseViewImpl {
        fun setProfile(name: String?, phone: String?,photo: String?)
        fun openWebviewPage(title:String,url:String)
        fun openPasswordPage()
        fun openProfilePage()
        fun openSlipPage()
        fun syncDatabase()
        fun showErrorMessage(code: Int, msg: String)
        fun loadProfile()
        fun onMasterPage()
        fun onAdminPage()
        fun onSalesPage()
        fun onPremium(isPremium:Boolean)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onLogout()
        fun loadProfile()
        fun onDestroy()
        fun getPremiumUrl():String
        fun getToken():String
        fun loadStore()
        fun loadLink()
        fun loadCategory()
        fun loadDiscount()
        fun loadTable()
        fun loadProduct()
        fun loadCustomer()
        fun loadSupplier()
    }

    interface Interactor : BaseInteractorImpl {
        fun onRestartDisposable()
        fun onDestroy()
        fun onLogout()
        fun callGetProfileAPI(context: Context, restModel: UserRestModel)
        fun getSessionUser(context: Context):User?
        fun getSessionLogin(context: Context):Login?
        fun saveUser(user: User)
        fun getToken(context: Context):String
        fun callGetStoreAPI(context: Context, restModel: StoreRestModel)
        fun callGetlinkAPI(context: Context, restModel: TransactionRestModel)
        fun callGetCategoriesAPI(context: Context, restModel: CategoryRestModel)
        fun callGetDiscountAPI(context: Context, restModel: DiscountRestModel)
        fun callGetTableAPI(context: Context, restModel: TableRestModel)
        fun callGetProductsAPI(context: Context, restModel: ProductRestModel, page: Int?)
        fun callGetCustomersAPI(context: Context, restModel: CustomerRestModel)
        fun callGetSupplierAPI(context: Context, restModel: SupplierRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onLogoutSuccess()
        fun onSuccessGetProfile(list:List<User>)
        fun onFailedAPI(code:Int,msg:String)
    }


}