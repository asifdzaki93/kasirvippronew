package id.kasirvippro.android.models.cart

import id.kasirvippro.android.models.product.Product
import io.reactivex.Observable
import retrofit2.http.*

interface CartRestInterface {

    @FormUrlEncoded
    @POST("sales/insert.php")
    fun add(
        @Field("key") key: String,
        @Field("name_product") nama: String,
        @Field("purchase_price") hargabeli: String,
        @Field("selling_price") hargajual: String): Observable<List<Product>>


    @FormUrlEncoded
    @POST("sales/insert.php")
    fun addWithBarcode(
        @Field("key") key: String,
        @Field("name_product") nama: String,
        @Field("codeproduct") kodebarang: String,
        @Field("purchase_price") hargabeli: String,
        @Field("selling_price") hargajual: String): Observable<List<Product>>

    @FormUrlEncoded
    @POST("sales/update.php")
    fun update(
        @Field("key") key: String,
        @Field("id_name_product") id: String,
        @Field("name_product") nama: String,
        @Field("codeproduct") kodebarang: String,
        @Field("purchase_price") hargabeli: String,
        @Field("selling_price") hargajual: String,
        @Field("stock") stok: String): Observable<List<Product>>

}