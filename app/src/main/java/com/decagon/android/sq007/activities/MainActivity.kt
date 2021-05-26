package com.decagon.android.sq007.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagon.android.sq007.DataModels.Comment
import com.decagon.android.sq007.DataModels.Post
import com.decagon.android.sq007.DataModels.UIViewModel
import com.decagon.android.sq007.R
import com.decagon.android.sq007.adapters.RecyclerViewAdapter
import com.decagon.android.sq007.databinding.ActivityMainBinding
import com.decagon.android.sq007.interfaces.OnAdapterItemListener

class MainActivity : AppCompatActivity(), OnAdapterItemListener {



    private  lateinit var viewBinder:ActivityMainBinding
    private lateinit var postViewModel: UIViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        viewBinder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)
        setupUI()
        setObservables()
        postViewModel.fetchPosts()

    }


    private fun setupUI() {
        viewBinder.postrecyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = RecyclerViewAdapter<Post>(mutableListOf(),this@MainActivity,this@MainActivity)
        }

        viewBinder.postview.apply {

            setOnClickListener{
                startPostActivity()
            }


        }




    }


    private fun setObservables(){

        postViewModel = ViewModelProvider.NewInstanceFactory().create(UIViewModel::class.java)
        postViewModel.livePosts.observe(this){
                Log.i("Getting Here","${it[it.size-1].body}")
                viewBinder.postrecyclerview.adapter.apply {

                    (this as RecyclerViewAdapter<Post>).apply {
                        setAdapterList(it)
                        notifyDataSetChanged()
                    }
                }
            }


    }



    override fun onItemClick(postId: Int) {

        startActivity(Intent(this,CommentActivity::class.java).apply {
            putExtra("POSTID",postId)
        })

    }


    private fun startPostActivity(){
        startActivityForResult(Intent(this,CreatePostActivity::class.java),100)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode == RESULT_OK){
            postViewModel.createPost(Post(data!!.getIntExtra("USERID",0),null,data!!.getStringExtra("TITLE")!!,data!!.getStringExtra("BODY")!!))

        }


    }




}