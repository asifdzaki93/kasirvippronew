package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class DetailSpend : Serializable {
    var info: Struk? = null
    var data: List<Data>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Struk : Serializable {
        var oleh: String? = null
        var no_invoice: String? = null
        var date: String? = null
        var totalnominal: String? = "0"
        var img: String? = "0"

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Data : Serializable {
        var name_spending: String? = null
        var nominal: String? = "0"

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    fun json(): String {
        return Gson().toJson(this)
    }
}
