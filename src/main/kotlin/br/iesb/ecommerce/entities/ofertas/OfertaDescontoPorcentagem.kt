package br.iesb.ecommerce.entities.ofertas

import br.iesb.ecommerce.util.key.IdGeneratorInterface

class OfertaDescontoPorcentagem(
        private val idProduto: String,
        private val valorSemDesconto: Float,
        private var desconto: Int,
        private var dataFimDaOferta: String,
        idGenerator: IdGeneratorInterface
): OfertaInterface {
    private val id = idGenerator.gerarId(idProduto)

    override fun obterId() = id
    override fun obterProdutoId() = idProduto
    fun obterDesconto() = desconto
    fun obterDataFimDaOferta() = dataFimDaOferta


    override fun atualizarOferta(oferta: OfertaDescontoPorcentagem) {
        desconto = oferta.obterDesconto()
        dataFimDaOferta = oferta.obterDataFimDaOferta()
    }
}