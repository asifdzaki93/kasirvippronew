package id.kasirvippro.android.models.transaction

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface TransactionRestInterface {

    @GET("customer/transaction.php")
    fun getCustomerTransactions(
        @Query("key") key:String,
        @Query("id_customer") id:String): Observable<List<Transaction>>

    @GET("customer/debt.php")
    fun getCustomerDebts(
        @Query("key") key:String,
        @Query("id_customer") id:String): Observable<List<Transaction>>

    @GET("supplier/transaction.php")
    fun getSupplierTransactions(
        @Query("key") key:String,
        @Query("id_supplier") id:String): Observable<List<Transaction>>

    @GET("supplier/debt.php")
    fun getSupplierDebts(
        @Query("key") key:String,
        @Query("id_supplier") id:String): Observable<List<Transaction>>

    @GET("transaction/struck.php")
    fun getDetailTransaction(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<DetailTransaction>

    @GET("transaction/strucktransfer.php")
    fun getDetailTransfer(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<DetailTransfer>

    @GET("transaction/struckreturn.php")
    fun getDetailTransactionReturn(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<DetailTransaction>

    @GET("transaction/struckreturntransfer.php")
    fun getDetailTransferReturn(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<DetailTransaction>

    @GET("transaction/detaillabel.php")
    fun getDetailLabel(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<DetailLabel>

    @GET("transaction/strucksupplier.php")
    fun getDetailTransactionSupplier(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<DetailTransaction>

    @GET("transaction/strucksupplierreturn.php")
    fun getDetailTransactionReturnSupplier(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<DetailTransaction>

    @GET("transaction/cancelorder.php")
    fun deleteDetailTransaction(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<Message>

    @POST("sales/order.php")
    fun order(@Body request: RequestTransaction) : Observable<Order>

    @POST("sales/orderreturn.php")
    fun orderReturn(@Body request: RequestTransaction) : Observable<Order>

    @POST("label/insert.php")
    fun printLabel(@Body request: RequestTransaction) : Observable<Order>

    @Multipart
    @POST("sales/orderdirect.php")
    fun orderDirect(
        @Part("data") data: RequestTransaction,
        @Part img: MultipartBody.Part?) : Observable<Message>

    @Multipart
    @POST("sales/opdatedirect.php")
    fun updateDirect(
        @Part("data") data: RequestTransaction) : Observable<Message>

    @POST("purchase/order.php")
    fun kulakan(@Body request: RequestKulakan) : Observable<Order>

    @POST("spending/order.php")
    fun orderraw(@Body request: RequestRawMaterial) : Observable<Order>

    @POST("purchase/transfer.php")
    fun transfer(@Body request: RequestTransfer) : Observable<Order>

    @POST("product/packages.php")
    fun packages(@Body request: RequestPackages) : Observable<Order>

    @POST("product/addpackages.php")
    fun addpackages(@Body request: RequestPackages) : Observable<Order>

    @POST("sales/recipe.php")
    fun recipe(@Body request: RequestTransaction) : Observable<Order>

    @POST("spending/insert.php")
    fun pengeluaran(@Body request: RequestSpend) : Observable<Message>

    @GET("history/completetransactionlist.php")
    fun historyTransaction(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String,
        @Query("status") status:String): Observable<List<HistoryTransaction>>

    @GET("history/historyjob.php")
    fun HistoryJob(
        @Query("key") key:String,
        @Query("operator") operator:String): Observable<List<DetailJob>>

    @GET("transaction/transferout.php")
    fun getTransferOut(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String,
        @Query("status") status:String): Observable<List<HistoryTransfer>>

    @GET("transaction/transferin.php")
    fun getTransferIn(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String,
        @Query("status") status:String): Observable<List<HistoryTransfer>>

    @GET("history/completetransactionlistreturn.php")
    fun historyTransactionReturn(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String,
        @Query("status") status:String): Observable<List<HistoryTransaction>>

    @GET("history/completetransferlistreturn.php")
    fun historyTransferReturn(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String,
        @Query("status") status:String): Observable<List<HistoryTransaction>>

    @GET("network/commision.php")
    fun historyComission(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String,
        @Query("status") status:String): Observable<List<Comission>>


    @FormUrlEncoded
    @POST("sales/salesdata.php")
    fun addSalesData(
        @Field("user") user: String,
        @Field("no_invoice") no_invoice: String,
        @Field("id_customer") id_customer: String,
        @Field("id_store") id_store: String,
        @Field("payment") payment: String,
        @Field("totalorder") totalorder: String,
        @Field("totalprice") totalprice: String,
        @Field("totalpay") totalpay: String,
        @Field("changepay") changepay: String,
        @Field("status") status: String,
        @Field("due_date") due_date: String,
        @Field("tax") tax: String,
        @Field("discount") discount: String,
        @Field("service_charge") service_charge: String,
        @Field("operator") operator: String,
        @Field("location") location: String,
        @Field("note") note: String,
        @Field("date") date: String): Observable<Message>


    @FormUrlEncoded
    @POST("spending/spendingdata.php")
    fun addSpendingData(
        @Field("user") user: String,
        @Field("id_store") id_store: String,
        @Field("no_invoice") payment: String,
        @Field("date") totalprice: String,
        @Field("totalnominal") totalpay: String): Observable<Message>

    @FormUrlEncoded
    @POST("spending/spending.php")
    fun addSpending(
        @Field("name_spending") user: String,
        @Field("user") id_store: String,
        @Field("no_invoice") payment: String,
        @Field("nominal") totalprice: String,
        @Field("date") totalpay: String): Observable<Message>


    @FormUrlEncoded
    @POST("sales/sales.php")
    fun addSales(
        @Field("user") user: String,
        @Field("no_invoice") no_invoice: String,
        @Field("id_customer") id_customer: String,
        @Field("id_store") id_store: String,
        @Field("id_product") payment: String,
        @Field("amount") totalorder: String,
        @Field("totalprice") totalprice: String,
        @Field("price") totalpay: String,
        @Field("status") status: String,
        @Field("note") note: String,
        @Field("date") date: String): Observable<Message>


    @FormUrlEncoded
    @POST("transaction/paydebtcustomers.php")
    fun payPiutang(
        @Field("key") key:String,
        @Field("no_invoice") id:String,
        @Field("totalpay") bayar:String): Observable<Message>

    @FormUrlEncoded
    @POST("transaction/paydebtsupplier.php")
    fun payHutang(
        @Field("key") key:String,
        @Field("no_invoice") id:String,
        @Field("totalpay") bayar:String): Observable<Message>

    @GET("history/searchtransaction.php")
    fun searchTransaction(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Transaction>>

    @GET("history/searchpreorder.php")
    fun searchPreorder(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Transaction>>

    @GET("history/searchtransferin.php")
    fun searchTransactionIn(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Transfer>>

    @GET("history/searchtransferout.php")
    fun searchTransactionOut(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Transfer>>

    @GET("history/searchtransactionreturn.php")
    fun searchTransactionReturn(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Transaction>>

    @GET("history/searchtransferreturn.php")
    fun searchTransferReturn(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Transaction>>

    @GET("transaction/cancelordersupplier.php")
    fun deleteSupplierDetailTransaction(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<Message>

    @GET("sales/detailprice.php")
    fun checkPayment(
        @Query("key") key:String,
        @Query("totalprice") totalharga:Double,
        @Query("id_discount") id_discount:String?): Observable<List<DetailPayment>>

    @GET("spending/history.php")
    fun historySpend(
        @Query("key") key:String,
        @Query("start_date") awal:String,
        @Query("finish_date") akhir:String): Observable<List<HistoryTransaction>>

    @GET("history/searchexpenses.php")
    fun searchSpend(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Transaction>>

    @GET("spending/detail.php")
    fun getDetailSpend(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<DetailSpend>

    @GET("sales/link.php")
    fun getNonTunai(
        @Query("key") key:String): Observable<List<NonTunai>>

    @POST("sales/addorder.php")
    fun addorder(@Body request: RequestAddOrder) : Observable<Order>

    @GET("transaction/deleteorder.php")
    fun deleteAddTransaction(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<Message>

    @GET("sales/deleteproduct.php")
    fun deleteProductItem(
        @Query("key") key:String,
        @Query("no_invoice") no_invoice:String,
        @Query("id_product") id:String): Observable<Message>

    @GET("sales/additemproduct.php")
    fun plusProductItem(
        @Query("key") key:String,
        @Query("no_invoice") no_invoice:String,
        @Query("id_product") id:String): Observable<Message>

    @GET("sales/cutitemproduct.php")
    fun minusProductItem(
        @Query("key") key:String,
        @Query("no_invoice") no_invoice:String,
        @Query("id_product") id:String): Observable<Message>

    @POST("sales/splitorder.php")
    fun paySplit(@Body request: ReqTrans) : Observable<Order>

    @GET("transaction/listordersuccsess.php")
    fun getTransactionSuccessOrder(
        @Query("key") key:String): Observable<List<HistoryTransaction>>

    @GET("transaction/listorder.php")
    fun getTransactionOrder(
        @Query("key") key:String): Observable<List<HistoryTransaction>>

    @GET("transaction/listpreorder.php")
    fun getTransactionPreOrder(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String): Observable<List<HistoryTransaction>>

    @GET("table/transactionsnotable.php")
    fun getTransactionsNotable(
        @Query("key") key:String): Observable<List<Transaction>>
    @GET("table/transactions.php")
    fun getTableTransactions(
        @Query("key") key:String,
        @Query("id_table") id:String): Observable<List<Transaction>>

    @GET("transaction/finishorder.php")
    fun finishDetailTransaction(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<Message>


    @GET("transaction/finishtransfer.php")
    fun finishTransfer(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<Message>

    @POST("sales/updateorder.php")
    fun payOrder(@Body request: RequestTransaction) : Observable<Order>



    @GET("transaction/historystock.php")
    fun getHistoryStock(
        @Query("key") key:String,
        @Query("id_product") id_product:String): Observable<List<DetailHistory>>



    @GET("transaction/historyjob.php")
    fun getHistoryJob(
        @Query("key") key:String,
        @Query("no_invoice") phone_number:String): Observable<List<DetailJob>>

    @Multipart
    @POST("spending/insert.php")
    fun add(
        @Part("key") key: RequestBody,
        @Part("date") date: RequestBody,
        @Part("amount") amount: RequestBody,
        @Part("data") data: RequestSpend,
        @Part img: MultipartBody.Part?): Observable<Message>

    @Multipart
    @POST("sales/transferreturn.php")
    fun transferReturn(
        @Part("key") key: RequestBody,
        @Part("data") data: RequestTransaction,
        @Part img: MultipartBody.Part?): Observable<Message>

    @GET("transaction/deletespend.php")
    fun deleteDetailSpend(
        @Query("key") key:String,
        @Query("no_invoice") id:String): Observable<Message>

    @GET("history/cashflow.php")
    fun cashFlow(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String,
        @Query("status") status:String): Observable<List<HistoryFlowCash>>

    @GET("history/searchcashflow.php")
    fun searchCashflow(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<FlowCash>>

    @GET("rawmaterial/historystock.php")
    fun getHistoryStockRawMaterial(
        @Query("key") key:String,
        @Query("id") id_product:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String): Observable<List<DetailHistory>>
}