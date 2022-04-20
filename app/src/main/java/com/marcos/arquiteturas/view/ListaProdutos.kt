package com.marcos.arquiteturas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcos.arquiteturas.adapter.AdapterProduto
import com.marcos.arquiteturas.database.Database
import com.marcos.arquiteturas.databinding.ActivityListaProdutosBinding
import com.marcos.arquiteturas.model.Produto

class ListaProdutos : AppCompatActivity() {

    private lateinit var binding: ActivityListaProdutosBinding
    private lateinit var adapterProduto: AdapterProduto
    private val listaProdutos: MutableList<Produto> = mutableListOf()

    private val db = Database()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewProdutos = binding.recyclerViewProdutos
        recyclerViewProdutos.layoutManager = LinearLayoutManager(this)
        adapterProduto = AdapterProduto(this,listaProdutos)
        recyclerViewProdutos.setHasFixedSize(true)
        recyclerViewProdutos.adapter = adapterProduto
        db.getListaProdutos(listaProdutos,adapterProduto)
    }
}