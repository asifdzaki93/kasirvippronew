package id.kasirvippro.android.sqlite.Model

import java.io.Serializable


class SupplierSQLDelete (
    var increment: String,
    var key: String,
    var id: String
) : Comparable<SupplierSQLDelete>, Serializable {
    override fun compareTo(other: SupplierSQLDelete): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SupplierSQLDelete){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$increment"
    }
}