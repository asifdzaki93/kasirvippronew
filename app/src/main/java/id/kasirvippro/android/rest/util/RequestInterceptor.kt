package id.kasirvippro.android.rest.util

import id.kasirvippro.android.BuildConfig
import id.kasirvippro.android.MyApplication
import id.kasirvippro.android.utils.AppSession
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.Signature
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (!Helper.isNetworkAvailable()) {
            val maxStale = 60 * 60 * 24 * 7
            request = request
                .newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .build()
        }
        else{
            val version = BuildConfig.VERSION_CODE
            val timestamp = Signature.generateTimestamp()
            val key = AppSession().getToken(MyApplication.applicationContext())
            val deviceToken: String? = AppSession().getDeviceToken(MyApplication.applicationContext())

            request = request.newBuilder()
                .addHeader("X-VIBRI-TIMESTAMP",timestamp)
                .addHeader("X-VIBRI-VERSION","$version")
                .addHeader("X-VIBRI-KEY",key)
                .addHeader("X-VIBRI-DeviceToken", deviceToken)
                .build()

        }
        return chain.proceed(request)

    }

}
