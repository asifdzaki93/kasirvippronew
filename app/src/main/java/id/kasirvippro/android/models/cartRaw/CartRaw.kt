package id.kasirvippro.android.models.cartRaw

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class CartRaw : Serializable {
    var position:Int? = -1
    var count:Double? = 0.0
    var note:String? = ""
    var new_price:String? = "0"
    var product:RawMaterial? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}