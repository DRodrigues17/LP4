package br.com.fundatec.exerciciosplashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager.LayoutParams.*
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getSupportActionBar()?.hide();
        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}