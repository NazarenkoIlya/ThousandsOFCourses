package com.example.thousandsofcourses.di

import com.example.thousandsofcourses.data.service.CoursesService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://drive.usercontent.google.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun provideGson(): Gson = GsonBuilder()
        .create()

    fun provideCoursesService(retrofit: Retrofit): CoursesService =
        retrofit.create(CoursesService::class.java)


    single { provideOkHttpClient() }
    single { provideCoursesService(get()) }
    factory { provideRetrofit(
        get(),
        client = get()
    ) }
    factory { provideGson() }
}