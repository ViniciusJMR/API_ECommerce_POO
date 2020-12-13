package br.iesb.ecommerce.storage

import br.iesb.ecommerce.services.categorias.CategoriaInterface

interface ArmazenamentoCategorias {
    fun obterCategorias(): MutableList<CategoriaInterface>
    fun obterCategoria(categoria: CategoriaInterface): CategoriaInterface

    fun addCategoria(novaCategoria: CategoriaInterface)
    fun removeCategoria(categoria: CategoriaInterface)
}