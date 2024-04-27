package id.kasirvippro.android.models.typestore

import io.reactivex.Observable
import retrofit2.http.*

interface TypeStoreRestInterface {

    @GET("typestore/list.php")
    fun geTypestore(): Observable<List<TypeStore>>
}