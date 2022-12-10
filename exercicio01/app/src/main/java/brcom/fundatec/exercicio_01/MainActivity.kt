package brcom.fundatec.exercicio_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.bt_limpar).setOnClickListener { limparInput() }
    }

    fun limparInput(){
        val nome = findViewById<EditText>(R.id.et_digite_aqui)
        nome.text.clear()
    }

    fun adcionarTextoFrase(){

    }
}