package ru.tinkoff.android.ashihmin.api

import retrofit2.Call
import retrofit2.http.GET
import ru.tinkoff.android.ashihmin.model.GifImage
import ru.tinkoff.android.ashihmin.model.GifImageList

interface JSONPlaceHolderApi {

    @GET("random?json=true")
    fun getRandomImage(): Call<GifImage>

    @GET("latest/0?json=true")
    fun getLatestImagesList(): Call<GifImageList>

    @GET("top/0?json=true")
    fun getTopImagesList(): Call<GifImageList>
}