package id.kasirvippro.android.feature.menu

import android.content.Context
import com.google.gson.Gson
import id.kasirvippro.android.models.category.Category
import id.kasirvippro.android.models.category.CategoryRestModel
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.discount.DiscountRestModel
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.supplier.SupplierRestModel
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.table.TableRestModel
import id.kasirvippro.android.models.transaction.NonTunai
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.models.user.Login
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.*
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class MenuInteractor(var output: MenuContract.InteractorOutput?) : MenuContract.Interactor {

    private var appSession = AppSession()
    private var disposable = CompositeDisposable()

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }
    override fun onLogout() {
        appSession.clearSession()
        output?.onLogoutSuccess()
    }


    override fun getSessionUser(context: Context): User? {
        val json = appSession.getSharedPreferenceString(context,AppConstant.USER)
        if(json.isNullOrEmpty()){
            return null
        }

        return Gson().fromJson(json,User::class.java) ?: null
    }

    override fun getSessionLogin(context: Context): Login? {
        val json = appSession.getSharedPreferenceString(context,AppConstant.LOGIN)
        if(json.isNullOrEmpty()){
            return null
        }

        return Gson().fromJson(json, Login::class.java) ?: null
    }



    override fun onDestroy() {
        disposable.clear()
    }

    override fun getToken(context: Context): String {
        val key = appSession.getToken(context)
        return key!!
    }

    override fun callGetProfileAPI(context: Context, restModel: UserRestModel) {
        disposable.add(restModel.getProfile(getToken(context)).subscribeWith(object : DisposableObserver<List<User>>() {

            override fun onNext(@NonNull response: List<User>) {
                output?.onSuccessGetProfile(response)

                val key = appSession.getToken(context)
                val dataManager = DataManager (context)
                dataManager.update(response[0].full_name!!)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                val errorMessage: String
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                }
                else{
                    errorMessage = e.message.toString()
                }
                output?.onFailedAPI(errorCode,errorMessage)
            }

            override fun onComplete() {
            }
        }))
    }

    override fun saveUser(user: User) {
        appSession.setSharedPreferenceString(AppConstant.USER,user.json())
    }

    override fun callGetStoreAPI(context: Context, restModel: StoreRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getStore(key!!).subscribeWith(object : DisposableObserver<List<Store>>() {

            override fun onNext(@NonNull response: List<Store>) {
                val dataManager = DataManager (context)
                dataManager.clearStoreAll()
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val store = StoreSQL(
                            inc.toString(),
                            item.id_store.toString(),
                            item.type.toString(),
                            item.name_store.toString(),
                            item.nohp.toString(),
                            item.address.toString(),
                            item.email.toString(),
                            item.omset.toString(),
                            item.transaksi.toString(),
                            item.order.toString(),
                            item.tax.toString(),
                            item.service_charge.toString(),
                            item.number_store.toString(),
                            item.level.toString(),
                            item.footer.toString(),
                            item.photo.toString(),
                        )
                        inc++
                        var result = dataManager.addStore(store)
                    }
                }
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                val errorMessage: String
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                }
                else{
                    errorMessage = e.message.toString()
                }
                output?.onFailedAPI(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }

    override fun callGetCategoriesAPI(context: Context, restModel: CategoryRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getCategories(key!!).subscribeWith(object : DisposableObserver<List<Category>>() {

            override fun onNext(@NonNull response: List<Category>) {
                val dataManager = DataManager (context)
                dataManager.clearCategoryAll()
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val category = CategorySQL(
                            inc.toString(),
                            key.toString(),
                            item.id_category.toString(),
                            item.name_category.toString(),
                        )
                        inc++
                        var result = dataManager.addCategory(category)
                    }
                }
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                var errorMessage = "There is an error"
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                }
                else{
                    errorMessage = e.message.toString()
                }
                output?.onFailedAPI(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }

    override fun callGetlinkAPI(context: Context, restModel: TransactionRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getNonTunai(key!!).subscribeWith(object : DisposableObserver<List<NonTunai>>() {

            override fun onNext(@NonNull response: List<NonTunai>) {
                val dataManager = DataManager (context)
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val link = LinkSQL(
                            inc.toString(),
                            key.toString(),
                            item.id_link.toString(),
                            item.name_link.toString(),
                            item.img.toString(),
                        )
                        inc++
                        var result = dataManager.addLink(link)
                    }
                }
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                val errorMessage: String
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                }
                else{
                    errorMessage = e.message.toString()
                }
                output?.onFailedAPI(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }

    override fun callGetDiscountAPI(context: Context, restModel: DiscountRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.gets(key!!).subscribeWith(object :
            DisposableObserver<List<Discount>>() {

            override fun onNext(@NonNull response: List<Discount>) {
                val dataManager = DataManager (context)
                dataManager.clearDiscountAll()
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val discount = DiscountSQL(
                            inc.toString(),
                            key.toString(),
                            item.id_discount.toString(),
                            item.name_discount.toString(),
                            item.note.toString(),
                            item.type.toString(),
                            item.nominal.toString(),
                        )
                        inc++
                        var result = dataManager.addDiscount(discount)
                    }
                }

            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                var errorMessage = "There is an error"
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                } else {
                    errorMessage = e.message.toString()
                }
                output?.onFailedAPI(errorCode, errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }

    override fun callGetTableAPI(context: Context, restModel: TableRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getTable(key!!).subscribeWith(object :
            DisposableObserver<List<Table>>() {

            override fun onNext(@NonNull response: List<Table>) {
                val dataManager = DataManager (context)
                dataManager.clearTableAll()
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val table = TableSQL(
                            inc.toString(),
                            key.toString(),
                            item.id_table.toString(),
                            item.name_table.toString(),
                        )
                        inc++
                        var result = dataManager.addTable(table)
                    }
                }

            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                var errorMessage = "There is an error"
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                } else {
                    errorMessage = e.message.toString()
                }
                output?.onFailedAPI(errorCode, errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }

    override fun callGetCustomersAPI(context: Context, restModel: CustomerRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.gets(key!!).subscribeWith(object : DisposableObserver<List<Customer>>() {

            override fun onNext(@NonNull response: List<Customer>) {
                val dataManager = DataManager (context)
                dataManager.clearCustomerAll()
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val customer = CustomerSQL(
                            inc.toString(),
                            key.toString(),
                            item.id_customer.toString(),
                            item.name_customer.toString(),
                            item.telephone.toString(),
                            item.email.toString(),
                            item.address.toString(),
                            item.customercode.toString(),
                        )
                        inc++
                        var result = dataManager.addCustomer(customer)
                    }
                }

            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                var errorMessage = "There is an error"
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                }
                else{
                    errorMessage = e.message.toString()
                }
                output?.onFailedAPI(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }

    override fun callGetSupplierAPI(context: Context, restModel: SupplierRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.gets(key!!).subscribeWith(object : DisposableObserver<List<Supplier>>() {

            override fun onNext(@NonNull response: List<Supplier>) {
                val dataManager = DataManager (context)
                dataManager.clearSupplierAll()
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val supplier = SupplierSQL(
                            inc.toString(),
                            key.toString(),
                            item.id_supplier.toString(),
                            item.name_supplier.toString(),
                            item.telephone.toString(),
                            item.email.toString(),
                            item.address.toString(),
                        )
                        inc++
                        var result = dataManager.addSupplier(supplier)
                    }
                }
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                var errorMessage = "There is an error"
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                }
                else{
                    errorMessage = e.message.toString()
                }
                output?.onFailedAPI(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }

    override fun callGetProductsAPI(context: Context, restModel: ProductRestModel, page: Int?) {
        val key = appSession.getToken(context)
        disposable.add(restModel.gets(key!!,page).subscribeWith(object : DisposableObserver<List<Product>>() {

            override fun onNext(@NonNull response: List<Product>) {
                val dataManager = DataManager (context)
                var response2 = mutableListOf<Product>()
                dataManager.clearProductAll()
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        var product1 = Product();
                        product1.set(item.id_product!!,item.name_product!!,item.unit!!,
                            item.img!!,item.codeproduct!!,
                            item.id_product!!,item.id_category!!,
                            item.purchase_price!!, item.selling_price!!,
                            item.stock!!, item.minimalstock!!,
                            item.description!!, item.online!!,
                            item.have_stock!!, item.wholesale_price!!,
                            inc.toString(), item.alertstock!!
                        )
                        response2.add(product1)
                        val product = ProductSQL(
                            inc.toString(),
                            item.id_product.toString(),
                            key.toString(),
                            item.name_product.toString(),
                            item.unit.toString(),
                            item.codeproduct.toString(),
                            item.id_category.toString(),
                            item.name_category.toString(),
                            item.active.toString(),
                            item.purchase_price.toString(),
                            item.selling_price.toString(),
                            item.stock.toString(),
                            item.minimalstock.toString(),
                            item.description.toString(),
                            item.online.toString(),
                            item.have_stock.toString(),
                            item.wholesale_price.toString(),
                            item.tax.toString(),
                            item.alertstock.toString(),
                            item.img.toString()
                        )
                        inc++
                        var result = dataManager.addProduct(product)
                    }
                }
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                var errorMessage = "There is an error"
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                }
                else{
                    errorMessage = e.message.toString()
                }
                output?.onFailedAPI(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }



}