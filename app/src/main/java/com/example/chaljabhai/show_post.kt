package com.example.chaljabhai

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_show_post.*
import kotlinx.android.synthetic.main.fragment_show_post.view.*
import post_folder.Post
import post_folder.postDao

class show_post : Fragment() {

    lateinit var adapter: PostAdapter
    lateinit var  postDao: postDao


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_post, container, false)

        view.postSend.setOnClickListener(){
            sendPost()
        }

        val postRecyclerView= view.postRecyclerView

        postDao= postDao()

        val postCollection= postDao.postCollections
        val query = postCollection.orderBy("createdAt", Query.Direction.DESCENDING)
        val recyclerViewOption = FirestoreRecyclerOptions.Builder<Post>().setQuery(query,Post::class.java).build()

      //  adapter = PostAdapter(recyclerViewOption)
        adapter = PostAdapter(recyclerViewOption,requireContext())

        postRecyclerView.adapter= adapter
        postRecyclerView.layoutManager= LinearLayoutManager(this.requireContext())
        postRecyclerView.scrollToPosition(adapter.itemCount.minus(-1))
        postRecyclerView.itemAnimator=null


        return  view
    }

    private fun sendPost() {
      val typedMessage= Message.text.toString()
        if(typedMessage.toString().isNotEmpty())
        {
            postDao.addPost(typedMessage)
            Log.d("navigation_here","error yaha he ")
            Message.text.clear()
            return
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.startListening()
    }

}