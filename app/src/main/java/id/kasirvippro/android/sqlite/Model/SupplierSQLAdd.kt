package id.kasirvippro.android.sqlite.Model
import java.io.Serializable

class SupplierSQLAdd (
    var increment : String,
    var key: String,
    var name_supplier: String,
    var telephone: String,
    var email: String,
    var address: String
    ) : Comparable<SupplierSQLAdd>, Serializable {
    override fun compareTo(other: SupplierSQLAdd): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SupplierSQLAdd){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$increment: $name_supplier"
    }
}