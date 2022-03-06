package com.it.wallet.data.storage.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.it.wallet.data.storage.sharedPref.AppPreferences.PrefKeys.IS_AUTHORIZED
import com.it.wallet.data.storage.sharedPref.AppPreferences.PrefKeys.USERNAME
import com.it.wallet.data.storage.sharedPref.core.CorePreferences
import com.it.wallet.data.storage.sharedPref.core.PreferenceDelegate
import com.it.wallet.data.storage.sharedPref.AppPreferences.PrefKeys.PASSWORD

class AppPreferences constructor(context: Context) :
    CorePreferences(context) {

    override val prefFileName: String = "com.academy.appPref"

    var isAuthenticated: Boolean by PreferenceDelegate(sharedPreference, IS_AUTHORIZED, false)

    var username: String by PreferenceDelegate(sharedPreference, USERNAME, "")
    var password: String by PreferenceDelegate(sharedPreference, PASSWORD, "")


    private object PrefKeys {
        const val IS_AUTHORIZED: String = "IS_AUTHORIZED"
        const val USERNAME: String = "USERNAME"
        const val PASSWORD: String = "PASSWORD"
    }
}