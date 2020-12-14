package br.iesb.ecommerce.services.categorias

interface CategoriaInterface {
    fun obterId(): String?
    fun obterNome(): String
    fun obterProdutos(): MutableList<String>
}