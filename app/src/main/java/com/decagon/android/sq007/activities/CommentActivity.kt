package com.decagon.android.sq007.activities

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagon.android.sq007.Models.Comment
import com.decagon.android.sq007.viewmodels.UIViewModel
import com.decagon.android.sq007.R
import com.decagon.android.sq007.adapters.RecyclerViewAdapter
import com.decagon.android.sq007.databinding.ActivityCommentBinding

class CommentActivity : AppCompatActivity() {

    private  lateinit var viewBinder:ActivityCommentBinding
    private  val commentViewModel: UIViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        viewBinder = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)
        setupUI()
        setObservables()
        fetchComments()



    }


    private fun setupUI() {

        supportActionBar?.apply{
            title ="COMMENTS"
            setBackgroundDrawable(
                ColorDrawable(getResources()
                    .getColor(R.color.BLUE,null))
            );

        }

        viewBinder.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@CommentActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = RecyclerViewAdapter<Comment>(mutableListOf(),this@CommentActivity,null)
        }

        viewBinder.sendbuton.apply {
            setOnClickListener{createComment(Comment(intent.getIntExtra("POSTID",0),null,
                "Darot","Darot@gmail.com",viewBinder.commentview.text.toString()))
                viewBinder.commentview.apply {
                    clearFocus()
                    text.clear()
                    viewBinder.recyclerView.requestFocus()

                }

            }
            isVisible=false
        }

        viewBinder.commentview.apply {
            doOnTextChanged { charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
                if(charSequence?.length!! > 0){
                    viewBinder.sendbuton.apply {
                        isVisible = true

                    }

                }
                else {
                    viewBinder.sendbuton.apply{
                        isVisible = false

                    }
                }
            }
        }

    }




    private fun setObservables(){

        //commentViewModel = ViewModelProvider.NewInstanceFactory().create(UIViewModel::class.java)
        commentViewModel.liveComments().observe(this){

            viewBinder.recyclerView.adapter.apply {

                (this as RecyclerViewAdapter<Comment>).apply {
                    setAdapterList(it)
                    notifyDataSetChanged()
                }
            }
        }
    }




    private fun fetchComments(){
        commentViewModel.getComments(intent.getIntExtra("POSTID",0))
    }

    private fun createComment(comment:Comment){
        commentViewModel.createComment(comment)
    }
}