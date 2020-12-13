package br.iesb.ecommerce.entities.ofertas

interface OfertaInterface {
    fun obterId(): String
    fun obterProdutoId(): String

    fun atualizarOferta(oferta: OfertaInterface)
}