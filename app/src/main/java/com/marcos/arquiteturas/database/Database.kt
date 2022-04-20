package com.marcos.arquiteturas.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.marcos.arquiteturas.adapter.AdapterProduto
import com.marcos.arquiteturas.model.Produto
import com.marcos.arquiteturas.services.LoginListener

class Database {

    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()


    fun autenticacaoUsuario(email: String, senha: String, listener: LoginListener){
        auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener { autenticacao ->

            if (autenticacao.isSuccessful) {
                listener.onSucesso()
            }
       }.addOnFailureListener {
            listener.onErro("Erro ao fazer o login!")
        }
    }

    fun getListaProdutos(
        listaProdutos: MutableList<Produto>,
        adapterProduto: AdapterProduto
    ){
        db.collection("produtos").get().addOnCompleteListener { tarefa ->
            if (tarefa.isSuccessful){
                for (documento in tarefa.result){
                    val produto = documento.toObject(Produto::class.java)
                    listaProdutos.add(produto)
                    adapterProduto.notifyDataSetChanged()
                }
            }
        }
    }
}