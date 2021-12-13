package com.example.chaljabhai

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_post.*
import kotlinx.android.synthetic.main.fragment_add_post.view.*
import post_folder.postDao


class add_post : Fragment() {

    private lateinit var postDao: postDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_post, container, false)

        postDao=postDao()

        view.send_post.setOnClickListener(){

            val postText=post.text.toString()
            if(postText.toString().isNotEmpty())
            {
                postDao.addPost(postText)
                findNavController().navigate(R.id.action_add_post_to_show_post)
                Log.d("navigation_here","error yaha he ")

            }

        }

        return view
    }

}