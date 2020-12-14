package br.iesb.ecommerce.services.ofertas

interface OfertaInterface {
    fun obterId(): String?
    fun obterProdutoId(): String
    fun obterDesconto(): Int
    fun obterValorSemDesconto(): Double
    fun obterDataFimDaOferta(): String
}