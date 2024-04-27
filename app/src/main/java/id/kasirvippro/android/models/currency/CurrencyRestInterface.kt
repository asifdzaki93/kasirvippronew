package id.kasirvippro.android.models.currency

import io.reactivex.Observable
import retrofit2.http.*

interface CurrencyRestInterface {

    @GET("currency/list.php")
    fun getCurrencies(): Observable<List<Currency>>

    @GET("currency/listdecimal.php")
    fun getDecimal(): Observable<List<Decimal>>
}