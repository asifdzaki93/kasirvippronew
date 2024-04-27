package id.kasirvippro.android.models

import androidx.fragment.app.Fragment
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class TabModel : Serializable {
    var title: String? = null
    var fragment: Fragment? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}
