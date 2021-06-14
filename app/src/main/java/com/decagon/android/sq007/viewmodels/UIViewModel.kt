package com.decagon.android.sq007.viewmodels


import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagon.android.sq007.Models.Comment
import com.decagon.android.sq007.Models.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.hilt.lifecycle.ViewModelInject
import com.decagon.android.sq007.repositories.IRepository


class  UIViewModel @ViewModelInject constructor(private val repository: IRepository): ViewModel() {



     private var livePosts:MutableLiveData<MutableList<Post>> = MutableLiveData()



     private var  liveComments:MutableLiveData<MutableList<Comment>> = MutableLiveData()




    fun getPosts(){

        try{
        CoroutineScope(IO).launch{
          val posts =  repository.getPosts()

            withContext(Main){

                livePosts.value =  posts

            }

     }}


        catch(e:Exception){
            Log.i("Error-Occured","${e.message}")
        }

    }



    fun getComments(postId: Int){

        CoroutineScope(IO).launch{
            val comments =  repository.getComments(postId)

            withContext(Main){

                liveComments.value =  comments
            }

        }


    }



    fun createPost(post: Post) {


        val newList: MutableList<Post> = livePosts.value!!

        CoroutineScope(IO).launch {
            newList.add(0, repository.createPost(post))

            withContext(Main) {

                livePosts.value = newList


            }
        }
    }



        fun createComment(comment: Comment) {
            val newList: MutableList<Comment> = liveComments.value!!

            CoroutineScope(IO).launch {
                newList.add(repository.createComment(comment))

                withContext(Main) {

                    liveComments.value = newList

                }
            }
        }




            fun deletePost(postId: Int) {


                val newList: MutableList<Post> = livePosts.value!!

                CoroutineScope(IO).launch {
                    newList.remove(repository.deletePost(postId))

                    withContext(Main) {

                        livePosts.value = newList

                    }
                }

            }


    // Query Post List based on Id or Title
    fun queryData(textToQuery: String?):MutableList<Post>{

        return if(!textToQuery?.isEmpty()!!){
            livePosts.value?.filter { post ->
                post.title.startsWith(textToQuery)
            }!!.toMutableList()
        }

        else if(textToQuery?.isDigitsOnly() == true){
            livePosts.value?.filter { post ->
                post.id.toString().contains(textToQuery)
            }!!.toMutableList()
        }

        else livePosts.value!!.toMutableList()
    }



    fun livePosts():MutableLiveData<MutableList<Post>>{
        return livePosts
    }


    fun liveComments():MutableLiveData<MutableList<Comment>>{
        return liveComments
    }





}