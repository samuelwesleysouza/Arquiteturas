package com.marcos.arquiteturas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.marcos.arquiteturas.databinding.ActivityFormCadastroBinding

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.btCadastrar.setOnClickListener {

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this,"Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }else{
                cadastrarUsuario(email,senha)
            }
        }

    }

    private fun cadastrarUsuario(email: String, senha: String){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener { cadastro ->
            if (cadastro.isSuccessful){
                Toast.makeText(this,"Cadastro realizado com sucesso",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Erro ao cadastrar usuário!",Toast.LENGTH_SHORT).show()
        }
    }
}