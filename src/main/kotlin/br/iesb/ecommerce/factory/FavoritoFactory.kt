package br.iesb.ecommerce.factory

import br.iesb.ecommerce.services.favoritos.FavoritoInterface
import br.iesb.ecommerce.services.favoritos.FavoritoPadrao

class FavoritoFactory {
    fun criarFavoritoPadrao(favoritoPadrao: FavoritoInterface) =
        FavoritoPadrao(favoritoPadrao.obterIdUsuario(), favoritoPadrao.obterFavoritos())
}