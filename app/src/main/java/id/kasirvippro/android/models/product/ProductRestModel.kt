package id.kasirvippro.android.models.product

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import id.kasirvippro.android.utils.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductRestModel(context: Context) : RestModel<ProductRestInterface>(context) {

    override fun createRestInterface(): ProductRestInterface {
        return RestClient.getInstance()!!.createInterface(ProductRestInterface::class.java)
    }

    fun gets(key:String, page:Int?): Observable<List<Product>> {
        return restInterface.gets(key,page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getsStock(key:String): Observable<List<Product>> {
        return restInterface.getsStock(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getsVariant(key:String, id_product:String): Observable<List<Product>> {
        return restInterface.getsVariant(key,id_product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun choose(key:String,trx:String,check:String, page:Int?): Observable<List<Product>> {
        return restInterface.choose(key,trx,check,page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun chooseVariable(key:String,id_product:String,check:String): Observable<List<Product>> {
        return restInterface.chooseVariable(key,id_product,check)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun chooseSearch(key:String,search:String,check:String): Observable<List<Product>> {
        return restInterface.chooseSearch(key,search,check)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun sort(key:String): Observable<List<Product>> {
        return restInterface.sort(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun add(key:String,name:String,unit:String,kode:String,idkategori:String,jual:String,beli:String,stok:String,minstok:String,
            img:String?,desk:String,online:String,haveStok:String,grosir:String,tax:String,alertstock:String): Observable<Message> {
        return restInterface.add(
            Helper.createPartFromString(key),
            Helper.createPartFromString(name),
            Helper.createPartFromString(unit),
            Helper.createPartFromString(kode),
            Helper.createPartFromString(idkategori),
            Helper.createPartFromString(beli),
            Helper.createPartFromString(jual),
            Helper.createPartFromString(stok),
            Helper.createPartFromString(minstok),
            Helper.createPartFromString(desk),
            Helper.createPartFromString(online),
            Helper.createPartFromString(haveStok),
            Helper.createPartFromString(grosir),
            Helper.createPartFromString(tax),
            Helper.createPartFromString(alertstock),
            Helper.createPartFromFile(img,"img"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addVariant(key:String,name:String,kode:String,id_product:String,jual:String,beli:String,stok:String,minstok:String,
            img:String?,desk:String,online:String,haveStok:String,grosir:String,tax:String,alertstock:String): Observable<Message> {
        return restInterface.addVariant(
            Helper.createPartFromString(key),
            Helper.createPartFromString(name),
            Helper.createPartFromString(kode),
            Helper.createPartFromString(id_product),
            Helper.createPartFromString(beli),
            Helper.createPartFromString(jual),
            Helper.createPartFromString(stok),
            Helper.createPartFromString(minstok),
            Helper.createPartFromString(desk),
            Helper.createPartFromString(online),
            Helper.createPartFromString(haveStok),
            Helper.createPartFromString(grosir),
            Helper.createPartFromString(tax),
            Helper.createPartFromString(alertstock),
            Helper.createPartFromFile(img,"img"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(key:String,id:String,name:String,unit:String,kode:String,idkategori:String,jual:String,beli:String,stok:String,minstok:String,
               img:String?,desk:String,online:String,haveStok:String,grosir:String,tax:String,alertstock:String): Observable<Message> {
        return restInterface.update(
            Helper.createPartFromString(key),
            Helper.createPartFromString(id),
            Helper.createPartFromString(name),
            Helper.createPartFromString(unit),
            Helper.createPartFromString(kode),
            Helper.createPartFromString(idkategori),
            Helper.createPartFromString(beli),
            Helper.createPartFromString(jual),
            Helper.createPartFromString(stok),
            Helper.createPartFromString(minstok),
            Helper.createPartFromString(desk),
            Helper.createPartFromString(online),
            Helper.createPartFromString(haveStok),
            Helper.createPartFromString(grosir),
            Helper.createPartFromString(tax),
            Helper.createPartFromString(alertstock),
            Helper.createPartFromFile(img,"img"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateStock(key:String,id:String,stok:String,reason:String): Observable<Message> {
        return restInterface.updateStock(
            Helper.createPartFromString(key),
            Helper.createPartFromString(id),
            Helper.createPartFromString(stok),
            Helper.createPartFromString(reason))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(key:String,id:String): Observable<Message> {
        return restInterface.delete(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun search(key:String,search:String): Observable<List<Product>> {
        return restInterface.search(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchStock(key:String,search:String): Observable<List<Product>> {
        return restInterface.searchStock(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun searchVariant(key:String,search:String): Observable<List<Product>> {
        return restInterface.searchVariant(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchByBarcode(key:String,barcode:String): Observable<List<Product>> {
        return restInterface.searchByBarcode(key,barcode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



}