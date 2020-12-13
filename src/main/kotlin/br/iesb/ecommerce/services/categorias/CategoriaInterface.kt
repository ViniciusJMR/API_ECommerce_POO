package br.iesb.ecommerce.services.categorias

interface CategoriaInterface {
    fun obterId(): String
    fun obterNome(): String

    fun obterProdutos(): MutableList<String>
    fun addProduto(idProduto: String)
    fun addProduto(idProdutos: MutableList<String>)
    fun removerProduto(idProduto: String)
    fun removerProduto(idProdutos: MutableList<String>)
    fun atualizarCategoria(categoriaAtualizada: CategoriaInterface)
}