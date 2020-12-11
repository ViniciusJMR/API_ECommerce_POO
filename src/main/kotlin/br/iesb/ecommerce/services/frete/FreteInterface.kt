package br.iesb.ecommerce.services.frete

interface FreteInterface {
    val nomeEmpresa: String
    val valorProduto: Float
    val endereco: String

    fun calcularFrete(): Float
    fun calcularTempoEntrega(): Int
}