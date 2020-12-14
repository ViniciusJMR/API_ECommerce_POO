package br.iesb.ecommerce.storage

import br.iesb.ecommerce.services.categorias.CategoriaInterface
import br.iesb.ecommerce.services.categorias.CategoriaPadraoInterface

interface ArmazenamentoCategorias {
    fun obterCategorias(): MutableList<CategoriaPadraoInterface>
    fun obterCategoria(categoria: String): CategoriaPadraoInterface

    fun addCategoria(novaCategoria: CategoriaPadraoInterface)
    fun removeCategoria(categoria: CategoriaInterface)
}