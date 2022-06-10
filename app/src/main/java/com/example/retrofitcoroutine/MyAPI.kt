package com.example.retrofitcoroutine

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

//interface MyAPI {
//    @GET("/comments")
//    fun getComments(): Call<List<Comments>>
//}
interface MyAPI {
    @GET("/comments")
    suspend fun getComments(): Response<List<Comments>>
}