package br.iesb.ecommerce.services.frete

class FretePadrão(
        nomeEmpresa:String,
        valor: Float,
        enderecoInicial: String
): FreteInterface {
    override val nomeEmpresa = nomeEmpresa
    override val valorProduto = valor
    override val endereco = enderecoInicial

    override fun calcularFrete(): Float {
        return valorProduto.times(5.0f).div(100f)
    }

    override fun calcularTempoEntrega(): Int {
        return 15
    }
}