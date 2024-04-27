package id.kasirvippro.android.feature.manage.supplier.list

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.supplier.SupplierRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.SupplierSQLDelete
import id.kasirvippro.android.utils.AppSession

class SupplierListPresenter(val context: Context, val view: SupplierListContract.View) : BasePresenter<SupplierListContract.View>(),
    SupplierListContract.Presenter, SupplierListContract.InteractorOutput {

    private var interactor: SupplierListInteractor = SupplierListInteractor(this)
    private var restModel = SupplierRestModel(context)


    override fun onViewCreated() {
        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val dataManager = DataManager (context)
        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {

                    loadSuppliers()
                    dataManager.clearSupplierAll()
                    return
                }else{
                    var allsupplier = dataManager.allSupplier()

                    var supplierList = mutableListOf<Supplier>()
                    for (item in allsupplier){
                        var supplier = Supplier();
                        supplier.set(item.id_supplier,item.name_supplier,
                            item.telephone,item.email,
                            item.address,
                            item.increment
                        )
                        supplierList.add(supplier)
                    }
                    setSupplier(supplierList);
                }
            }else{
                var allsupplier = dataManager.allSupplier()

                var supplierList = mutableListOf<Supplier>()
                for (item in allsupplier){
                    var supplier = Supplier();
                    supplier.set(item.id_supplier,item.name_supplier,
                        item.telephone,item.email,
                        item.address,
                        item.increment
                    )
                    supplierList.add(supplier)
                }
                setSupplier(supplierList);
            }
        }else{
            var allsupplier = dataManager.allSupplier()

            var suppliertList = mutableListOf<Supplier>()
            for (item in allsupplier){
                var supplier = Supplier();
                supplier.set(item.id_supplier,item.name_supplier,
                    item.telephone,item.email,
                    item.address,
                    item.increment
                )
                suppliertList.add(supplier)
            }
            setSupplier(suppliertList);
        }
    }

    override fun loadSuppliers() {
        interactor.callGetSuppliersAPI(context,restModel)
    }

    override fun deleteSupplier(id: String,position:Int,increment:String) {

        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = context?.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val dataManager = DataManager (context)
        val appSession = AppSession()
        val key = appSession.getToken(context)

        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    interactor.callDeleteSupplierAPI(context,restModel,id)
                }else{
                    val supplier = SupplierSQLDelete(increment,key!!,id)
                    var result1 = dataManager.addSupplierDelete(supplier!!)
                    var result = dataManager.clearSupplier(increment)

                    Toast.makeText(context,"Delete Supplier Local", Toast.LENGTH_SHORT).show()
                    (context as SupplierListActivity).list2.removeAt(position)
                    (context as SupplierListActivity).setList()
                    (context as SupplierListActivity).hideLoading()
                }
            }else{
                val supplier = SupplierSQLDelete(increment,key!!,id)
                var result1 = dataManager.addSupplierDelete(supplier)
                var result = dataManager.clearSupplier(increment)

                Toast.makeText(context,"Delete Supplier Local", Toast.LENGTH_SHORT).show()
                (context as SupplierListActivity).list2.removeAt(position)
                (context as SupplierListActivity).setList()
                (context as SupplierListActivity).hideLoading()
            }
        }else{
            val supplier = SupplierSQLDelete(increment,key!!,id)
            var result1 = dataManager.addSupplierDelete(supplier!!)
            var result = dataManager.clearSupplier(increment)

            Toast.makeText(context,"Delete Supplier Local", Toast.LENGTH_SHORT).show()
            (context as SupplierListActivity).list2.removeAt(position)
            (context as SupplierListActivity).setList()
            (context as SupplierListActivity).hideLoading()
        }

    }

    override fun searchSupplier(search: String) {
        interactor.onRestartDisposable()
        if(search.isNullOrEmpty() || search.isNullOrBlank()){
            interactor.callGetSuppliersAPI(context,restModel)
        }
        else{


            var connectivity : ConnectivityManager? = null
            var info : NetworkInfo? = null
            connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            val dataManager = DataManager (context)
            if ( connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                        interactor.callSearchSupplierAPI(context,restModel,search)
                    }else{
                        var searchsupplier = dataManager.searchSupplier(search)

                        var supplierList = mutableListOf<Supplier>()
                        for (item in searchsupplier){
                            var supplier = Supplier();
                            supplier.set(item.id_supplier,item.name_supplier,
                                item.telephone,item.email,
                                item.address,
                                item.increment
                            )
                            supplierList.add(supplier)
                        }
                        setSupplier(supplierList);
                    }
                }else{
                    var searchsupplier = dataManager.searchSupplier(search)

                    var supplierList = mutableListOf<Supplier>()
                    for (item in searchsupplier){
                        var supplier = Supplier();
                        supplier.set(item.id_supplier,item.name_supplier,
                            item.telephone,item.email,
                            item.address,
                            item.increment
                        )
                        supplierList.add(supplier)
                    }
                    setSupplier(supplierList);
                }
            }else{
                var searchsupplier = dataManager.searchSupplier(search)

                var supplierList = mutableListOf<Supplier>()
                for (item in searchsupplier){
                    var supplier = Supplier();
                    supplier.set(item.id_supplier,item.name_supplier,
                        item.telephone,item.email,
                        item.address,
                        item.increment
                    )
                    supplierList.add(supplier)
                }
                setSupplier(supplierList);
            }
        }
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetSuppliers(list: List<Supplier>) {
        view.setData(list)
    }

    fun setSupplier(list: List<Supplier>){
        view.setSuppliers(list)
    }

    override fun onSuccessDeleteSupplier(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}