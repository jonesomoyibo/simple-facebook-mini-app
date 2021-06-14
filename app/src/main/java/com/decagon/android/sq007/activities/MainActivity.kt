package com.decagon.android.sq007.activities

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagon.android.sq007.Models.Post
import com.decagon.android.sq007.viewmodels.UIViewModel
import com.decagon.android.sq007.R
import com.decagon.android.sq007.adapters.RecyclerViewAdapter
import com.decagon.android.sq007.databinding.ActivityMainBinding
import com.decagon.android.sq007.interfaces.ui.OnAdapterItemListener


class MainActivity : AppCompatActivity(), OnAdapterItemListener {



    private  lateinit var viewBinder:ActivityMainBinding
    private  val postViewModel: UIViewModel by viewModels()
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        viewBinder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)
        setupUI()
        setObservables()
        showProgressBar()

    }


    private fun setupUI() {


        supportActionBar?.apply{
           title ="POSTS"
            setBackgroundDrawable(
                ColorDrawable(getResources()
                .getColor(R.color.BLUE,null))
            );

        }

        // setup post list recyclerView
        viewBinder.postrecyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = RecyclerViewAdapter<Post>(mutableListOf(),this@MainActivity,this@MainActivity)
        }


        // register click event on the post button
        viewBinder.postview.apply {

            setOnClickListener{
                startPostActivity()
            }


        }


        // register queryEvent on the searchView
        viewBinder.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {



                    viewBinder.postrecyclerview.adapter.apply {

                        (this as RecyclerViewAdapter<Post>).apply {

                            setAdapterList(postViewModel.queryData(newText))

                            notifyDataSetChanged()
                        }
                    }



                return true
            }
        })

        progressDialog = ProgressDialog(this).apply {
            setTitle("Loading...Please Wait")
            setCancelable(false)

        }

    }


    private fun setObservables(){

       // postViewModel = ViewModelProvider.NewInstanceFactory().create(UIViewModel::class.java)
        postViewModel.livePosts().observe(this){


                viewBinder.postrecyclerview.adapter.apply {

                    (this as RecyclerViewAdapter<Post>).apply {
                        setAdapterList(it)
                        notifyDataSetChanged()
                        hideProgressBar()
                    }
                }
            }


    }



    override fun onItemClick(postId: Int) = startActivity(Intent(this, CommentActivity::class.java)
        .apply {
            putExtra("POSTID",postId)
        })




    private fun startPostActivity(){
        startActivityForResult(Intent(this, CreatePostActivity::class.java),100)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode == RESULT_OK){
            postViewModel.createPost(Post(data!!.getIntExtra("USERID",0),null,data!!.getStringExtra("TITLE")!!,data!!.getStringExtra("BODY")!!))

        }


    }

//  private fun fetchData(){
//      showProgressBar()
//      postViewModel.getPosts()
//  }


    private fun showProgressBar() = progressDialog.show()

    private fun hideProgressBar() = progressDialog.hide()




}