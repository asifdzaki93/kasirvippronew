package id.kasirvippro.android.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import id.kasirvippro.android.MyApplication
import id.kasirvippro.android.models.user.Login
import id.kasirvippro.android.models.user.User

class AppSession {

    // Shared Preferences
    private lateinit var sharedPreferences: SharedPreferences

    companion object {

        const val PREF_SESSION = "PREF_SESSION_INFORMASI_WISATA"
        const val PREF_FIRST_TIME_LAUNCH = "PREF_FIRST_TIME_LAUNCH"
    }

    init {
        sharedPreferences = MyApplication.applicationContext().getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE)
    }


    fun clearSession() {
        // Clearing all data from Shared Preferences
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(PREF_FIRST_TIME_LAUNCH, isFirstTime)
        editor.apply()
    }

    fun setSharedPreferenceString(key: String, value: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun setSharedPreferenceInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun setSharedPreferenceBoolean(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun setSharedPreferenceLong(key: String, value: Long?) {
        val editor = sharedPreferences.edit()
        editor.putLong(key, value!!)
        editor.apply()
    }

    fun setSharedPreferenceFloat(key: String, value: Float) {
        val editor = sharedPreferences.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun getSharedPreferenceString(context: Context,key: String): String? {

        return sharedPreferences.getString(key, null)
    }

    fun getSharedPreferenceString2(context: Context,currency: String): String? {

        return sharedPreferences.getString(currency, null)
    }

    fun getSharedPreferenceInt(context: Context,key: String): Int {

        return sharedPreferences.getInt(key, 0)
    }

    fun getSharedPreferenceBoolean(context: Context,key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getSharedPreferenceLong(context: Context,key: String): Long {
        return sharedPreferences.getLong(key, 0)
    }

    fun getSharedPreferenceFloat(context: Context,key: String): Float {
        return sharedPreferences.getFloat(key, 0f)
    }

    fun getToken(context: Context): String? {
        var token = getSharedPreferenceString(context, AppConstant.TOKEN)
        if (token != null) {
            if (token.isEmpty()) {
                token = ""
            }
        }
        else{
            token = ""
        }
        return token
    }

    fun getCurrency(context: Context): String? {
        var unit = getSharedPreferenceString2(context, AppConstant.CURRENCYES)
        if (unit != null) {
            if (unit.isEmpty()) {
                unit = ""
            }
        }
        else{
            unit = ""
        }

        AppConstant.CURRENCY.setMyCurrencyData(unit)
        return AppConstant.CURRENCY.getCurrencyData()
    }

    fun getDeviceToken(context: Context): String? {
        var token = getSharedPreferenceString(context, AppConstant.DEVICE_TOKEN)
        if (token != null) {
            if (token.isEmpty()) {
                token = ""
            }
        }
        else{
            token = ""
        }
        return token
    }

    fun getLevel(context: Context): String? {
        val json = getSharedPreferenceString(context,AppConstant.LOGIN)
        if(json.isNullOrEmpty()){
            return null
        }

        val data = Gson().fromJson(json, User::class.java) ?: null
        return if(data == null){
            ""
        } else{
            data.level
        }
    }

    fun getPackage(context: Context): String? {
        val json = getSharedPreferenceString(context,AppConstant.LOGIN)
        if(json.isNullOrEmpty()){
            return null
        }

        val data = Gson().fromJson(json, Login::class.java) ?: null
        return if(data == null){
            ""
        } else{
            data.packages
        }
    }

    fun getTypeStore(context: Context): String {
        var type = getSharedPreferenceString2(context, AppConstant.TYPEW)
        if (type != null) {
            if (type.isEmpty()) {
                type = ""
            }
        }
        else{
            type = ""
        }

        AppConstant.TYPESTORE.setMyTypeData(type)
        return AppConstant.TYPESTORE.getTypeData()
    }

    fun getDecimal(context: Context): String {
        var decimal = getSharedPreferenceString2(context, AppConstant.DECIMALS)
        if (decimal != null) {
            if (decimal.isEmpty()) {
                decimal = ""
            }
        }
        else{
            decimal = ""
        }

        AppConstant.DECIMAL.setMyDecimalData(decimal)
        return AppConstant.DECIMAL.getDecimalData()
    }

    fun getIdstore(context: Context): String {
        var idstore = getSharedPreferenceString(context, AppConstant.IDSTORES)
        if (idstore != null) {
            if (idstore.isEmpty()) {
                idstore = ""
            }
        }
        else{
            idstore = ""
        }

        AppConstant.IDSTORE.setMyIdstoreData(idstore)
        return AppConstant.IDSTORE.getIdstoreData()
    }




}