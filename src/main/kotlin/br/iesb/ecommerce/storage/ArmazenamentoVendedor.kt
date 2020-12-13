package br.iesb.ecommerce.storage

import br.iesb.ecommerce.entities.vendedores.VendedorInterface

interface ArmazenamentoVendedor {
    fun obterVendedores(): MutableList<VendedorInterface>
    fun obterVendedor(vendedor: VendedorInterface): VendedorInterface

    fun addVendedor(novoVendedor: VendedorInterface)
    fun removerVendedor(vendedor: VendedorInterface)
}