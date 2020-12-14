package br.iesb.ecommerce.entities.produtos

import br.iesb.ecommerce.services.frete.FreteInterface

interface ProdutoInterface {
    fun obterNome(): String
    fun obterId(): String?
}

interface ProdutoVendedorInterface: ProdutoInterface {
    fun atualizarInformacoes(nome: String,
                           listaCaracteristicas: MutableList<String>,
                           descricao: String,
                           valor: Double,
                           qtdEstoque: Int
    )

    fun addEstoque(qtd: Int)
    fun venderProduto(qtd: Int)
    fun extornoProduto(qtd: Int)
    fun addClassificacao(novaClassificacao: Float)
}