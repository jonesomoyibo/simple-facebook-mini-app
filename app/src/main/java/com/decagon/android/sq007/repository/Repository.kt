package com.decagon.android.sq007.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.decagon.android.sq007.Daos.RetrofitDao
import com.decagon.android.sq007.DataModels.Comment
import com.decagon.android.sq007.DataModels.Post
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.channels.broadcast

object Repository {





    suspend fun  getPosts(): MutableList<Post>{
        return RetrofitDao.getPosts()
    }


   suspend fun getCommentsOnPost(postId:Int):MutableList<Comment>{
        return RetrofitDao.getComments(postId)

    }



    suspend fun createPost(post: Post):Post{
       return  RetrofitDao.createPost(post)


    }




    suspend fun deletePost(postId:Int): Post {
        return  RetrofitDao.deletePost(postId)
    }






    suspend fun createComment(comment: Comment):Comment{

        return  RetrofitDao.createComment(comment)

    }







    suspend fun deleteComment(postId: Int):Comment{
        return  RetrofitDao.deleteComment(postId)
    }







}