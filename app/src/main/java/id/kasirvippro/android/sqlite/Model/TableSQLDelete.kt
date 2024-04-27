package id.kasirvippro.android.sqlite.Model

import java.io.Serializable


class TableSQLDelete (
    var increment: String,
    var key: String,
    var id: String
) : Comparable<TableSQLDelete>, Serializable {
    override fun compareTo(other: TableSQLDelete): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is TableSQLDelete){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$increment"
    }
}