package id.kasirvippro.android.models.report

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class ReportStock : Serializable {
    var id_barang : String? = null
    var nama_barang : String? = "0"
    var tanggal_awal : String? = null
    var tanggal_akhir : String? = "0"
    var terjual : String? = null
    var stok_terakhir : String? = "0"
    var minimal_stok : String? = null
    var datastok: List<Detail>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Detail : Serializable {
        var sisa_stok: String? = "0"
        var minimal_stok: String? = "0"
        var tanggal: String? = null

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    fun json(): String {
        return Gson().toJson(this)
    }
}
