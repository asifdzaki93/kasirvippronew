package id.kasirvippro.android.rest.entity

import java.io.IOException

class RestException(message: String?, val errorCode: Int) : IOException(message) {

    companion object {

        val RESPONSE_USER_NOT_FOUND = "00"
        val RESPONSE_SUCCESS = "01"
        val RESPONSE_ERROR = "02"
        val RESPONSE_UPDATE_APP = "98"
        val RESPONSE_MAINTENANCE = "99"


        val CODE_UPDATE_APP = 98
        val CODE_MAINTENANCE = 99
        val CODE_ERROR_UNKNOWN = 999
        val CODE_USER_NOT_FOUND = 0

    }

}

