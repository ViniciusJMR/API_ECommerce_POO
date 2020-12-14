package br.iesb.ecommerce.storage

import br.iesb.ecommerce.services.ofertas.OfertaDescontoInterface

interface ArmazenamentoOfertas {
    fun obterOfertas(): MutableList<OfertaDescontoInterface>
    fun obterOferta(oferta: String): OfertaDescontoInterface

    fun addOferta(novaOferta: OfertaDescontoInterface)
    fun removerOferta(oferta: OfertaDescontoInterface)
}