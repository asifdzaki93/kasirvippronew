package id.kasirvippro.android.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.kasirvippro.android.sqlite.Model.*

class DataManager(context: Context) {
    private val db: SQLiteDatabase = context.openOrCreateDatabase("Pos", Context.MODE_PRIVATE, null)

    val user_table = "Users";
    val store_table = "Store";

    val category_table = "Category";
    val category_update_table = "CategoryUpdate";
    val category_delete_table = "CategoryDelete";
    val category_add_table = "CategoryAdd";

    val produc_table = "Product";
    val produc_add_table = "ProductAdd";
    val produc_update_table = "ProductUpdate";
    val produc_delete_table = "ProductDelete";

    val discount_table = "Discount";
    val discount_update_table = "DiscountUpdate";
    val discount_delete_table = "DiscountDelete";
    val discount_add_table = "DiscountAdd";

    val customer_table = "Customer";
    val customer_update_table = "CustomerUpdate";
    val customer_delete_table = "CustomerDelete";
    val customer_add_table = "CustomerAdd";

    val table_table = "Table";
    val table_update_table = "TableUpdate";
    val table_delete_table = "TableDelete";
    val table_add_table = "TableAdd";

    val supplier_table = "Supplier";
    val supplier_update_table = "SupplierUpdate";
    val supplier_delete_table = "SupplierDelete";
    val supplier_add_table = "SupplierAdd";

    val sales_table = "Sales";
    val salesdata_table = "SalesData";
    val spending_table = "Spending";
    val spendingdata_table = "SpendingData";
    val link_table = "Link";
    init {

        val salesQuery =
            "CREATE TABLE IF NOT EXISTS '${sales_table}' (`increment` INTEGER PRIMARY KEY,uuid_salesData TEXT,id_sales TEXT, `id_customer` TEXT,`id_product` TEXT,`codeproduct` TEXT,`name_product` TEXT,`id_store` TEXT  ,`user` TEXT,`no_invoice` TEXT,`amount` TEXT,`price` TEXT,`totalprice` TEXT,`hpp` TEXT,`totalcapital` TEXT,`date` TEXT,`status` TEXT,`note` TEXT,`rest_stock` TEXT,`sift` TEXT,`station` TEXT)"
        db.execSQL(salesQuery)

        val salesDataQuery =
            "CREATE TABLE IF NOT EXISTS '${salesdata_table}' (`increment` INTEGER PRIMARY KEY,uuid TEXT,id_sales_data TEXT, `user` TEXT,`id_customer` TEXT,`name_customer` TEXT,`id_store` TEXT  ,`name_store` TEXT,`email_store` TEXT,`nohp_store` TEXT,`address_store` TEXT,`id_supplier` TEXT,`name_supplier` TEXT,`no_invoice` TEXT,`date` TEXT,`payment` TEXT,`note` TEXT,`totalorder` TEXT,`totalprice` TEXT,`totalpay` TEXT,`changepay` TEXT,`status` TEXT,`due_date` TEXT,`tax` TEXT,`discount` TEXT,`service_charge` TEXT,`operator` TEXT,`location` TEXT,`total_sales_hpp` TEXT,`sift` TEXT,`station` TEXT,`footer` TEXT,`img` TEXT,`id_table` TEXT)"
        db.execSQL(salesDataQuery)

        val spendingQuery =
            "CREATE TABLE IF NOT EXISTS '${spending_table}' (`increment` INTEGER PRIMARY KEY,id_spending TEXT, `name_spending` TEXT,`user` TEXT,`no_invoice` TEXT,`nominal` TEXT,`date` TEXT)"
        db.execSQL(spendingQuery)

        val spendingDataQuery =
            "CREATE TABLE IF NOT EXISTS '${spendingdata_table}' (`increment` INTEGER PRIMARY KEY,id_spending_data TEXT, `user` TEXT,`id_store` TEXT,`no_invoice` TEXT,`date` TEXT  ,`totalnominal` TEXT,`operator` TEXT)"
        db.execSQL(spendingDataQuery)

        val userQuery =
            "CREATE TABLE IF NOT EXISTS '${user_table}' ( `key` TEXT NOT NULL,`currency` TEXT NOT NULL,`name` TEXT NOT NULL,`user` TEXT NOT NULL,`level` TEXT NOT NULL,`master` TEXT NOT NULL,`packages` TEXT NOT NULL,`typestore` TEXT NOT NULL,`decimal` TEXT NOT NULL,`id_store` TEXT NOT NULL,`phone` TEXT NOT NULL,`password` TEXT NOT NULL,`latitude` TEXT NOT NULL,`longitude` TEXT NOT NULL, PRIMARY KEY(`user`) )"
        db.execSQL(userQuery)

        val productQuery =
            "CREATE TABLE IF NOT EXISTS '${produc_table}' (`increment` INTEGER PRIMARY KEY,id_product TEXT, `key` TEXT,`nama` TEXT,`unit` TEXT,`kode` TEXT  ,`idkategori` TEXT,`namakategori` TEXT,`active` TEXT,`beli` TEXT,`jual` TEXT,`stok` TEXT,`minstok` TEXT,`deskripsi` TEXT,`online` TEXT,`havestok` TEXT,`grosir` TEXT,`tax` TEXT,`alertstock` TEXT,`img` String)"
        db.execSQL(productQuery)

        val storeQuery =
            "CREATE TABLE IF NOT EXISTS '${store_table}' (`increment` INTEGER PRIMARY KEY,id_store TEXT, `type` TEXT,`name_store` TEXT,`nohp` TEXT  ,`address` TEXT,`email` TEXT,`omset` TEXT,`transaksi` TEXT,`order_x` TEXT,`tax` TEXT, `service_charge` TEXT,`number_store` TEXT,`level` TEXT,`footer` TEXT,`photo` TEXT)"
        db.execSQL(storeQuery)


        val productAddQuery =
            "CREATE TABLE IF NOT EXISTS '${produc_add_table}' (`increment` INTEGER PRIMARY KEY, `key` TEXT,`nama` TEXT,`unit` TEXT,`kode` TEXT  ,`idkategori` TEXT,`beli` TEXT,`jual` TEXT,`stok` TEXT,`minstok` TEXT,`deskripsi` TEXT,`online` TEXT,`havestok` TEXT,`grosir` TEXT,`tax` TEXT,`img` String)"
        db.execSQL(productAddQuery)

        val productUpdateQuery =
            "CREATE TABLE IF NOT EXISTS '${produc_update_table}' (`increment` INTEGER PRIMARY KEY,  `key` TEXT,`id_product` TEXT ,`nama` TEXT,`unit` TEXT,`kode` TEXT,`idkategori` TEXT,`beli` TEXT,`jual` TEXT,`stok` TEXT,`minstok` TEXT,`deskripsi` TEXT,`online` TEXT,`havestok` TEXT,`grosir` TEXT,`tax` TEXT,`img` String)"
        db.execSQL(productUpdateQuery)

        val productDeleteQuery =
            "CREATE TABLE IF NOT EXISTS '${produc_delete_table}' ( `increment` INTEGER PRIMARY KEY,`key` TEXT,`id_product` TEXT NOT NULL)"
        db.execSQL(productDeleteQuery)


        val customerQuery =
            "CREATE TABLE IF NOT EXISTS '${customer_table}' ( `increment` INTEGER PRIMARY KEY, `key` TEXT,`id_customer` TEXT,`email` TEXT,`name_customer` TEXT,`telephone` TEXT,`address` TEXT,`customercode` TEXT)"
        db.execSQL(customerQuery)

        val customerUpdateQuery =
            "CREATE TABLE IF NOT EXISTS '${customer_update_table}' (`increment` INTEGER PRIMARY KEY,  `key` TEXT,`id_customer` TEXT ,`email` TEXT,`name_customer` TEXT,`telephone` TEXT,`address` TEXT,`customercode` TEXT)"
        db.execSQL(customerUpdateQuery)

        val customerDeleteQuery =
            "CREATE TABLE IF NOT EXISTS '${customer_delete_table}' ( `increment` INTEGER PRIMARY KEY,`key` TEXT,`id_customer` TEXT NOT NULL)"
        db.execSQL(customerDeleteQuery)

        val customerAddQuery =
            "CREATE TABLE IF NOT EXISTS '${customer_add_table}' (`increment` INTEGER PRIMARY KEY, `key` TEXT,`name_customer` TEXT,`telephone` TEXT  ,`email` TEXT,`address` TEXT,`customercode` TEXT)"
        db.execSQL(customerAddQuery)

        val tableQuery =
            "CREATE TABLE IF NOT EXISTS '${table_table}' ( `increment` INTEGER PRIMARY KEY, `key` TEXT,`id_table` TEXT,`name_table` TEXT)"
        db.execSQL(tableQuery)

        val tableUpdateQuery =
            "CREATE TABLE IF NOT EXISTS '${table_update_table}' (`increment` INTEGER PRIMARY KEY,  `key` TEXT,`id_table` TEXT,`name_table` TEXT)"
        db.execSQL(tableUpdateQuery)

        val tableDeleteQuery =
            "CREATE TABLE IF NOT EXISTS '${table_delete_table}' ( `increment` INTEGER PRIMARY KEY,`key` TEXT,`id_table` TEXT NOT NULL)"
        db.execSQL(tableDeleteQuery)

        val tableAddQuery =
            "CREATE TABLE IF NOT EXISTS '${table_add_table}' (`increment` INTEGER PRIMARY KEY, `key` TEXT,`name_table` TEXT)"
        db.execSQL(tableAddQuery)

        val supplierQuery =
            "CREATE TABLE IF NOT EXISTS '${supplier_table}' ( `increment` INTEGER PRIMARY KEY, `key` TEXT,`id_supplier` TEXT,`email` TEXT,`name_supplier` TEXT,`telephone` TEXT,`address` TEXT)"
        db.execSQL(supplierQuery)

        val supplierUpdateQuery =
            "CREATE TABLE IF NOT EXISTS '${supplier_update_table}' (`increment` INTEGER PRIMARY KEY,  `key` TEXT,`id_supplier` TEXT ,`email` TEXT,`name_supplier` TEXT,`telephone` TEXT,`address` TEXT)"
        db.execSQL(supplierUpdateQuery)

        val supplierDeleteQuery =
            "CREATE TABLE IF NOT EXISTS '${supplier_delete_table}' ( `increment` INTEGER PRIMARY KEY,`key` TEXT,`id_supplier` TEXT NOT NULL)"
        db.execSQL(supplierDeleteQuery)

        val supplierAddQuery =
            "CREATE TABLE IF NOT EXISTS '${supplier_add_table}' (`increment` INTEGER PRIMARY KEY, `key` TEXT,`name_supplier` TEXT,`telephone` TEXT  ,`email` TEXT,`address` TEXT)"
        db.execSQL(supplierAddQuery)

        val discountQuery =
            "CREATE TABLE IF NOT EXISTS '${discount_table}' ( `increment` INTEGER PRIMARY KEY, `key` TEXT,`id_discount` TEXT,`name_discount` TEXT,`note` TEXT,`type` TEXT,`nominal` TEXT)"
        db.execSQL(discountQuery)

        val discountUpdateQuery =
            "CREATE TABLE IF NOT EXISTS '${discount_update_table}' (`increment` INTEGER PRIMARY KEY,  `key` TEXT,`id_discount` TEXT ,`name_discount` TEXT,`note` TEXT,`type` TEXT,`nominal` TEXT)"
        db.execSQL(discountUpdateQuery)

        val discountDeleteQuery =
            "CREATE TABLE IF NOT EXISTS '${discount_delete_table}' ( `increment` INTEGER PRIMARY KEY,`key` TEXT,`id_discount` TEXT NOT NULL)"
        db.execSQL(discountDeleteQuery)

        val discountAddQuery =
            "CREATE TABLE IF NOT EXISTS '${discount_add_table}' (`increment` INTEGER PRIMARY KEY, `key` TEXT,`name_discount` TEXT,`note` TEXT  ,`type` TEXT,`nominal` TEXT)"
        db.execSQL(discountAddQuery)

        val categoryQuery =
            "CREATE TABLE IF NOT EXISTS '${category_table}' (`increment` INTEGER PRIMARY KEY, `key` TEXT,`id_category` TEXT,`name_category` TEXT)"
        db.execSQL(categoryQuery)


        val categoryUpdateQuery =
            "CREATE TABLE IF NOT EXISTS '${category_update_table}' (`increment` INTEGER PRIMARY KEY,  `key` TEXT,`id_category` TEXT ,`name_category` TEXT)"
        db.execSQL(categoryUpdateQuery)

        val categoryDeleteQuery =
            "CREATE TABLE IF NOT EXISTS '${category_delete_table}' ( `increment` INTEGER PRIMARY KEY,`key` TEXT,`id_category` TEXT NOT NULL)"
        db.execSQL(categoryDeleteQuery)

        val categoryAddQuery =
            "CREATE TABLE IF NOT EXISTS '${category_add_table}' (`increment` INTEGER PRIMARY KEY, `key` TEXT,`name_category` TEXT)"
        db.execSQL(categoryAddQuery)

        val LinkQuery =
            "CREATE TABLE IF NOT EXISTS '${link_table}' ( `increment` INTEGER PRIMARY KEY, `key` TEXT,`id_link` TEXT,`name_link` TEXT,`img` TEXT)"
        db.execSQL(LinkQuery)


    }

    fun addSales(sales: SalesSQL): Boolean {
        return if (sales(sales.increment) == null) {
            val query = "INSERT INTO '${sales_table}' (uuid_salesData,id_sales,id_customer,id_product,codeproduct,name_product,id_store,user,no_invoice,amount,price,totalprice,hpp,totalcapital,date,status,note,rest_stock,sift,station) VALUES ('${sales.uuid_salesData}','${sales.id_sales}', '${sales.id_customer}', '${sales.id_product}', '${sales.codeproduct}', '${sales.name_product}', '${sales.id_store}', '${sales.user}', '${sales.no_invoice}', '${sales.amount}', '${sales.price}', '${sales.totalprice}', '${sales.hpp}', '${sales.totalcapital}', '${sales.date}', '${sales.status}', '${sales.note}', '${sales.rest_stock}', '${sales.sift}', '${sales.station}')"
            db.execSQL(query)
            true
        } else {
            val query = "INSERT INTO '${sales_table}' (uuid_salesData,id_sales,id_customer,id_product,codeproduct,name_product,id_store,user,no_invoice,amount,price,totalprice,hpp,totalcapital,date,status,note,rest_stock,sift,station) VALUES ('${sales.uuid_salesData}','${sales.id_sales}', '${sales.id_customer}', '${sales.id_product}', '${sales.codeproduct}', '${sales.name_product}', '${sales.id_store}', '${sales.user}', '${sales.no_invoice}', '${sales.amount}', '${sales.price}', '${sales.totalprice}', '${sales.hpp}', '${sales.totalcapital}', '${sales.date}', '${sales.status}', '${sales.note}', '${sales.rest_stock}', '${sales.sift}', '${sales.station}')"
            db.execSQL(query)
            true
        }
    }

    fun addSalesData(salesData: SalesDataSQL): Boolean {
        return if (salesData(salesData.increment) == null) {
            val query = "INSERT INTO '${salesdata_table}' (uuid,id_sales_data,user,id_customer,name_customer,id_store,name_store,email_store,nohp_store,address_store,id_supplier,name_supplier,no_invoice,date,payment,note,totalorder,totalprice,totalpay,changepay,status,due_date,tax,discount,service_charge,operator,location,total_sales_hpp,sift,station,footer,img,id_table) VALUES ('${salesData.uuid}','${salesData.id_sales_data}', '${salesData.user}', '${salesData.id_customer}','${salesData.name_customer}', '${salesData.id_store}', '${salesData.name_store}', '${salesData.email_store}', '${salesData.nohp_store}', '${salesData.address_store}', '${salesData.id_supplier}', '${salesData.name_supplier}', '${salesData.no_invoice}', '${salesData.date}', '${salesData.payment}', '${salesData.note}', '${salesData.totalorder}', '${salesData.totalprice}', '${salesData.totalpay}', '${salesData.changepay}', '${salesData.status}', '${salesData.due_date}', '${salesData.tax}', '${salesData.discount}', '${salesData.service_charge}', '${salesData.operator}', '${salesData.location}', '${salesData.total_sales_hpp}', '${salesData.sift}', '${salesData.station}', '${salesData.footer}', '${salesData.img}', '${salesData.id_table}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }

    fun addSpendingData(spendingData: SpendingDataSQL): Boolean {
        return if (spendingData(spendingData.increment) == null) {
            val query = "INSERT INTO '${spendingdata_table}' (id_spending_data,user,id_store,no_invoice,date,totalnominal,operator) VALUES ('${spendingData.id_spending_data}','${spendingData.user}', '${spendingData.id_store}', '${spendingData.no_invoice}','${spendingData.date}', '${spendingData.totalnominal}', '${spendingData.operator}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }

    fun addSpending(spending: SpendingSQL): Boolean {
        return if (spending(spending.increment) == null) {
            val query = "INSERT INTO '${spending_table}' (id_spending,name_spending,user,no_invoice,nominal,date) VALUES ('${spending.id_spending}','${spending.name_spending}', '${spending.user}', '${spending.no_invoice}', '${spending.nominal}', '${spending.date}')"
            db.execSQL(query)
            true
        } else {
            val query = "INSERT INTO '${spending_table}' (id_spending,name_spending,user,no_invoice,nominal,date) VALUES ('${spending.id_spending}','${spending.name_spending}', '${spending.user}', '${spending.no_invoice}', '${spending.nominal}', '${spending.date}')"
            db.execSQL(query)
            true
        }
    }

    fun addCustomer(customer: CustomerSQL): Boolean {
        return if (customer(customer.increment) == null) {
            val query = "INSERT INTO '${customer_table}' (key,id_customer,email,name_customer,telephone,address) VALUES ('${customer.key}', '${customer.id_customer}', '${customer.email}', '${customer.name_customer}', '${customer.telephone}', '${customer.address}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }

    fun addTable(table: TableSQL): Boolean {
        return if (table(table.increment) == null) {
            val query = "INSERT INTO '${table_table}' (key,id_table,name_table) VALUES ('${table.key}', '${table.id_table}', '${table.name_table}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }

    fun addSupplier(supplier: SupplierSQL): Boolean {
        return if (supplier(supplier.increment) == null) {
            val query = "INSERT INTO '${supplier_table}' (key,id_supplier,name_supplier,telephone,email,address) VALUES ('${supplier.key}', '${supplier.id_supplier}', '${supplier.name_supplier}', '${supplier.telephone}', '${supplier.email}', '${supplier.address}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }

    fun addStore(store: StoreSQL): Boolean {
        return if (store(store.increment) == null) {
            val query = "INSERT INTO '${store_table}' (id_store,type,name_store,nohp,address,email,omset,transaksi,order_x,tax,service_charge,number_store,level,footer,photo) VALUES ('${store.id_store}', '${store.type}', '${store.name_store}', '${store.nohp}', '${store.address}', '${store.email}', '${store.omset}', '${store.transaksi}', '${store.order}', '${store.tax}', '${store.service_charge}', '${store.number_store}', '${store.level}', '${store.footer}', '${store.photo}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }

    fun addDiscount(discount: DiscountSQL): Boolean {
        return if (discount(discount.increment) == null) {
            val query = "INSERT INTO '${discount_table}' (key,id_discount,name_discount,note,type,nominal) VALUES ('${discount.key}','${discount.id_discount}','${discount.name_discount}', '${discount.note}', '${discount.type}', '${discount.nominal}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }

    fun addLink(link: LinkSQL): Boolean {
        return if (link(link.increment) == null) {
            val query = "INSERT INTO '${link_table}' (key,id_link,name_link,img) VALUES ('${link.key}','${link.id_link}','${link.name_link}', '${link.img}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }

    fun addCategory(category: CategorySQL): Boolean {
        return if (category(category.increment) == null) {
            val query = "INSERT INTO '${category_table}' (key,id_category,name_category) VALUES ('${category.key}','${category.id_category}','${category.name_category}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }


    fun searchCategoryd(search: CategorySQLSearch): CategorySQLSearch? {
        val query = "SELECT * FROM '${category_table}' WHERE name_category LIKE '%foo%'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val key = cursor.getString(cursor.getColumnIndex("key")).toString()
            val id_category = cursor.getString(cursor.getColumnIndex("id_category")).toString()
            cursor.close()
            CategorySQLSearch(key,id_category)
        } else {
            cursor.close()
            null
        }
    }

    fun add(user: UserSQL): Boolean {
        return if (user(user.phone) == null) {
            val query = "INSERT INTO '${user_table}' (key,currency,name,user,level,master,packages,typestore,decimal,id_store,phone,password,latitude,longitude) VALUES ('${user.key}', '${user.currency}', '${user.name}', '${user.user}', '${user.level}', '${user.master}', '${user.packages}', '${user.typestore}',  '${user.decimal}', '${user.id_store}', '${user.phone}', '${user.password}', '${user.latitude}', '${user.longitude}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }

    fun update(name : String): Boolean {
        val query = "UPDATE '${user_table}' SET name = '${name}'"
        val result = db.execSQL(query)
        return true
    }

    fun addProduct(product: ProductSQL): Boolean {
        return if (product(product.increment) == null) {
            val query = "INSERT INTO '${produc_table}' " +
                    "(id_product,key,nama,unit,kode,idkategori,namakategori,active,beli,jual,stok,minstok,deskripsi,online,havestok,grosir,tax,alertstock,img) " +
                    "VALUES ('${product.id_product}', '${product.key}', '${product.nama}', '${product.unit}','${product.kode}', '${product.idkategori}','${product.namakategori}','${product.active}', '${product.beli}', " +
                    "'${product.jual}', '${product.stok}', '${product.minstok}', '${product.deskripsi}', " +
                    "'${product.online}', '${product.havestok}', '${product.grosir}', '${product.tax}', '${product.alertstock}', '${product.img}')"
            db.execSQL(query)
            return true
        } else {
            false
        }
    }


    fun addProductAdd(product: ProductSQLAdd): Boolean {
        return if (productAdd(product.increment) == null) {
            val query = "INSERT INTO '${produc_add_table}' " +
                    "(key,nama,unit,kode,idkategori,beli,jual,stok,minstok,deskripsi,online,havestok,grosir,tax,img) " +
                    "VALUES ('${product.key}', '${product.nama}', '${product.unit}','${product.kode}', '${product.idkategori}', '${product.beli}', " +
                    "'${product.jual}', '${product.stok}', '${product.minstok}', '${product.deskripsi}', " +
                    "'${product.online}', '${product.havestok}', '${product.grosir}','${product.tax}', '${product.img}')"
            db.execSQL(query)
            return true
        } else {
            false
        }
    }

    fun addProductUpdate(product: ProductSQLUpdate): Boolean {
        return if (productUpdate(product.increment) == null) {
            val query = "INSERT INTO '${produc_update_table}' " +
                    "(key,id_product,nama,unit,kode,idkategori,beli,jual,stok,minstok,deskripsi,online,havestok,grosir,tax,img) " +
                    "VALUES ('${product.key}', '${product.id_product}', '${product.nama}','${product.unit}','${product.kode}', " +
                    "'${product.idkategori}', '${product.beli}', '${product.jual}', '${product.stok}', " +
                    "'${product.minstok}', '${product.deskripsi}', '${product.online}', '${product.havestok}', " +
                    "'${product.grosir}', '${product.tax}', '${product.img}')"
            db.execSQL(query)
            return true
        } else {
            false
        }
    }

    fun addProductDelete(product: ProductSQLDelete): Boolean {
        return if (productDelete(product.increment) == null) {
            val query = "INSERT INTO '${produc_delete_table}' (key,id_product) VALUES ('${product.key}', '${product.id_product}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }
//customer

    fun addCustomerDelete(customer: CustomerSQLDelete): Boolean {
        return if (customerDelete(customer.increment) == null) {
            val query = "INSERT INTO '${customer_delete_table}' (key,id_customer) VALUES ('${customer.key}', '${customer.id}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }
    fun addTableDelete(table: TableSQLDelete): Boolean {
        return if (tableDelete(table.increment) == null) {
            val query = "INSERT INTO '${table_delete_table}' (key,id_table) VALUES ('${table.key}', '${table.id}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }
    fun addCustomerAdd(customer: CustomerSQLAdd): Boolean {
        return if (customerAdd(customer.increment) == null) {
            val query = "INSERT INTO '${customer_add_table}' " +
                    "(key,name_customer,telephone,email,address,customercode) " +
                    "VALUES ('${customer.key}', '${customer.name_customer}','${customer.telephone}', '${customer.email}', '${customer.address}', '${customer.customercode}')"
            db.execSQL(query)
            return true
        } else {
            false
        }
    }
    fun addTableAdd(table: TableSQLAdd): Boolean {
        return if (tableAdd(table.increment) == null) {
            val query = "INSERT INTO '${table_add_table}' " +
                    "(key,name_table) " +
                    "VALUES ('${table.key}', '${table.name_table}')"
            db.execSQL(query)
            return true
        } else {
            false
        }
    }



    fun addCustomerUpdate(customer: CustomerSQLUpdate): Boolean {
        return if (customerUpdate(customer.increment) == null) {
            val query = "INSERT INTO '${customer_update_table}' " +
                    "(key,id_customer,name_customer,telephone,email,address,customercode) " +
                    "VALUES ('${customer.key}', '${customer.id_customer}', '${customer.name_customer}','${customer.telephone}', " +
                    "'${customer.email}', '${customer.address}', '${customer.customercode}')"
            db.execSQL(query)
            return true
        } else {
            false
        }
    }

    fun addTableUpdate(table: TableSQLUpdate): Boolean {
        return if (tableUpdate(table.increment) == null) {
            val query = "INSERT INTO '${table_update_table}' " +
                    "(key,id_table,name_table) " +
                    "VALUES ('${table.key}', '${table.id_table}', '${table.name_table}')"
            db.execSQL(query)
            return true
        } else {
            false
        }
    }


    fun addDiscountUpdate(discount: DiscountSQLUpdate): Boolean {
        return if (discountUpdate(discount.increment) == null) {
            val query = "INSERT INTO '${discount_update_table}' " +
                    "(key,id_discount,name_discount,note,type,nominal) " +
                    "VALUES ('${discount.key}', '${discount.id_discount}', '${discount.name_discount}','${discount.note}', " +
                    "'${discount.type}', '${discount.nominal}')"
            db.execSQL(query)
            return true
        } else {
            false
        }
    }

    fun addCategoryUpdate(category: CategorySQLUpdate): Boolean {
        return if (categoryUpdate(category.increment) == null) {
            val query = "INSERT INTO '${category_update_table}' " +
                    "(key,id_category,name_category) " +
                    "VALUES ('${category.key}', '${category.id_category}', '${category.name_category}')"
            db.execSQL(query)
            return true
        } else {
            false
        }
    }
//supplier
    fun addSupplierDelete(supplier: SupplierSQLDelete): Boolean {
        return if (supplierDelete(supplier.increment) == null) {
            val query = "INSERT INTO '${supplier_delete_table}' (key,id_supplier) VALUES ('${supplier.key}', '${supplier.id}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }

    fun addSupplierAdd(supplier: SupplierSQLAdd): Boolean {
        return if (supplierAdd(supplier.increment) == null) {
            val query = "INSERT INTO '${supplier_add_table}' " +
                    "(key,name_supplier,telephone,email,address) " +
                    "VALUES ('${supplier.key}', '${supplier.name_supplier}','${supplier.telephone}', '${supplier.email}', '${supplier.address}')"
            db.execSQL(query)
            return true
        } else {
            false
        }
    }


    fun addDiscountDelete(discount: DiscountSQLDelete): Boolean {
        return if (discountDelete(discount.increment) == null) {
            val query = "INSERT INTO '${discount_delete_table}' (key,id_discount) VALUES ('${discount.key}', '${discount.id}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }

    fun addCategoryDelete(category: CategorySQLDelete): Boolean {
        return if (categoryDelete(category.increment) == null) {
            val query = "INSERT INTO '${category_delete_table}' (key,id_category) VALUES ('${category.key}', '${category.id}')"
            db.execSQL(query)
            true
        } else {
            false
        }
    }

    fun addDiscountAdd(discount: DiscountSQLAdd): Boolean {
        return if (discountAdd(discount.increment) == null) {
            val query = "INSERT INTO '${discount_add_table}' " +
                    "(key,name_discount,note,type,nominal) " +
                    "VALUES ('${discount.key}', '${discount.name_discount}','${discount.note}', '${discount.type}', '${discount.nominal}')"
            db.execSQL(query)
            return true
        } else {
            false
        }
    }
    fun addCategoryAdd(category: CategorySQLAdd): Boolean {
        return if (categoryAdd(category.increment) == null) {
            val query = "INSERT INTO '${category_add_table}' " +
                    "(key,name_category) " +
                    "VALUES ('${category.key}', '${category.name_category}')"
            db.execSQL(query)
            return true
        } else {
            false
        }
    }



    fun addSupplierUpdate(supplier: SupplierSQLUpdate): Boolean {
        return if (supplierUpdate(supplier.increment) == null) {
            val query = "INSERT INTO '${supplier_update_table}' " +
                    "(key,id_supplier,name_supplier,telephone,email,address) " +
                    "VALUES ('${supplier.key}', '${supplier.id_supplier}', '${supplier.name_supplier}','${supplier.telephone}', " +
                    "'${supplier.email}', '${supplier.address}')"
            db.execSQL(query)
            return true
        } else {
            false
        }
    }


    fun clear():Boolean{
        val query = "DELETE FROM '${user_table}';"
        db.execSQL(query)
        return true
    }

    fun clearSalesAll():Boolean{
        val query = "DELETE FROM '${sales_table}';"
        db.execSQL(query)
        return true
    }

    fun clearSalesDataAll():Boolean{
        val query = "DELETE FROM '${salesdata_table}';"
        db.execSQL(query)
        return true
    }

    fun clearStoreAll():Boolean{
        val query = "DELETE FROM '${store_table}';"
        db.execSQL(query)
        return true
    }

    fun clearCustomerAll():Boolean{
        val query = "DELETE FROM '${customer_table}';"
        db.execSQL(query)
        return true
    }

    fun clearTableAll():Boolean{
        val query = "DELETE FROM '${table_table}';"
        db.execSQL(query)
        return true
    }
    fun clearDiscountAll():Boolean{
        val query = "DELETE FROM '${discount_table}';"
        db.execSQL(query)
        return true
    }

    fun clearSupplierAll():Boolean{
        val query = "DELETE FROM '${supplier_table}';"
        db.execSQL(query)
        return true
    }

    fun clearProductAll():Boolean{
        val query = "DELETE FROM '${produc_table}';"
        db.execSQL(query)
        return true
    }

    fun clearCategoryAll():Boolean{
        val query = "DELETE FROM '${category_table}';"
        db.execSQL(query)
        return true
    }

    fun clearSales(increment: String):Boolean{
        val query = "DELETE FROM '${sales_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearSalesData(increment: String):Boolean{
        val query = "DELETE FROM '${salesdata_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearStore(increment: String):Boolean{
        val query = "DELETE FROM '${store_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearCustomer(increment: String):Boolean{
        val query = "DELETE FROM '${customer_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }
    fun clearTable(increment: String):Boolean{
        val query = "DELETE FROM '${table_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearDiscount(increment: String):Boolean{
        val query = "DELETE FROM '${discount_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearCategory(increment: String):Boolean{
        val query = "DELETE FROM '${category_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearProduct(increment: String):Boolean{
        val query = "DELETE FROM '${produc_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }


    fun clearSupplier(increment: String):Boolean{
        val query = "DELETE FROM '${supplier_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }


    fun clearProductAdd(increment: String):Boolean{
        val query = "DELETE FROM '${produc_add_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearProductUpdate(increment: String):Boolean{
        val query = "DELETE FROM '${produc_update_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearProductDelete(increment : String):Boolean{
        val query = "DELETE FROM '${produc_delete_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearCustomerAdd(increment: String):Boolean{
        val query = "DELETE FROM '${customer_add_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearTableAdd(increment: String):Boolean{
        val query = "DELETE FROM '${table_add_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearSalesDataAdd(increment: String):Boolean{
        val query = "DELETE FROM '${salesdata_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearSpendingDataAdd(increment: String):Boolean{
        val query = "DELETE FROM '${spendingdata_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearSpendingAdd(increment: String):Boolean{
        val query = "DELETE FROM '${spending_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearSalesAdd(increment: String):Boolean{
        val query = "DELETE FROM '${sales_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearDiscountAdd(increment: String):Boolean{
        val query = "DELETE FROM '${discount_add_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearCategoryAdd(increment: String):Boolean{
        val query = "DELETE FROM '${category_add_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearCustomerDelete(increment : String):Boolean{
        val query = "DELETE FROM '${customer_delete_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearTableDelete(increment : String):Boolean{
        val query = "DELETE FROM '${table_delete_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearDiscountDelete(increment : String):Boolean{
        val query = "DELETE FROM '${discount_delete_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearCategoryDelete(increment : String):Boolean{
        val query = "DELETE FROM '${category_delete_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearCustomerUpdate(increment: String):Boolean{
        val query = "DELETE FROM '${customer_update_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearTableUpdate(increment: String):Boolean{
        val query = "DELETE FROM '${table_update_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearDiscountUpdate(increment: String):Boolean{
        val query = "DELETE FROM '${discount_update_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearCategoryUpdate(increment: String):Boolean{
        val query = "DELETE FROM '${category_update_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearSupplierAdd(increment: String):Boolean{
        val query = "DELETE FROM '${supplier_add_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearSupplierDelete(increment : String):Boolean{
        val query = "DELETE FROM '${supplier_delete_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun clearSupplierUpdate(increment: String):Boolean{
        val query = "DELETE FROM '${supplier_update_table}' WHERE increment = '${increment}';"
        db.execSQL(query)
        return true
    }

    fun sales(increment: String): SalesSQL? {
        val query = "SELECT * FROM '${sales_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val uuid_salesData = cursor.getString(cursor.getColumnIndex("uuid_salesData")).toString()
            val id_sales = cursor.getString(cursor.getColumnIndex("id_sales")).toString()
            val id_customer = cursor.getString(cursor.getColumnIndex("id_customer")).toString()
            val id_product = cursor.getString(cursor.getColumnIndex("id_product"))
            val codeproduct = cursor.getString(cursor.getColumnIndex("codeproduct"))
            val name_product = cursor.getString(cursor.getColumnIndex("name_product"))
            val id_store = cursor.getString(cursor.getColumnIndex("id_store"))
            val user = cursor.getString(cursor.getColumnIndex("user"))
            val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
            val amount = cursor.getString(cursor.getColumnIndex("amount"))
            val price = cursor.getString(cursor.getColumnIndex("price"))
            val totalprice = cursor.getString(cursor.getColumnIndex("totalprice"))
            val hpp = cursor.getString(cursor.getColumnIndex("hpp"))
            val totalcapital = cursor.getString(cursor.getColumnIndex("totalcapital"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            val status = cursor.getString(cursor.getColumnIndex("status"))
            val note = cursor.getString(cursor.getColumnIndex("note"))
            val rest_stock = cursor.getString(cursor.getColumnIndex("rest_stock"))
            val sift = cursor.getString(cursor.getColumnIndex("sift"))
            val station = cursor.getString(cursor.getColumnIndex("station"))
            cursor.close()
            SalesSQL(increment,uuid_salesData,id_sales,id_customer,id_product,codeproduct,name_product,id_store,user,no_invoice,amount,price,totalprice,hpp,totalcapital,date,status,note,rest_stock,sift,station)
        } else {
            cursor.close()
            null
        }
    }

    fun salesUUID(uuid_salesData: String): SalesSQL? {
        val query = "SELECT * FROM '${sales_table}' WHERE uuid_salesData='$uuid_salesData'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val uuid_salesData = cursor.getString(cursor.getColumnIndex("uuid_salesData")).toString()
            val id_sales = cursor.getString(cursor.getColumnIndex("id_sales")).toString()
            val id_customer = cursor.getString(cursor.getColumnIndex("id_customer")).toString()
            val id_product = cursor.getString(cursor.getColumnIndex("id_product"))
            val codeproduct = cursor.getString(cursor.getColumnIndex("codeproduct"))
            val name_product = cursor.getString(cursor.getColumnIndex("name_product"))
            val id_store = cursor.getString(cursor.getColumnIndex("id_store"))
            val user = cursor.getString(cursor.getColumnIndex("user"))
            val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
            val amount = cursor.getString(cursor.getColumnIndex("amount"))
            val price = cursor.getString(cursor.getColumnIndex("price"))
            val totalprice = cursor.getString(cursor.getColumnIndex("totalprice"))
            val hpp = cursor.getString(cursor.getColumnIndex("hpp"))
            val totalcapital = cursor.getString(cursor.getColumnIndex("totalcapital"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            val status = cursor.getString(cursor.getColumnIndex("status"))
            val note = cursor.getString(cursor.getColumnIndex("note"))
            val rest_stock = cursor.getString(cursor.getColumnIndex("rest_stock"))
            val sift = cursor.getString(cursor.getColumnIndex("sift"))
            val station = cursor.getString(cursor.getColumnIndex("station"))
            cursor.close()
            SalesSQL(increment,uuid_salesData,id_sales,id_customer,id_product,codeproduct,name_product,id_store,user,no_invoice,amount,price,totalprice,hpp,totalcapital,date,status,note,rest_stock,sift,station)
        } else {
            cursor.close()
            null
        }
    }

    fun salesID(id_sales: String): SalesSQL? {
        val query = "SELECT * FROM '${sales_table}' WHERE id_sales='$id_sales'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val uuid_salesData = cursor.getString(cursor.getColumnIndex("uuid_salesData")).toString()
            val id_sales = cursor.getString(cursor.getColumnIndex("id_sales")).toString()
            val id_customer = cursor.getString(cursor.getColumnIndex("id_customer")).toString()
            val id_product = cursor.getString(cursor.getColumnIndex("id_product"))
            val codeproduct = cursor.getString(cursor.getColumnIndex("codeproduct"))
            val name_product = cursor.getString(cursor.getColumnIndex("name_product"))
            val id_store = cursor.getString(cursor.getColumnIndex("id_store"))
            val user = cursor.getString(cursor.getColumnIndex("user"))
            val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
            val amount = cursor.getString(cursor.getColumnIndex("amount"))
            val price = cursor.getString(cursor.getColumnIndex("price"))
            val totalprice = cursor.getString(cursor.getColumnIndex("totalprice"))
            val hpp = cursor.getString(cursor.getColumnIndex("hpp"))
            val totalcapital = cursor.getString(cursor.getColumnIndex("totalcapital"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            val status = cursor.getString(cursor.getColumnIndex("status"))
            val note = cursor.getString(cursor.getColumnIndex("note"))
            val rest_stock = cursor.getString(cursor.getColumnIndex("rest_stock"))
            val sift = cursor.getString(cursor.getColumnIndex("sift"))
            val station = cursor.getString(cursor.getColumnIndex("station"))
            cursor.close()
            SalesSQL(increment,uuid_salesData,id_sales,id_customer,id_product,codeproduct,name_product,id_store,user,no_invoice,amount,price,totalprice,hpp,totalcapital,date,status,note,rest_stock,sift,station)
        } else {
            cursor.close()
            null
        }
    }

    fun salesDataUUID(uuid: String): SalesDataSQL? {
        val query = "SELECT * FROM '${salesdata_table}' WHERE uuid='$uuid'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val uuid = cursor.getString(cursor.getColumnIndex("uuid")).toString()
            val id_sales_data = cursor.getString(cursor.getColumnIndex("id_sales_data")).toString()
            val user = cursor.getString(cursor.getColumnIndex("user")).toString()
            val id_customer = cursor.getString(cursor.getColumnIndex("id_customer"))
            val name_customer = cursor.getString(cursor.getColumnIndex("name_customer"))
            val id_store = cursor.getString(cursor.getColumnIndex("id_store"))
            val name_store = cursor.getString(cursor.getColumnIndex("name_store"))
            val email_store = cursor.getString(cursor.getColumnIndex("email_store"))
            val nohp_store = cursor.getString(cursor.getColumnIndex("nohp_store"))
            val address_store = cursor.getString(cursor.getColumnIndex("address_store"))
            val id_supplier = cursor.getString(cursor.getColumnIndex("id_supplier"))
            val name_supplier = cursor.getString(cursor.getColumnIndex("name_supplier"))
            val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            val payment = cursor.getString(cursor.getColumnIndex("payment"))
            val note = cursor.getString(cursor.getColumnIndex("note"))
            val totalorder = cursor.getString(cursor.getColumnIndex("totalorder"))
            val totalprice = cursor.getString(cursor.getColumnIndex("totalprice"))
            val totalpay = cursor.getString(cursor.getColumnIndex("totalpay"))
            val changepay = cursor.getString(cursor.getColumnIndex("changepay"))
            val status = cursor.getString(cursor.getColumnIndex("status"))
            val due_date = cursor.getString(cursor.getColumnIndex("due_date"))
            val tax = cursor.getString(cursor.getColumnIndex("tax"))
            val discount = cursor.getString(cursor.getColumnIndex("discount"))
            val service_charge = cursor.getString(cursor.getColumnIndex("service_charge"))
            val operator = cursor.getString(cursor.getColumnIndex("operator"))
            val location = cursor.getString(cursor.getColumnIndex("location"))
            val total_sales_hpp = cursor.getString(cursor.getColumnIndex("total_sales_hpp"))
            val sift = cursor.getString(cursor.getColumnIndex("sift"))
            val station = cursor.getString(cursor.getColumnIndex("station"))
            val footer = cursor.getString(cursor.getColumnIndex("footer"))
            val img = cursor.getString(cursor.getColumnIndex("img"))
            val id_table = cursor.getString(cursor.getColumnIndex("id_table"))
            cursor.close()
            SalesDataSQL(increment,uuid,id_sales_data,user,id_customer,name_customer,id_store,name_store,email_store,nohp_store,address_store,id_supplier,name_supplier,no_invoice,date,payment,note,totalorder,totalprice,totalpay,changepay,status,due_date,tax,discount,service_charge,operator,location,total_sales_hpp,sift,station,footer,img,id_table)
        } else {
            cursor.close()
            null
        }
    }

    fun salesData(increment: String): SalesDataSQL? {
        val query = "SELECT * FROM '${salesdata_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val uuid = cursor.getString(cursor.getColumnIndex("uuid")).toString()
            val id_sales_data = cursor.getString(cursor.getColumnIndex("id_sales_data")).toString()
            val user = cursor.getString(cursor.getColumnIndex("user")).toString()
            val id_customer = cursor.getString(cursor.getColumnIndex("id_customer"))
            val name_customer = cursor.getString(cursor.getColumnIndex("name_customer"))
            val id_store = cursor.getString(cursor.getColumnIndex("id_store"))
            val name_store = cursor.getString(cursor.getColumnIndex("name_store"))
            val email_store = cursor.getString(cursor.getColumnIndex("email_store"))
            val nohp_store = cursor.getString(cursor.getColumnIndex("nohp_store"))
            val address_store = cursor.getString(cursor.getColumnIndex("address_store"))
            val id_supplier = cursor.getString(cursor.getColumnIndex("id_supplier"))
            val name_supplier = cursor.getString(cursor.getColumnIndex("name_supplier"))
            val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            val payment = cursor.getString(cursor.getColumnIndex("payment"))
            val note = cursor.getString(cursor.getColumnIndex("note"))
            val totalorder = cursor.getString(cursor.getColumnIndex("totalorder"))
            val totalprice = cursor.getString(cursor.getColumnIndex("totalprice"))
            val totalpay = cursor.getString(cursor.getColumnIndex("totalpay"))
            val changepay = cursor.getString(cursor.getColumnIndex("changepay"))
            val status = cursor.getString(cursor.getColumnIndex("status"))
            val due_date = cursor.getString(cursor.getColumnIndex("due_date"))
            val tax = cursor.getString(cursor.getColumnIndex("tax"))
            val discount = cursor.getString(cursor.getColumnIndex("discount"))
            val service_charge = cursor.getString(cursor.getColumnIndex("service_charge"))
            val operator = cursor.getString(cursor.getColumnIndex("operator"))
            val location = cursor.getString(cursor.getColumnIndex("location"))
            val total_sales_hpp = cursor.getString(cursor.getColumnIndex("total_sales_hpp"))
            val sift = cursor.getString(cursor.getColumnIndex("sift"))
            val station = cursor.getString(cursor.getColumnIndex("station"))
            val footer = cursor.getString(cursor.getColumnIndex("footer"))
            val img = cursor.getString(cursor.getColumnIndex("img"))
            val id_table = cursor.getString(cursor.getColumnIndex("id_table"))
            cursor.close()
            SalesDataSQL(increment,uuid,id_sales_data,user,id_customer,name_customer,id_store,name_store,email_store,nohp_store,address_store,id_supplier,name_supplier,no_invoice,date,payment,note,totalorder,totalprice,totalpay,changepay,status,due_date,tax,discount,service_charge,operator,location,total_sales_hpp,sift,station,footer,img,id_table)
        } else {
            cursor.close()
            null
        }
    }

    fun spendingData(increment: String): SpendingDataSQL? {
        val query = "SELECT * FROM '${spendingdata_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val id_spending_data = cursor.getString(cursor.getColumnIndex("id_spending_data")).toString()
            val user = cursor.getString(cursor.getColumnIndex("user")).toString()
            val id_store = cursor.getString(cursor.getColumnIndex("id_store")).toString()
            val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            val totalnominal = cursor.getString(cursor.getColumnIndex("totalnominal"))
            val operator = cursor.getString(cursor.getColumnIndex("operator"))
            cursor.close()
            SpendingDataSQL(increment,id_spending_data,user,id_store,no_invoice,date,totalnominal,operator)
        } else {
            cursor.close()
            null
        }
    }

    fun salesDataUser(user: String): SalesDataSQL? {
        val query = "SELECT * FROM '${salesdata_table}' WHERE user='$user'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val uuid = cursor.getString(cursor.getColumnIndex("uuid")).toString()
            val id_sales_data = cursor.getString(cursor.getColumnIndex("id_sales_data")).toString()
            val user = cursor.getString(cursor.getColumnIndex("user")).toString()
            val id_customer = cursor.getString(cursor.getColumnIndex("id_customer"))
            val name_customer = cursor.getString(cursor.getColumnIndex("name_customer"))
            val id_store = cursor.getString(cursor.getColumnIndex("id_store"))
            val name_store = cursor.getString(cursor.getColumnIndex("name_store"))
            val email_store = cursor.getString(cursor.getColumnIndex("email_store"))
            val nohp_store = cursor.getString(cursor.getColumnIndex("nohp_store"))
            val address_store = cursor.getString(cursor.getColumnIndex("address_store"))
            val id_supplier = cursor.getString(cursor.getColumnIndex("id_supplier"))
            val name_supplier = cursor.getString(cursor.getColumnIndex("name_supplier"))
            val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            val payment = cursor.getString(cursor.getColumnIndex("payment"))
            val note = cursor.getString(cursor.getColumnIndex("note"))
            val totalorder = cursor.getString(cursor.getColumnIndex("totalorder"))
            val totalprice = cursor.getString(cursor.getColumnIndex("totalprice"))
            val totalpay = cursor.getString(cursor.getColumnIndex("totalpay"))
            val changepay = cursor.getString(cursor.getColumnIndex("changepay"))
            val status = cursor.getString(cursor.getColumnIndex("status"))
            val due_date = cursor.getString(cursor.getColumnIndex("due_date"))
            val tax = cursor.getString(cursor.getColumnIndex("tax"))
            val discount = cursor.getString(cursor.getColumnIndex("discount"))
            val service_charge = cursor.getString(cursor.getColumnIndex("service_charge"))
            val operator = cursor.getString(cursor.getColumnIndex("operator"))
            val location = cursor.getString(cursor.getColumnIndex("location"))
            val total_sales_hpp = cursor.getString(cursor.getColumnIndex("total_sales_hpp"))
            val sift = cursor.getString(cursor.getColumnIndex("sift"))
            val station = cursor.getString(cursor.getColumnIndex("station"))
            val footer = cursor.getString(cursor.getColumnIndex("footer"))
            val img = cursor.getString(cursor.getColumnIndex("img"))
            val id_table = cursor.getString(cursor.getColumnIndex("id_table"))
            cursor.close()
            SalesDataSQL(increment,uuid,id_sales_data,user,id_customer,name_customer,id_store,name_store,email_store,nohp_store,address_store,id_supplier,name_supplier,no_invoice,date,payment,note,totalorder,totalprice,totalpay,changepay,status,due_date,tax,discount,service_charge,operator,location,total_sales_hpp,sift,station,footer,img,id_table)
        } else {
            cursor.close()
            null
        }
    }

    fun salesDataID(id_sales_data: String): SalesDataSQL? {
        val query = "SELECT * FROM '${salesdata_table}' WHERE id_sales_data='$id_sales_data'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val uuid = cursor.getString(cursor.getColumnIndex("uuid")).toString()
            val id_sales_data = cursor.getString(cursor.getColumnIndex("id_sales_data")).toString()
            val user = cursor.getString(cursor.getColumnIndex("user")).toString()
            val id_customer = cursor.getString(cursor.getColumnIndex("id_customer"))
            val name_customer = cursor.getString(cursor.getColumnIndex("name_customer"))
            val id_store = cursor.getString(cursor.getColumnIndex("id_store"))
            val name_store = cursor.getString(cursor.getColumnIndex("name_store"))
            val email_store = cursor.getString(cursor.getColumnIndex("email_store"))
            val nohp_store = cursor.getString(cursor.getColumnIndex("nohp_store"))
            val address_store = cursor.getString(cursor.getColumnIndex("address_store"))
            val id_supplier = cursor.getString(cursor.getColumnIndex("id_supplier"))
            val name_supplier = cursor.getString(cursor.getColumnIndex("name_supplier"))
            val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            val payment = cursor.getString(cursor.getColumnIndex("payment"))
            val note = cursor.getString(cursor.getColumnIndex("note"))
            val totalorder = cursor.getString(cursor.getColumnIndex("totalorder"))
            val totalprice = cursor.getString(cursor.getColumnIndex("totalprice"))
            val totalpay = cursor.getString(cursor.getColumnIndex("totalpay"))
            val changepay = cursor.getString(cursor.getColumnIndex("changepay"))
            val status = cursor.getString(cursor.getColumnIndex("status"))
            val due_date = cursor.getString(cursor.getColumnIndex("due_date"))
            val tax = cursor.getString(cursor.getColumnIndex("tax"))
            val discount = cursor.getString(cursor.getColumnIndex("discount"))
            val service_charge = cursor.getString(cursor.getColumnIndex("service_charge"))
            val operator = cursor.getString(cursor.getColumnIndex("operator"))
            val location = cursor.getString(cursor.getColumnIndex("location"))
            val total_sales_hpp = cursor.getString(cursor.getColumnIndex("total_sales_hpp"))
            val sift = cursor.getString(cursor.getColumnIndex("sift"))
            val station = cursor.getString(cursor.getColumnIndex("station"))
            val footer = cursor.getString(cursor.getColumnIndex("footer"))
            val img = cursor.getString(cursor.getColumnIndex("img"))
            val id_table = cursor.getString(cursor.getColumnIndex("id_table"))
            cursor.close()
            SalesDataSQL(increment,uuid,id_sales_data,user,id_customer,name_customer,id_store,name_store,email_store,nohp_store,address_store,id_supplier,name_supplier,no_invoice,date,payment,note,totalorder,totalprice,totalpay,changepay,status,due_date,tax,discount,service_charge,operator,location,total_sales_hpp,sift,station,footer,img,id_table)
        } else {
            cursor.close()
            null
        }
    }

    fun spending(increment: String): SpendingSQL? {
        val query = "SELECT * FROM '${spending_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val id_spending = cursor.getString(cursor.getColumnIndex("id_spending")).toString()
            val name_spending = cursor.getString(cursor.getColumnIndex("name_spending")).toString()
            val user = cursor.getString(cursor.getColumnIndex("user")).toString()
            val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
            val nominal = cursor.getString(cursor.getColumnIndex("nominal"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            cursor.close()
            SpendingSQL(increment,id_spending,name_spending,user,no_invoice,nominal,date)
        } else {
            cursor.close()
            null
        }
    }

    fun store(increment: String): StoreSQL? {
        val query = "SELECT * FROM '${store_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val id_store = cursor.getString(cursor.getColumnIndex("id_store")).toString()
            val type = cursor.getString(cursor.getColumnIndex("type"))
            val name_store = cursor.getString(cursor.getColumnIndex("name_store"))
            val nohp = cursor.getString(cursor.getColumnIndex("nohp"))
            val address = cursor.getString(cursor.getColumnIndex("address"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val omset = cursor.getString(cursor.getColumnIndex("omset"))
            val transaksi = cursor.getString(cursor.getColumnIndex("transaksi"))
            val order = cursor.getString(cursor.getColumnIndex("order_x"))
            val tax = cursor.getString(cursor.getColumnIndex("tax"))
            val service_charge = cursor.getString(cursor.getColumnIndex("service_charge"))
            val number_store = cursor.getString(cursor.getColumnIndex("number_store"))
            val level = cursor.getString(cursor.getColumnIndex("level"))
            val footer = cursor.getString(cursor.getColumnIndex("footer"))
            val photo = cursor.getString(cursor.getColumnIndex("photo"))
            cursor.close()
            StoreSQL(increment,id_store,type,name_store,nohp,address,email,omset,transaksi,order,tax,service_charge,number_store,level,footer,photo)
        } else {
            cursor.close()
            null
        }
    }

    fun storeID(id_store: String): StoreSQL? {
        val query = "SELECT * FROM '${store_table}' WHERE id_store='$id_store'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val id_store = cursor.getString(cursor.getColumnIndex("id_store")).toString()
            val type = cursor.getString(cursor.getColumnIndex("type"))
            val name_store = cursor.getString(cursor.getColumnIndex("name_store"))
            val nohp = cursor.getString(cursor.getColumnIndex("nohp"))
            val address = cursor.getString(cursor.getColumnIndex("address"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val omset = cursor.getString(cursor.getColumnIndex("omset"))
            val transaksi = cursor.getString(cursor.getColumnIndex("transaksi"))
            val order = cursor.getString(cursor.getColumnIndex("order_x"))
            val tax = cursor.getString(cursor.getColumnIndex("tax"))
            val service_charge = cursor.getString(cursor.getColumnIndex("service_charge"))
            val number_store = cursor.getString(cursor.getColumnIndex("number_store"))
            val level = cursor.getString(cursor.getColumnIndex("level"))
            val footer = cursor.getString(cursor.getColumnIndex("footer"))
            val photo = cursor.getString(cursor.getColumnIndex("photo"))
            cursor.close()
            StoreSQL(increment,id_store,type,name_store,nohp,address,email,omset,transaksi,order,tax,service_charge,number_store,level,footer,photo)
        } else {
            cursor.close()
            null
        }
    }

    fun customer(increment: String): CustomerSQL? {
        val query = "SELECT * FROM '${customer_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key")).toString()
            val id_customer = cursor.getString(cursor.getColumnIndex("id_customer")).toString()
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val name_customer = cursor.getString(cursor.getColumnIndex("name_customer"))
            val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
            val address = cursor.getString(cursor.getColumnIndex("address"))
            val customercode = cursor.getString(cursor.getColumnIndex("customercode"))
            cursor.close()
            CustomerSQL(increment,key,id_customer,email,name_customer,telephone,address,customercode)
        } else {
            cursor.close()
            null
        }
    }

    fun table(increment: String): TableSQL? {
        val query = "SELECT * FROM '${table_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key")).toString()
            val id_table = cursor.getString(cursor.getColumnIndex("id_table")).toString()
            val name_table = cursor.getString(cursor.getColumnIndex("name_table"))
            cursor.close()
            TableSQL(increment,key,id_table,name_table)
        } else {
            cursor.close()
            null
        }
    }

    fun customerID(id_customer: String): CustomerSQL? {
        val query = "SELECT * FROM '${customer_table}' WHERE id_customer='$id_customer'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key")).toString()
            val id_customer = cursor.getString(cursor.getColumnIndex("id_customer")).toString()
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val name_customer = cursor.getString(cursor.getColumnIndex("name_customer"))
            val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
            val address = cursor.getString(cursor.getColumnIndex("address"))
            val customercode = cursor.getString(cursor.getColumnIndex("customercode"))
            cursor.close()
            CustomerSQL(increment,key,id_customer,email,name_customer,telephone,address,customercode)
        } else {
            cursor.close()
            null
        }
    }

    fun tableID(id_customer: String): TableSQL? {
        val query = "SELECT * FROM '${customer_table}' WHERE id_customer='$id_customer'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key")).toString()
            val id_table = cursor.getString(cursor.getColumnIndex("id_table")).toString()
            val name_table = cursor.getString(cursor.getColumnIndex("name_table"))
            cursor.close()
            TableSQL(increment,key,id_table,name_table)
        } else {
            cursor.close()
            null
        }
    }

    fun supplier(increment: String): SupplierSQL? {
        val query = "SELECT * FROM '${supplier_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_supplier = cursor.getString(cursor.getColumnIndex("id_supplier")).toString()
            val name_supplier = cursor.getString(cursor.getColumnIndex("name_supplier"))
            val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val address = cursor.getString(cursor.getColumnIndex("address"))
            cursor.close()
            SupplierSQL(increment,key,id_supplier,name_supplier,telephone,email,address)
        } else {
            cursor.close()
            null
        }
    }

    fun supplierID(id_supplier: String): SupplierSQL? {
        val query = "SELECT * FROM '${supplier_table}' WHERE id_supplier='$id_supplier'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_supplier = cursor.getString(cursor.getColumnIndex("id_supplier")).toString()
            val name_supplier = cursor.getString(cursor.getColumnIndex("name_supplier"))
            val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val address = cursor.getString(cursor.getColumnIndex("address"))
            cursor.close()
            SupplierSQL(increment,key,id_supplier,name_supplier,telephone,email,address)
        } else {
            cursor.close()
            null
        }
    }

    fun discount(increment: String): DiscountSQL? {
        val query = "SELECT * FROM '${discount_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_discount = cursor.getString(cursor.getColumnIndex("id_discount")).toString()
            val name_discount = cursor.getString(cursor.getColumnIndex("name_discount"))
            val note = cursor.getString(cursor.getColumnIndex("note"))
            val type = cursor.getString(cursor.getColumnIndex("type"))
            val nominal = cursor.getString(cursor.getColumnIndex("nominal"))
            cursor.close()
            DiscountSQL(increment,key,id_discount,name_discount,note,type,nominal)
        } else {
            cursor.close()
            null
        }
    }

    fun link(increment: String): LinkSQL? {
        val query = "SELECT * FROM '${link_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_link = cursor.getString(cursor.getColumnIndex("id_link")).toString()
            val name_link = cursor.getString(cursor.getColumnIndex("name_link"))
            val img = cursor.getString(cursor.getColumnIndex("img"))
            cursor.close()
            LinkSQL(increment,key,id_link,name_link,img)
        } else {
            cursor.close()
            null
        }
    }

    fun category(increment: String): CategorySQL? {
        val query = "SELECT * FROM '${category_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_category = cursor.getString(cursor.getColumnIndex("id_category")).toString()
            val name_category = cursor.getString(cursor.getColumnIndex("name_category"))
            cursor.close()
            CategorySQL(increment,key,id_category,name_category)
        } else {
            cursor.close()
            null
        }
    }

    fun discountID(id_discount: String): DiscountSQL? {
        val query = "SELECT * FROM '${discount_table}' WHERE id_discount='$id_discount'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_discount = cursor.getString(cursor.getColumnIndex("id_discount")).toString()
            val name_discount = cursor.getString(cursor.getColumnIndex("name_discount"))
            val note = cursor.getString(cursor.getColumnIndex("note"))
            val type = cursor.getString(cursor.getColumnIndex("type"))
            val nominal = cursor.getString(cursor.getColumnIndex("nominal"))
            cursor.close()
            DiscountSQL(increment,key,id_discount,name_discount,note,type,nominal)
        } else {
            cursor.close()
            null
        }
    }

    fun categoryID(id_category: String): CategorySQL? {
        val query = "SELECT * FROM '${category_table}' WHERE id_discount='$id_category'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_category = cursor.getString(cursor.getColumnIndex("id_category")).toString()
            val name_category = cursor.getString(cursor.getColumnIndex("name_category"))
            cursor.close()
            CategorySQL(increment,key,id_category,name_category)
        } else {
            cursor.close()
            null
        }
    }

    fun product(increment: String): ProductSQL? {
        val query = "SELECT * FROM '${produc_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val id_product = cursor.getString(cursor.getColumnIndex("id_product")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val nama = cursor.getString(cursor.getColumnIndex("nama"))
            val unit = cursor.getString(cursor.getColumnIndex("unit"))
            val kode = cursor.getString(cursor.getColumnIndex("kode"))
            val idkategori = cursor.getString(cursor.getColumnIndex("idkategori"))
            val namakategori = cursor.getString(cursor.getColumnIndex("namakategori"))
            val active = cursor.getString(cursor.getColumnIndex("active"))
            val beli = cursor.getString(cursor.getColumnIndex("beli"))
            val jual = cursor.getString(cursor.getColumnIndex("jual"))
            val stok = cursor.getString(cursor.getColumnIndex("stok"))
            val minstok = cursor.getString(cursor.getColumnIndex("minstok"))
            val deskripsi = cursor.getString(cursor.getColumnIndex("deskripsi"))
            val online = cursor.getString(cursor.getColumnIndex("online"))
            val havestok = cursor.getString(cursor.getColumnIndex("havestok"))
            val grosir = cursor.getString(cursor.getColumnIndex("grosir"))
            val tax = cursor.getString(cursor.getColumnIndex("tax"))
            val alertstock = cursor.getString(cursor.getColumnIndex("alertstock"))
            val img = cursor.getString(cursor.getColumnIndex("img"))
            cursor.close()
            ProductSQL(increment,id_product,key,nama,unit,kode,idkategori,namakategori,active,beli,jual,stok,minstok,deskripsi,online,havestok,grosir,tax,alertstock,img)
        } else {
            cursor.close()
            null
        }
    }

    fun productID(id: String): ProductSQL? {
        val query = "SELECT * FROM '${produc_table}' WHERE id_product='$id'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val id_product = cursor.getString(cursor.getColumnIndex("id_product")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val nama = cursor.getString(cursor.getColumnIndex("nama"))
            val unit = cursor.getString(cursor.getColumnIndex("unit"))
            val kode = cursor.getString(cursor.getColumnIndex("kode"))
            val idkategori = cursor.getString(cursor.getColumnIndex("idkategori"))
            val namakategori = cursor.getString(cursor.getColumnIndex("namakategori"))
            val active = cursor.getString(cursor.getColumnIndex("active"))
            val beli = cursor.getString(cursor.getColumnIndex("beli"))
            val jual = cursor.getString(cursor.getColumnIndex("jual"))
            val stok = cursor.getString(cursor.getColumnIndex("stok"))
            val minstok = cursor.getString(cursor.getColumnIndex("minstok"))
            val deskripsi = cursor.getString(cursor.getColumnIndex("deskripsi"))
            val online = cursor.getString(cursor.getColumnIndex("online"))
            val havestok = cursor.getString(cursor.getColumnIndex("havestok"))
            val grosir = cursor.getString(cursor.getColumnIndex("grosir"))
            val tax = cursor.getString(cursor.getColumnIndex("tax"))
            val alertstock = cursor.getString(cursor.getColumnIndex("alertstock"))
            val img = cursor.getString(cursor.getColumnIndex("img"))
            cursor.close()
            ProductSQL(increment,id_product,key,nama,unit,kode,idkategori,namakategori,active,beli,jual,stok,minstok,deskripsi,online,havestok,grosir,tax,alertstock,img)
        } else {
            cursor.close()
            null
        }
    }


    fun productAdd(increment: String): ProductSQLAdd? {
        val query = "SELECT * FROM '${produc_add_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val nama = cursor.getString(cursor.getColumnIndex("nama"))
            val unit = cursor.getString(cursor.getColumnIndex("unit"))
            val kode = cursor.getString(cursor.getColumnIndex("kode"))
            val idkategori = cursor.getString(cursor.getColumnIndex("idkategori"))
            val beli = cursor.getString(cursor.getColumnIndex("beli"))
            val jual = cursor.getString(cursor.getColumnIndex("jual"))
            val stok = cursor.getString(cursor.getColumnIndex("stok"))
            val minstok = cursor.getString(cursor.getColumnIndex("minstok"))
            val deskripsi = cursor.getString(cursor.getColumnIndex("deskripsi"))
            val online = cursor.getString(cursor.getColumnIndex("online"))
            val havestok = cursor.getString(cursor.getColumnIndex("havestok"))
            val grosir = cursor.getString(cursor.getColumnIndex("grosir"))
            val tax = cursor.getString(cursor.getColumnIndex("tax"))
            val alertstock = cursor.getString(cursor.getColumnIndex("alertstock"))
            val img = cursor.getString(cursor.getColumnIndex("img"))
            cursor.close()
            ProductSQLAdd(increment,key,nama,unit,kode,idkategori,beli,jual,stok,minstok,deskripsi,online,havestok,grosir,tax,alertstock,img)
        } else {
            cursor.close()
            null
        }
    }

    fun productUpdate(increment: String): ProductSQLUpdate? {
        val query = "SELECT * FROM '${produc_update_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment"))
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id = cursor.getString(cursor.getColumnIndex("id"))
            val nama = cursor.getString(cursor.getColumnIndex("nama"))
            val unit = cursor.getString(cursor.getColumnIndex("unit"))
            val kode = cursor.getString(cursor.getColumnIndex("kode"))
            val idkategori = cursor.getString(cursor.getColumnIndex("idkategori"))
            val beli = cursor.getString(cursor.getColumnIndex("beli"))
            val jual = cursor.getString(cursor.getColumnIndex("jual"))
            val stok = cursor.getString(cursor.getColumnIndex("stok"))
            val minstok = cursor.getString(cursor.getColumnIndex("minstok"))
            val deskripsi = cursor.getString(cursor.getColumnIndex("deskripsi"))
            val online = cursor.getString(cursor.getColumnIndex("online"))
            val havestok = cursor.getString(cursor.getColumnIndex("havestok"))
            val grosir = cursor.getString(cursor.getColumnIndex("grosir"))
            val tax = cursor.getString(cursor.getColumnIndex("tax"))
            val alertstock = cursor.getString(cursor.getColumnIndex("alertstock"))
            val img = cursor.getString(cursor.getColumnIndex("img"))
            cursor.close()
            ProductSQLUpdate(increment,key,id,nama,unit,kode,idkategori,beli,jual,stok,minstok,deskripsi,online,havestok,grosir,tax,alertstock,img)
        } else {
            cursor.close()
            null
        }
    }

    fun productDelete(increment: String): ProductSQLDelete? {
        val query = "SELECT * FROM '${produc_delete_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment"))
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id = cursor.getString(cursor.getColumnIndex("id"))
            cursor.close()
            ProductSQLDelete(increment,key,id)
        } else {
            cursor.close()
            null
        }
    }


    fun customerAdd(increment: String): CustomerSQLAdd? {
        val query = "SELECT * FROM '${customer_add_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val name_customer = cursor.getString(cursor.getColumnIndex("name_customer"))
            val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val address = cursor.getString(cursor.getColumnIndex("address"))
            val customercode = cursor.getString(cursor.getColumnIndex("customercode"))
            cursor.close()
            CustomerSQLAdd(increment,key,name_customer,telephone,email,address,customercode)
        } else {
            cursor.close()
            null
        }
    }

    fun customerDelete(increment: String): CustomerSQLDelete? {
        val query = "SELECT * FROM '${customer_delete_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment"))
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_customer = cursor.getString(cursor.getColumnIndex("id"))
            cursor.close()
            CustomerSQLDelete(increment,key,id_customer)
        } else {
            cursor.close()
            null
        }
    }


    fun customerUpdate(increment: String): CustomerSQLUpdate? {
        val query = "SELECT * FROM '${customer_update_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment"))
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_customer = cursor.getString(cursor.getColumnIndex("id"))
            val name_customer = cursor.getString(cursor.getColumnIndex("nama"))
            val telephone = cursor.getString(cursor.getColumnIndex("phone"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val address = cursor.getString(cursor.getColumnIndex("address"))
            val customercode = cursor.getString(cursor.getColumnIndex("customercode"))
            cursor.close()
            CustomerSQLUpdate(increment,key,id_customer,name_customer,telephone,email,address,customercode)
        } else {
            cursor.close()
            null
        }
    }


    fun tableAdd(increment: String): TableSQLAdd? {
        val query = "SELECT * FROM '${table_add_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val name_table = cursor.getString(cursor.getColumnIndex("name_table"))
            cursor.close()
            TableSQLAdd(increment,key,name_table)
        } else {
            cursor.close()
            null
        }
    }

    fun tableDelete(increment: String): TableSQLDelete? {
        val query = "SELECT * FROM '${table_delete_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment"))
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_table = cursor.getString(cursor.getColumnIndex("id"))
            cursor.close()
            TableSQLDelete(increment,key,id_table)
        } else {
            cursor.close()
            null
        }
    }


    fun tableUpdate(increment: String): TableSQLUpdate? {
        val query = "SELECT * FROM '${table_update_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment"))
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_table = cursor.getString(cursor.getColumnIndex("id"))
            val name_table = cursor.getString(cursor.getColumnIndex("nama"))
            cursor.close()
            TableSQLUpdate(increment,key,id_table,name_table)
        } else {
            cursor.close()
            null
        }
    }

    fun supplierAdd(increment: String): SupplierSQLAdd? {
        val query = "SELECT * FROM '${supplier_add_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val name_supplier = cursor.getString(cursor.getColumnIndex("name_supplier"))
            val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val address = cursor.getString(cursor.getColumnIndex("address"))
            cursor.close()
            SupplierSQLAdd(increment,key,name_supplier,telephone,email,address)
        } else {
            cursor.close()
            null
        }
    }

    fun discountAdd(increment: String): DiscountSQLAdd? {
        val query = "SELECT * FROM '${discount_add_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val name_discount = cursor.getString(cursor.getColumnIndex("name_discount"))
            val note = cursor.getString(cursor.getColumnIndex("note"))
            val type = cursor.getString(cursor.getColumnIndex("type"))
            val nominal = cursor.getString(cursor.getColumnIndex("nominal"))
            cursor.close()
            DiscountSQLAdd(increment,key,name_discount,note,type,nominal)
        } else {
            cursor.close()
            null
        }
    }

    fun categoryAdd(increment: String): CategorySQLAdd? {
        val query = "SELECT * FROM '${category_add_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val name_category = cursor.getString(cursor.getColumnIndex("name_category"))
            cursor.close()
            CategorySQLAdd(increment,key,name_category)
        } else {
            cursor.close()
            null
        }
    }

    fun supplierDelete(increment: String): SupplierSQLDelete? {
        val query = "SELECT * FROM '${supplier_delete_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment"))
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_supplier = cursor.getString(cursor.getColumnIndex("id"))
            cursor.close()
            SupplierSQLDelete(increment,key,id_supplier)
        } else {
            cursor.close()
            null
        }
    }




    fun supplierUpdate(increment: String): SupplierSQLUpdate? {
        val query = "SELECT * FROM '${supplier_update_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment"))
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_supplier = cursor.getString(cursor.getColumnIndex("id"))
            val name_supplier = cursor.getString(cursor.getColumnIndex("nama"))
            val telephone = cursor.getString(cursor.getColumnIndex("phone"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val address = cursor.getString(cursor.getColumnIndex("address"))
            cursor.close()
            SupplierSQLUpdate(increment,key,id_supplier,name_supplier,telephone,email,address)
        } else {
            cursor.close()
            null
        }
    }

    fun discountUpdate(increment: String): SupplierSQLUpdate? {
        val query = "SELECT * FROM '${discount_update_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment"))
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_discount = cursor.getString(cursor.getColumnIndex("id"))
            val name_discount = cursor.getString(cursor.getColumnIndex("nama"))
            val note = cursor.getString(cursor.getColumnIndex("note"))
            val type = cursor.getString(cursor.getColumnIndex("type"))
            val nominal = cursor.getString(cursor.getColumnIndex("nominal"))
            cursor.close()
            SupplierSQLUpdate(increment,key,id_discount,name_discount,note,type,nominal)
        } else {
            cursor.close()
            null
        }
    }

    fun categoryUpdate(increment: String): CategorySQLUpdate? {
        val query = "SELECT * FROM '${category_update_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment"))
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_category = cursor.getString(cursor.getColumnIndex("id_category"))
            val name_category = cursor.getString(cursor.getColumnIndex("name_category"))
            cursor.close()
            CategorySQLUpdate(increment,key,id_category,name_category)
        } else {
            cursor.close()
            null
        }
    }

    fun discountDelete(increment: String): DiscountSQLDelete? {
        val query = "SELECT * FROM '${discount_delete_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment"))
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_discount = cursor.getString(cursor.getColumnIndex("id"))
            cursor.close()
            DiscountSQLDelete(increment,key,id_discount)
        } else {
            cursor.close()
            null
        }
    }

    fun categoryDelete(increment: String): CategorySQLDelete? {
        val query = "SELECT * FROM '${category_delete_table}' WHERE increment='$increment'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val increment = cursor.getString(cursor.getColumnIndex("increment"))
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val id_category = cursor.getString(cursor.getColumnIndex("id"))
            cursor.close()
            CategorySQLDelete(increment,key,id_category)
        } else {
            cursor.close()
            null
        }
    }

    fun userKey(key: String): UserSQL? {
        val query = "SELECT * FROM '${user_table}' WHERE key='$key'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val currency = cursor.getString(cursor.getColumnIndex("currency"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val level = cursor.getString(cursor.getColumnIndex("level"))
            val master = cursor.getString(cursor.getColumnIndex("master"))
            val packages = cursor.getString(cursor.getColumnIndex("packages"))
            val typestore = cursor.getString(cursor.getColumnIndex("typestore"))
            val decimal = cursor.getString(cursor.getColumnIndex("decimal"))
            val id_store = cursor.getString(cursor.getColumnIndex("id_store"))
            val user = cursor.getString(cursor.getColumnIndex("user"))
            val phone = cursor.getString(cursor.getColumnIndex("phone"))
            val password = cursor.getString(cursor.getColumnIndex("password"))
            val latitude = cursor.getString(cursor.getColumnIndex("latitude"))
            val longitude = cursor.getString(cursor.getColumnIndex("longitude"))
            cursor.close()
            UserSQL(key,currency,name,user,level,master,packages,typestore,decimal,id_store,phone,password,latitude,longitude)
        } else {
            cursor.close()
            null
        }
    }

    fun user(phone: String): UserSQL? {
        val query = "SELECT * FROM '${user_table}' WHERE phone='$phone'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val currency = cursor.getString(cursor.getColumnIndex("currency"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val level = cursor.getString(cursor.getColumnIndex("level"))
            val master = cursor.getString(cursor.getColumnIndex("master"))
            val packages = cursor.getString(cursor.getColumnIndex("packages"))
            val typestore = cursor.getString(cursor.getColumnIndex("typestore"))
            val decimal = cursor.getString(cursor.getColumnIndex("decimal"))
            val id_store = cursor.getString(cursor.getColumnIndex("id_store"))
            val user = cursor.getString(cursor.getColumnIndex("user"))
            val password = cursor.getString(cursor.getColumnIndex("password"))
            val latitude = cursor.getString(cursor.getColumnIndex("latitude"))
            val longitude = cursor.getString(cursor.getColumnIndex("longitude"))
            cursor.close()
            UserSQL(key,currency,name,user,level,master,packages,typestore,decimal,id_store,phone,password,latitude,longitude)
        } else {
            cursor.close()
            null
        }
    }

    fun user(phone: String,password: String): UserSQL? {
        val query = "SELECT * FROM '${user_table}' WHERE phone='$phone' AND password='$password'"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            val key = cursor.getString(cursor.getColumnIndex("key"))
            val currency = cursor.getString(cursor.getColumnIndex("currency"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val level = cursor.getString(cursor.getColumnIndex("level"))
            val master = cursor.getString(cursor.getColumnIndex("master"))
            val packages = cursor.getString(cursor.getColumnIndex("packages"))
            val typestore = cursor.getString(cursor.getColumnIndex("typestore"))
            val decimal = cursor.getString(cursor.getColumnIndex("decimal"))
            val id_store = cursor.getString(cursor.getColumnIndex("id_store"))
            val user = cursor.getString(cursor.getColumnIndex("user"))
            val latitude = cursor.getString(cursor.getColumnIndex("latitude"))
            val longitude = cursor.getString(cursor.getColumnIndex("longitude"))
            cursor.close()
            UserSQL(key,currency,name,user,level,master,packages,typestore,decimal,id_store,phone,password,latitude,longitude)
        } else {
            cursor.close()
            null
        }
    }

    fun allSalesData(): List<SalesDataSQL>? {
        val salesDataAll = mutableListOf<SalesDataSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${salesdata_table}'", null)

        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
                val uuid = cursor.getString(cursor.getColumnIndex("uuid")).toString()
                val id_sales_data = cursor.getString(cursor.getColumnIndex("id_sales_data")).toString()
                val user = cursor.getString(cursor.getColumnIndex("user")).toString()
                val id_customer = cursor.getString(cursor.getColumnIndex("id_customer"))
                val name_customer = cursor.getString(cursor.getColumnIndex("name_customer"))
                val id_store = cursor.getString(cursor.getColumnIndex("id_store"))
                val name_store = cursor.getString(cursor.getColumnIndex("name_store"))
                val email_store = cursor.getString(cursor.getColumnIndex("email_store"))
                val nohp_store = cursor.getString(cursor.getColumnIndex("nohp_store"))
                val address_store = cursor.getString(cursor.getColumnIndex("address_store"))
                val id_supplier = cursor.getString(cursor.getColumnIndex("id_supplier"))
                val name_supplier = cursor.getString(cursor.getColumnIndex("name_supplier"))
                val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val payment = cursor.getString(cursor.getColumnIndex("payment"))
                val note = cursor.getString(cursor.getColumnIndex("note"))
                val totalorder = cursor.getString(cursor.getColumnIndex("totalorder"))
                val totalprice = cursor.getString(cursor.getColumnIndex("totalprice"))
                val totalpay = cursor.getString(cursor.getColumnIndex("totalpay"))
                val changepay = cursor.getString(cursor.getColumnIndex("changepay"))
                val status = cursor.getString(cursor.getColumnIndex("status"))
                val due_date = cursor.getString(cursor.getColumnIndex("due_date"))
                val tax = cursor.getString(cursor.getColumnIndex("tax"))
                val discount = cursor.getString(cursor.getColumnIndex("discount"))
                val service_charge = cursor.getString(cursor.getColumnIndex("service_charge"))
                val operator = cursor.getString(cursor.getColumnIndex("operator"))
                val location = cursor.getString(cursor.getColumnIndex("location"))
                val total_sales_hpp = cursor.getString(cursor.getColumnIndex("total_sales_hpp"))
                val sift = cursor.getString(cursor.getColumnIndex("sift"))
                val station = cursor.getString(cursor.getColumnIndex("station"))
                val footer = cursor.getString(cursor.getColumnIndex("footer"))
                val img = cursor.getString(cursor.getColumnIndex("img"))
                val id_table = cursor.getString(cursor.getColumnIndex("id_table"))
                val salesData = SalesDataSQL(increment,uuid,id_sales_data,user,id_customer,name_customer,id_store,name_store,email_store,nohp_store,address_store,id_supplier,name_supplier,no_invoice,date,payment,note,totalorder,totalprice,totalpay,changepay,status,due_date,tax,discount,service_charge,operator,location,total_sales_hpp,sift,station,footer,img,id_table)
                salesDataAll.add(salesData)
            } while (cursor.moveToNext())
        }else {
            cursor.close()
            null
        }
        return salesDataAll.reversed()
    }

    fun allSpendingData(): List<SpendingDataSQL>? {
        val spendingDataAll = mutableListOf<SpendingDataSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${spendingdata_table}'", null)

        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
                val id_spending_data = cursor.getString(cursor.getColumnIndex("id_spending_data")).toString()
                val user = cursor.getString(cursor.getColumnIndex("user")).toString()
                val id_store = cursor.getString(cursor.getColumnIndex("id_store")).toString()
                val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val totalnominal = cursor.getString(cursor.getColumnIndex("totalnominal"))
                val operator = cursor.getString(cursor.getColumnIndex("operator"))
                val spendingData = SpendingDataSQL(increment,id_spending_data,user,id_store,no_invoice,date,totalnominal,operator)
                spendingDataAll.add(spendingData)
            } while (cursor.moveToNext())
        }else {
            cursor.close()
            null
        }
        return spendingDataAll.reversed()
    }

    fun allSpending(): List<SpendingSQL>? {
        val spendingAll = mutableListOf<SpendingSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${spending_table}'", null)

        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
                val id_spending = cursor.getString(cursor.getColumnIndex("id_spending")).toString()
                val name_spending = cursor.getString(cursor.getColumnIndex("name_spending")).toString()
                val user = cursor.getString(cursor.getColumnIndex("user")).toString()
                val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
                val nominal = cursor.getString(cursor.getColumnIndex("nominal"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val spending = SpendingSQL(increment,id_spending,name_spending,user,no_invoice,nominal,date)
                spendingAll.add(spending)
            } while (cursor.moveToNext())
        }else {
            cursor.close()
            null
        }
        return spendingAll.reversed()
    }

    fun allSalesUUID(uuid : String): List<SalesSQL> {
        val salesAll = mutableListOf<SalesSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${sales_table}' WHERE uuid_salesData = '${uuid}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
                val uuid_salesData = cursor.getString(cursor.getColumnIndex("uuid_salesData")).toString()
                val id_sales = cursor.getString(cursor.getColumnIndex("id_sales")).toString()
                val id_customer = cursor.getString(cursor.getColumnIndex("id_customer")).toString()
                val id_product = cursor.getString(cursor.getColumnIndex("id_product"))
                val codeproduct = cursor.getString(cursor.getColumnIndex("codeproduct"))
                val name_product = cursor.getString(cursor.getColumnIndex("name_product"))
                val id_store = cursor.getString(cursor.getColumnIndex("id_store"))
                val user = cursor.getString(cursor.getColumnIndex("user"))
                val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
                val amount = cursor.getString(cursor.getColumnIndex("amount"))
                val price = cursor.getString(cursor.getColumnIndex("price"))
                val totalprice = cursor.getString(cursor.getColumnIndex("totalprice"))
                val hpp = cursor.getString(cursor.getColumnIndex("hpp"))
                val totalcapital = cursor.getString(cursor.getColumnIndex("totalcapital"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val status = cursor.getString(cursor.getColumnIndex("status"))
                val note = cursor.getString(cursor.getColumnIndex("note"))
                val rest_stock = cursor.getString(cursor.getColumnIndex("rest_stock"))
                val sift = cursor.getString(cursor.getColumnIndex("sift"))
                val station = cursor.getString(cursor.getColumnIndex("station"))
                val sales = SalesSQL(increment,uuid_salesData,id_sales,id_customer,id_product,codeproduct,name_product,id_store,user,no_invoice,amount,price,totalprice,hpp,totalcapital,date,status,note,rest_stock,sift,station)
                salesAll.add(sales)
            } while (cursor.moveToNext())
        }else {
            cursor.close()
            null
        }
        return salesAll.sorted()
    }

    fun allSales(): List<SalesSQL>? {
        val salesAll = mutableListOf<SalesSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${sales_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
                val uuid_salesData = cursor.getString(cursor.getColumnIndex("uuid_salesData")).toString()
                val id_sales = cursor.getString(cursor.getColumnIndex("id_sales")).toString()
                val id_customer = cursor.getString(cursor.getColumnIndex("id_customer")).toString()
                val id_product = cursor.getString(cursor.getColumnIndex("id_product"))
                val codeproduct = cursor.getString(cursor.getColumnIndex("codeproduct"))
                val name_product = cursor.getString(cursor.getColumnIndex("name_product"))
                val id_store = cursor.getString(cursor.getColumnIndex("id_store"))
                val user = cursor.getString(cursor.getColumnIndex("user"))
                val no_invoice = cursor.getString(cursor.getColumnIndex("no_invoice"))
                val amount = cursor.getString(cursor.getColumnIndex("amount"))
                val price = cursor.getString(cursor.getColumnIndex("price"))
                val totalprice = cursor.getString(cursor.getColumnIndex("totalprice"))
                val hpp = cursor.getString(cursor.getColumnIndex("hpp"))
                val totalcapital = cursor.getString(cursor.getColumnIndex("totalcapital"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val status = cursor.getString(cursor.getColumnIndex("status"))
                val note = cursor.getString(cursor.getColumnIndex("note"))
                val rest_stock = cursor.getString(cursor.getColumnIndex("rest_stock"))
                val sift = cursor.getString(cursor.getColumnIndex("sift"))
                val station = cursor.getString(cursor.getColumnIndex("station"))
                val sales = SalesSQL(increment,uuid_salesData,id_sales,id_customer,id_product,codeproduct,name_product,id_store,user,no_invoice,amount,price,totalprice,hpp,totalcapital,date,status,note,rest_stock,sift,station)
                salesAll.add(sales)
            } while (cursor.moveToNext())
        }else {
            cursor.close()
            null
        }
        return salesAll.sorted()
    }

    fun allDiscount(): List<DiscountSQL> {

        val discountAll = mutableListOf<DiscountSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${discount_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_discount = cursor.getString(cursor.getColumnIndex("id_discount")).toString()
                val name_discount = cursor.getString(cursor.getColumnIndex("name_discount"))
                val note = cursor.getString(cursor.getColumnIndex("note"))
                val type = cursor.getString(cursor.getColumnIndex("type"))
                val nominal = cursor.getString(cursor.getColumnIndex("nominal"))

                val discount = DiscountSQL(increment,key,id_discount,name_discount,note,type,nominal)
                discountAll.add(discount)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return discountAll.sorted()
    }

    fun allStore(): List<StoreSQL> {

        val storeAll = mutableListOf<StoreSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${store_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment")).toString()
                val id_store = cursor.getString(cursor.getColumnIndex("id_store")).toString()
                val type = cursor.getString(cursor.getColumnIndex("type"))
                val name_store = cursor.getString(cursor.getColumnIndex("name_store"))
                val nohp = cursor.getString(cursor.getColumnIndex("nohp"))
                val address = cursor.getString(cursor.getColumnIndex("address"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val omset = cursor.getString(cursor.getColumnIndex("omset"))
                val transaksi = cursor.getString(cursor.getColumnIndex("transaksi"))
                val order = cursor.getString(cursor.getColumnIndex("order_x"))
                val tax = cursor.getString(cursor.getColumnIndex("tax"))
                val service_charge = cursor.getString(cursor.getColumnIndex("service_charge"))
                val number_store = cursor.getString(cursor.getColumnIndex("number_store"))
                val level = cursor.getString(cursor.getColumnIndex("level"))
                val footer = cursor.getString(cursor.getColumnIndex("footer"))
                val photo = cursor.getString(cursor.getColumnIndex("photo"))
                cursor.close()
                val store = StoreSQL(increment,id_store,type,name_store,nohp,address,email,omset,transaksi,order,tax,service_charge,number_store,level,footer,photo)

                storeAll.add(store)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return storeAll.sorted()
    }

    fun allCustomer(): List<CustomerSQL> {

        val customerAll = mutableListOf<CustomerSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${customer_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_customer = cursor.getString(cursor.getColumnIndex("id_customer")).toString()
                val name_customer = cursor.getString(cursor.getColumnIndex("name_customer"))
                val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val address = cursor.getString(cursor.getColumnIndex("address"))
                val customercode = cursor.getString(cursor.getColumnIndex("customercode"))

                val customer = CustomerSQL(increment,key,id_customer,name_customer,telephone,email, address, customercode)
                customerAll.add(customer)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return customerAll
    }

    fun allTable(): List<TableSQL> {

        val tableAll = mutableListOf<TableSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${table_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_table = cursor.getString(cursor.getColumnIndex("id_table")).toString()
                val name_table = cursor.getString(cursor.getColumnIndex("name_table"))
                val table = TableSQL(increment,key,id_table,name_table)
                tableAll.add(table)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return tableAll
    }

    fun allLink(): List<LinkSQL> {

        val linkAll = mutableListOf<LinkSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${link_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_link = cursor.getString(cursor.getColumnIndex("id_link")).toString()
                val name_link = cursor.getString(cursor.getColumnIndex("name_link"))
                val img = cursor.getString(cursor.getColumnIndex("img"))

                val link = LinkSQL(increment,key,id_link,name_link,img)
                linkAll.add(link)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return linkAll
    }

    fun allSupplier(): List<SupplierSQL> {

        val supplierAll = mutableListOf<SupplierSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${supplier_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_supplier = cursor.getString(cursor.getColumnIndex("id_supplier")).toString()
                val name_supplier = cursor.getString(cursor.getColumnIndex("name_supplier"))
                val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val address = cursor.getString(cursor.getColumnIndex("address"))

                val supplier = SupplierSQL(increment,key,id_supplier,name_supplier,telephone,email,address)
                supplierAll.add(supplier)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return supplierAll
    }

    fun allProduct(): List<ProductSQL> {

        val productAll = mutableListOf<ProductSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${produc_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val id_product = cursor.getInt(cursor.getColumnIndex("id_product")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val nama = cursor.getString(cursor.getColumnIndex("nama"))
                val unit = cursor.getString(cursor.getColumnIndex("unit"))
                val kode = cursor.getString(cursor.getColumnIndex("kode"))
                val idkategori = cursor.getString(cursor.getColumnIndex("idkategori"))
                val namakategori = cursor.getString(cursor.getColumnIndex("namakategori"))
                val active = cursor.getString(cursor.getColumnIndex("active"))
                val beli = cursor.getString(cursor.getColumnIndex("beli"))
                val jual = cursor.getString(cursor.getColumnIndex("jual"))
                val stok = cursor.getString(cursor.getColumnIndex("stok"))
                val minstok = cursor.getString(cursor.getColumnIndex("minstok"))
                val deskripsi = cursor.getString(cursor.getColumnIndex("deskripsi"))
                val online = cursor.getString(cursor.getColumnIndex("online"))
                val havestok = cursor.getString(cursor.getColumnIndex("havestok"))
                val grosir = cursor.getString(cursor.getColumnIndex("grosir"))
                val tax = cursor.getString(cursor.getColumnIndex("tax"))
                val alertstock = cursor.getString(cursor.getColumnIndex("alertstock"))
                val img = cursor.getString(cursor.getColumnIndex("img"))

                val product = ProductSQL(increment,id_product,key,nama,unit,kode,idkategori,namakategori,active,beli,jual,stok,minstok,deskripsi,online,havestok,grosir,tax,alertstock,img)
                productAll.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return productAll.sorted()
    }

    fun allCategory(): List<CategorySQL> {

        val categoryAll = mutableListOf<CategorySQL>()

        val cursor = db.rawQuery("SELECT * FROM '${category_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_category = cursor.getInt(cursor.getColumnIndex("id_category")).toString()
                val name_category = cursor.getString(cursor.getColumnIndex("name_category"))

                val product = CategorySQL(increment,key, id_category,name_category)
                categoryAll.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return categoryAll.sorted()
    }

    fun allProductAdd(): List<ProductSQLAdd> {

        val productAdd = mutableListOf<ProductSQLAdd>()

        val cursor = db.rawQuery("SELECT * FROM '${produc_add_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val nama = cursor.getString(cursor.getColumnIndex("nama"))
                val unit = cursor.getString(cursor.getColumnIndex("unit"))
                val kode = cursor.getString(cursor.getColumnIndex("kode"))
                val idkategori = cursor.getString(cursor.getColumnIndex("idkategori"))
                val beli = cursor.getString(cursor.getColumnIndex("beli"))
                val jual = cursor.getString(cursor.getColumnIndex("jual"))
                val stok = cursor.getString(cursor.getColumnIndex("stok"))
                val minstok = cursor.getString(cursor.getColumnIndex("minstok"))
                val deskripsi = cursor.getString(cursor.getColumnIndex("deskripsi"))
                val online = cursor.getString(cursor.getColumnIndex("online"))
                val havestok = cursor.getString(cursor.getColumnIndex("havestok"))
                val grosir = cursor.getString(cursor.getColumnIndex("grosir"))
                val tax = cursor.getString(cursor.getColumnIndex("tax"))
                val alertstock = cursor.getString(cursor.getColumnIndex("alertstock"))
                val img = cursor.getString(cursor.getColumnIndex("img"))

                val product = ProductSQLAdd(increment,key,nama,unit,kode,idkategori,beli,jual,stok,minstok,deskripsi,online,havestok,grosir,tax,alertstock,img)
                productAdd.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return productAdd.sorted()
    }

    fun allProductUpdate(): List<ProductSQLUpdate> {

        val productUpdate = mutableListOf<ProductSQLUpdate>()

        val cursor = db.rawQuery("SELECT * FROM '${produc_update_table}'", null)
        if (cursor.moveToFirst()) {
            do {

                val increment = cursor.getString(cursor.getColumnIndex("increment"))
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_product = cursor.getString(cursor.getColumnIndex("id_product"))
                val nama = cursor.getString(cursor.getColumnIndex("nama"))
                val unit = cursor.getString(cursor.getColumnIndex("unit"))
                val kode = cursor.getString(cursor.getColumnIndex("kode"))
                val idkategori = cursor.getString(cursor.getColumnIndex("idkategori"))
                val beli = cursor.getString(cursor.getColumnIndex("beli"))
                val jual = cursor.getString(cursor.getColumnIndex("jual"))
                val stok = cursor.getString(cursor.getColumnIndex("stok"))
                val minstok = cursor.getString(cursor.getColumnIndex("minstok"))
                val deskripsi = cursor.getString(cursor.getColumnIndex("deskripsi"))
                val online = cursor.getString(cursor.getColumnIndex("online"))
                val havestok = cursor.getString(cursor.getColumnIndex("havestok"))
                val grosir = cursor.getString(cursor.getColumnIndex("grosir"))
                val tax = cursor.getString(cursor.getColumnIndex("tax"))
                val alertstock = cursor.getString(cursor.getColumnIndex("alertstock"))
                val img = cursor.getString(cursor.getColumnIndex("img"))
                val product = ProductSQLUpdate(increment,key,id_product,nama,unit,kode,idkategori,beli,jual,stok,minstok,deskripsi,online,havestok,grosir,tax,alertstock,img)
                productUpdate.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return productUpdate.sorted()
    }

    fun allProductDelete(): List<ProductSQLDelete> {

        val productDelete = mutableListOf<ProductSQLDelete>()

        val cursor = db.rawQuery("SELECT * FROM '${produc_delete_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment"))
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_product = cursor.getString(cursor.getColumnIndex("id_product"))

                val product = ProductSQLDelete(increment,key,id_product)
                productDelete.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return productDelete.sorted()
    }

    fun updateProduct(product: ProductSQL) {
        val contentValues = ContentValues()

        contentValues.put("key", product.key)
        contentValues.put("id_product", product.id_product)
        contentValues.put("nama", product.nama)
        contentValues.put("kode", product.kode)
        contentValues.put("idkategori", product.idkategori)
        contentValues.put("namakategori", product.namakategori)
        contentValues.put("active", product.namakategori)
        contentValues.put("beli", product.beli)
        contentValues.put("jual", product.jual)
        contentValues.put("stok", product.stok)
        contentValues.put("minstok", product.minstok)
        contentValues.put("deskripsi", product.deskripsi)
        contentValues.put("online", product.online)
        contentValues.put("havestok", product.havestok)
        contentValues.put("grosir", product.grosir)
        contentValues.put("tax", product.tax)
        contentValues.put("img", product.img)
        val args = arrayOf(product.increment.toString())
        db.update("'${produc_table}'",contentValues,"increment = ?",args)
    }

    fun allSupplierAdd(): List<SupplierSQLAdd> {

        val supplierAdd = mutableListOf<SupplierSQLAdd>()

        val cursor = db.rawQuery("SELECT * FROM '${supplier_add_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val name_supplier = cursor.getString(cursor.getColumnIndex("name_supplier"))
                val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val address = cursor.getString(cursor.getColumnIndex("address"))

                val supplier = SupplierSQLAdd(increment,key,name_supplier,telephone,email,address)
                supplierAdd.add(supplier)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return supplierAdd.sorted()
    }

    fun allCustomerAdd(): List<CustomerSQLAdd> {

        val customerAdd = mutableListOf<CustomerSQLAdd>()

        val cursor = db.rawQuery("SELECT * FROM '${customer_add_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val name_customer = cursor.getString(cursor.getColumnIndex("name_customer"))
                val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val address = cursor.getString(cursor.getColumnIndex("address"))
                val customercode = cursor.getString(cursor.getColumnIndex("customercode"))

                val customer = CustomerSQLAdd(increment,key,name_customer,telephone,email,address,customercode)
                customerAdd.add(customer)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return customerAdd.sorted()
    }

    fun allDiscountAdd(): List<DiscountSQLAdd> {

        val discountAdd = mutableListOf<DiscountSQLAdd>()

        val cursor = db.rawQuery("SELECT * FROM '${discount_add_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val name_discount = cursor.getString(cursor.getColumnIndex("name_discount"))
                val note = cursor.getString(cursor.getColumnIndex("note"))
                val type = cursor.getString(cursor.getColumnIndex("type"))
                val nominal = cursor.getString(cursor.getColumnIndex("nominal"))

                val discount = DiscountSQLAdd(increment,key,name_discount,note,type,nominal)
                discountAdd.add(discount)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return discountAdd.sorted()
    }

    fun allTableAdd(): List<TableSQLAdd> {

        val tableAdd = mutableListOf<TableSQLAdd>()

        val cursor = db.rawQuery("SELECT * FROM '${table_add_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val name_table = cursor.getString(cursor.getColumnIndex("name_table"))

                val table = TableSQLAdd(increment,key,name_table)
                tableAdd.add(table)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return tableAdd.sorted()
    }

    fun allCategoryAdd(): List<CategorySQLAdd> {

        val categoryAdd = mutableListOf<CategorySQLAdd>()

        val cursor = db.rawQuery("SELECT * FROM '${category_add_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val name_category = cursor.getString(cursor.getColumnIndex("name_category"))
                val discount = CategorySQLAdd(increment,key,name_category)
                categoryAdd.add(discount)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return categoryAdd.sorted()
    }

    fun allSupplierDelete(): List<SupplierSQLDelete> {

        val supplierDelete = mutableListOf<SupplierSQLDelete>()

        val cursor = db.rawQuery("SELECT * FROM '${supplier_delete_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment"))
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_supplier = cursor.getString(cursor.getColumnIndex("id_supplier"))

                val supplier = SupplierSQLDelete(increment,key,id_supplier)
                supplierDelete.add(supplier)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return supplierDelete.sorted()
    }

    fun allCustomerDelete(): List<CustomerSQLDelete> {

        val customerDelete = mutableListOf<CustomerSQLDelete>()

        val cursor = db.rawQuery("SELECT * FROM '${customer_delete_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment"))
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_customer = cursor.getString(cursor.getColumnIndex("id_customer"))

                val customer = CustomerSQLDelete(increment,key,id_customer)
                customerDelete.add(customer)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return customerDelete.sorted()
    }

    fun allDiscountDelete(): List<DiscountSQLDelete> {

        val discountDelete = mutableListOf<DiscountSQLDelete>()

        val cursor = db.rawQuery("SELECT * FROM '${discount_delete_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment"))
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_discount = cursor.getString(cursor.getColumnIndex("id_discount"))

                val discount = DiscountSQLDelete(increment,key,id_discount)
                discountDelete.add(discount)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return discountDelete.sorted()
    }

    fun allTableDelete(): List<TableSQLDelete> {

        val tableDelete = mutableListOf<TableSQLDelete>()

        val cursor = db.rawQuery("SELECT * FROM '${table_delete_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment"))
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_Table = cursor.getString(cursor.getColumnIndex("id_Table"))

                val Table = TableSQLDelete(increment,key,id_Table)
                tableDelete.add(Table)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return tableDelete.sorted()
    }

    fun allCategoryDelete(): List<CategorySQLDelete> {

        val categoryDelete = mutableListOf<CategorySQLDelete>()

        val cursor = db.rawQuery("SELECT * FROM '${category_delete_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment"))
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_category = cursor.getString(cursor.getColumnIndex("id_category"))

                val category = CategorySQLDelete(increment,key,id_category)
                categoryDelete.add(category)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return categoryDelete.sorted()
    }


    fun allSupplierUpdate(): List<SupplierSQLUpdate> {

        val supplierUpdate = mutableListOf<SupplierSQLUpdate>()

        val cursor = db.rawQuery("SELECT * FROM '${supplier_update_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment"))
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_supplier = cursor.getString(cursor.getColumnIndex("id_supplier"))
                val name_supplier = cursor.getString(cursor.getColumnIndex("name_supplier"))
                val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val address = cursor.getString(cursor.getColumnIndex("address"))
                val supplier = SupplierSQLUpdate(increment,key,id_supplier,name_supplier,telephone,email,address)
                supplierUpdate.add(supplier)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return supplierUpdate.sorted()
    }

    fun allCustomerUpdate(): List<CustomerSQLUpdate> {

        val customerUpdate = mutableListOf<CustomerSQLUpdate>()

        val cursor = db.rawQuery("SELECT * FROM '${customer_update_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment"))
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_customer = cursor.getString(cursor.getColumnIndex("id_customer"))
                val name_customer = cursor.getString(cursor.getColumnIndex("name_customer"))
                val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val address = cursor.getString(cursor.getColumnIndex("address"))
                val customercode = cursor.getString(cursor.getColumnIndex("customercode"))
                val customer = CustomerSQLUpdate(increment,key,id_customer,name_customer,telephone,email,address,customercode)
                customerUpdate.add(customer)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return customerUpdate.sorted()
    }

    fun allDiscountUpdate(): List<DiscountSQLUpdate> {

        val discountUpdate = mutableListOf<DiscountSQLUpdate>()

        val cursor = db.rawQuery("SELECT * FROM '${discount_update_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment"))
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_discount = cursor.getString(cursor.getColumnIndex("id_discount"))
                val name_discount = cursor.getString(cursor.getColumnIndex("name_discount"))
                val note = cursor.getString(cursor.getColumnIndex("note"))
                val type = cursor.getString(cursor.getColumnIndex("type"))
                val nominal = cursor.getString(cursor.getColumnIndex("nominal"))
                val discount = DiscountSQLUpdate(increment,key,id_discount,name_discount,note,type,nominal)
                discountUpdate.add(discount)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return discountUpdate.sorted()
    }

    fun allTableUpdate(): List<TableSQLUpdate> {

        val tableUpdate = mutableListOf<TableSQLUpdate>()

        val cursor = db.rawQuery("SELECT * FROM '${table_update_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment"))
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_table = cursor.getString(cursor.getColumnIndex("id_table"))
                val name_table = cursor.getString(cursor.getColumnIndex("name_table"))
                val table = TableSQLUpdate(increment,key,id_table,name_table)
                tableUpdate.add(table)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return tableUpdate.sorted()
    }

    fun allCategoryUpdate(): List<CategorySQLUpdate> {

        val categoryUpdate = mutableListOf<CategorySQLUpdate>()

        val cursor = db.rawQuery("SELECT * FROM '${category_update_table}'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getString(cursor.getColumnIndex("increment"))
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_category = cursor.getString(cursor.getColumnIndex("id_category"))
                val name_category = cursor.getString(cursor.getColumnIndex("name_category"))
                val category = CategorySQLUpdate(increment,key,id_category,name_category)
                categoryUpdate.add(category)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return categoryUpdate.sorted()
    }

    fun updateSupplier(supplier: SupplierSQL) {
        val contentValues = ContentValues()

        contentValues.put("key", supplier.key)
        contentValues.put("id_supplier", supplier.id_supplier)
        contentValues.put("name_supplier", supplier.name_supplier)
        contentValues.put("telephone", supplier.telephone)
        contentValues.put("email", supplier.email)
        contentValues.put("address", supplier.address)
        val args = arrayOf(supplier.increment.toString())
        db.update("'${supplier_table}'",contentValues,"increment = ?",args)
    }

    fun updateCustomer(customer: CustomerSQL) {
        val contentValues = ContentValues()

        contentValues.put("key", customer.key)
        contentValues.put("id_customer", customer.id_customer)
        contentValues.put("name_customer", customer.name_customer)
        contentValues.put("telephone", customer.telephone)
        contentValues.put("email", customer.email)
        contentValues.put("address", customer.address)
        val args = arrayOf(customer.increment.toString())
        db.update("'${customer_table}'",contentValues,"increment = ?",args)
    }

    fun updateDiscount(discount: DiscountSQL) {
        val contentValues = ContentValues()

        contentValues.put("key", discount.key)
        contentValues.put("id_discount", discount.id_discount)
        contentValues.put("name_discount", discount.name_discount)
        contentValues.put("note", discount.note)
        contentValues.put("type", discount.type)
        contentValues.put("nominal", discount.nominal)
        val args = arrayOf(discount.increment.toString())
        db.update("'${discount_table}'",contentValues,"increment = ?",args)
    }

    fun updateTable(table: TableSQL) {
        val contentValues = ContentValues()

        contentValues.put("key", table.key)
        contentValues.put("id_table", table.id_table)
        contentValues.put("name_table", table.name_table)
        val args = arrayOf(table.increment.toString())
        db.update("'${table_table}'",contentValues,"increment = ?",args)
    }

    fun updateCategory(category: CategorySQL) {
        val contentValues = ContentValues()

        contentValues.put("key", category.key)
        contentValues.put("id_category", category.id_category)
        contentValues.put("name_category", category.name_category)
        val args = arrayOf(category.increment.toString())
        db.update("'${category_table}'",contentValues,"increment = ?",args)
    }


    fun searchCategory(search: String): List<CategorySQL> {
        val categorySearch = mutableListOf<CategorySQL>()

        val cursor = db.rawQuery("SELECT * FROM '${category_table}' WHERE name_category LIKE '%$search%'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_category = cursor.getInt(cursor.getColumnIndex("id_category")).toString()
                val name_category = cursor.getString(cursor.getColumnIndex("name_category"))

                val categorys        = CategorySQL(increment,key,id_category,name_category)
                categorySearch.add(categorys)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return categorySearch.sorted()
    }

    fun searchCustomer(search: String): List<CustomerSQL> {
        val customerSearch = mutableListOf<CustomerSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${customer_table}' WHERE name_customer LIKE '%$search%'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_customer = cursor.getString(cursor.getColumnIndex("id_customer")).toString()
                val name_customer = cursor.getString(cursor.getColumnIndex("name_customer"))
                val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val address = cursor.getString(cursor.getColumnIndex("address"))
                val customercode = cursor.getString(cursor.getColumnIndex("customercode"))

                val customer = CustomerSQL(increment,key,id_customer,name_customer,telephone,email, address, customercode)
                customerSearch.add(customer)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return customerSearch.sorted()
    }

    fun searchSupplier(search: String): List<SupplierSQL> {
        val supplierSearch = mutableListOf<SupplierSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${supplier_table}' WHERE name_supplier LIKE '%$search%'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val id_supplier = cursor.getString(cursor.getColumnIndex("id_supplier")).toString()
                val name_supplier = cursor.getString(cursor.getColumnIndex("name_supplier"))
                val telephone = cursor.getString(cursor.getColumnIndex("telephone"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val address = cursor.getString(cursor.getColumnIndex("address"))

                val supplier = SupplierSQL(increment,key,id_supplier,name_supplier,telephone,email,address)
                supplierSearch.add(supplier)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return supplierSearch.sorted()
    }

    fun searchProduct(search: String): List<ProductSQL> {
        val productSearch = mutableListOf<ProductSQL>()

        val cursor = db.rawQuery("SELECT * FROM '${produc_table}' WHERE nama LIKE '%$search%'", null)
        if (cursor.moveToFirst()) {
            do {
                val increment = cursor.getInt(cursor.getColumnIndex("increment")).toString()
                val id = cursor.getInt(cursor.getColumnIndex("id")).toString()
                val key = cursor.getString(cursor.getColumnIndex("key"))
                val nama = cursor.getString(cursor.getColumnIndex("nama"))
                val unit = cursor.getString(cursor.getColumnIndex("unit"))
                val kode = cursor.getString(cursor.getColumnIndex("kode"))
                val idkategori = cursor.getString(cursor.getColumnIndex("idkategori"))
                val namakategori = cursor.getString(cursor.getColumnIndex("namakategori"))
                val active = cursor.getString(cursor.getColumnIndex("active"))
                val beli = cursor.getString(cursor.getColumnIndex("beli"))
                val jual = cursor.getString(cursor.getColumnIndex("jual"))
                val stok = cursor.getString(cursor.getColumnIndex("stok"))
                val minstok = cursor.getString(cursor.getColumnIndex("minstok"))
                val deskripsi = cursor.getString(cursor.getColumnIndex("deskripsi"))
                val online = cursor.getString(cursor.getColumnIndex("online"))
                val havestok = cursor.getString(cursor.getColumnIndex("havestok"))
                val grosir = cursor.getString(cursor.getColumnIndex("grosir"))
                val tax = cursor.getString(cursor.getColumnIndex("tax"))
                val alertstock = cursor.getString(cursor.getColumnIndex("alertstock"))
                val img = cursor.getString(cursor.getColumnIndex("img"))

                val product = ProductSQL(increment,id,key,nama,unit,kode,idkategori,namakategori,active,beli,jual,stok,minstok,deskripsi,online,havestok,grosir,tax,alertstock,img)
                productSearch.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return productSearch.sorted()
    }




}