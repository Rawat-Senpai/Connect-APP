package com.example.chaljabhai

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_add_user.*
import kotlinx.android.synthetic.main.post_layout.*
import post_folder.postActivity
import users_folder.users
import users_folder.usersDao
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class addUserActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var personUrl: Uri
    lateinit var databaseRefrence: DatabaseReference
    private lateinit var usersDao: usersDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        auth= Firebase.auth

        imageView.setOnClickListener(){
            selectPersonImage()
        }

        addUserDataToFirebase.setOnClickListener{
            val currentUser= auth.currentUser
            addUserData(currentUser)
        }

    }

    private fun selectPersonImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 15)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 15 && resultCode == AppCompatActivity.RESULT_OK) {
            personUrl = data?.data!!
            imageView.setImageURI(personUrl)

        }
    }


    private fun addUserData(currentUser: FirebaseUser?) {
        val firebaseUser=auth.currentUser
        if(firebaseUser != null) {

        val loading = loadingDialog(this)
            loading.startLoadin()

            val formatter = SimpleDateFormat("dd_MM_yyyy_ss", Locale.getDefault())
            val now = Date()
            val fileName = formatter.format(now)

            val storageReference = FirebaseStorage.getInstance().getReference("UserPhoto/$fileName")

            val resolver = this.contentResolver
            val bitmap= MediaStore.Images.Media.getBitmap(resolver,personUrl)
            val byteArrayOutputStrem= ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG,10,byteArrayOutputStrem)
            val reduceImage: ByteArray= byteArrayOutputStrem.toByteArray()

            storageReference.putBytes(reduceImage).addOnSuccessListener {

                Toast.makeText(this, "Photo is uploaded successfully ", Toast.LENGTH_LONG).show()



                storageReference.downloadUrl.addOnSuccessListener {

                    val personImageUrl = it.toString()
                    val userName = userName.text.toString()



                        val user = users(firebaseUser.uid, userName, personImageUrl)
                        val usersDao = usersDao()
                        usersDao.addUser(user)

                        loading.isDismiss()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)


                }

                }

        }
    }


}