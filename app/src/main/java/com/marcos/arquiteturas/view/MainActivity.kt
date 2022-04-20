package com.marcos.arquiteturas.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.marcos.arquiteturas.databinding.ActivityMainBinding
import com.marcos.arquiteturas.factory.MainViewModelFactory
import com.marcos.arquiteturas.repositorio.RepositorioUsuario
import com.marcos.arquiteturas.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(RepositorioUsuario())
        ).get(MainViewModel::class.java)

        binding.btEntrar.setOnClickListener {

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.autenticacaoUsuario(email,senha)
            }
        }

        binding.textTelaCadastro.setOnClickListener {
            iniciarTelaCadastro()
        }

        observers()
    }

    private fun observers() {
        viewModel.login.observe(this){login ->
            if (login){
                binding.progressBarLogin.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    iniciarTelaProdutos()
                }, 3000)
            }
        }
        viewModel.mensagem.observe(this){mensagem ->
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
        }
    }

    private fun iniciarTelaProdutos() {
        val intent = Intent(this, ListaProdutos::class.java)
        startActivity(intent)
        finish()
    }

    private fun iniciarTelaCadastro() {
        val intent = Intent(this, FormCadastro::class.java)
        startActivity(intent)
    }

}
