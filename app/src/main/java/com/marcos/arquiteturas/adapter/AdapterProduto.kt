package com.marcos.arquiteturas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcos.arquiteturas.databinding.ProdutoItemBinding
import com.marcos.arquiteturas.model.Produto

class AdapterProduto(private val context: Context,private val listaProdutos: MutableList<Produto>):
    RecyclerView.Adapter<AdapterProduto.ProdutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val itemLista = ProdutoItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ProdutoViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.txtNome.text = listaProdutos[position].nome
        holder.txtPreco.text = listaProdutos[position].preco
        holder.txtDescricao.text = listaProdutos[position].descricao
    }

    override fun getItemCount() = listaProdutos.size

    inner class ProdutoViewHolder(binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val txtNome = binding.nomeProduto
        val txtPreco = binding.precoProduto
        val txtDescricao = binding.descricaoProduto
    }
}