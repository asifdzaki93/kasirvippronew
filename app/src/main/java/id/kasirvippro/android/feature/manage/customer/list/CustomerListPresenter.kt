package id.kasirvippro.android.feature.manage.customer.list

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.CustomerSQLDelete
import id.kasirvippro.android.utils.AppSession

class CustomerListPresenter(val context: Context, val view: CustomerListContract.View) : BasePresenter<CustomerListContract.View>(),
    CustomerListContract.Presenter, CustomerListContract.InteractorOutput {

    private var interactor = CustomerListInteractor(this)
    private var restModel = CustomerRestModel(context)


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

                    loadCustomers()
                    dataManager.clearCustomerAll()
                    return
                }else{
                    var allcustomer = dataManager.allCustomer()

                    var customerList = mutableListOf<Customer>()
                    for (item in allcustomer){
                        var customer = Customer();
                        customer.set(
                            item.id_customer,
                            item.name_customer,
                            item.telephone,
                            item.email,
                            item.address,
                            item.customercode,
                            item.increment
                        )
                        customerList.add(customer)
                    }
                    setCustomer(customerList);
                }
            }else{
                var allcustomer = dataManager.allCustomer()

                var customerList = mutableListOf<Customer>()
                for (item in allcustomer){
                    var customer = Customer();
                    customer.set(
                        item.id_customer,
                        item.name_customer,
                        item.telephone,
                        item.email,
                        item.address,
                        item.customercode,
                        item.increment
                    )
                    customerList.add(customer)
                }
                setCustomer(customerList);
            }
        }else{
            var allcustomer = dataManager.allCustomer()

            var customerList = mutableListOf<Customer>()
            for (item in allcustomer){
                var customer = Customer();
                customer.set(
                    item.id_customer,
                    item.name_customer,
                    item.telephone,
                    item.email,
                    item.address,
                    item.customercode,
                    item.increment
                )
                customerList.add(customer)
            }
            setCustomer(customerList);
        }
    }

    override fun loadCustomers() {
        interactor.callGetCustomersAPI(context,restModel)
    }

    override fun deleteCustomer(id: String,position:Int,increment:String) {
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
                    interactor.callDeleteCustomerAPI(context,restModel,id)
                }else{
                    val customer = CustomerSQLDelete(increment,key!!,id)
                    var result1 = dataManager.addCustomerDelete(customer!!)
                    var result = dataManager.clearCustomer(increment)

                    Toast.makeText(context,"Delete Customer Local", Toast.LENGTH_SHORT).show()
                    (context as CustomerListActivity).list2.removeAt(position)
                    (context as CustomerListActivity).setList()
                    (context as CustomerListActivity).hideLoading()
                }
            }else{
                val customer = CustomerSQLDelete(increment,key!!,id)
                var result1 = dataManager.addCustomerDelete(customer)
                var result = dataManager.clearCustomer(increment)

                Toast.makeText(context,"Delete Customer Local", Toast.LENGTH_SHORT).show()
                (context as CustomerListActivity).list2.removeAt(position)
                (context as CustomerListActivity).setList()
                (context as CustomerListActivity).hideLoading()
            }
        }else{
            val customer = CustomerSQLDelete(increment,key!!,id)
            var result1 = dataManager.addCustomerDelete(customer!!)
            var result = dataManager.clearCustomer(increment)

            Toast.makeText(context,"Delete Customer Local", Toast.LENGTH_SHORT).show()
            (context as CustomerListActivity).list2.removeAt(position)
            (context as CustomerListActivity).setList()
            (context as CustomerListActivity).hideLoading()
        }
    }

    override fun searchCustomer(search: String) {
        interactor.onRestartDisposable()
        if(search.isNullOrEmpty() || search.isNullOrBlank()){
            interactor.callGetCustomersAPI(context,restModel)
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
                        interactor.callSearchCustomerrAPI(context,restModel,search)
                    }else{
                        var searchcustomer = dataManager.searchCustomer(search)

                        var customerList = mutableListOf<Customer>()
                        for (item in searchcustomer){
                            var customer = Customer();
                            customer.set(
                                item.id_customer,
                                item.name_customer,
                                item.telephone,
                                item.email,
                                item.address,
                                item.customercode,
                                item.increment
                            )
                            customerList.add(customer)
                        }
                        setCustomer(customerList);
                    }
                }else{
                    var searchcustomer = dataManager.searchCustomer(search)

                    var customerList = mutableListOf<Customer>()
                    for (item in searchcustomer){
                        var customer = Customer();
                        customer.set(
                            item.id_customer,
                            item.name_customer,
                            item.telephone,
                            item.email,
                            item.address,
                            item.customercode,
                            item.increment
                        )
                        customerList.add(customer)
                    }
                    setCustomer(customerList);
                }
            }else{
                var searchcustomer = dataManager.searchCustomer(search)

                var customerList = mutableListOf<Customer>()
                for (item in searchcustomer){
                    var customer = Customer();
                    customer.set(
                        item.id_customer,
                        item.name_customer,
                        item.telephone,
                        item.email,
                        item.address,
                        item.customercode,
                        item.increment
                    )
                    customerList.add(customer)
                }
                setCustomer(customerList);
            }
        }
    }

    fun setCustomer(list: List<Customer>){
        view.setCustomers(list)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetCustomers(list: List<Customer>) {
        view.setData(list)
    }

    override fun onSuccessDeleteCustomer(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}