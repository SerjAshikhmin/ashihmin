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

//    @SerializedName("id")
//    @Expose
//    val id: Int,
//
//    @SerializedName("votes")
//    @Expose
//    val votes: Int,
//
//    @SerializedName("author")
//    @Expose
//    val author: String,
//
//    @SerializedName("date")
//    @Expose
//    val date: String,
//
//    @SerializedName("gifSize")
//    @Expose
//    val gifSize: Long,
//
//    @SerializedName("previewURL")
//    @Expose
//    val previewURL: String,
//
//    @SerializedName("videoURL")
//    @Expose
//    val videoURL: String,
//
//    @SerializedName("videoPath")
//    @Expose
//    val videoPath: String,
//
//    @SerializedName("videoSize")
//    @Expose
//    val videoSize: Long,
//
//    @SerializedName("type")
//    @Expose
//    val type: String,
//
//    @SerializedName("width")
//    @Expose
//    val width: String,
//
//    @SerializedName("height")
//    @Expose
//    val height: String,
//
//    @SerializedName("commentsCount")
//    @Expose
//    val commentsCount: Int,
//
//    @SerializedName("fileSize")
//    @Expose
//    val fileSize: Long,
//
//    @SerializedName("canVote")
//    @Expose
//    val canVote: Boolean,
)