package ru.tinkoff.android.ashihmin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GifImage (

    @SerializedName("gifURL")
    @Expose
    val gifURL: String,

    @SerializedName("description")
    @Expose
    val description: String,

)