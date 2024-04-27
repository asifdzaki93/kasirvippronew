package id.kasirvippro.android.models.product

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import id.kasirvippro.android.models.priceVariant.PriceVariant
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Product : Serializable {
    var id_product: String? = null
    var name_product: String? = ""
    var unit: String? = ""
    var img: String? = ""
    var codeproduct: String? = null
    var id_category: String? = null
    var name_category: String? = null
    var active: Boolean? = false
    var selling_price: String? = "0"
    var purchase_price: String? = "0"
    var stock: String? = "0"
    var minimalstock: String? = "0"
    var description: String? = "-"
    var discount: String? = null
    var posisi: Boolean? = false
    var online: String? = "0"
    var have_stock: String? = "0"
    var wholesale_price: String? = "0"
    var tax: String? = "0"
    var inc: String? = "0"
    var id_master: String? = "0"
    var alertstock: String? = "0"
    var packages: String? = "NO"
    var pricevariant: List<PriceVariant>? = null

    fun json(): String {
        return Gson().toJson(this)
    }

    fun set(id_product : String,nama : String,unit : String,img : String, kode : String,
            idkategori: String, namakategori : String, beli : String, jual : String,
            stok : String, minStok : String, deskripsi : String, online_2 : String, haveStok : String, grosir : String,
            increment : String,alertstock : String){
        this.id_product = id_product
        this.name_product = nama
        this.unit = unit
        this.img = img
        this.codeproduct = kode
        this.id_category = idkategori
        this.name_category = namakategori
        this.active = active
        this.selling_price = jual
        this.purchase_price= beli
        this.stock = stok
        this.minimalstock = minStok
        this.description = deskripsi
        this.online = online_2
        this.have_stock = haveStok
        this.wholesale_price = grosir
        this.inc = increment
        this.alertstock = alertstock
    }
}
