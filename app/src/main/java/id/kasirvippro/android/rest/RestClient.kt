package id.kasirvippro.android.rest

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import id.kasirvippro.android.BuildConfig
import id.kasirvippro.android.MyApplication
import id.kasirvippro.android.rest.util.RequestInterceptor
import id.kasirvippro.android.rest.util.ResponseInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

class RestClient {
    private val retrofit: Retrofit


    companion object {

        val TAG = RestClient::class.java.javaClass.simpleName

        private var instance: RestClient? = null


        fun getInstance(): RestClient? {
            if (instance == null) {
                synchronized(RestClient::class.java) {
                    if (instance == null)
                        instance = RestClient()
                }
            }
            return instance
        }

        /**
         * refer to documentation written in build.gradle
         *
         * @param appVer : app versionName from app level build.gradle
         * @return ApiVersion
         */
        private fun getApiVersion(appVer: String): String {
            val separatorCount = appVer.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size - 1
            var lastIndex = appVer.length
            if (separatorCount > 1) {
                lastIndex = appVer.indexOf(".", appVer.indexOf(".") + 1)
            }
            return appVer.substring(0, lastIndex)
        }
    }

    init {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
            val cache = Cache(MyApplication.applicationContext().cacheDir, cacheSize)

            val hostnameVerifier = HostnameVerifier { hostname, sslSession ->  true}
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(hostnameVerifier)
            builder.addInterceptor(RequestInterceptor())
                .addInterceptor(ResponseInterceptor())
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(cache)


            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(httpLoggingInterceptor)
            }

            val client = builder.build()

            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

        } catch (e: Exception) {
            throw RuntimeException(e)
        }


    }

    fun <T> createInterface(A: Class<T>): T {
        return retrofit.create(A)
    }

}
