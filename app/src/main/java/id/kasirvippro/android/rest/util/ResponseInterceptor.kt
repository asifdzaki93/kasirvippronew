package id.kasirvippro.android.rest.util

import android.util.Log
import com.google.gson.*
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.entity.ResponseEntity
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import id.kasirvippro.android.rest.entity.RestException
import okhttp3.MediaType
import okhttp3.ResponseBody
import java.nio.charset.Charset


class ResponseInterceptor : Interceptor {

    companion object {
        private val UTF8 = Charset.forName("UTF-8")
        private val TAG = ResponseInterceptor::class.java.javaClass.simpleName
        const val KEY_DATA = "data"
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        try {

            val request = chain.request()
            val response = chain.proceed(request)

            val responseCode = response.code()
            val responseBody = response.body()

            val parser = JsonParser()

            Log.d(TAG, "responseCode: $responseCode")

            when (responseCode) {
                HttpURLConnection.HTTP_OK, HttpURLConnection.HTTP_CREATED -> {
                    /** check JSON format of overall response  */
                    if(responseBody == null){
                        throw RestException("There is an error ", RestException.CODE_ERROR_UNKNOWN)
                    }
//                    val responseBodyString = responseBody!!.string()
//                    if(responseBodyString.isNullOrEmpty() || responseBodyString.isNullOrBlank()){
//                        throw RestException("There is an error ", RestException.CODE_ERROR_UNKNOWN)
//                    }
                    val cObject: JsonObject
                    try {
                        cObject = parser.parse(responseBody!!.string()).asJsonObject
                    } catch (e: JsonSyntaxException) {
                        throw RestException(e.message, RestException.CODE_ERROR_UNKNOWN)
                    }


                    /** check JSON format of response as [ResponseEntity]  */
                    val apiResponse: ResponseEntity
                    try {
                        apiResponse = Gson().fromJson<ResponseEntity>(cObject, ResponseEntity::class.java)
                    } catch (e: JsonSyntaxException) {
                        Log.e(TAG, e.localizedMessage)
                        throw RestException(e.message, RestException.CODE_ERROR_UNKNOWN)
                    }

                    responseBody.close()

                    when {
                        apiResponse.errCode == RestException.RESPONSE_SUCCESS -> {
                            val jsonElement: JsonElement
                            val data = cObject.get(KEY_DATA) ?: null
                            if(data == null){
                                val message = Message()
                                message.message = apiResponse.msg
                                val newResponse = response.newBuilder()
                                    .body(ResponseBody.create(JSON, message.json()))

                                return newResponse.build()
                            }
                            when {
                                data!!.isJsonArray -> {
                                    Log.d(TAG, "data is array")

                                    val arr = cObject.getAsJsonArray(KEY_DATA)

                                    jsonElement = arr

                                }
                                data!!.isJsonObject -> {
                                    Log.d(TAG, "data is object")

                                    jsonElement = cObject.get(KEY_DATA)

                                }
                                else -> throw RestException("data is not object or array", RestException.CODE_ERROR_UNKNOWN)
                            }

                            val newResponse = response.newBuilder()
                                .body(ResponseBody.create(JSON, jsonElement.toString()))

                            return newResponse.build()
                        }
                        apiResponse.errCode == RestException.RESPONSE_USER_NOT_FOUND -> {
                            throw RestException(apiResponse.msg, RestException.CODE_USER_NOT_FOUND)
                        }
                        apiResponse.errCode == RestException.RESPONSE_UPDATE_APP -> {
                            throw RestException(apiResponse.msg, RestException.CODE_UPDATE_APP)
                        }
                        apiResponse.errCode == RestException.RESPONSE_MAINTENANCE -> {
                            throw RestException(apiResponse.msg, RestException.CODE_MAINTENANCE)
                        }
                        else -> {
                            throw RestException(apiResponse.msg, RestException.CODE_ERROR_UNKNOWN)
                        }
                    }


                }
                HttpURLConnection.HTTP_GATEWAY_TIMEOUT ->  throw RestException("network problem", responseCode)
                else -> {
                    val source = responseBody!!.source()
                    source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
                    val buffer = source.buffer()
                    val content = buffer.clone().readString(UTF8) ?: "There is an error"
                    throw RestException(content, responseCode)
                }
            }
        } catch (connectException: ConnectException) {
            connectException.printStackTrace()
            throw RestException(
                connectException.message,
                HttpURLConnection.HTTP_CLIENT_TIMEOUT
            )
        } catch (connectException: SocketTimeoutException) {
            connectException.printStackTrace()
            throw RestException(
                connectException.message,
                HttpURLConnection.HTTP_CLIENT_TIMEOUT
            )
        } catch (connectException: UnknownHostException) {
            connectException.printStackTrace()
            throw RestException(
                connectException.message,
                HttpURLConnection.HTTP_CLIENT_TIMEOUT
            )
        } catch (connectException: SSLException) {
            connectException.printStackTrace()
            throw RestException(
                connectException.message,
                HttpURLConnection.HTTP_CLIENT_TIMEOUT
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(TAG, "intercept: ")
            if (e !is RestException)
                throw RestException(
                    e.message,
                    RestException.CODE_ERROR_UNKNOWN
                )
            else
                throw e
        }

    }

}
