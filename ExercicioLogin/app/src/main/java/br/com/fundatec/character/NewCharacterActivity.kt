package br.com.fundatec.character

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.fundatec.R
import br.com.fundatec.databinding.ActivityNewCharacterBinding
import br.com.fundatec.home.view.MainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso

class NewCharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewCharacterBinding

    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private val viewModel: NewCharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)
        binding = ActivityNewCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        generateSpinners()

        viewModel.viewState.observe(this) { state ->
            when (state) {
                is ViewCharacterState.ShowNullCampsError -> showNullCampsSnack()
                ViewCharacterState.ShowSuccess -> successCase()

            }
        }

    }

    private fun generateSpinners() {
        val spinnerEditora: Spinner = findViewById(R.id.s_editora)
        ArrayAdapter.createFromResource(
            this, R.array.lista_editoras, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerEditora.adapter = adapter
        }

        val spinnerTipo: Spinner = findViewById(R.id.s_tipo)
        ArrayAdapter.createFromResource(
            this, R.array.lista_tipos, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTipo.adapter = adapter
        }

        findViewById<FloatingActionButton>(R.id.bt_return).setOnClickListener {
            val navegateToMainActivity = Intent(this, MainActivity::class.java)
            startActivity(navegateToMainActivity)
        }
    }

    private fun showNullCampsSnack() {
        val container = findViewById<ConstraintLayout>(R.id.container)
        Snackbar
            .make(container, "Preencha todos os campos", Snackbar.LENGTH_LONG)
            .show()
    }

    private fun successCase() {

        val preferences = getPreferences(MODE_PRIVATE)

        val characterString = moshi
            .adapter(Character::class.java)
            .toJson(createNewCharacter())

        preferences.edit().putString("character", characterString).apply()


        val container = findViewById<ConstraintLayout>(R.id.container)
        Snackbar
            .make(
                container,
                "personagem salvo com sucesso, volte para a home e verá seu personagem",
                Snackbar.LENGTH_LONG
            )
            .show()
    }

    private fun changePictureUrl() {
        val image = findViewById<ImageView>(R.id.iv_character)
        val url = findViewById<TextInputEditText>(R.id.tiet_url).toString()


        if (!url.isBlank()) {
            Picasso.get().load(url).into(image)
        }
    }

    private fun createNewCharacter(): Character? {
        val name = findViewById<TextInputEditText>(R.id.tiet_name).toString()
        val imageUrl = findViewById<TextInputEditText>(R.id.tiet_url).toString()
        val description = findViewById<TextInputEditText>(R.id.tiet_description).toString()
        val tipo = findViewById<Spinner>(R.id.s_tipo).toString()
        val editora = findViewById<Spinner>(R.id.s_editora).toString()
        val age = findViewById<TextInputEditText>(R.id.tiet_age).toString()
        val birthday = findViewById<TextInputEditText>(R.id.tiet_birthday).toString()

        return Character(name, imageUrl, description, tipo, editora, age, birthday)
    }
}

data class Character(
    val name: String,
    val imageUrl: String,
    val description: String,
    val tipo: String,
    val editora: String,
    val age: String,
    val birthday: String
)


