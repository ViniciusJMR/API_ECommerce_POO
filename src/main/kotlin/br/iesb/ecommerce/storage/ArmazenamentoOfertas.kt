package br.iesb.ecommerce.storage

import br.iesb.ecommerce.entities.ofertas.OfertaInterface

interface ArmazenamentoOfertas {
    fun obterOfertas(): MutableList<OfertaInterface>
    fun obterOferta(oferta: OfertaInterface): OfertaInterface

    fun addOferta(novaOferta: OfertaInterface)
    fun removerOferta(oferta: OfertaInterface)
}