package br.iesb.ecommerce.services.ofertas

interface OfertaDescontoInterface : OfertaInterface {

    fun obterValorComDesconto(): Double
    fun atualizarOferta(oferta: OfertaInterface)
}