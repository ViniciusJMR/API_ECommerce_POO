package br.iesb.ecommerce.services.categorias

interface CategoriaInterface {
    fun obterId(): String
    fun obterNome(): String

    fun addSubCategoria(subCategoria: SubCategoriaInterface)
    fun removerSubCategoria(subCategoria: SubCategoriaInterface)
    fun obterSubCategorias(): MutableList<SubCategoriaInterface>
    fun obterSubCategoria(subCategoria: SubCategoriaInterface): SubCategoriaInterface
    fun atualizarSubCategoria(subCategoriaAtualizada: SubCategoriaInterface)
    fun atualizarCategoria(categoriaAtualizada: CategoriaInterface)
}