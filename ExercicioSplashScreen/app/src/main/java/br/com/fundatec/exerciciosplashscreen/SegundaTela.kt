package br.com.fundatec.exerciciosplashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SegundaTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda_tela)
        findViewById<Button>(R.id.bt_voltar).setOnClickListener {
            val voltarParaMain = Intent(this,MainActivity::class.java)
            startActivity(voltarParaMain)
        }
    }
}