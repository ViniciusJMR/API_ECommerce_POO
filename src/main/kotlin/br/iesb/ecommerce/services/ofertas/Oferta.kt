package br.iesb.ecommerce.services.ofertas

class Oferta(
    private val id: String?,
    private val produtoId: String,
    private val valorSemDesconto: Double,
    private var desconto: Int,
    private var dataFimDaOferta: String,
): OfertaInterface {
    override fun obterId() = id
    override fun obterProdutoId() = produtoId
    override fun obterDesconto() = desconto
    override fun obterValorSemDesconto() = valorSemDesconto
    override fun obterDataFimDaOferta() = dataFimDaOferta
}