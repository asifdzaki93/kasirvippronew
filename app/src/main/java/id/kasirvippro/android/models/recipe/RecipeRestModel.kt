package id.kasirvippro.android.models.recipe

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecipeRestModel(context: Context) : RestModel<RecipeRestInterface>(context) {

    override fun createRestInterface(): RecipeRestInterface {
        return RestClient.getInstance()!!.createInterface(RecipeRestInterface::class.java)
    }

    fun gets(key:String, id_product:String): Observable<List<Recipe>> {
        return restInterface.gets(key,id_product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun add(key:String,stock:String,id_raw_material:String, id_product: String): Observable<Message> {
        return restInterface.add(key, stock,id_raw_material, id_product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(key:String,id:String,stock:String): Observable<Message> {
        return restInterface.update(key,id,stock)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(key:String,id:String): Observable<Message> {
        return restInterface.delete(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}