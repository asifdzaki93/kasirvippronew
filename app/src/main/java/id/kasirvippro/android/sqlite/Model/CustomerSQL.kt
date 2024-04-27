package id.kasirvippro.android.sqlite.Model

import java.io.Serializable

class CustomerSQL (
    var increment : String,
    var key: String,
    var id_customer : String,
    var name_customer: String,
    var telephone: String,
    var email: String,
    var address: String,
    var customercode: String,
) : Comparable<CustomerSQL>, Serializable {
    override fun compareTo(other: CustomerSQL): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is CustomerSQL) {
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_customer: $name_customer"
    }
}