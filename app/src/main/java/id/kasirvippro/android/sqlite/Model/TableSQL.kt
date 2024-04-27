package id.kasirvippro.android.sqlite.Model

import java.io.Serializable

class TableSQL (
    var increment : String,
    var key: String,
    var id_table : String,
    var name_table: String,
) : Comparable<TableSQL>, Serializable {
    override fun compareTo(other: TableSQL): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is TableSQL) {
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_table: $name_table"
    }
}