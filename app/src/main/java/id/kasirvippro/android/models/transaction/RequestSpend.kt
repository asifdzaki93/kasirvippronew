package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class RequestSpend : Serializable {
    var key: String ?= null
    var amount: Double? = 0.0
    var date: String? = null
    var img: String? = null
    var spending: List<Barang>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Barang : Serializable {
        var id: String? = null
        var name_spending: String? = null
        var nominal: Int? = 0

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    fun json(): String {
        return Gson().toJson(this)
    }
}
