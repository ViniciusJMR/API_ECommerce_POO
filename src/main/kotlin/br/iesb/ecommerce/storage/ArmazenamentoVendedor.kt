package br.iesb.ecommerce.storage

import br.iesb.ecommerce.entities.produtos.ProdutoVendedorInterface
import br.iesb.ecommerce.entities.vendedores.VendedorPadraoInterface

interface ArmazenamentoVendedor {
    fun obterVendedores(): MutableList<VendedorPadraoInterface>
    fun obterVendedor(vendedorId: String): VendedorPadraoInterface
    fun obterProduto(produtoId: String): ProdutoVendedorInterface
    fun obterProdutos(): MutableList<ProdutoVendedorInterface>

    fun addVendedor(novoVendedor: VendedorPadraoInterface)
    fun removerVendedor(vendedorId: VendedorPadraoInterface)
}