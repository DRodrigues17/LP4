package br.com.fundatec.exerciciologin

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tv_main).setOnClickListener {
            val navegarTelaPerfil = Intent(this, PerfilActivity::class.java)
            startActivity(navegarTelaPerfil)
        }
    }
}