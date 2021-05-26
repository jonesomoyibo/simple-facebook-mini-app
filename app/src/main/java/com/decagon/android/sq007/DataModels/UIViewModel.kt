package com.decagon.android.sq007.DataModels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagon.android.sq007.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UIViewModel(): ViewModel() {






     var livePosts:MutableLiveData<MutableList<Post>> = MutableLiveData()


     var  liveComments:MutableLiveData<MutableList<Comment>> = MutableLiveData()



    fun fetchPosts(){

        CoroutineScope(IO).launch{
          val posts =  Repository.getPosts()

            withContext(Main){

                livePosts.value =  posts
            }

     }


    }


    fun fetchComments(postId:Int){

        CoroutineScope(IO).launch{
            val comments =  Repository.getCommentsOnPost(postId)

            withContext(Main){

                liveComments.value =  comments
            }

        }


    }


    fun createPost(post:Post) {

        Log.i("Getting Here","${post.body}")
        val newList: MutableList<Post> = livePosts.value!!

        CoroutineScope(IO).launch {
            newList.add(Repository.createPost(post))

            withContext(Main) {

                livePosts.value = newList
                Log.i("Getting Here","${newList[newList.size-1].body}")

            }
        }
    }


        fun createComment(comment: Comment) {
            val newList: MutableList<Comment> = liveComments.value!!

            CoroutineScope(IO).launch {
                newList.add(Repository.createComment(comment))

                withContext(Main) {

                    liveComments.value = newList

                }
            }
        }


            fun deletePost(postId: Int) {


                val newList: MutableList<Post> = livePosts.value!!

                CoroutineScope(IO).launch {
                    newList.remove(Repository.deletePost(postId))

                    withContext(Main) {

                        livePosts.value = newList

                    }
                }

            }


            fun deleteComment(postId: Int) {
                val newList: MutableList<Comment> = liveComments.value!!
                CoroutineScope(IO).launch {
                    newList.remove(Repository.deleteComment(postId))

                    withContext(Main) {

                        liveComments.value = newList

                    }
                }
            }





}