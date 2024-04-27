package id.kasirvippro.android.sqlite.Model
import java.io.Serializable

class DiscountSQLAdd (
    var increment : String,
    var key: String,
    var name_discount: String,
    var note: String,
    var type: String,
    var nominal: String,
    ) : Comparable<DiscountSQLAdd>, Serializable {
    override fun compareTo(other: DiscountSQLAdd): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is DiscountSQLAdd){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$increment: $name_discount"
    }
}