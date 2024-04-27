package id.kasirvippro.android.models.currency

import android.content.Context
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CurrencyRestModel(context: Context) : RestModel<CurrencyRestInterface>(context) {

    override fun createRestInterface(): CurrencyRestInterface {
        return RestClient.getInstance()!!.createInterface(CurrencyRestInterface::class.java)
    }

    fun getCurrencies(): Observable<List<Currency>> {
        return restInterface.getCurrencies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDecimal(): Observable<List<Decimal>> {
        return restInterface.getDecimal()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}