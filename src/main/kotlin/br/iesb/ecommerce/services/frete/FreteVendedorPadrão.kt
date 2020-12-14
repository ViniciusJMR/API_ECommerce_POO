package br.iesb.ecommerce.services.frete

class FreteVendedorPadrão(
        valor: Double,
        idVendedor: String
): FreteInterface {
    override val valorProduto = valor
    override val idVendedor = idVendedor


    override fun calcularFrete(): Double {
        return valorProduto.times(5.0).div(100)
    }
}