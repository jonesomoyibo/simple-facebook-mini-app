package com.decagon.android.sq007.interfaces.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.decagon.android.sq007.Models.Post

@Dao
interface RoomDao {


    @Query("SELECT * FROM posts")
    fun getAllPosts(): List<Post>


    @Insert(onConflict = REPLACE)
    fun insertPost (posts:List<Post>)

    @Query("DELETE FROM posts")
    fun deleteAllPosts()

    @Query("DELETE FROM posts")
    fun deletePost(postId:Int)



}