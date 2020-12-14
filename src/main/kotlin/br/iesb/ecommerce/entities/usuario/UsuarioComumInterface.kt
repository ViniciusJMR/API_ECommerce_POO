package br.iesb.ecommerce.entities.usuario

interface UsuarioComumInterface : UsuarioInterface{

    fun obterFavoritos(): MutableList<String>
    fun addFavorito(idProduto: String)
    fun addFavorito(idProdutos: MutableList<String>)
    fun removeFavorito(idProduto: String)
    fun removeFavorito(idProdutos: MutableList<String>)
}