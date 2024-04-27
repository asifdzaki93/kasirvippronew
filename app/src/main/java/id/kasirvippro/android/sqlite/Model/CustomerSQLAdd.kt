package id.kasirvippro.android.sqlite.Model
import java.io.Serializable

class CustomerSQLAdd (
    var increment : String,
    var key: String,
    var name_customer: String,
    var telephone: String,
    var email: String,
    var address: String,
    var customercode: String,
    ) : Comparable<CustomerSQLAdd>, Serializable {
    override fun compareTo(other: CustomerSQLAdd): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SupplierSQLAdd){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$increment: $name_customer"
    }
}