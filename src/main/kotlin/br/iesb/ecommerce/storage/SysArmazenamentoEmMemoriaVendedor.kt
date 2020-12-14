package br.iesb.ecommerce.storage

import br.iesb.ecommerce.entities.produtos.ProdutoVendedorInterface
import br.iesb.ecommerce.entities.vendedores.VendedorPadraoInterface
import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.util.key.UUIDGenerator

class SysArmazenamentoEmMemoriaVendedor {
    companion object :ArmazenamentoVendedor{
        private var vendedores = mutableListOf<VendedorPadraoInterface>()

        override fun obterVendedores() = vendedores

        override fun obterVendedor(vendedorId: String): VendedorPadraoInterface{
            if(existeVendedor(vendedorId)){
                return vendedores[obterIndexVendedor(vendedorId)]
            }
            else
                throw ExistsException("Vendedor não cadastrado")
        }

        override fun obterProdutos(): MutableList<ProdutoVendedorInterface>{
            val produtos = mutableListOf<ProdutoVendedorInterface>()

            for(x in vendedores){
                produtos.addAll(x.obterLista())
            }

            return produtos
        }

        override fun obterProduto(produtoId: String): ProdutoVendedorInterface {
            val idVendedor = UUIDGenerator().obterPrimeiraId(produtoId)
            try{
                return obterVendedor(idVendedor).obterProduto(produtoId)
            }
            catch(e: ExistsException){
                throw ExistsException("${e.message}")
            }
        }

        override fun addVendedor(novoVendedor: VendedorPadraoInterface){
            if(!existeVendedor(novoVendedor)){
                vendedores.add(novoVendedor)
            }
            else
                throw ExistsException("Nome de Vendedor já cadastrado")
        }

        override fun removerVendedor(vendedorId: VendedorPadraoInterface){
            if(vendedores.contains(vendedorId)){
                vendedores.remove(vendedorId)
            }
            else
                throw ExistsException("Vendedor não cadastrado")
        }

        private fun existeVendedor(vendedor: VendedorPadraoInterface): Boolean{
            for(x in vendedores){
                if(vendedor.obterNome() == x.obterNome()){
                    return true
                }
            }
            return false
        }

        fun existeVendedor(vendedorId: String):Boolean{
            for(x in vendedores){
                if(vendedorId == x.obterId()){
                    return true
                }
            }
            return false
        }

        private fun obterIndexVendedor(vendedor: VendedorPadraoInterface): Int{
            var i = 0

            for(x in vendedores){
                if(vendedor.obterNome() == x.obterNome())
                    break
                else
                    i++
            }

            return i
        }

        private fun obterIndexVendedor(vendedorId: String): Int{
            var i = 0

            for(x in vendedores){
                if(vendedorId == x.obterId())
                    break
                else
                    i++
            }

            return i
        }

    }
}