package id.kasirvippro.android.sqlite.Model
import java.io.Serializable

class CategorySQLUpdate (
    var increment: String,
    var key: String,
    var id_category: String,
    var name_category: String,
    ) : Comparable<CategorySQLUpdate>, Serializable {
    override fun compareTo(other: CategorySQLUpdate): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is CategorySQLUpdate){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_category: $name_category"
    }
}