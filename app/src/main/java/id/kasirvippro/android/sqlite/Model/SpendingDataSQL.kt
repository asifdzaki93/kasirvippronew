package id.kasirvippro.android.sqlite.Model

import java.io.Serializable

class SpendingDataSQL (
    var increment : String,
    var id_spending_data : String,
    var user : String,
    var id_store: String,
    var no_invoice: String,
    var date: String,
    var totalnominal: String,
    var operator: String,
) : Comparable<SpendingDataSQL>, Serializable {
    override fun compareTo(other: SpendingDataSQL): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SpendingDataSQL) {
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_store: $totalnominal"
    }
}