package br.iesb.ecommerce.factory

import br.iesb.ecommerce.services.categorias.CategoriaPadrao
import br.iesb.ecommerce.util.key.UUIDGenerator

class CategoriaFactory {
    fun criarCatgoriaPadrao(dataCategoriaPadrao: CategoriaPadrao) =
            CategoriaPadrao(
                    dataCategoriaPadrao.obterNome(),
                    UUIDGenerator()
            )
}