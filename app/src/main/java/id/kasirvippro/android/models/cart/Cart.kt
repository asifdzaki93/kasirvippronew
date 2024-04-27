package id.kasirvippro.android.models.cart

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.addOn.AddOn
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class Cart : Serializable {
    var position:Int? = -1
    var count:Double? = 0.0
    var note:String? = ""
    var new_price:String? = "0"
    var nominal_addon:String? = "0"
    var ongkir:String? = "0"
    var product:Product? = null
    var addon:AddOn? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}