package br.iesb.ecommerce.services.categorias

class Categoria(
    private val nome: String,
    private val id: String?
): CategoriaInterface {
    override fun obterNome() = nome
    override fun obterId() = id
}