package uz.texnopos.paging3.util.preferences

import uz.texnopos.paging3.App
import uz.texnopos.paging3.util.Constants

fun getSharedPreferences(): SharedPrefUtils {
    return if (App.sharedPrefUtils == null) {
        App.sharedPrefUtils = SharedPrefUtils()
        App.sharedPrefUtils!!
    } else App.sharedPrefUtils!!
}

var token: String?
    set(value) = getSharedPreferences().setValue(Constants.TOKEN, value)
    get() = getSharedPreferences().getStringValue(Constants.TOKEN)

fun isSignedIn(): Boolean = !token.isNullOrEmpty()
