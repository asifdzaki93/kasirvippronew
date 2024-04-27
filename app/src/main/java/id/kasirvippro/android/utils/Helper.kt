package id.kasirvippro.android.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.Settings
import android.text.InputFilter
import android.text.Spanned
import android.view.View
import android.view.inputmethod.InputMethodManager
import id.kasirvippro.android.MyApplication
import id.kasirvippro.android.R
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


object Helper {

    fun getIdLocale(context: Context): Locale {
        return Locale(
            context.getString(R.string.id_lang),
            context.getString(R.string.id_country)
        )
    }

    @Throws(ParseException::class)
    fun getDateFormat(context: Context, date: String, formatFrom: String, formatTo: String): String {
        try {
            val sdfBefore = SimpleDateFormat(formatFrom, getIdLocale(context))
            val dateBefore = sdfBefore.parse(date)

            val sdfAfter = SimpleDateFormat(formatTo, getIdLocale(context))
            return sdfAfter.format(dateBefore!!)
        }catch (ex:Exception) {
            return date
        }
    }

    @SuppressLint("DefaultLocale")
    fun getInisialName(name: String?): String {
        var fullName: String? = name ?: return ""
        fullName = fullName!!.trim { it <= ' ' }
        val separateName = fullName.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (separateName.isEmpty()) {
            return ""
        }
        return if (separateName.size > 1) {
            separateName[0][0] + "" + separateName[1][0]
        } else {
            separateName[0][0].toString().toUpperCase()
        }
    }

    @Throws(ParseException::class)
    fun getDateFormat(context: Context, date: Date, formatTo: String): String {
        val sdfAfter = SimpleDateFormat(formatTo, getIdLocale(context))
        //        SimpleDateFormat sdfAfter = new SimpleDateFormat(formatTo, Locale.getDefault());
        return sdfAfter.format(date)
    }



// class to decimal digits input filter

    class DecimalDigitsInputFilter(
        maxDigitsIncludingPoint: Int, maxDecimalPlaces: Int
    ) : InputFilter {
        private val pattern: Pattern = Pattern.compile(
            "[0-9]{0," + (maxDigitsIncludingPoint - 1) + "}+((\\.[0-9]{0,"
                    + (maxDecimalPlaces - 1) + "})?)||(\\.)?"
        )

        override fun filter(

            p0: CharSequence?, p1: Int, p2: Int, p3: Spanned?, p4: Int, p5: Int

        ): CharSequence? {

            p3?.apply {

                val matcher: Matcher = pattern.matcher(p3)

                return if (!matcher.matches()) "" else null

            }

            return null

        }

    }

    fun convertToCurrency(value: String): String {
        val currentValue: Double
        currentValue = try {
            java.lang.Double.parseDouble(value)
        } catch (nfe: NumberFormatException) {
            0.0
        }

        return convertToCurrency(currentValue)
    }

    fun convertToCurrency(value: Int): String {
        val currentValue: Double
        currentValue = try {
            value.toDouble()
        } catch (nfe: NumberFormatException) {
            0.0
        }

        return convertToCurrency(currentValue)
    }

    fun convertToCurrency(amount: Double): String {
        val formatter = DecimalFormat("#,###,###")
        return formatter.format(amount).replace(".", ",")
    }

    fun openAppSettings(activity: Activity) {
        val packageUri = Uri.fromParts("package", activity.applicationContext.packageName, null)
        val applicationDetailsSettingsIntent = Intent()
        applicationDetailsSettingsIntent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        applicationDetailsSettingsIntent.data = packageUri
        applicationDetailsSettingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.applicationContext.startActivity(applicationDetailsSettingsIntent)
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    @Suppress("DEPRECATION")
    fun isNetworkAvailable(): Boolean {
        val connectivityManager: ConnectivityManager =
            MyApplication.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPhoneValid(phone: String): Boolean {
        if (phone.length < 8) {
            return false
        }
        val prefix = phone.substring(0, 0)
        if ("" == prefix) {
            return true
        }
        return false
    }
    fun getJsonStringFromAssets(context: Context, fileName: String): String? {
        val json: String?
        try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun createPartFromString(text: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), text)
    }

    fun createPartFromFile(path: String?, key: String): MultipartBody.Part? {
        if (path == null) {
            return null
        }
        val file = File(path)
        val request = RequestBody.create(MediaType.parse("image/*"), file)
        return MultipartBody.Part.createFormData(key, file.name, request)
    }

    fun shareBitmapToApps(context: Context, pathUri: Uri) {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "image/*"
        i.putExtra(Intent.EXTRA_STREAM, pathUri)
        context.startActivity(Intent.createChooser(i, "Share to ..."))
    }

}