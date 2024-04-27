package id.kasirvippro.android.sqlite.Model

import java.io.Serializable

class SalesDataSQL (
    var increment : String,
    var uuid : String,
    var id_sales_data : String,
    var user : String,
    var id_customer: String,
    var name_customer: String,
    var id_store: String,
    var name_store: String,
    var email_store: String,
    var nohp_store: String,
    var address_store: String,
    var id_supplier: String,
    var name_supplier: String,
    var no_invoice: String,
    var date: String,
    var payment: String,
    var note: String,
    var totalorder: String,
    var totalprice: String,
    var totalpay: String,
    var changepay: String,
    var status: String,
    var due_date: String,
    var tax: String,
    var discount: String,
    var service_charge: String,
    var operator: String,
    var location: String,
    var total_sales_hpp: String,
    var sift: String,
    var station: String,
    var footer: String,
    var img: String,
    var id_table: String,
) : Comparable<SalesDataSQL>, Serializable {
    override fun compareTo(other: SalesDataSQL): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SalesDataSQL) {
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_store: $totalprice"
    }
}