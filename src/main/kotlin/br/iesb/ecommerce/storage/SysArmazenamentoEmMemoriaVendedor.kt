package br.iesb.ecommerce.storage

import br.iesb.ecommerce.entities.produtos.ProdutoInterface
import br.iesb.ecommerce.entities.vendedores.VendedorInterface
import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.util.key.UUIDGenerator

class SysArmazenamentoEmMemoriaVendedor {
    companion object :ArmazenamentoVendedor{
        private var vendedores = mutableListOf<VendedorInterface>()

        override fun obterVendedores() = vendedores

        override fun obterVendedor(vendedor: String): VendedorInterface{
            if(existeVendedor(vendedor)){
                return vendedores.elementAt(obterIndexVendedor(vendedor))
            }
            else
                throw ExistsException("Vendedor não cadastrado")
        }

        override fun obterProduto(produtoId: String): ProdutoInterface {
            val idVendedor = UUIDGenerator().obterPrimeiraId(produtoId)
            try{
                return obterVendedor(idVendedor).obterProduto((produtoId))
            }
            catch(e: ExistsException){
                throw ExistsException(e.message!!)
            }
        }

        override fun addVendedor(novoVendedor: VendedorInterface){
            if(!existeVendedor(novoVendedor)){
                vendedores.add(novoVendedor)
            }
            else
                throw ExistsException("Nome de Vendedor já cadastrado")
        }

        override fun removerVendedor(vendedor: VendedorInterface){
            if(existeVendedor(vendedor)){
                vendedores.removeAt(obterIndexVendedor(vendedor))
            }
        }

        private fun existeVendedor(vendedor: VendedorInterface): Boolean{
            for(x in vendedores){
                if(vendedor.obterNome() == x.obterNome()){
                    return true
                }
            }
            return false
        }

        private fun existeVendedor(vendedorId: String):Boolean{
            for(x in vendedores){
                if(vendedorId == x.obterId()){
                    return true
                }
            }
            return false
        }

        private fun obterIndexVendedor(vendedor: VendedorInterface): Int{
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