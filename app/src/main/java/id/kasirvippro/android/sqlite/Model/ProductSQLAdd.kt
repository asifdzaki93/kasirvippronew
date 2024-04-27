package id.kasirvippro.android.sqlite.Model
import java.io.Serializable

class ProductSQLAdd (
    var increment : String,
    var key: String,
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
    ) : Comparable<ProductSQLAdd>, Serializable {
    override fun compareTo(other: ProductSQLAdd): Int {
        return kode.compareTo(other.kode)
    }

    override fun equals(other: Any?): Boolean {
        if (other is ProductSQLAdd){
            return kode == other.kode
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "$kode: $nama"
    }
}