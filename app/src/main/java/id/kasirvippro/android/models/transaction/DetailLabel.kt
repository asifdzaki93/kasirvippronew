package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class DetailLabel : Serializable {
    var struk: Struk? = null
    var data: List<Data>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Struk : Serializable {
        var no_invoice: String? = null

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Data : Serializable {
        var name_store: String? = null
        var codeproduct: String? = null
        var name_product: String? = null
        var no_invoice: String? = null
        var id_product: String? = null
        var price: String? = null
        var date: String? = null
        var amount: String? = null
        var img: String? = null
        var description: String? = null

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
