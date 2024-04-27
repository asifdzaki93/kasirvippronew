package id.kasirvippro.android.sqlite.Model

import java.io.Serializable

class UserSQL (
    var key: String,
    var currency: String,
    var name: String,
    var user: String,
    var level: String,
    var master: String,
    var packages: String,
    var typestore: String,
    var decimal: String,
    var id_store: String,
    var phone: String,
    var password: String,
    var latitude: String,
    var longitude: String) : Comparable<UserSQL>, Serializable {
    override fun compareTo(other: UserSQL): Int {
        return phone.compareTo(other.phone)
    }

    override fun equals(other: Any?): Boolean {
        if (other is UserSQL){
            return phone == other.phone
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$phone: $id_store"
    }
}