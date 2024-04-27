package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class DetailTransaction : Serializable {
    var struk: Struk? = null
    var data: List<Data>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Struk : Serializable {
        var name_store: String? = null
        var email: String? = null
        var nohp: String? = null
        var address: String? = null
        var no_invoice: String? = null
        var operator: String? = null
        var id_customer: String? = null
        var name_customer: String? = null
        var id_table: String? = null
        var name_table: String? = null
        var id_supplier: String? = null
        var name_supplier: String? = null
        var date: String? = null
        var payment: String? = null
        var totalorder: String? = "0"
        var totalpay: String? = "0"
        var totallast: String? = "0"
        var changepay: String? = null
        var status: String? = null
        var note: String? = null
        var ongkir: String? = null
        var divisi: String? = null
        var due_date: String? = null
        var service_charge: String? = "0"
        var discount: String? = "0"
        var tax: String? = "0"
        var footer: String? = ""
        var img: String? = null
        var paper: Int? = 42
        var link_order: String? = null

        fun json(): String {
            return Gson().toJson(this)
        }
        fun set(name_store: String,email: String,nohp: String,address: String,no_invoice: String,operator: String,id_customer: String,name_customer: String,id_supplier: String,name_supplier: String,date: String,payment: String,totalorder: String,totalpay: String,totallast: String,changepay: String,status: String,note: String,due_date: String,service_charge: String,discount: String,tax: String,footer: String, img: String, id_table: String){
            this.name_store = name_store
            this.email = email
            this.nohp = nohp
            this.address = address
            this.no_invoice = no_invoice
            this.operator = operator
            this.id_customer = id_customer
            this.name_customer = name_customer
            this.id_supplier = id_supplier
            this.name_supplier = name_supplier
            this.date = date
            this.payment = payment
            this.totalorder = totalorder
            this.totalpay = totalpay
            this.totallast = totallast
            this.changepay = changepay
            this.status = status
            this.note = note
            this.due_date = due_date
            this.service_charge = service_charge
            this.discount = discount
            this.tax = tax
            this.footer = footer
            this.img = img
            this.id_table = id_table
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Data : Serializable {
        var codeproduct: String? = null
        var name_product: String? = null
        var no_invoice: String? = null
        var id_product: String? = null
        var date: String? = null
        var amount: String? = null
        var price: String? = null
        var totalprice: String? = null
        var status: String? = null
        var have_stock: String? = null
        var stock: String? = null
        var img: String? = null
        var note: String? = null
        var unit: String? = null

        fun json(): String {
            return Gson().toJson(this)
        }
        fun set(codeproduct: String,name_product: String,date: String,amount: String,price: String,totalprice: String,status: String){
            this.codeproduct = codeproduct
            this.name_product = name_product
            this.date = date
            this.amount = amount
            this.price = price
            this.totalprice = totalprice
            this.status = status
        }
    }

    fun set(struk: Struk,data: List<Data>){
        this.struk = struk
        this.data = data
    }

    fun json(): String {
        return Gson().toJson(this)
    }
}
