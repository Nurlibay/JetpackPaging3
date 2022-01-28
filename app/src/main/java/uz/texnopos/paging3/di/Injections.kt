package uz.texnopos.paging3.di

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.texnopos.paging3.data.retrofit.ApiInterface
import uz.texnopos.paging3.data.retrofit.CacheInterceptor
import uz.texnopos.paging3.ui.product.ProductViewModel
import uz.texnopos.paging3.util.Constants.BASE_URL
import uz.texnopos.paging3.util.isNetworkAvailable
import uz.texnopos.paging3.util.myCache
import uz.texnopos.paging3.util.preferences.token
import java.util.concurrent.TimeUnit

const val appTimeOut = 24L

val networkModule = module {

    single {
        GsonBuilder().setLenient().create()
    }

    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        //OkHttpClient start
        OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (isNetworkAvailable())
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
                chain.proceed(request)
            }

            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                return@addInterceptor chain.proceed(request)
            }
            .addNetworkInterceptor(CacheInterceptor())
            .addInterceptor(loggingInterceptor)
            .connectTimeout(appTimeOut, TimeUnit.SECONDS)
            .readTimeout(appTimeOut, TimeUnit.SECONDS)
            .writeTimeout(appTimeOut, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }
    single {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(ApiInterface::class.java) }
}

val viewModelModule = module {
    viewModel { ProductViewModel(get()) }
}