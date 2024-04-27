package id.kasirvippro.android.feature.manage.discount.list

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.discount.DiscountRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.DiscountSQLDelete
import id.kasirvippro.android.utils.AppSession

class DiscountListPresenter(val context: Context, val view: DiscountListContract.View) : BasePresenter<DiscountListContract.View>(),
    DiscountListContract.Presenter, DiscountListContract.InteractorOutput {

    private var interactor = DiscountListInteractor(this)
    private var restModel = DiscountRestModel(context)

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

                    loadDiscounts()
                    dataManager.clearDiscountAll()
                    return
                }else{
                    var alldiscount = dataManager.allDiscount()

                    var discountList = mutableListOf<Discount>()
                    for (item in alldiscount){
                        var discount = Discount();
                        discount.set(
                            item.id_discount,
                            item.name_discount,
                            item.note,
                            item.type,
                            item.nominal,
                            item.increment
                        )
                        discountList.add(discount)
                    }
                    setDiscount(discountList);
                }
            }else{
                var alldiscount = dataManager.allDiscount()

                var discountList = mutableListOf<Discount>()
                for (item in alldiscount){
                    var discount = Discount();
                    discount.set(
                        item.id_discount,
                        item.name_discount,
                        item.note,
                        item.type,
                        item.nominal,
                        item.increment
                    )
                    discountList.add(discount)
                }
                setDiscount(discountList);
            }
        }else{
            var alldiscount = dataManager.allDiscount()

            var discountList = mutableListOf<Discount>()
            for (item in alldiscount){
                var discount = Discount();
                discount.set(
                    item.id_discount,
                    item.name_discount,
                    item.note,
                    item.type,
                    item.nominal,
                    item.increment
                )
                discountList.add(discount)
            }
            setDiscount(discountList);
        }
    }

    override fun loadDiscounts() {
        interactor.callGetDataAPI(context,restModel)
    }

    fun setDiscount(list: List<Discount>){
        view.setDiscounts(list)
    }

    override fun deleteDiscount(id: String,position:Int,increment:String) {
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
                    interactor.callDeleteAPI(context,restModel,id)
                }else{
                    val discount = DiscountSQLDelete(increment,key!!,id)
                    var result1 = dataManager.addDiscountDelete(discount!!)
                    var result = dataManager.clearDiscount(increment)

                    Toast.makeText(context,"Delete Discount Local", Toast.LENGTH_SHORT).show()
                    (context as DiscountListActivity).list2.removeAt(position)
                    (context as DiscountListActivity).setList()
                    (context as DiscountListActivity).hideLoading()
                }
            }else{
                val discount = DiscountSQLDelete(increment,key!!,id)
                var result1 = dataManager.addDiscountDelete(discount)
                var result = dataManager.clearDiscount(increment)

                Toast.makeText(context,"Delete Discount Local", Toast.LENGTH_SHORT).show()
                (context as DiscountListActivity).list2.removeAt(position)
                (context as DiscountListActivity).setList()
                (context as DiscountListActivity).hideLoading()
            }
        }else{
            val discount = DiscountSQLDelete(increment,key!!,id)
            var result1 = dataManager.addDiscountDelete(discount!!)
            var result = dataManager.clearDiscount(increment)

            Toast.makeText(context,"Delete Discount Local", Toast.LENGTH_SHORT).show()
            (context as DiscountListActivity).list2.removeAt(position)
            (context as DiscountListActivity).setList()
            (context as DiscountListActivity).hideLoading()
        }
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetData(list: List<Discount>) {
        view.setData(list)
    }

    override fun onSuccessDelete(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}