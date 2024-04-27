package id.kasirvippro.android.sqlite.Model

import java.io.Serializable


class CategorySQLDelete (
    var increment: String,
    var key: String,
    var id: String
) : Comparable<CategorySQLDelete>, Serializable {
    override fun compareTo(other: CategorySQLDelete): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is CategorySQLDelete){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$increment"
    }
}