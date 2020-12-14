package br.iesb.ecommerce.storage

import br.iesb.ecommerce.entities.produtos.ProdutoInterface
import br.iesb.ecommerce.entities.vendedores.VendedorInterface

interface ArmazenamentoVendedor {
    fun obterVendedores(): MutableList<VendedorInterface>
    fun obterVendedor(vendedorId: String): VendedorInterface
    fun obterProduto(produtoId: String): ProdutoInterface

    fun addVendedor(novoVendedor: VendedorInterface)
    fun removerVendedor(vendedor: VendedorInterface)
}