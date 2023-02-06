package com.nihilus13.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nihilus13.data.BuildConfig
import com.nihilus13.data.repository.api.SearchService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal object NetworkModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient = with(OkHttpClient.Builder()) {
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            addInterceptor(logging)
        }
        addInterceptor {
            val original = it.request()

            val request = original.newBuilder()
                .method(original.method, original.body)
                .build()
            it.proceed(request)
        }
    }.build()

    @Provides
    @Singleton
    internal fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    internal fun provideSearchService(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): SearchService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(BuildConfig.API_URL)
            .build()
            .create(SearchService::class.java)
}
