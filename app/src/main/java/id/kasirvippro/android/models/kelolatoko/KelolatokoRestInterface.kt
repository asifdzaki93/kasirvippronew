package id.kasirvippro.android.models.kelolatoko

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface KelolatokoRestInterface {

    @GET("etalase/detail.php")
    fun gets(
        @Query("key") key: String): Observable<List<Kelolatoko>>

    @Multipart
    @POST("etalase/update.php")
    fun update(
        @Part("key") key: RequestBody,
        @Part("id") id: RequestBody,
        @Part("name_store") nama_toko: RequestBody,
        @Part("address_store") alamat_toko: RequestBody,
        @Part("color_store") warna_toko: RequestBody,
        @Part("operational_hour") jam_operasional: RequestBody,
        @Part("linkfb") linkfb: RequestBody,
        @Part("linkinstagram") linkinstagram: RequestBody,
        @Part("nowa") nowa: RequestBody,
        @Part("subdomain") subdomain: RequestBody,
        @Part img: MultipartBody.Part?): Observable<Message>
}