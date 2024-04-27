package id.kasirvippro.android.sqlite.Model

import java.io.Serializable


class CategorySQLSearch (
    var key: String,
    var search: String
) : Comparable<CategorySQLSearch>, Serializable {
    override fun compareTo(other: CategorySQLSearch): Int {
        return key.compareTo(other.key)
    }

    override fun equals(other: Any?): Boolean {
        if (other is CategorySQLSearch){
            return key == other.key
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$key"
    }
}