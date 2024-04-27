package id.kasirvippro.android.sqlite.Model

import java.io.Serializable

class DetailPaymentSQL (
    var increment : String,
    var totalprice: String,
    var totalpay: String,
    var discount: String, //
    var tax: String,
    var service_charge: String,
    var id_discount: String, //
    var name_discount: String, //
) : Comparable<DetailPaymentSQL>, Serializable {
    override fun compareTo(other: DetailPaymentSQL): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is DetailPaymentSQL) {
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$totalprice: $id_discount"
    }
}