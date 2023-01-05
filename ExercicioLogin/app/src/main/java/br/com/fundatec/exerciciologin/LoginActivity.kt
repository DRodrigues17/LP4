package br.com.fundatec.exerciciologin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.bt_login).setOnClickListener {

            if (emailValido()) {
                Toast.makeText(this, "digite um e-mail válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (!senhaValida()) {
                Toast.makeText(this, "digite uma senha válida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val navegarHomePage = Intent(this, MainActivity::class.java)
            startActivity(navegarHomePage)
        }
    }

    private fun emailValido(): Boolean {
        val email = findViewById<EditText>(R.id.et_email).toString()
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun senhaValida(): Boolean {
        val senha = findViewById<EditText>(R.id.et_senha).toString()
        return senha.length >= 8
    }

}
