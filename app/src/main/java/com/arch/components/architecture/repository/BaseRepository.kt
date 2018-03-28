package com.arch.components.architecture.repository

import com.arch.components.api.NetworkService
import com.arch.components.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Yuvraj on 27/03/18.
 */
open class BaseRepository {

    private var service : NetworkService? = null

    fun getNetworkService() : NetworkService {
        if(service == null) {
            service = getService();
        }
        return service!!
    }

    private fun getService(): NetworkService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS).addInterceptor(logging)
                .build()

        // Retrofit handling
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(NetworkService::class.java)
    }
}