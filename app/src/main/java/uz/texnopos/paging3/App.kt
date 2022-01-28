package uz.texnopos.paging3

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import uz.texnopos.paging3.di.networkModule
import uz.texnopos.paging3.di.viewModelModule
import uz.texnopos.paging3.util.preferences.SharedPrefUtils

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val modules = listOf(viewModelModule, networkModule)
        startKoin { // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@App)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
            koin.loadModules(modules)
        }
    }

    companion object {
        private lateinit var appInstance: App
        var sharedPrefUtils: SharedPrefUtils? = null
        fun getAppInstance(): App {
            return appInstance
        }
    }
}