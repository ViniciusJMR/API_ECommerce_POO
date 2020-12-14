package br.iesb.ecommerce.entities.usuario

interface UsuarioInterface {
    fun obterEmail(): String

    fun atualizarInformações(novoNome: String, novoEmail: String, novaSenha: String, novoEndereco: String)
    fun obterFavoritos(): MutableList<String>
    fun addFavorito(idProduto: String)
    fun addFavorito(idProdutos: MutableList<String>)
    fun removeFavorito(idProduto: String)
    fun removeFavorito(idProdutos: MutableList<String>)
}