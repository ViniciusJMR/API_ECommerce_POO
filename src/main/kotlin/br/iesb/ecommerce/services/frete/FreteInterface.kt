package br.iesb.ecommerce.services.frete

interface FreteInterface {
    val valorProduto: Double
    val idVendedor: String

    fun calcularFrete(): Double
}