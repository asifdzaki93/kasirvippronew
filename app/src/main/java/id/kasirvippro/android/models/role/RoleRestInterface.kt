package id.kasirvippro.android.models.role

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import retrofit2.http.*

interface RoleRestInterface {

    @GET("role/list.php")
    fun getRole(
        @Query("key") key:String): Observable<List<Role>>

    @GET("role/listuser.php")
    fun getRoleUser(
        @Query("key") key:String): Observable<List<Role>>


    @GET("role/itemrole.php")
    fun getDetailRole(
        @Query("key") key:String,
        @Query("id_role") id:String): Observable<List<Role>>

    @FormUrlEncoded
    @POST("role/update.php")
    fun updateRole(
        @Field("key") key: String,
        @Field("value") value: String,
        @Field("id") id: String,
        @Field("name") name: String): Observable<Message>

}