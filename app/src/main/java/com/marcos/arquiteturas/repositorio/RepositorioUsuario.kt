package com.marcos.arquiteturas.repositorio

import com.marcos.arquiteturas.database.Database
import com.marcos.arquiteturas.services.LoginListener

class RepositorioUsuario {

    val database = Database()

    fun autenticacaoUsuarioRepositorio(email: String, senha: String, listener: LoginListener){
        database.autenticacaoUsuario(email,senha,listener)

    }

}