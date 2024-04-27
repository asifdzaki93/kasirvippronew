package id.kasirvippro.android.feature.pulsaPpob.dataProduct

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.productPpob.ProductPpob
import id.kasirvippro.android.models.pulsaPpob.PulsaPpob
import id.kasirvippro.android.models.pulsaPpob.PulsaPpobRestModel
import id.kasirvippro.android.utils.AppConstant

class DataProductPresenter(val context: Context, val view: DataProductContract.View) : BasePresenter<DataProductContract.View>(),
    DataProductContract.Presenter, DataProductContract.InteractorOutput {


    private var interactor: DataProductInteractor = DataProductInteractor(this)
    private var data:ProductPpob? = null
    private var pulsappobRestModel = PulsaPpobRestModel(context)


    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as ProductPpob
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }
        data?.let {
            view.setData(it.category,it.brand)
        }
        loadData()
    }

    override fun loadData() {
        val number = view.getValue()
        val category = view.getJenis()
        val brand = view.getBrand()
        interactor.callPrefixAPI(context,pulsappobRestModel, category, brand,number)
    }


    override fun onCheck(name: String) {
        if(name.isBlank() || name.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_category))
            return
        }

        return

    }

    override fun onSearch(search: String) {
        if(search.isNullOrBlank() || search.isNullOrEmpty()){
            view.showMessage(999,"Nomor harus diisi")
            return
        }
        val category = view.getJenis()
        val brand = view.getBrand()
        interactor.callPrefixAPI(context,pulsappobRestModel,category,brand,search)
    }
    override fun searchPrefix(search: String) {
        val category = view.getJenis()
        val brand = view.getBrand()
        if(search.isEmpty() || search.isBlank()){

        }else{
            interactor.callPrefixAPI(context,pulsappobRestModel, category, brand,search)
        }

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessPrefix(list: List<PulsaPpob>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

}