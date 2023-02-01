package br.com.fundatec.login.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.fundatec.R
import br.com.fundatec.databinding.ActivityLoginBinding
import br.com.fundatec.home.view.MainActivity
import br.com.fundatec.login.presentation.LoginViewModel
import br.com.fundatec.login.presentation.ViewState
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etEmail.error = "Campo obrigatório"

        configLoginButton()
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is ViewState.ShowNullCampsError -> showNullCampsSnack()
                ViewState.ShowInvalidEmailError -> showInvalidEmailSnack()
                ViewState.ShowInvalidPasswordError -> showInvalidPasswordSnack()
                ViewState.ShowSuccess -> goToMainActivity()
            }
        }
    }

    private fun configLoginButton() {
        binding.btLogin.setOnClickListener {
            viewModel.validateUserInput(
                email = binding.etEmail.text.toString(),
                password = binding.etPassword?.text.toString(),
            )
        }
    }

    private fun showNullCampsSnack() {
        val container = findViewById<ConstraintLayout>(R.id.container)
        Snackbar
            .make(container, "Preencha todos os campos", Snackbar.LENGTH_LONG)
            .show()
    }

    private fun showInvalidEmailSnack() {
        val container = findViewById<ConstraintLayout>(R.id.container)
        Snackbar
            .make(container, "E-mail inválido", Snackbar.LENGTH_LONG)
            .show()
    }

    private fun showInvalidPasswordSnack() {
        val container = findViewById<ConstraintLayout>(R.id.container)
        Snackbar
            .make(container, "Senha inválida", Snackbar.LENGTH_LONG)
            .show()
    }


    private fun goToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }
}