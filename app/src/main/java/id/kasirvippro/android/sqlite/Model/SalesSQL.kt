package id.kasirvippro.android.sqlite.Model

import java.io.Serializable

class SalesSQL(
    var increment: String,
    var uuid_salesData: String,
    var id_sales: String,
    var id_customer: String,
    var id_product: String,
    var codeproduct: String,
    var name_product: String,
    var id_store: String,
    var user: String,
    var no_invoice: String,
    var amount: String,
    var price: String,
    var totalprice: String,
    var hpp: String,
    var totalcapital: String,
    var date: String,
    var status: String,
    var note: String,
    var rest_stock: String,
    var sift: String,
    var station: String,
) : Comparable<SalesSQL>, Serializable {
    override fun compareTo(other: SalesSQL): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SalesSQL) {
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_store: $totalprice"
    }
}