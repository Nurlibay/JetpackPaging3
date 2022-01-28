package uz.texnopos.paging3.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import okhttp3.Cache
import uz.texnopos.paging3.App.Companion.getAppInstance

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()

fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    if (context != null) {
        context!!.toast(text, duration)
    }
}

inline fun <T : View> T.onClick(crossinline func: T.() -> Unit) = setOnClickListener { func() }

inline fun <T : Toolbar> T.navOnClick(crossinline func: T.() -> Unit) =
    setNavigationOnClickListener { func() }

const val cacheSize = (5 * 1024 * 1024).toLong()
val myCache = Cache(getAppInstance().cacheDir, cacheSize)

fun isNetworkAvailable(): Boolean {
    val info = getAppInstance().getConnectivityManager().activeNetworkInfo
    return info != null && info.isConnected
}

fun Context.getConnectivityManager() =
    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}





