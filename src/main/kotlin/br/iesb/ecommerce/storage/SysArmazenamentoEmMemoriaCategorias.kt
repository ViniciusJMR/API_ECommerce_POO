package br.iesb.ecommerce.storage

import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.services.categorias.CategoriaInterface

class SysArmazenamentoEmMemoriaCategorias {
    companion object: ArmazenamentoCategorias{
        private var categorias = mutableListOf<CategoriaInterface>()

        override fun obterCategorias() = categorias

        override fun obterCategoria(categoria: CategoriaInterface): CategoriaInterface {
            if(existeCategoria(categoria)){
                return categorias.elementAt(obterIndexCategoria(categoria))
            }
            else
                throw ExistsException("Categoria não cadastrada")
        }

        override fun addCategoria(novaCategoria: CategoriaInterface) {
            if(!existeCategoria(novaCategoria)){
                categorias.add(novaCategoria)
            }
            else
                throw ExistsException("Categoria já cadastrada")
        }

        override fun removeCategoria(categoria: CategoriaInterface) {
            if(existeCategoria(categoria)){
                categorias.removeAt(obterIndexCategoria(categoria))
            }
        }

        private fun existeCategoria(categoria: CategoriaInterface): Boolean{
            for(x in categorias){
                if(categoria.obterNome() == x.obterNome())
                    return true
            }

            return false
        }



        private fun obterIndexCategoria(categoria: CategoriaInterface): Int{
            var i=0

            for(x in categorias){
                if(categoria.obterNome() == x.obterNome())
                    break
                else
                    i++
            }

            return i
        }

    }
}