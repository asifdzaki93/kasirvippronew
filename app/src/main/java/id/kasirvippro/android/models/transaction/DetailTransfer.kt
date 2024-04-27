package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class DetailTransfer : Serializable {
    var struk: Struk? = null
    var data: List<Data>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Struk : Serializable {
        var name_store: String? = null
        var email: String? = null
        var nohp: String? = null
        var address: String? = null
        var no_invoice: String? = null
        var operator: String? = null
        var date: String? = null
        var payment: String? = null
        var totalorder: String? = "0"
        var totalpay: String? = "0"
        var totallast: String? = "0"
        var changepay: String? = null
        var status: String? = null
        var note: String? = null
        var img: String? = null
        var store_destination: String? = null
        var paper: Int? = 42

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Data : Serializable {
        var codeproduct: String? = null
        var name_product: String? = null
        var no_invoice: String? = null
        var id_product: String? = null
        var date: String? = null
        var amount: String? = null
        var price: String? = null
        var totalprice: String? = null
        var status: String? = null
        var have_stock: String? = null
        var stock: String? = null
        var img: String? = null

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    fun set(struk: Struk,data: List<Data>){
        this.struk = struk
        this.data = data
    }

    fun json(): String {
        return Gson().toJson(this)
    }
}
