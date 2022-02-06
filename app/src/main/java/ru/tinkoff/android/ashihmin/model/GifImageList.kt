package ru.tinkoff.android.ashihmin.model

import com.google.gson.annotations.SerializedName

data class GifImageList (

    @SerializedName("result")
    val list : List<GifImage>
)