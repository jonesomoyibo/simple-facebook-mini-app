package com.decagon.android.sq007.repositories


import com.decagon.android.sq007.Models.Comment
import com.decagon.android.sq007.Models.Post
import com.decagon.android.sq007.interfaces.data.RetrofitDao
import com.decagon.android.sq007.interfaces.data.RoomDao
import javax.inject.Inject


class Repository @Inject constructor (
        private val retrofitDao: RetrofitDao ,
        private val roomDao: RoomDao
        ): IRepository
{
    override fun getAllPosts(): List<Post> {
        TODO("Not yet implemented")
    }

    override fun insertPost(posts: List<Post>) {
        TODO("Not yet implemented")
    }

    override fun deleteAllPosts() {
        TODO("Not yet implemented")
    }

    override fun deletePost(postId: Int) {
        TODO("Not yet implemented")
    }


    override suspend fun getComments(id: Int): MutableList<Comment> {
        TODO("Not yet implemented")
    }




    override suspend fun  getPosts(): MutableList<Post>{
        return retrofitDao.getPosts()
    }


       suspend fun getCommentsOnPost(postId:Int):MutableList<Comment>{
        return retrofitDao.getComments(postId)

    }



       override suspend fun createPost(post: Post):Post{
       return  retrofitDao.createPost(post)


    }





     override suspend fun createComment(comment: Comment):Comment{

        return  retrofitDao.createComment(comment)

    }


    override suspend fun updatePost(id: Int): Post {
        TODO("Not yet implemented")
    }

    override suspend fun updateComment(id: Int): Comment {
        TODO("Not yet implemented")
    }


}