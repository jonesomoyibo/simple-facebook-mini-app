package com.decagon.android.sq007.interfaces.data


import com.decagon.android.sq007.Models.Comment
import com.decagon.android.sq007.Models.Post
import retrofit2.http.*


interface RetrofitDao {

    @GET("/posts/{id}/comments")
    suspend fun getComments(@Path("id") id:Int):MutableList<Comment>

    @GET("posts")
    suspend fun getPosts(): MutableList<Post>


    @POST("/posts")
    suspend fun createPost(@Body post:Post):Post


    @POST("/comments")
    suspend fun createComment(@Body comment: Comment): Comment


    @PUT("/posts/{id}")
    suspend fun updatePost(@Path("id") id:Int):Post


    @PUT("/comments/{id}")
    suspend fun updateComment(@Path("id" ) id:Int):Comment
}