package id.kasirvippro.android.sqlite.Model
import java.io.Serializable

class ProductSQLUpdate (
    var increment: String,
    var key: String,
    var id_product: String,
    var nama: String,
    var unit: String,
    var kode: String,
    var idkategori: String,
    var beli: String,
    var jual: String,
    var stok: String,
    var minstok: String,
    var deskripsi: String,
    var online: String,
    var havestok: String,
    var grosir: String,
    var tax: String,
    var alertstock: String,
    var img: String
    ) : Comparable<ProductSQLUpdate>, Serializable {
    override fun compareTo(other: ProductSQLUpdate): Int {
        return increment.compareTo(other.increment)
    }

    override fun equals(other: Any?): Boolean {
        if (other is ProductSQLUpdate){
            return increment == other.increment
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$kode: $nama"
    }
}