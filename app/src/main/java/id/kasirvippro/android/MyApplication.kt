package id.kasirvippro.android

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlin.system.exitProcess


class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        AndroidThreeTen.init(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {

        private var instance: MyApplication? = null

        fun applicationContext() : MyApplication {
            return instance as MyApplication
        }


        /**
         * Exit current activity
         *
         * @param currentActivity
         */
        fun exit(currentActivity: Activity) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//***Change Here***
            currentActivity.startActivity(intent)
            currentActivity.finish()
            exitProcess(0)
        }

    }


}