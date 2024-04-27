package id.kasirvippro.android.sqlite.Model

import java.io.Serializable

class LinkSQL (
    var increment : String,
    var key: String,
    var id_link : String,
    var name_link: String,
    var img: String,
) : Comparable<LinkSQL>, Serializable {
    override fun compareTo(other: LinkSQL): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is LinkSQL) {
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$id_link: $name_link"
    }
}