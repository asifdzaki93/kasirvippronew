package id.kasirvippro.android.models.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Login: Serializable {
    constructor(
        key: String,
        currency: String,
        user: String,
        level: String,
        master: String,
        packages: String,
        typestore: String,
        decimal: String,
        id_store: String
    ){
        this.key = key;
        this.currency = currency;
        this.user = user;
        this.level = level;
        this.master = master;
        this.packages = packages;
        this.typestore = typestore;
        this.decimal = typestore;
        this.id_store = id_store;
    }

    var key: String? = null
    var currency: String? = null
    var user: String? = null
    var level: String? = ""
    var master: String? = ""
    var packages: String? = "0"
    var typestore: String? = "0"
    var decimal: String? = "0"
    var id_store: String? = "0"



    fun json(): String {
        return Gson().toJson(this)
    }
}
