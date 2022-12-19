package kr.co.lastory.android.yachtboard.common

import android.app.Application

class MyApplication : Application() {
    companion object {
        lateinit var prefs: PrefUtil

        const val PREF_INPUT_DIALOG_TYPE = "PREF_INPUT_DIALOG_TYPE"
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PrefUtil(applicationContext)
    }
}