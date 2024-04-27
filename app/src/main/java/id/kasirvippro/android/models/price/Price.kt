package id.kasirvippro.android.models.price

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import id.kasirvippro.android.models.addOn.AddOn
import java.io.Serializable
import id.kasirvippro.android.models.priceVariant.PriceVariant


/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Price : Serializable {
    var id_price: String? = null
    var name_price: String? = ""
    var nominal: String? = ""

    fun json(): String {
        return Gson().toJson(this)
    }
}
