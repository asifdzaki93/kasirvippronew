package id.kasirvippro.android.feature.menu

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import id.kasirvippro.android.BuildConfig
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseFragment
import id.kasirvippro.android.feature.report.slip.chooseMonth.MonthActivity
import id.kasirvippro.android.feature.setting.account.AccountActivity
import id.kasirvippro.android.feature.setting.password.PasswordActivity
import id.kasirvippro.android.feature.webview.WebViewActivity
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.category.CategoryRestModel
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.models.discount.DiscountRestModel
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.models.supplier.SupplierRestModel
import id.kasirvippro.android.models.table.TableRestModel
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import id.kasirvippro.android.utils.glide.GlideApp
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.fragment_menu.view.*
import java.util.*

@Suppress("DEPRECATION")
class MenuFragment : BaseFragment<MenuPresenter, MenuContract.View>(), MenuContract.View {

    private lateinit var _view: View

    private val codeProfile = 1001
    private val codeStore = 1002

    private  var onMenu: MenuClick? = null
    private  var context1: Context? = null

    companion object {


        @JvmStatic
        fun newInstance() =
                MenuFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun createPresenter(): MenuPresenter {
        return MenuPresenter(activity as Context, this)
    }

    override fun onCreateLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_menu, container, false)
    }


    override fun initAction(view: View) {
        _view = view
        renderView()
        getPresenter()?.onViewCreated()
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(){

        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                var count = 0
                val dataManager = context1?.let { DataManager (it) }
                if (dataManager != null) {
                    var productAdd = dataManager.allProductAdd()
                    var productUpdate = dataManager.allProductUpdate()
                    var productDelete = dataManager.allProductDelete()
                    var suplierUpdate = dataManager.allSupplierUpdate()
                    var suplierDelete = dataManager.allSupplierDelete()
                    var supplierAdd = dataManager.allSupplierAdd()
                    var customerAdd = dataManager.allCustomerAdd()
                    var customerDelete = dataManager.allCustomerDelete()
                    var customerUpdate = dataManager.allCustomerUpdate()
                    var discountDelete = dataManager.allDiscountDelete()
                    var discountAdd = dataManager.allDiscountAdd()
                    var discountUpdate = dataManager.allDiscountUpdate()

                    var tableDelete = dataManager.allTableDelete()
                    var tableAdd = dataManager.allTableAdd()
                    var tableUpdate = dataManager.allTableUpdate()

                    var categoryDelete = dataManager.allCategoryDelete()
                    var categoryAdd = dataManager.allCategoryAdd()
                    var categoryUpdate = dataManager.allCategoryUpdate()
                    var salesdataAdd = dataManager.allSalesData()
                    var spendingdataAdd = dataManager.allSpendingData()
                    if (salesdataAdd != null) {
                        if (spendingdataAdd != null) {
                            count = count + productAdd.size + productDelete.size + productUpdate.size + suplierUpdate.size + suplierDelete.size + supplierAdd.size + customerDelete.size + customerAdd.size + customerUpdate.size + discountDelete.size + discountAdd.size + discountUpdate.size + tableDelete.size + tableAdd.size + tableUpdate.size + categoryDelete.size + categoryAdd.size + categoryUpdate.size + salesdataAdd.size + spendingdataAdd.size
                        }
                    }

                    if (activity != null){
                        activity!!.runOnUiThread {
                            _view.count_sync.text = count.toString()
                        }
                    }
                }
            }
        }, 0, 1000)

        _view.tv_version.text = "v${BuildConfig.VERSION_NAME}"

        _view.ll_account.setOnClickListener {
            openProfilePage()
        }

        _view.btn_account.setOnClickListener {
            openProfilePage()
        }

        _view.btn_password.setOnClickListener {
            openPasswordPage()
        }

        _view.btn_update.setOnClickListener {
            var connectivity : ConnectivityManager? = null
            var info : NetworkInfo? = null
            connectivity = context1?.getSystemService(Service.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

            if ( connectivity != null)
            {
                info = connectivity!!.activeNetworkInfo
                if (info != null)
                {
                    if (info!!.state == NetworkInfo.State.CONNECTED)
                    {
            getPresenter()?.loadStore()
            getPresenter()?.loadLink()
            getPresenter()?.loadCategory()
            getPresenter()?.loadDiscount()
            getPresenter()?.loadProduct()
            getPresenter()?.loadCustomer()
            getPresenter()?.loadSupplier()
            getPresenter()?.loadTable()
                        val builder = AlertDialog.Builder(context!!)
                        builder.setTitle("Info")
                        builder.setMessage("retrieve data to Offline Success!")
                        builder.setCancelable(false)
                        builder.setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        builder.show()
                    }else{
                        Toast.makeText(context1,"Your connection is not available",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context1,"Your connection is not available",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context1,"Your connection is not available",Toast.LENGTH_SHORT).show()
            }
        }

        _view.btn_sync.setOnClickListener {
            syncDatabase();
        }

        _view.btn_slip.setOnClickListener {
            openSlipPage()
        }

        _view.btn_premium.setOnClickListener {
            openWebviewPage("Premium",getPresenter()?.getPremiumUrl()!!)
        }

        _view.btn_privacy.setOnClickListener {
            openWebviewPage(getString(R.string.lbl_setting_privacy_title),AppConstant.URL.PRIVACY)
        }

        _view.btn_terms.setOnClickListener {
            openWebviewPage(getString(R.string.lbl_setting_term_title),AppConstant.URL.TERM)
        }

        _view.btn_help.setOnClickListener {
            openWebviewPage(getString(R.string.lbl_about),AppConstant.URL.ABOUT)
        }

        _view.btn_logout.setOnClickListener {
            getPresenter()?.onLogout()
        }


    }

    override fun loadProfile() {
        getPresenter()?.loadProfile()
    }

    override fun setProfile(name: String?, phone: String?,photo: String?) {
        _view.tv_name.text = ""
        _view.tv_phone.text = ""


        name?.let {
            _view.tv_name.text = it
        }

        phone?.let {
            _view.tv_phone.text = it
        }

        GlideApp.with(activity!!)
            .load(photo)
            .error(R.drawable.user)
            .placeholder(R.drawable.user)
            .transform(CenterCrop(), CircleCrop())
            .into(_view.iv_photo)


    }

    override fun openWebviewPage(title:String,url: String) {
        val browserIntent = Intent(activity, WebViewActivity::class.java)
        browserIntent.putExtra(AppConstant.Webview.TITLE,title)
        browserIntent.putExtra(AppConstant.Webview.URL,url)
        startActivity(browserIntent)
    }

    override fun showErrorMessage(code: Int, msg: String) {
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> toast(msg)
        }

    }

   override fun openPasswordPage() {
        startActivity(Intent(activity, PasswordActivity::class.java))
   }

    override fun openProfilePage() {
        startActivityForResult(Intent(activity, AccountActivity::class.java),codeProfile)
    }

    override fun syncDatabase(){

        var progressDialog = ProgressDialog(_view.context)
        progressDialog.setTitle("Loading")
        progressDialog.setCancelable(false)

        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = context1?.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        if ( connectivity != null)
        {
            info = connectivity!!.activeNetworkInfo
            if (info != null)
            {
                if (info!!.state == NetworkInfo.State.CONNECTED)
                {
                    val dataManager = context1?.let { DataManager (it) }
                    if (dataManager != null) {
                        var productAdd = dataManager.allProductAdd()
                        var productAddModel = context1?.let { ProductRestModel(it) }
                        val appSession = AppSession()
                        var disposable = CompositeDisposable()
                        for (item in productAdd) {
                            var photo = "";
                            context1?.let {
                                if (productAddModel != null) {
                                    if (item.img != "null"){
                                        progressDialog.show()
                                        disposable.add(productAddModel.add(
                                            item.key,
                                            item.nama,
                                            item.unit,
                                            item.kode,
                                            item.idkategori,
                                            item.jual,
                                            item.beli,
                                            item.stok,
                                            item.minstok,
                                            item.img,
                                            item.deskripsi,
                                            item.online,
                                            item.havestok.toString(),
                                            item.grosir,
                                            item.tax,
                                            item.alertstock).subscribeWith(object : DisposableObserver<Message>() {

                                            override fun onNext(@NonNull response: Message) {
                                                dataManager.clearProductAdd(item.increment)
                                                progressDialog.dismiss()
                                            }

                                            override fun onError(@NonNull e: Throwable) {
                                                dataManager.clearProductUpdate(item.increment)
                                                progressDialog.dismiss()
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
                                                Log.e(errorCode.toString(), errorMessage)
                                            }

                                            override fun onComplete() {

                                            }
                                        }))
                                    }else{
                                        progressDialog.show()
                                        disposable.add(productAddModel.add(
                                            item.key,
                                            item.nama,
                                            item.unit,
                                            item.kode,
                                            item.idkategori,
                                            item.jual,
                                            item.beli,
                                            item.stok,
                                            item.minstok,
                                            null,
                                            item.deskripsi,
                                            item.online,
                                            item.havestok.toString(),
                                            item.grosir,
                                            item.tax,
                                            item.alertstock).subscribeWith(object : DisposableObserver<Message>() {

                                            override fun onNext(@NonNull response: Message) {
                                                dataManager.clearProductAdd(item.increment)
                                                progressDialog.dismiss()
                                            }

                                            override fun onError(@NonNull e: Throwable) {
                                                dataManager.clearProductUpdate(item.increment)
                                                progressDialog.dismiss()
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
                                                Log.e(errorCode.toString(), errorMessage)
                                            }

                                            override fun onComplete() {

                                            }
                                        }))
                                    }

                                }
                            }
                        }

                        var productUpdate = dataManager.allProductUpdate()
                        var productUpdateModel = context1?.let { ProductRestModel(it) }
                        for (item in productUpdate) {
                            var photoPath = "";
                            context1?.let {
                                if (productUpdateModel != null) {
                                    var photo: String? = null;
                                    if (item.img != "null"){
                                        progressDialog.show()
                                        disposable.add(productUpdateModel.update(
                                            item.key,
                                            item.id_product,
                                            item.nama,
                                            item.unit,
                                            item.kode,
                                            item.idkategori,
                                            item.jual,
                                            item.beli,
                                            item.stok,
                                            item.minstok,
                                            item.img,
                                            item.deskripsi,
                                            item.online,
                                            item.havestok.toString(),
                                            item.grosir,
                                            item.tax,
                                            item.alertstock).subscribeWith(object : DisposableObserver<Message>() {

                                            override fun onNext(@NonNull response: Message) {
                                                dataManager.clearProductUpdate(item.increment)
                                                progressDialog.dismiss()
                                            }

                                            override fun onError(@NonNull e: Throwable) {
                                                dataManager.clearProductUpdate(item.increment)
                                                progressDialog.dismiss()
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
                                                Log.e(errorCode.toString(), errorMessage)
                                            }

                                            override fun onComplete() {

                                            }
                                        }))
                                    }else{
                                        progressDialog.show()
                                        disposable.add(productUpdateModel.update(
                                            item.key,
                                            item.id_product,
                                            item.nama,
                                            item.unit,
                                            item.kode,
                                            item.idkategori,
                                            item.jual,
                                            item.beli,
                                            item.stok,
                                            item.minstok,
                                            null,
                                            item.deskripsi,
                                            item.online,
                                            item.havestok.toString(),
                                            item.grosir,
                                            item.tax,
                                            item.alertstock).subscribeWith(object : DisposableObserver<Message>() {

                                            override fun onNext(@NonNull response: Message) {
                                                dataManager.clearProductUpdate(item.increment)
                                                progressDialog.dismiss()
                                            }

                                            override fun onError(@NonNull e: Throwable) {
                                                dataManager.clearProductUpdate(item.increment)
                                                progressDialog.dismiss()
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
                                                Log.e(errorCode.toString(), errorMessage)
                                            }

                                            override fun onComplete() {

                                            }
                                        }))
                                    }

                                }
                            }
                        }

                        var productDelete = dataManager.allProductDelete()
                        var productDeleteModel = context1?.let { ProductRestModel(it) }
                        for (item in productDelete) {
                            var photoPath = "";
                            context1?.let {
                                if (productDeleteModel != null) {
                                    progressDialog.show()
                                    disposable.add(productDeleteModel.delete(
                                        item.key,
                                        item.id_product).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearProductDelete(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearProductDelete(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))
                                }
                            }
                        }

                        var supplierDelete = dataManager.allSupplierDelete()
                        var supplierDeleteModel = context1?.let { SupplierRestModel(it) }
                        for (item in supplierDelete) {
                            context1?.let {
                                if (supplierDeleteModel != null) {
                                    progressDialog.show()
                                    disposable.add(supplierDeleteModel.delete(
                                        item.key,
                                        item.id).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearSupplierDelete(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearSupplierDelete(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))
                                }
                            }
                        }

                        var supplierUpdate = dataManager.allSupplierUpdate()
                        var supplierUpdateModel = context1?.let { SupplierRestModel(it) }
                        for (item in supplierUpdate) {
                            context1?.let {
                                if (supplierUpdateModel != null) {

                                        progressDialog.show()
                                        disposable.add(supplierUpdateModel.update(
                                            item.key,
                                            item.id_supplier,
                                            item.name_supplier,
                                            item.email,
                                            item.telephone,
                                            item.address).subscribeWith(object : DisposableObserver<Message>() {

                                            override fun onNext(@NonNull response: Message) {
                                                dataManager.clearSupplierUpdate(item.increment)
                                                progressDialog.dismiss()
                                            }

                                            override fun onError(@NonNull e: Throwable) {
                                                dataManager.clearSupplierUpdate(item.increment)
                                                progressDialog.dismiss()
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
                                                Log.e(errorCode.toString(), errorMessage)
                                            }

                                            override fun onComplete() {

                                            }
                                        }))


                                }
                            }
                        }

                        var customerUpdate = dataManager.allCustomerUpdate()
                        var customerUpdateModel = context1?.let { CustomerRestModel(it) }
                        for (item in customerUpdate) {
                            context1?.let {
                                if (customerUpdateModel != null) {

                                    progressDialog.show()
                                    disposable.add(customerUpdateModel.update(
                                        item.key,
                                        item.id_customer,
                                        item.name_customer,
                                        item.email,
                                        item.telephone,
                                        item.address,
                                        item.customercode).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearCustomerUpdate(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearCustomerUpdate(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))


                                }
                            }
                        }

                        var discountUpdate = dataManager.allDiscountUpdate()
                        var discountUpdateModel = context1?.let { DiscountRestModel(it) }
                        for (item in discountUpdate) {
                            context1?.let {
                                if (discountUpdateModel != null) {

                                    progressDialog.show()
                                    disposable.add(discountUpdateModel.update(
                                        item.key,
                                        item.id_discount,
                                        item.name_discount,
                                        item.note,
                                        item.type,
                                        item.nominal).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearDiscountUpdate(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearDiscountUpdate(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))


                                }
                            }
                        }

                        var tableUpdate = dataManager.allTableUpdate()
                        var tableUpdateModel = context1?.let { TableRestModel(it) }
                        for (item in tableUpdate) {
                            context1?.let {
                                if (tableUpdateModel != null) {

                                    progressDialog.show()
                                    disposable.add(tableUpdateModel.update(
                                        item.key,
                                        item.id_table,
                                        item.name_table).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearTableUpdate(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearTableUpdate(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))


                                }
                            }
                        }

                        var categoryUpdate = dataManager.allCategoryUpdate()
                        var categoryUpdateModel = context1?.let { CategoryRestModel(it) }
                        for (item in categoryUpdate) {
                            context1?.let {
                                if (categoryUpdateModel != null) {

                                    progressDialog.show()
                                    disposable.add(categoryUpdateModel.updateCategory(
                                        item.key,
                                        item.id_category,
                                        item.name_category).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearCategoryUpdate(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearCategoryUpdate(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))


                                }
                            }
                        }

                        var supplierAdd = dataManager.allSupplierAdd()
                        var supplierAddModel = context1?.let { SupplierRestModel(it) }
                        for (item in supplierAdd) {
                            context1?.let {
                                if (supplierAddModel != null) {

                                        progressDialog.show()
                                        disposable.add(supplierAddModel.add(
                                            item.key,
                                            item.name_supplier,
                                            item.telephone,
                                            item.email,
                                            item.address).subscribeWith(object : DisposableObserver<Message>() {

                                            override fun onNext(@NonNull response: Message) {
                                                dataManager.clearSupplierAdd(item.increment)
                                                progressDialog.dismiss()
                                            }

                                            override fun onError(@NonNull e: Throwable) {
                                                dataManager.clearSupplierUpdate(item.increment)
                                                progressDialog.dismiss()
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
                                                Log.e(errorCode.toString(), errorMessage)
                                            }

                                            override fun onComplete() {

                                            }
                                        }))


                                }
                            }
                        }


                        var customerAdd = dataManager.allCustomerAdd()
                        var customerAddModel = context1?.let { CustomerRestModel(it) }
                        for (item in customerAdd) {
                            context1?.let {
                                if (customerAddModel != null) {

                                    progressDialog.show()
                                    disposable.add(customerAddModel.add(
                                        item.key,
                                        item.name_customer,
                                        item.telephone,
                                        item.email,
                                        item.address,
                                        item.customercode).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearCustomerAdd(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearCustomerUpdate(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))


                                }
                            }
                        }

                        var discountAdd = dataManager.allDiscountAdd()
                        var discountAddModel = context1?.let { DiscountRestModel(it) }
                        for (item in discountAdd) {
                            context1?.let {
                                if (discountAddModel != null) {

                                    progressDialog.show()
                                    disposable.add(discountAddModel.add(
                                        item.key,
                                        item.name_discount,
                                        item.note,
                                        item.type,
                                        item.nominal).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearDiscountAdd(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearDiscountUpdate(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))


                                }
                            }
                        }

                        var tableAdd = dataManager.allTableAdd()
                        var tableAddModel = context1?.let { TableRestModel(it) }
                        for (item in tableAdd) {
                            context1?.let {
                                if (tableAddModel != null) {

                                    progressDialog.show()
                                    disposable.add(tableAddModel.add(
                                        item.key,
                                        item.name_table).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearTableAdd(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearTableUpdate(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))


                                }
                            }
                        }

                        var categoryAdd = dataManager.allCategoryAdd()
                        var categoryAddModel = context1?.let { CategoryRestModel(it) }
                        for (item in categoryAdd) {
                            context1?.let {
                                if (categoryAddModel != null) {

                                    progressDialog.show()
                                    disposable.add(categoryAddModel.addCategory(
                                        item.key,
                                        item.name_category).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearCategoryAdd(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearCategoryUpdate(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))


                                }
                            }
                        }

                        var customerDelete = dataManager.allCustomerDelete()
                        var customerDeleteModel = context1?.let { CustomerRestModel(it) }
                        for (item in customerDelete) {
                            context1?.let {
                                if (customerDeleteModel != null) {
                                    progressDialog.show()
                                    disposable.add(customerDeleteModel.delete(
                                        item.key,
                                        item.id).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearCustomerDelete(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearCustomerDelete(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))
                                }
                            }
                        }


                        var discountDelete = dataManager.allDiscountDelete()
                        var discountDeleteModel = context1?.let { DiscountRestModel(it) }
                        for (item in discountDelete) {
                            context1?.let {
                                if (discountDeleteModel != null) {
                                    progressDialog.show()
                                    disposable.add(discountDeleteModel.delete(
                                        item.key,
                                        item.id).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearDiscountDelete(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearDiscountDelete(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))
                                }
                            }
                        }

                        var tableDelete = dataManager.allTableDelete()
                        var tableDeleteModel = context1?.let { TableRestModel(it) }
                        for (item in tableDelete) {
                            context1?.let {
                                if (tableDeleteModel != null) {
                                    progressDialog.show()
                                    disposable.add(tableDeleteModel.delete(
                                        item.key,
                                        item.id).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearTableDelete(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearTableDelete(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))
                                }
                            }
                        }

                        var categoryDelete = dataManager.allCategoryDelete()
                        var categoryDeleteModel = context1?.let { CategoryRestModel(it) }
                        for (item in categoryDelete) {
                            context1?.let {
                                if (categoryDeleteModel != null) {
                                    progressDialog.show()
                                    disposable.add(categoryDeleteModel.deleteCategory(
                                        item.key,
                                        item.id).subscribeWith(object : DisposableObserver<Message>() {

                                        override fun onNext(@NonNull response: Message) {
                                            dataManager.clearCategoryDelete(item.increment)
                                            progressDialog.dismiss()
                                        }

                                        override fun onError(@NonNull e: Throwable) {
                                            dataManager.clearCategoryDelete(item.increment)
                                            progressDialog.dismiss()
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
                                            Log.e(errorCode.toString(), errorMessage)
                                        }

                                        override fun onComplete() {

                                        }
                                    }))
                                }
                            }
                        }


                        var salesdataAdd = dataManager.allSalesData()
                        var salesdataAddModel = context1?.let { TransactionRestModel(it) }
                        if (salesdataAdd != null) {
                            for (item in salesdataAdd) {
                                context1?.let {
                                    if (salesdataAddModel != null) {

                                        progressDialog.show()
                                        disposable.add(salesdataAddModel.addSalesData(
                                            item.user,
                                            item.no_invoice,
                                            item.id_customer,
                                            item.id_store,
                                            item.payment,
                                            item.totalorder,
                                            item.totalprice,
                                            item.totalpay,
                                            item.changepay,
                                            item.status,
                                            item.due_date,
                                            item.tax,
                                            item.discount,
                                            item.service_charge,
                                            item.operator,
                                            item.location,
                                            item.note,
                                            item.date).subscribeWith(object : DisposableObserver<Message>() {

                                            override fun onNext(@NonNull response: Message) {
                                                dataManager.clearSalesDataAdd(item.increment)
                                                progressDialog.dismiss()
                                            }

                                            override fun onError(@NonNull e: Throwable) {
                                                dataManager.clearSalesDataAdd(item.increment)
                                                progressDialog.dismiss()
                                                e.printStackTrace()
                                                var errorCode = 999
                                                var errorMessage = "There is an error"
                                                if (e is RestException) {
                                                    errorCode = e.errorCode
                                                    errorMessage = e.message ?: "There is an error"
                                                } else{
                                                    errorMessage = e.message.toString()
                                                }
                                                Log.e(errorCode.toString(), errorMessage)
                                            }

                                            override fun onComplete() {

                                            }
                                        }))


                                    }
                                }
                            }
                        }

                        var spendingdataAdd = dataManager.allSpendingData()
                        var spendingdataAddModel = context1?.let { TransactionRestModel(it) }
                        if (spendingdataAdd != null) {
                            for (item in spendingdataAdd) {
                                context1?.let {
                                    if (spendingdataAddModel != null) {

                                        progressDialog.show()
                                        disposable.add(spendingdataAddModel.addSpendingData(
                                            item.user,
                                            item.id_store,
                                            item.no_invoice,
                                            item.date,
                                            item.totalnominal).subscribeWith(object : DisposableObserver<Message>() {

                                            override fun onNext(@NonNull response: Message) {
                                                dataManager.clearSpendingDataAdd(item.increment)
                                                progressDialog.dismiss()
                                            }

                                            override fun onError(@NonNull e: Throwable) {
                                                dataManager.clearSpendingDataAdd(item.increment)
                                                progressDialog.dismiss()
                                                e.printStackTrace()
                                                var errorCode = 999
                                                var errorMessage = "There is an error"
                                                if (e is RestException) {
                                                    errorCode = e.errorCode
                                                    errorMessage = e.message ?: "There is an error"
                                                } else{
                                                    errorMessage = e.message.toString()
                                                }
                                                Log.e(errorCode.toString(), errorMessage)
                                            }

                                            override fun onComplete() {

                                            }
                                        }))


                                    }
                                }
                            }
                        }

                        var spendingAdd = dataManager.allSpending()
                        var spendingAddModel = context1?.let { TransactionRestModel(it) }
                        if (spendingAdd != null) {
                            for (item in spendingAdd) {
                                context1?.let {
                                    if (spendingAddModel != null) {

                                        progressDialog.show()
                                        disposable.add(spendingAddModel.addSpending(
                                            item.name_spending,
                                            item.user,
                                            item.no_invoice,
                                            item.nominal,
                                            item.date).subscribeWith(object : DisposableObserver<Message>() {

                                            override fun onNext(@NonNull response: Message) {
                                                dataManager.clearSpendingAdd(item.increment)
                                                progressDialog.dismiss()
                                            }

                                            override fun onError(@NonNull e: Throwable) {
                                                dataManager.clearSpendingAdd(item.increment)
                                                progressDialog.dismiss()
                                                e.printStackTrace()
                                                var errorCode = 999
                                                var errorMessage = "There is an error"
                                                if (e is RestException) {
                                                    errorCode = e.errorCode
                                                    errorMessage = e.message ?: "There is an error"
                                                } else{
                                                    errorMessage = e.message.toString()
                                                }
                                                Log.e(errorCode.toString(), errorMessage)
                                            }

                                            override fun onComplete() {

                                            }
                                        }))


                                    }
                                }
                            }
                        }



                        var salesAdd = dataManager.allSales()
                        var salesAddModel = context1?.let { TransactionRestModel(it) }
                        if (salesAdd != null) {
                            for (item in salesAdd) {
                                context1?.let {
                                    if (salesAddModel != null) {

                                        progressDialog.show()
                                        disposable.add(salesAddModel.addSales(
                                            item.user,
                                            item.no_invoice,
                                            item.id_customer,
                                            item.id_store,
                                            item.id_product,
                                            item.amount,
                                            item.totalprice,
                                            item.price,
                                            item.status,
                                            item.note,
                                            item.date).subscribeWith(object : DisposableObserver<Message>() {

                                            override fun onNext(@NonNull response: Message) {
                                                dataManager.clearSalesAdd(item.increment)
                                                progressDialog.dismiss()
                                            }

                                            override fun onError(@NonNull e: Throwable) {
                                                dataManager.clearSalesAdd(item.increment)
                                                progressDialog.dismiss()
                                                e.printStackTrace()
                                                var errorCode = 999
                                                var errorMessage = "There is an error"
                                                if (e is RestException) {
                                                    errorCode = e.errorCode
                                                    errorMessage = e.message ?: "There is an error"
                                                } else{
                                                    errorMessage = e.message.toString()
                                                }
                                                Log.e(errorCode.toString(), errorMessage)
                                            }

                                            override fun onComplete() {

                                            }
                                        }))


                                    }
                                }
                            }
                        }






                    }
                }
                else
                {
                    Toast.makeText(context1,"Your connection is not available",Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(context1,"Your connection is not available",Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun openSlipPage() {
        val newintent = Intent(activity,MonthActivity::class.java)
        newintent.putExtra(AppConstant.CODE,AppConstant.Code.CODE_KARYAWAN_GAJI)
        newintent.putExtra(AppConstant.DATA,getPresenter()?.getToken())
        startActivity(newintent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == codeProfile && resultCode == Activity.RESULT_OK){
            getPresenter()?.loadProfile()
        }
        else if(requestCode == codeStore && resultCode == Activity.RESULT_OK){
            onMenu?.loadStore()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MenuClick) {
            onMenu = context
            context1 = context
        } else {
            throw RuntimeException("$context must implement MenuClick")
        }
    }

    override fun onDetach() {
        super.onDetach()
        onMenu = null
        getPresenter()?.onDestroy()
    }

    interface MenuClick {
        fun loadStore()
    }

    override fun onMasterPage() {
        _view.ll_slip.visibility = View.GONE
    }

    override fun onAdminPage() {
        _view.ll_slip.visibility = View.VISIBLE
        _view.ll_premium.visibility = View.GONE
    }

    override fun onSalesPage() {
        _view.ll_slip.visibility = View.VISIBLE
        _view.ll_premium.visibility = View.GONE

    }

    override fun onPremium(isPremium:Boolean) {
        if(isPremium){
            _view.ll_premium.visibility = View.GONE
        }
        else{
            _view.ll_premium.visibility = View.VISIBLE
        }
    }
}
