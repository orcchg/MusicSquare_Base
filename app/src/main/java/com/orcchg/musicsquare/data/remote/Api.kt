package com.orcchg.musicsquare.data.remote

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.orcchg.musicsquare.BuildConfig
import com.orcchg.musicsquare.data.local.UserDatabase
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api {

    /* Common */
    // --------------------------------------------------------------------------------------------
    fun provideGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
    }

    /* OkHttp */
    // --------------------------------------------------------------------------------------------
    fun provideHttpLoggingInterceptor(): LoggingInterceptor =
            LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("Request")
                    .response("Response")
                    .build()

    fun provideRequestHeaderInterceptor(): RequestHeaderInterceptor {
        return RequestHeaderInterceptor("")
    }

    fun provideResponseHeaderInterceptor(): ResponseHeaderInterceptor {
        return ResponseHeaderInterceptor()
    }

    fun provideOkHttpClient(headerInterceptor: RequestHeaderInterceptor,
                            responseHeaderInterceptor: ResponseHeaderInterceptor,
                            logInterceptor: LoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(responseHeaderInterceptor)
                .addInterceptor(logInterceptor)  // logging interceptor must be initialized last to log properly
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    /* REST api */
    // --------------------------------------------------------------------------------------------
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
    }

    fun provideRestAdapter(retrofit: Retrofit.Builder): RestAdapter {
        return retrofit.baseUrl(RestAdapter.ENDPOINT).build()
                .create(RestAdapter::class.java)
    }

    // ------------------------------------------
    fun provideCloud(): RestAdapter {
        return provideRestAdapter(provideRetrofit(provideGson(),
                provideOkHttpClient(provideRequestHeaderInterceptor(),
                                    provideResponseHeaderInterceptor(),
                                    provideHttpLoggingInterceptor())))
    }

    fun provideDatabase(context: Context) = UserDatabase.build(context).userDao()
}
