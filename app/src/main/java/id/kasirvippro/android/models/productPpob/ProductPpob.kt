package id.kasirvippro.android.models.productPpob

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class ProductPpob : Serializable {
    var id_ppob: String? = ""
    var nama_ppob: String? = ""
    var detail: String? = ""
    var jenis: String? = ""
    var brand: String? = ""
    var category: String? = ""
    var gbr: String? = ""
    var kode_produk: String? = ""

    fun json(): String {
        return Gson().toJson(this)
    }

}
