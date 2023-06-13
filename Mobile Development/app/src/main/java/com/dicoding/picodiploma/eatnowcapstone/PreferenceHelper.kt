package com.dicoding.picodiploma.eatnowcapstone
import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
        private const val PREF_NAME = "MyAppPreferences"
        private const val KEY_FIRST_LOGIN = "firstLogin"

        fun setFirstLogin(context: Context, isFirstLogin: Boolean) {
            val sharedPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPrefs.edit()
            editor.putBoolean(KEY_FIRST_LOGIN, isFirstLogin)
            editor.apply()
        }

        fun isFirstLogin(context: Context): Boolean {
            val sharedPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            return sharedPrefs.getBoolean(KEY_FIRST_LOGIN, true)
        }


}