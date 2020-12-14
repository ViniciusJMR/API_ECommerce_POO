package br.iesb.ecommerce.entities.vendedores

import br.iesb.ecommerce.entities.produtos.ProdutoVendedorInterface

interface VendedorPadraoInterface: VendedorInterface{

    fun obterLista(): MutableList<ProdutoVendedorInterface>

    fun alterarInformacoes(nome: String,
                           sobre: String,
                           email: String,
                           endereco: String)
    fun obterProduto(produtoId: String): ProdutoVendedorInterface
    fun addProdutoCatalogo(novoProduto: ProdutoVendedorInterface)
    fun removeProdutoCatalogo(produto: String)
    fun venderProduto(produto: ProdutoVendedorInterface, qtd: Int)
    fun extornarProduto(produto: ProdutoVendedorInterface, qtd: Int)
    fun addClassificao(novaClassificacao: Float)
    fun alterarClassificacao(classificacaoAntiga: Float, novaClassificacao: Float)
}