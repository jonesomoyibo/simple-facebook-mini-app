package com.decagon.android.sq007.api


import com.decagon.android.sq007.DataModels.Comment
import com.decagon.android.sq007.DataModels.Post
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/posts/{id}/comments")
    suspend fun getComments(@Path("id") id:Int):MutableList<Comment>

    @GET("posts")
    suspend fun getPosts(): MutableList<Post>



    @POST("posts")
    suspend fun createPost(@Body post:Post):Post


    @POST("/comments")
    suspend fun createComment(@Body comment: Comment): Comment


   @DELETE("/posts/{id}")
   suspend fun deletePost(@Path("id") id:Int):Post


    @DELETE("/comments/{id}")
    suspend fun deleteComment(@Path("id") id:Int):Comment


    @PUT("/posts/{id}")
    suspend fun updatePost(@Path("id") id:Int):Post


    @PUT("/comments/{id}")
    suspend fun updateComment(@Path("id" ) id:Int):Comment
}