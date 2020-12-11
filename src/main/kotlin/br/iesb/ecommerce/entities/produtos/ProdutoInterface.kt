package br.iesb.ecommerce.entities.produtos

import br.iesb.ecommerce.services.frete.FreteInterface

interface ProdutoInterface {
    fun obterNome(): String
}

interface ProdutoVendedorInterface: ProdutoInterface {
    fun addCaracteristica(novaCaracteristica: String)
    fun removeCaracteristica(caracteristica: String)
    fun addEstoque(qtd: Int)
    fun venderProduto(qtd: Int)
    fun extornoProduto(qtd: Int)
    fun addClassificacao(novaClassificacao: Float)
}