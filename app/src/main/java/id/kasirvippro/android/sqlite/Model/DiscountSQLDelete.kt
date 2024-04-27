package id.kasirvippro.android.sqlite.Model

import java.io.Serializable


class DiscountSQLDelete (
    var increment: String,
    var key: String,
    var id: String
) : Comparable<DiscountSQLDelete>, Serializable {
    override fun compareTo(other: DiscountSQLDelete): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is DiscountSQLDelete){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$increment"
    }
}