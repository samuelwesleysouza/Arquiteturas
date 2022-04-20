package com.marcos.arquiteturas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcos.arquiteturas.repositorio.RepositorioUsuario
import com.marcos.arquiteturas.services.LoginListener

class MainViewModel(private val repositorioUsuario: RepositorioUsuario) : ViewModel() {


    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login

    private val _mensagem = MutableLiveData<String>()
    val mensagem: LiveData<String> = _mensagem

    fun autenticacaoUsuario(email: String, senha: String) {
        repositorioUsuario.autenticacaoUsuarioRepositorio(email, senha, object : LoginListener {
            override fun onSucesso() {
                _login.postValue(true)
                _mensagem.postValue("Sucesso ao fazer o login!")
            }
            override fun onErro(erro: String) {
                _login.postValue(false)
                _mensagem.postValue(erro)
            }
        })
    }
}