package kr.co.lastory.android.yachtboard.common

import android.app.Application

class MyApplication : Application() {
    companion object {
        lateinit var prefs: PrefUtil

        const val BASE_URL = "base_url"
        const val BASE_URL_SELECTED = "base_url_selected"
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PrefUtil(applicationContext)
    }
}