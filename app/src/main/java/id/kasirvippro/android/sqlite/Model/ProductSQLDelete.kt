package id.kasirvippro.android.sqlite.Model

import java.io.Serializable


class ProductSQLDelete (
    var increment: String,
    var key: String,
    var id_product: String
) : Comparable<ProductSQLDelete>, Serializable {
    override fun compareTo(other: ProductSQLDelete): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is ProductSQLDelete){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$increment"
    }
}