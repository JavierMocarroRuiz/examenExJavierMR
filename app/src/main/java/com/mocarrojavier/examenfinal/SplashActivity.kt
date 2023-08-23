package com.mocarrojavier.examenfinal

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mocarrojavier.examenfinal.databinding.ActivitySplashBinding
import com.mocarrojavier.examenfinal.databinding.FragmentListBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = getSharedPreferences(LoginActivity.SESSION_PREFERENCE, MODE_PRIVATE)

        Handler().postDelayed({
            val emaill: String = sharedPreferences.getString(LoginActivity.EMAIL, "")?: ""
            val intent = if(emaill.isNotEmpty()){
                Intent(this, MainActivity::class.java)
            }else{
                Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        },2000)
    }
}