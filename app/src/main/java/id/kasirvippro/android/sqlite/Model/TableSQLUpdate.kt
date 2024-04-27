package id.kasirvippro.android.sqlite.Model
import java.io.Serializable

class TableSQLUpdate (
    var increment: String,
    var key: String,
    var id_table: String,
    var name_table: String,
    ) : Comparable<TableSQLUpdate>, Serializable {
    override fun compareTo(other: TableSQLUpdate): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is TableSQLUpdate){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_table: $name_table"
    }
}