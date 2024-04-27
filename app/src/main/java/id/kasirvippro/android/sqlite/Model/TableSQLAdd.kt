package id.kasirvippro.android.sqlite.Model
import java.io.Serializable

class TableSQLAdd (
    var increment : String,
    var key: String,
    var name_table: String,
    ) : Comparable<TableSQLAdd>, Serializable {
    override fun compareTo(other: TableSQLAdd): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is TableSQLAdd){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$increment: $name_table"
    }
}