package com.winnews.justwin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LinkModel(
    @Expose
    @SerializedName("message")
    val link: String?
)
