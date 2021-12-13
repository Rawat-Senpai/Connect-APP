package com.example.chaljabhai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_log_in.*
import post_folder.postActivity

class logInActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)


        firebaseAuth= FirebaseAuth.getInstance()
        logInButton.setOnClickListener(){

            logInFunction()

            val intent= Intent(this,postActivity::class.java)
            startActivity(intent)
        }

        galtiSe.setOnClickListener(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun logInFunction() {
        val gmail= logInemail.text.toString()
        val password = logInPassword.text.toString()

        if (gmail.equals("")||password.equals("")){
            Toast.makeText(this,"enter data carefully  ", Toast.LENGTH_LONG).show()
            return
        }



        firebaseAuth.signInWithEmailAndPassword(gmail,password).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this,"Log in successfully ", Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(this,"error aa gaya bhai", Toast.LENGTH_LONG).show()
            }
        }

    }
}