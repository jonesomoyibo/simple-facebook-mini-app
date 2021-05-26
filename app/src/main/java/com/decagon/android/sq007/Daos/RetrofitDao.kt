package com.decagon.android.sq007.Daos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.decagon.android.sq007.api.ApiService
import com.decagon.android.sq007.constants.Constants
import com.decagon.android.sq007.DataModels.Comment
import com.decagon.android.sq007.DataModels.Post
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitDao{

    // initialize this once and only on the first referencing
    private val retrofit : Retrofit.Builder by lazy {
        Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)

    }


    // initialize this once and only on the first referencing
    private val retrofitApiService : ApiService by lazy {
        retrofit .build().create(ApiService::class.java)
    }



     suspend fun postComment(comment: Comment):Comment {
      return retrofitApiService.createComment(comment)

     }

    suspend fun getComments(postId:Int): MutableList<Comment> {
        return retrofitApiService.getComments(postId)


   }

    suspend fun deleteComment(postId: Int):Comment{
     return retrofitApiService.deleteComment(postId)

    }

    suspend fun updateComment(postId: Int): Comment {
        return retrofitApiService.updateComment(postId)

    }

    suspend fun getPosts(): MutableList<Post> {
     return retrofitApiService.getPosts()

    }

    suspend fun deletePost(postId: Int):Post {
        return retrofitApiService.deletePost(postId)

    }

    suspend fun updatePost(postId: Int): Post {
      return retrofitApiService.deletePost(postId)

    }

    suspend fun createPost(post: Post):Post {
       return retrofitApiService.createPost(post)

    }
    suspend fun createComment(comment: Comment):Comment{
        return retrofitApiService.createComment(comment)

    }
}