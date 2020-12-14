package br.iesb.ecommerce.entities.ofertas

import br.iesb.ecommerce.util.key.IdGeneratorInterface

class OfertaDescontoPorcentagem(
        private val idProduto: String,
        private val valorSemDesconto: Double,
        private var desconto: Int,
        private var dataFimDaOferta: String,
        idGenerator: IdGeneratorInterface?
): OfertaInterface {
    private val id = idGenerator?.gerarId(idProduto)

    constructor(idProduto: String, valorSemDesconto: Double, desconto: Int, dataFimDaOferta: String)
        :this(idProduto, valorSemDesconto, desconto, dataFimDaOferta, null)

    override fun obterId() = id
    override fun obterProdutoId() = idProduto
    override fun obterDesconto() = desconto
    fun obterValorSemDesconto() = valorSemDesconto
    override fun obterValorComDesconto() = valorSemDesconto - (valorSemDesconto.times(desconto)/100.0)
    override fun obterDataFimDaOferta() = dataFimDaOferta


    override fun atualizarOferta(oferta: OfertaInterface) {
        desconto = oferta.obterDesconto()
        dataFimDaOferta = oferta.obterDataFimDaOferta()
    }
}