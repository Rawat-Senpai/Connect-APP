package users_folder

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class usersDao {
    val db= FirebaseFirestore.getInstance()
    val userCollection=db.collection("users")


    fun addUser(user: users?){
        user?.let {
            GlobalScope.launch ( Dispatchers.IO ){
                userCollection.document(user.uid).set(it)
            }
        }
    }

    fun gerUserById(uId:String): Task<DocumentSnapshot> {
        return userCollection.document(uId).get()
    }
}

