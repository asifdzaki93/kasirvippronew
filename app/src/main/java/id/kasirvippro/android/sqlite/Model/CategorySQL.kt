package id.kasirvippro.android.sqlite.Model

import java.io.Serializable

class CategorySQL (
    var increment : String,
    var key: String,
    var id_category: String,
    var name_category: String
    ) : Comparable<CategorySQL>, Serializable {
    override fun compareTo(other: CategorySQL): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is CategorySQL){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_category: $name_category"
    }
}