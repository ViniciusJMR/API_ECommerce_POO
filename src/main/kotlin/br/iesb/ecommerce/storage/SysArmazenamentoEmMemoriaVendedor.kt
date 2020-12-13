package br.iesb.ecommerce.storage

import br.iesb.ecommerce.entities.vendedores.VendedorInterface
import br.iesb.ecommerce.exceptions.ExistsException

class SysArmazenamentoEmMemoriaVendedor {
    companion object :ArmazenamentoVendedor{
        private var vendedores = mutableListOf<VendedorInterface>()

        override fun obterVendedores() = vendedores

        override fun obterVendedor(vendedor: VendedorInterface): VendedorInterface{
            if(existeVendedor(vendedor)){
                return vendedores.elementAt(obterIndexVendedor(vendedor))
            }
            else
                throw ExistsException("Vendedor não cadastrado")
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

        private fun existeVendedor(novoVendedor: VendedorInterface): Boolean{
            for(x in vendedores){
                if(x.obterNome() == x.obterNome()){
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
    }
}