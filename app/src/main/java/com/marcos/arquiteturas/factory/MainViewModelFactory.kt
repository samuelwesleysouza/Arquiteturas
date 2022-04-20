package com.marcos.arquiteturas.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marcos.arquiteturas.repositorio.RepositorioUsuario
import com.marcos.arquiteturas.viewmodel.MainViewModel

class MainViewModelFactory (private val repositorioUsuario: RepositorioUsuario): ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repositorioUsuario) as T
    }

}