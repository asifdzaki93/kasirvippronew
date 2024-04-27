package id.kasirvippro.android.models.news

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class News : Serializable {
    var id_news: Int? = 0
    var title: String? = ""
    var detail: String? = ""
    var desc: String? = ""
    var img: String? = ""

    fun json(): String {
        return Gson().toJson(this)
    }

}
