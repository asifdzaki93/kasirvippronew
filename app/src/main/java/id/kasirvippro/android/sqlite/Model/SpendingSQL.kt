package id.kasirvippro.android.sqlite.Model

import java.io.Serializable

class SpendingSQL(
    var increment: String,
    var id_spending: String,
    var name_spending: String,
    var user: String,
    var no_invoice: String,
    var nominal: String,
    var date: String,
) : Comparable<SpendingSQL>, Serializable {
    override fun compareTo(other: SpendingSQL): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SpendingSQL) {
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_spending: $nominal"
    }
}