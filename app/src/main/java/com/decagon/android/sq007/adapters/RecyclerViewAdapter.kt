package com.decagon.android.sq007.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.Models.Comment
import com.decagon.android.sq007.Models.Post
import com.decagon.android.sq007.R
import com.decagon.android.sq007.databinding.CommentslayoutBinding
import com.decagon.android.sq007.databinding.PostlayoutBinding
import com.decagon.android.sq007.interfaces.ui.OnAdapterItemListener

class RecyclerViewAdapter<T>(private var list:MutableList<T>, private var context: Context, private val adapterItemClickListener: OnAdapterItemListener?): RecyclerView.Adapter<RecyclerViewAdapter<T>.ListViewHolder>(){


    inner class ListViewHolder(view: View):RecyclerView.ViewHolder(view){}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

      var listViewHolder : ListViewHolder? =null
        if(list[0] is Post) {
            listViewHolder= ListViewHolder(
                LayoutInflater.from(context).inflate(R.layout.postlayout, parent, false)
            )
        }
        if(list[0] is Comment){
            listViewHolder= ListViewHolder(
                LayoutInflater.from(context).inflate(R.layout.commentslayout, parent, false)
            )
        }
        return  listViewHolder!!
    }




    override fun getItemCount(): Int = list.size




    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        when{

            list[0] is Post -> {
                val postBinder = PostlayoutBinding.bind(holder.itemView)
                val lists= list as MutableList<Post>
                postBinder.Postcontent.text = lists[holder.adapterPosition].body
                postBinder.posttitle.text = lists[holder.adapterPosition].title
                postBinder.commentstextview.setOnClickListener{
                    lists[holder.adapterPosition]?.id?.let { postId ->
                        adapterItemClickListener?.onItemClick(
                            postId
                        )
                    }
                }
                postBinder.commmenttextview.setOnClickListener{
                    lists[holder.adapterPosition]?.id?.let { postId ->
                        adapterItemClickListener?.onItemClick(
                            postId
                        )
                    }
                }


            }

            list[0] is Comment -> {
                var commentBinder:CommentslayoutBinding =  CommentslayoutBinding.bind(holder.itemView)
                val lists= list as MutableList<Comment>
                commentBinder.commentmessage.text = lists[holder.adapterPosition].body
                commentBinder.fullname.text = lists[holder.adapterPosition].name
            }
        }


    }


     fun setAdapterList(postList:MutableList<T>): () -> Unit = {list = postList}


}