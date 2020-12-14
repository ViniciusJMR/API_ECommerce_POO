package br.iesb.ecommerce.services.favoritos

import br.iesb.ecommerce.storage.ArmazenamentoUsuario

class FavoritoPadrao(private val idUsuario: String,
                     private val favoritos: MutableList<String>
): FavoritoInterface{
    override fun obterIdUsuario() = idUsuario
    override fun obterFavoritos() = favoritos
}