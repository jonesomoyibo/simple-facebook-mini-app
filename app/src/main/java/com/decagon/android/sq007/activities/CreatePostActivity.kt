package com.decagon.android.sq007.activities

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.decagon.android.sq007.R
import com.decagon.android.sq007.databinding.ActivityCreatePostBinding


class CreatePostActivity : AppCompatActivity() {

    private  lateinit var viewBinder:ActivityCreatePostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewBinder = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_create_post)
        setContentView(viewBinder.root)
        setupUI()





    }


    private fun setupUI() {

        supportActionBar?.apply{
            title ="NEW POST"
            setBackgroundDrawable(
                ColorDrawable(getResources()
                    .getColor(R.color.BLUE,null))
            );

        }
        viewBinder.postbutton.apply {
            isEnabled= false

            setOnClickListener{
                if( viewBinder.titlearea.text.toString().length < 3){
                    Toast.makeText(this@CreatePostActivity,"Please Enter a Title For the Post",Toast.LENGTH_SHORT).show()
                }

                else {

                    setResult(RESULT_OK, Intent().apply {
                        putExtra("USERID",200)
                        putExtra("TITLE",viewBinder.titlearea.text.toString())
                        putExtra("BODY",viewBinder.postcontentarea.text.toString())
                    })

                Toast.makeText(this@CreatePostActivity,"Posting...",Toast.LENGTH_LONG).show()
                 finish()
                }
            }
        }

        viewBinder.backarrow.apply {
            setOnClickListener{
                onBackPressed()
            }
        }

        viewBinder.postcontentarea.apply{
             doOnTextChanged { charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
                  if(charSequence?.length!! > 0){
                      viewBinder.postbutton.apply {
                          isEnabled =true
                          setBackgroundColor(resources.getColor(R.color.BLUE,null))
                      }

                  }
                  else {
                      viewBinder.postbutton.apply{
                          isEnabled =false
                          setBackgroundColor(resources.getColor(R.color.grey,null))
                      }
                  }
             }
        }

    }

}