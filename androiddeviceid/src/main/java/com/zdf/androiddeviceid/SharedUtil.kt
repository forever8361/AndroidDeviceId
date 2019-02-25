package com.zdf.androiddeviceid

import android.content.Context
import android.preference.PreferenceManager

object SharedUtil {
    fun saveString(context: Context, key: String, value: String) {
        val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(context: Context,key: String, defValue: String): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(key, defValue)
    }
}