package br.iesb.ecommerce.entities.ofertas

interface OfertaInterface {
    fun obterId(): String?
    fun obterProdutoId(): String
    fun obterDesconto(): Int
    fun obterValorComDesconto(): Double
    fun obterDataFimDaOferta(): String

    fun atualizarOferta(oferta: OfertaInterface)
}