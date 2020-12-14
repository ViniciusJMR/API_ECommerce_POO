package br.iesb.ecommerce.factory

import br.iesb.ecommerce.entities.ofertas.OfertaDescontoPorcentagem
import br.iesb.ecommerce.util.key.UUIDGenerator

class OfertaFactory {
    fun criarOfertaDescontoPorcentagem(oferta: OfertaDescontoPorcentagem) =
            OfertaDescontoPorcentagem(
                    oferta.obterProdutoId(),
                    oferta.obterValorSemDesconto(),
                    oferta.obterDesconto(),
                    oferta.obterDataFimDaOferta(),
                    UUIDGenerator()
            )
}