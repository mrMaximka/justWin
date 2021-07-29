package com.winnews.justwin.link

import com.winnews.justwin.model.LinkModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LinkInterface {
    @GET("api/v1/applications/{id}")
    fun loadData(@Path("id") uuid: String, @Query("application") applicationID: String): Call<LinkModel>
}