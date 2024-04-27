package id.kasirvippro.android.utils

object AppConstant {
    private val appSession = AppSession()
    const val SPLASH_TIMER = 2000L
    const val LIMIT = 10
    const val LIMIT_HUTANG_PIUTANG = 5

    const val TOKEN = "token"
    const val CURRENCYES = "currency"
    const val DEVICE_TOKEN = "device_token"
    const val TITLE = "title"
    const val CODE = "code"
    const val DATA = "data"
    const val USER = "user"
    const val LOGIN = "login"
    const val MAIN = "main"
    const val DATE = "date"
    const val POSITION = "position"
    const val TYPEW = "typestore"
    const val DECIMALS = "decimal"
    const val IDSTORES = "idstore"

    var sales = "sales"
    var salesData = "salesData"

    var spending = "Spending"
    var spendingData = "spendingData"


    const val REQUEST_CAMERA_PERMISSION = 1001

    object Code{
        val CODE_PROVINCE = 1
        val CODE_CITY = 2
        val CODE_FILTER_DATE_SELL = 100
        val CODE_FILTER_DATE_HISTORY = 101
        val CODE_TRANSACTION_CUSTOMER = 201
        val CODE_TRANSACTION_CUSTOMER2 = 205
        val CODE_TRANSACTION_SUPPLIER = 202
        val CODE_KARYAWAN_ABSENSI = 206
        val CODE_KARYAWAN_GAJI = 207
        val CODE_OPEN_VIEW = 203
        val CODE_OPEN_ADD = 204
    }

    object URL{
        val STORE = "https://pro.kasir.vip/store/"
        val ABOUT = "https://pro.kasir.vip/geten/pages/about.php"
        val TERM = "https://pro.kasir.vip/geten/pages/term.php"
        val PRIVACY = "https://pro.kasir.vip/geten/pages/privacy.php"
        val PREMIUM = "https://pro.kasir.vip/geten/pages/premium.php?key="
        val NEWS = "https://pro.kasir.vip/geten/pages/news.php"
        val TOPUP = "https://pro.kasir.vip/geten/pages/topup.php?key="
        val NONTUNAI = "https://pro.kasir.vip/geten/pages/topup.php?key="
        val NONTUNAIPAY = "https://pro.kasir.vip/geten/pages/payments.php?key="
    }


    object Currencyes {

        var myCurrency: String = String()

        fun setMyCurrencyData(str: String) {
            myCurrency = str
        }

        fun getCurrencyData(): String = myCurrency

    }

    object CURRENCY{

            var myCurrency: String = String()

            fun setMyCurrencyData(str: String) {
                myCurrency = str
            }

            fun getCurrencyData(): String = myCurrency

        //val getCurrencyData() = "Rp "

    }

    object TYPESTORE{

        var myType: String = String()

        fun setMyTypeData(str: String) {
            myType = str
        }

        fun getTypeData(): String = myType
    }


    object DECIMAL{

        var myDecimal: String = String()

        fun setMyDecimalData(str: String) {
            myDecimal = str
        }

        fun getDecimalData(): String = myDecimal
    }


    object IDSTORE{

        var myIdstore: String = String()

        fun setMyIdstoreData(str: String) {
            myIdstore = str
        }

        fun getIdstoreData(): String = myIdstore
    }

    object SCAN{
        val TYPE = "type"
        val SELL = 1
        val SELL_SEARCH = 2
        val SELL_ADD = 3
    }

    const val NOTIFICATION_CHANNEL_ID_DEFAULT = "10000"
    const val NOTIFICATION_CHANNEL_NAME_DEFAULT = "default"

    object FilterDate{
        val DAILY = 1
        val WEEKLY = 2
        val MONTHLY = 3
    }

    object Webview{
        val TITLE = "title"
        val URL = "url"
        val IS_PREMIUM = "url"
    }
}