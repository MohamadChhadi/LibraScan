package com.ids.librascan.controller.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.ids.librascan.R
import com.ids.librascan.controller.MyApplication
import kotlinx.android.synthetic.main.activity_main.*
import utils.toast

class ActivityMain : AppCompatActivity() {
    lateinit var mGoogleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

   fun init() {
       cVScan.setOnClickListener {
           startActivity(Intent(this, ActivityScan::class.java))
       }

       val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
           .requestIdToken(getString(R.string.default_web_client_id_auth))
           .requestEmail()
           .build()
       mGoogleSignInClient= GoogleSignIn.getClient(this,gso)


       iVLogout.setOnClickListener {
           mGoogleSignInClient.signOut().addOnCompleteListener {
               MyApplication.isLogin = false
               val intent= Intent(this, ActivityLogin::class.java)
               toast(getString(R.string.logout))
               startActivity(intent)
               finish()
           }
       }

    }


}