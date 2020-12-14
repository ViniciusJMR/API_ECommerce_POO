package br.iesb.ecommerce.services.categorias

interface CategoriaPadraoInterface: CategoriaInterface {


    override fun obterProdutos(): MutableList<String>
    fun addProduto(idProduto: String)
    fun addProduto(idProdutos: MutableList<String>)
    fun removerProduto(idProduto: String)
    fun removerProduto(idProdutos: MutableList<String>)
    fun atualizarCategoria(categoriaAtualizada: CategoriaPadraoInterface)
}