package br.iesb.ecommerce.entities.produtos

class Produto(
        private val idProduto: String,
        private var nome: String,
        private var listaCaracteristicas: MutableList<String>,
        private var descricao: String,
        private var valor: Float
): ProdutoInterface{
    override fun obterNome() = nome
    override fun obterId() = idProduto
    fun obterListaCaracteristicas() = listaCaracteristicas
    fun obterDescricao() = descricao
    fun obterValor() = valor
}