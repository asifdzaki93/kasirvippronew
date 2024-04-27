package id.kasirvippro.android.models

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import com.prolificinteractive.materialcalendarview.CalendarDay

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class FilterDialogDate() : Parcelable {
    var id: Int? = 0
    var firstDate: CalendarDay? = null
    var lastDate: CalendarDay? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        firstDate = parcel.readParcelable(CalendarDay::class.java.classLoader)
        lastDate = parcel.readParcelable(CalendarDay::class.java.classLoader)
    }

    fun json(): String {
        return Gson().toJson(this)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeParcelable(firstDate, flags)
        parcel.writeParcelable(lastDate, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterDialogDate> {
        override fun createFromParcel(parcel: Parcel): FilterDialogDate {
            return FilterDialogDate(parcel)
        }

        override fun newArray(size: Int): Array<FilterDialogDate?> {
            return arrayOfNulls(size)
        }
    }
}
