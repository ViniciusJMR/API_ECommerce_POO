package br.iesb.ecommerce.services.categorias

import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.util.key.IdGeneratorInterface
import br.iesb.ecommerce.util.key.UUIDGenerator

class SubCategoriaPadrao(
        idCategoria: String,
        idGenerator: IdGeneratorInterface,
        private var nome: String
): SubCategoriaInterface {
    private val id = idGenerator.gerarId(idCategoria)
    private var produtos = mutableListOf<String>()

    override fun obterId() = id
    override fun obterNome() = nome

    override fun obterProdutos() = produtos

    constructor(): this("", UUIDGenerator(), "")

    override fun atualizarCategoria(subCategoriaAtualizada: SubCategoriaInterface) {
        nome = subCategoriaAtualizada.obterNome()
    }

    override fun addProduto(idProduto: String){
        if(!produtos.contains(idProduto)){
            produtos.add(idProduto)
        }
        else
            throw ExistsException("Produto já cadastrado em Subcategoria: $nome")
    }

    override fun addProduto(idProdutos: MutableList<String>){
        if(!produtos.containsAll(idProdutos)){
            produtos.addAll(idProdutos)
        }
        else
            throw ExistsException("Pelo menos um produto já cadastrado em Subcategoria: $nome")
    }

    override fun removerProduto(idProduto: String){
        if(produtos.contains(idProduto)){
            produtos.remove(idProduto)
        }
        else
            throw ExistsException("Produto não cadastrado em Subcategoria: $nome")
    }

    override fun removerProduto(idProdutos: MutableList<String>) {
        if(produtos.containsAll(idProdutos)){
            produtos.removeAll(idProdutos)
        }
        else
            throw ExistsException("Produto não cadastrado em Subcategoria: $nome")
    }
}