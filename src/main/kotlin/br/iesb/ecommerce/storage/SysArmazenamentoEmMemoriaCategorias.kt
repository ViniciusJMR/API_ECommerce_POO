package br.iesb.ecommerce.storage

import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.services.categorias.CategoriaInterface
import br.iesb.ecommerce.services.categorias.CategoriaPadraoInterface

class SysArmazenamentoEmMemoriaCategorias {
    companion object: ArmazenamentoCategorias{
        private var categorias = mutableListOf<CategoriaPadraoInterface>()

        override fun obterCategorias() = categorias

        override fun obterCategoria(categoriaId: String): CategoriaPadraoInterface {
            if(existeCategoria(categoriaId)){
                return categorias[obterIndexCategoria(categoriaId)]
            }
            else
                throw ExistsException("Categoria não cadastrada")
        }

        override fun addCategoria(novaCategoria: CategoriaPadraoInterface) {
            if(!existeCategoria(novaCategoria)){
                categorias.add(novaCategoria)
            }
            else
                throw ExistsException("Categoria já cadastrada")
        }

        override fun removeCategoria(categoria: CategoriaInterface) {
            if(existeCategoria(categoria.obterId()!!)){
                categorias.remove(categoria)
            }
            else
                throw ExistsException("Categoria não cadastrada")
        }

        private fun existeCategoria(categoria: CategoriaPadraoInterface): Boolean{
            for(x in categorias){
                if(categoria.obterNome() == x.obterNome())
                    return true
            }

            return false
        }



        private fun existeCategoria(categoriaId: String): Boolean{
            for(x in categorias){
                if(categoriaId == x.obterNome())
                    return true
            }

            return false
        }



        private fun obterIndexCategoria(categoria: CategoriaPadraoInterface): Int{
            var i=0

            for(x in categorias){
                if(categoria.obterNome() == x.obterNome())
                    break
                else
                    i++
            }

            return i
        }

        private fun obterIndexCategoria(categoriaId: String): Int{
            var i=0

            for(x in categorias){
                if(categoriaId == x.obterId())
                    break
                else
                    i++
            }

            return i
        }

    }
}