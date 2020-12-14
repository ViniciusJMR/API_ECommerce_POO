package br.iesb.ecommerce.services.categorias

class Categoria(
    private val nome: String,
    private var produtos: MutableList<String>,
    private val id: String?
): CategoriaInterface {
    override fun obterNome() = nome
    override fun obterId() = id
    override fun obterProdutos() = produtos
}