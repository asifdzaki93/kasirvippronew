package id.kasirvippro.android.sqlite.Model

import java.io.Serializable

class StoreSQL (
    var increment : String,
    var id_store : String,
    var type: String,
    var name_store: String,
    var nohp: String,
    var address: String,
    var email: String,
    var omset: String,
    var transaksi: String,
    var order: String,
    var tax: String,
    var service_charge: String,
    var number_store: String,
    var level: String,
    var footer: String,
    var photo: String,
) : Comparable<StoreSQL>, Serializable {
    override fun compareTo(other: StoreSQL): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is StoreSQL) {
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_store: $name_store"
    }
}