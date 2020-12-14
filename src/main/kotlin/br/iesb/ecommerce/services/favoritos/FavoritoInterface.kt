package br.iesb.ecommerce.services.favoritos

interface FavoritoInterface {
    fun obterIdUsuario(): String
    fun obterFavoritos(): MutableList<String>
}