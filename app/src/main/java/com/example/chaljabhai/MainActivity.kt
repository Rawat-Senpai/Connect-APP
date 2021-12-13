package com.example.chaljabhai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import post_folder.postActivity

class MainActivity : AppCompatActivity() {

    lateinit var signGmale : EditText
    lateinit var signPassword: EditText
    lateinit var signUpPasswordConf: EditText
    lateinit var  firebaseAuth: FirebaseAuth
    lateinit var signUpBtn: Button
    private lateinit var auth: FirebaseAuth
    lateinit var  signInToLogIn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signGmale=findViewById(R.id.SignUpGmale)
        signPassword=findViewById(R.id.SignUpPassword)
        signUpPasswordConf=findViewById(R.id.SignUpPasswordConf)
        signInToLogIn=findViewById(R.id.SignInToLogIn)
        signUpBtn=findViewById(R.id.signUpBtn)

        firebaseAuth= FirebaseAuth.getInstance()

        signUpBtn.setOnClickListener(){
            signUpNewUser()
        }

        auth= FirebaseAuth.getInstance()
        if(auth.currentUser !=null)
        {
            Toast.makeText(this,"User is already log in ",Toast.LENGTH_SHORT).show()
            redirectFunction("MAIN")
        }


        SignInToLogIn.setOnClickListener(){
            val intent= Intent(this,logInActivity::class.java)
            startActivity(intent)
        }

    }

    private fun redirectFunction(name: String) {
        val intent = when(name)
        {
            "MAIN" -> Intent(this,postActivity::class.java)

            else-> Toast.makeText(this,"you have to log in first ",Toast.LENGTH_SHORT).show()
        }
        startActivity(intent as Intent?)
        finish()
    }

    private fun signUpNewUser() {
        val emale= signGmale.text.toString()
        val password= signPassword.text.toString()
        val password2=signUpPasswordConf.text.toString()

        if (emale.equals("")||password.equals("")||password2.equals(""))
        {
            Toast.makeText(this," something is blank ", Toast.LENGTH_LONG).show()
            return
        }
        if (password!=password2)
        {
            Toast.makeText(this,"passowrd do not match ", Toast.LENGTH_LONG).show()
            return
        }
        if(password.length<=7){
            Toast.makeText(this,"Password should be of 8 or more than 8 degit ",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(emale,password).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this,"Account created successfully ", Toast.LENGTH_LONG).show()
                val intent = Intent(this,addUserActivity::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this,"Error in creating new user", Toast.LENGTH_LONG).show()
            }
        }



    }




}