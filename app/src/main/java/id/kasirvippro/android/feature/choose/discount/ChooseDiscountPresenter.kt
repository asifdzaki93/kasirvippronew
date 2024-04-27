package id.kasirvippro.android.feature.choose.discount

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.discount.DiscountRestModel

class ChooseDiscountPresenter(val context: Context, val view: ChooseDiscountContract.View) : BasePresenter<ChooseDiscountContract.View>(),
    ChooseDiscountContract.Presenter, ChooseDiscountContract.InteractorOutput {

    private var interactor = ChooseDiscountInteractor(this)
    private var restModel = DiscountRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    fun setDiscount(list: List<Discount>){
        view.setData(list)
    }

    override fun loadData() {
        interactor.callGetsAPI(context,restModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGets(list: List<Discount>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}