package brcom.fundatec.exercicio_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.bt_limpar).setOnClickListener {
            limparInput()
            findViewById<Button>(R.id.bt_ok).setOnClickListener {
                adcionarTextoFrase()
            }
        }
    }

    fun limparInput() {
        val nome = findViewById<EditText>(R.id.et_digite_aqui)
        nome.text.clear()
    }

    fun adcionarTextoFrase() {
        val nome = findViewById<EditText>(R.id.et_digite_aqui)
        val frase = findViewById<TextView>(R.id.tv_frase)

        frase.setText("oi ${nome.text}, nome legal")
        nome.text.clear()

    }
}