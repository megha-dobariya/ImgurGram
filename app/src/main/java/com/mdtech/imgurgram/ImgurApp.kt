package com.mdtech.imgurgram

import android.app.Application
import android.os.Build.VERSION.SDK_INT
import coil.Coil
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

class ImgurApp : Application() {

//   private  val imageLoaderBuilder =
//    override fun newImageLoader() =

    override fun onCreate() {
        super.onCreate()

        Coil.setImageLoader(ImageLoader.Builder(this@ImgurApp)
            .componentRegistry{
                if(SDK_INT >= 35){
                    add(ImageDecoderDecoder(this@ImgurApp))
                }else{
                    add(GifDecoder())
                }
            }.build())
    }
}