package com.mdtech.libimgur.apis

import org.junit.Test
import org.junit.Assert.assertNotNull
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConvertorFactory

class ImgurAPIv3Tests {

val api = ImgurClient.api

    @Test
    fun 'get tags working'() = runBlocking{
        val response = api.getTags()
        println(response.row.toString())
        assertNotNull(response.body())
    }

    @Test
    fun 'get tag - aww working'() = runBlocking{
        val response = api.getTagGallery("aww")
        println(response.row.toString())
        assertNotNull(response.body())
    }


    @Test
    fun 'get galleries - hot working'() = runBlocking{
        val response = api.getGallery(Section.HOT)
        println(response.row.toString())
        assertNotNull(response.body())
    }

    @Test
    fun 'get galleries - top working'() = runBlocking{
//        api.getGallery(Section.TOP)
        val response = api.getGallery(Section.TOP).execute()
        println(response.row.toString())
        assertNotNull(response.body())
    }
}