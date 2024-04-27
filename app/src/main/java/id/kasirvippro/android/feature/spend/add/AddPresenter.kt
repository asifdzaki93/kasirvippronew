package id.kasirvippro.android.feature.spend.add

import android.annotation.SuppressLint
import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.transaction.RequestSpend

class AddPresenter(val context: Context, val view: AddContract.View) : BasePresenter<AddContract.View>(),
    AddContract.Presenter, AddContract.InteractorOutput {

    private var interactor = AddInteractor(this)

    override fun onViewCreated() {

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    @SuppressLint("DefaultLocale")
    override fun onCheck(name: String, buy: String) {
        if(name.isBlank() || name.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_product_name))
            return
        }

        if(buy.isBlank() || buy.isEmpty() || "0" == buy){
            view.showMessage(999,context.getString(R.string.err_empty_buy))
            return
        }

        val nominal = buy.replace(",","")
        val item = RequestSpend.Barang()
        item.id = name.toLowerCase().replace(" ","")
        item.name_spending = name
        item.nominal = nominal.toInt()

        view.onSuccess(item)

    }
}