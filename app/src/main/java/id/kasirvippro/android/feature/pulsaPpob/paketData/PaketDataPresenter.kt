package id.kasirvippro.android.feature.pulsaPpob.paketData

import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.pulsaPpob.PulsaPpob
import id.kasirvippro.android.models.pulsaPpob.PulsaPpobRestModel

class PaketDataPresenter(val context: Context, val view: PaketDataContract.View) : BasePresenter<PaketDataContract.View>(),
    PaketDataContract.Presenter, PaketDataContract.InteractorOutput {


    private var interactor: PaketDataInteractor = PaketDataInteractor(this)
    private var pulsappobRestModel = PulsaPpobRestModel(context)


    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        val number = view.getValue()
        interactor.callPrefixAPI(context,pulsappobRestModel,"Data",number)
    }


    override fun onCheck(name: String) {
        if(name.isNullOrBlank() || name.isNullOrEmpty()){
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
        interactor.callPrefixAPI(context,pulsappobRestModel,"Data",search)
    }

    override fun searchPrefix(search: String) {
        if(search.isEmpty() || search.isBlank()){

        }else{
            interactor.callPrefixAPI(context,pulsappobRestModel,"Data",search)
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