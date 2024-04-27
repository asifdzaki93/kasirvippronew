package id.kasirvippro.android.sqlite.Model

import java.io.Serializable

class SupplierSQL (
    var increment : String,
    var key: String,
    var id_supplier : String,
    var name_supplier: String,
    var telephone: String,
    var email: String,
    var address: String,
) : Comparable<SupplierSQL>, Serializable {
    override fun compareTo(other: SupplierSQL): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SupplierSQL) {
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_supplier: $name_supplier"
    }
}