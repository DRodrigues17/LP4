package br.com.fundatec.character

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.fundatec.R
import br.com.fundatec.databinding.ActivityNewCharacterBinding
import br.com.fundatec.home.view.MainActivity
import com.google.android.material.snackbar.Snackbar

class NewCharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewCharacterBinding

    private val viewModel: NewCharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)
        binding = ActivityNewCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configCreateButton()
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is ViewCharacterState.ShowNullCampsError -> showNullCampsSnack()
                ViewCharacterState.ShowSuccess -> returnToMainActivity()

            }
        }

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

        findViewById<Button>(R.id.bt_return).setOnClickListener {
            val navegateToMainActivity = Intent(this, MainActivity::class.java)
            startActivity(navegateToMainActivity)
        }

    }

    private fun returnToMainActivity() {
        val container = findViewById<ConstraintLayout>(R.id.container)
        Snackbar
            .make(container, "personagem salvo com sucesso", Snackbar.LENGTH_LONG)
            .show()
    }

    private fun showNullCampsSnack() {
        val container = findViewById<ConstraintLayout>(R.id.container)
        Snackbar
            .make(container, "Preencha todos os campos", Snackbar.LENGTH_LONG)
            .show()
    }

    private fun configCreateButton() {
        binding.btCreate.setOnClickListener {
            viewModel.validateUserInput(
                name = binding.tietName.text.toString(),
                url = binding.tietUrl?.text.toString(),
                description = binding.tietDescription?.text.toString(),
                age = binding.tietAge?.text.toString(),
                birthday = binding.tietBirthday?.text.toString()
            )
        }
    }
}


