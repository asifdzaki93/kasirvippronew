package id.kasirvippro.android.sqlite.Model
import java.io.Serializable

class DiscountSQLUpdate (
    var increment: String,
    var key: String,
    var id_discount: String,
    var name_discount: String,
    var note: String,
    var type: String,
    var nominal: String,
    ) : Comparable<DiscountSQLUpdate>, Serializable {
    override fun compareTo(other: DiscountSQLUpdate): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is DiscountSQLUpdate){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_discount: $name_discount"
    }
}