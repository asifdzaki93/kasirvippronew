package id.kasirvippro.android.models.hutangpiutang

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class DetailPiutang : Serializable {
    var datadebt: Piutang? = null
    var sudah_bayar: List<Data>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Piutang : Serializable {
        var total_tagihan: String? = null
        var jumlah_piutang: String? = null
        var jatuh_tempo: String? = null
        var total_dibayar: String? = null

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Data : Serializable {
        var id_customer: String? = null
        var name_customer: String? = null
        var date: String? = null
        var no_invoice: String? = null
        var nominal: String? = null

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    fun json(): String {
        return Gson().toJson(this)
    }
}
