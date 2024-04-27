package id.kasirvippro.android.sqlite.Model
import java.io.Serializable

class CategorySQLAdd (
    var increment : String,
    var key: String,
    var name_category: String,
    ) : Comparable<CategorySQLAdd>, Serializable {
    override fun compareTo(other: CategorySQLAdd): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is CategorySQLAdd){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$increment: $name_category"
    }
}