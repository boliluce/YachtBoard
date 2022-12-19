package kr.co.lastory.android.yachtboard.common

import android.content.Context
import android.content.SharedPreferences

class PrefUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun getInt(key: String, defValue: Int): Int {
        return prefs.getInt(key, defValue)
    }

    fun setInt(key: String, n: Int) {
        prefs.edit().putInt(key, n).apply()
    }
}
