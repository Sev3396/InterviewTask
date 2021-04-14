package com.tcs.task.di.module

import android.app.Application
import android.content.Context
import com.tcs.task.BuildConfig
import com.tcs.task.data.manager.AppDataManager
import com.tcs.task.data.manager.DataManager
import com.tcs.task.rx.AppSchedulerProvider
import com.tcs.task.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class
DataModule {


    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        cache: Cache,
        interceptors: Array<Interceptor?>
    ): OkHttpClient {
        val client = OkHttpClient.Builder()

        // add interceptors
        for (interceptor in interceptors) {
            if (interceptor != null) {
                client.addInterceptor(interceptor)
            }
        }
        return client.build()
    }

    /**
     * Generic method to provide OkhttpInterceptors.
     */
    @Provides
    @Singleton
    fun provideOkhttpInterceptors(): Array<Interceptor> {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return arrayOf(logging)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("Add base url here")
            .client(okHttpClient)
            .build()
    }

    /*@Provides
    @Singleton
    fun providesNetworkService(
        retrofit: Retrofit
    ): APIHelper {
        return retrofit.create<APIHelper>(APIHelper::class.java)
    }*/

    /*@Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit,
        apiHelper: APIHelper
    ): APIService {
        return APIService(retrofit, apiHelper)
    }*/
}
