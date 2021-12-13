package post_folder

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import users_folder.users
import users_folder.usersDao

class postDao {

    val db = FirebaseFirestore.getInstance()
    val postCollections = db.collection("posts")
    val auth = Firebase.auth

    fun addPost(text: String)
    {
        GlobalScope.launch {
            val currentUserId=auth.currentUser!!.uid
            val userDao= usersDao()
            val user= userDao.gerUserById(currentUserId).await().toObject(users::class.java)!!

            val currentTime=System.currentTimeMillis()
            val post = Post(text,user,currentTime)
            postCollections.document().set(post)

        }

        fun getPost(postId:String): Task<DocumentSnapshot> {
            return postCollections.document(postId).get()
        }
    }

}