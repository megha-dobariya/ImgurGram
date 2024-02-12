package com.mdtech.libimgur

import com.mdtech.libimgur.apis.ImgurAPIv3
import com.mdtech.libimgur.converters.EnumConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ImgurClient {

    public const val API_KEY="af14fe8fb61d2b4"

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("Authorization","Client-ID $API_KEY")
                    .build()
                it.proceed(request)
            }
//            .cache()
            .build()
    }


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(EnumConverterFactory())
            .baseUrl("https://api.imgur.com/3/")
            .build()
    }

    val api: ImgurAPIv3 by lazy {
        retrofit.create(ImgurAPIv3::class.java)
    }
}