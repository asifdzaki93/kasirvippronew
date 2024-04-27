package id.kasirvippro.android.sqlite.Model
import java.io.Serializable

class CustomerSQLUpdate (
    var increment: String,
    var key: String,
    var id_customer: String,
    var name_customer: String,
    var telephone: String,
    var email: String,
    var address: String,
    var customercode: String
    ) : Comparable<CustomerSQLUpdate>, Serializable {
    override fun compareTo(other: CustomerSQLUpdate): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SupplierSQLUpdate){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_customer: $name_customer"
    }
}