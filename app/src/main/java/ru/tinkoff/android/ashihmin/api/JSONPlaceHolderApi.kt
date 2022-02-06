package ru.tinkoff.android.ashihmin.api

import retrofit2.Call
import retrofit2.http.GET
import ru.tinkoff.android.ashihmin.model.GifImage

interface JSONPlaceHolderApi {

    @GET("random?json=true")
    fun getRandomImage(): Call<GifImage>
}