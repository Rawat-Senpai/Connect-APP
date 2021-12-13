package com.example.chaljabhai

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import post_folder.Post

class PostAdapter(options: FirestoreRecyclerOptions<Post>, requireContext: Context):
    FirestoreRecyclerAdapter<Post, PostAdapter.postViewHolder>(options) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.postViewHolder {

//        val view= LayoutInflater.from(parent.context).inflate(R.layout.post_layout,parent,false)
//        val viewHolder= postViewHolder(view)
//        return viewHolder

        val viewHolder = postViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_layout,parent,false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: PostAdapter.postViewHolder, position: Int, model: Post) {
        //  holder.user_name.text=model.text
        holder.userPost.text=model.text
        holder.user_name.text=model.createdBy.displayName
        Glide.with(holder.user_image.context).load(model.createdBy.imageUrl).circleCrop().into(holder.user_image)
        holder.post_time.text= Utils.getTimeAgo(model.createdAt)
    }

    inner class postViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {

        val user_name: TextView = itemView.findViewById(R.id.user_name)
        val post_time: TextView = itemView.findViewById(R.id.post_time)
        val userPost: TextView = itemView.findViewById(R.id.user_text)
        val user_image: ImageView = itemView.findViewById(R.id.user_image)

    }
}