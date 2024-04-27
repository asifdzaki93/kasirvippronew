package id.kasirvippro.android.models.transaction

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import id.kasirvippro.android.utils.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TransactionRestModel(context: Context) : RestModel<TransactionRestInterface>(context) {

    override fun createRestInterface(): TransactionRestInterface {
        return RestClient.getInstance()!!.createInterface(TransactionRestInterface::class.java)
    }

    fun getCustomerTransactions(key:String,id:String): Observable<List<Transaction>> {
        return restInterface.getCustomerTransactions(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCustomerDebts(key:String,id:String): Observable<List<Transaction>> {
        return restInterface.getCustomerDebts(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSupplierTransactions(key:String,id:String): Observable<List<Transaction>> {
        return restInterface.getSupplierTransactions(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSupplierDebts(key:String,id:String): Observable<List<Transaction>> {
        return restInterface.getSupplierDebts(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDetailTransaction(key:String,id:String): Observable<DetailTransaction> {
        return restInterface.getDetailTransaction(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDetailTransfer(key:String,id:String): Observable<DetailTransfer> {
        return restInterface.getDetailTransfer(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDetailTransactionReturn(key:String,id:String): Observable<DetailTransaction> {
        return restInterface.getDetailTransactionReturn(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDetailTransferReturn(key:String,id:String): Observable<DetailTransaction> {
        return restInterface.getDetailTransferReturn(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDetailLabel(key:String,id:String): Observable<DetailLabel> {
        return restInterface.getDetailLabel(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDetailTransactionSupplier(key:String,id:String): Observable<DetailTransaction> {
        return restInterface.getDetailTransactionSupplier(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDetailTransactionReturnSupplier(key:String,id:String): Observable<DetailTransaction> {
        return restInterface.getDetailTransactionReturnSupplier(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun deleteDetailTransaction(key:String,id:String): Observable<Message> {
        return restInterface.deleteDetailTransaction(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTransactionSuccessOrder(key:String): Observable<List<HistoryTransaction>> {
        return restInterface.getTransactionSuccessOrder(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun order(
        req: RequestTransaction,
        note: String
    ): Observable<Order> {
        return restInterface.order(req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun orderReturn(
        req: RequestTransaction,
        note: String
    ): Observable<Order> {
        return restInterface.orderReturn(req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun transferReturn(key:String, req: RequestTransaction,img:String?): Observable<Message> {
        return restInterface.transferReturn(
            Helper.createPartFromString(key),
            req,
            Helper.createPartFromFile(img,"img"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun printLabel(
        req: RequestTransaction,
        note: String
    ): Observable<Order> {
        return restInterface.printLabel(req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun orderDirect(req: RequestTransaction,img:String?): Observable<Message> {
        return restInterface.orderDirect(
            req,
            Helper.createPartFromFile(img,"img"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateDirect(req: RequestTransaction): Observable<Message> {
        return restInterface.updateDirect(
            req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addSalesData(user:String,no_invoice:String,id_customer:String,id_store:String,payment:String,totalorder:String,totalprice:String,totalpay:String,changepay:String,status:String,due_date:String,tax:String,discount:String,service_charge:String,operator:String,location:String,note:String,date:String): Observable<Message> {
        return restInterface.addSalesData(user,no_invoice,id_customer,id_store,payment,totalorder,totalprice,totalpay,changepay,status,due_date,tax,discount,service_charge,operator,location,note,date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addSpendingData(user:String,id_store:String,no_invoice:String,date:String,totalnominal:String): Observable<Message> {
        return restInterface.addSpendingData(user,id_store,no_invoice,date,totalnominal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addSpending(name_spending:String,user:String,no_invoice:String,nominal:String,date:String): Observable<Message> {
        return restInterface.addSpending(name_spending,user,no_invoice,nominal,date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addSales(
        user:String,
        no_invoice:String,
        id_customer:String,
        id_store:String,
        id_product:String,
        amount: String,
        totalprice:String,
        price:String,
        status:String,
        note:String,
        date:String): Observable<Message> {
        return restInterface.addSales(user,no_invoice,id_customer,id_store,id_product,amount,totalprice,price,status,note,date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun addorder(req:RequestAddOrder): Observable<Order> {
        return restInterface.addorder(req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteAddTransaction(key:String,id:String): Observable<Message> {
        return restInterface.deleteAddTransaction(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteProductItem(key:String, no_invoice:String, id:String): Observable<Message> {
        return restInterface.deleteProductItem(key,no_invoice,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun plusProductItem(key:String, no_invoice:String, id:String): Observable<Message> {
        return restInterface.plusProductItem(key,no_invoice,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun minusProductItem(key:String, no_invoice:String, id:String): Observable<Message> {
        return restInterface.minusProductItem(key,no_invoice,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun paySplit(
        req: ReqTrans,
        note: String,
        id: String
    ): Observable<Order> {
        return restInterface.paySplit(req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun kulakan(req:RequestKulakan): Observable<Order> {
        return restInterface.kulakan(req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun orderraw(req:RequestRawMaterial): Observable<Order> {
        return restInterface.orderraw(req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun transfer(req:RequestTransfer): Observable<Order> {
        return restInterface.transfer(req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun packages(req:RequestPackages): Observable<Order> {
        return restInterface.packages(req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun addpackages(req:RequestPackages): Observable<Order> {
        return restInterface.addpackages(req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun recipe(req:RequestTransaction): Observable<Order> {
        return restInterface.recipe(req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun pengeluaran(req:RequestSpend): Observable<Message> {
        return restInterface.pengeluaran(req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun historyTransaction(key:String,awal:String,akhir:String,status:String): Observable<List<HistoryTransaction>> {
        return restInterface.historyTransaction(key,awal,akhir,status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun HistoryJob(key:String,operator:String): Observable<List<DetailJob>> {
        return restInterface.HistoryJob(key,operator)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun historyTransactionReturn(key:String,awal:String,akhir:String,status:String): Observable<List<HistoryTransaction>> {
        return restInterface.historyTransactionReturn(key,awal,akhir,status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun historyTransferReturn(key:String,awal:String,akhir:String,status:String): Observable<List<HistoryTransaction>> {
        return restInterface.historyTransferReturn(key,awal,akhir,status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun historyComission(key:String,awal:String,akhir:String,status:String): Observable<List<Comission>> {
        return restInterface.historyComission(key,awal,akhir,status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun payPiutang(key:String,id:String,total:String): Observable<Message> {
        return restInterface.payPiutang(key,id,total)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun payHutang(key:String,id:String,total:String): Observable<Message> {
        return restInterface.payHutang(key,id,total)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchTransaction(key:String,id:String): Observable<List<Transaction>> {
        return restInterface.searchTransaction(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchPreorder(key:String,id:String): Observable<List<Transaction>> {
        return restInterface.searchPreorder(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchTransactionIn(key:String,id:String): Observable<List<Transfer>> {
        return restInterface.searchTransactionIn(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchTransactionOut(key:String,id:String): Observable<List<Transfer>> {
        return restInterface.searchTransactionOut(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchTransactionReturn(key:String,id:String): Observable<List<Transaction>> {
        return restInterface.searchTransactionReturn(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchTransferReturn(key:String,id:String): Observable<List<Transaction>> {
        return restInterface.searchTransferReturn(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteSupplierDetailTransaction(key:String,id:String): Observable<Message> {
        return restInterface.deleteSupplierDetailTransaction(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun checkPayment(key:String,total:Double,id:String?): Observable<List<DetailPayment>> {
        return restInterface.checkPayment(key,total,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun historySpend(key:String,awal:String,akhir:String): Observable<List<HistoryTransaction>> {
        return restInterface.historySpend(key,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchSpend(key:String,id:String): Observable<List<Transaction>> {
        return restInterface.searchSpend(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDetailSpend(key:String,id:String): Observable<DetailSpend> {
        return restInterface.getDetailSpend(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getNonTunai(key:String): Observable<List<NonTunai>> {
        return restInterface.getNonTunai(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTransactionOrder(key:String): Observable<List<HistoryTransaction>> {
        return restInterface.getTransactionOrder(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTransactionPreOrder(key:String,awal:String,akhir:String): Observable<List<HistoryTransaction>> {
        return restInterface.getTransactionPreOrder(key,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTransferIn(key:String,awal:String,akhir:String,status:String): Observable<List<HistoryTransfer>> {
        return restInterface.getTransferIn(key,awal,akhir,status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTransferOut(key:String,awal:String,akhir:String,status:String): Observable<List<HistoryTransfer>> {
        return restInterface.getTransferOut(key,awal,akhir,status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTransactionsNotable(key:String): Observable<List<Transaction>> {
        return restInterface.getTransactionsNotable(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTableTransactions(key:String,id:String): Observable<List<Transaction>> {
        return restInterface.getTableTransactions(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun finishDetailTransaction(key:String,id:String): Observable<Message> {
        return restInterface.finishDetailTransaction(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun finishTransfer(key:String,id:String): Observable<Message> {
        return restInterface.finishTransfer(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun payOrder(
        req: RequestTransaction,
        note: String,
        id: String
    ): Observable<Order> {
        return restInterface.payOrder(req)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getHistoryStock(key:String, id_product:String): Observable<List<DetailHistory>> {
        return restInterface.getHistoryStock(key,id_product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun getHistoryJob(key:String, no_invoice:String): Observable<List<DetailJob>> {
        return restInterface.getHistoryJob(key,no_invoice)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun add(key:String, date:String, amount:Double?, req: RequestSpend,img:String?): Observable<Message> {
        return restInterface.add(
            Helper.createPartFromString(key),
            Helper.createPartFromString(date),
            Helper.createPartFromString(amount.toString()),
            req,
            Helper.createPartFromFile(img,"img"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun deleteDetailSpend(key:String,id:String): Observable<Message> {
        return restInterface.deleteDetailSpend(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun cashFlow(key:String,awal:String,akhir:String,status:String): Observable<List<HistoryFlowCash>> {
        return restInterface.cashFlow(key,awal,akhir,status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun searchCashflow(key:String,id:String): Observable<List<FlowCash>> {
        return restInterface.searchCashflow(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun getHistoryStockRawMaterial(key:String, id:String,awal:String,akhir:String): Observable<List<DetailHistory>> {
        return restInterface.getHistoryStockRawMaterial(key,id,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}