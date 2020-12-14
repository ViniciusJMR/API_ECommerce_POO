package br.iesb.ecommerce.services.categorias

import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.util.key.IdGeneratorInterface

class CategoriaPadrao(
        private var nome:String,
        idGenerator: IdGeneratorInterface?
): CategoriaPadraoInterface{
    private val id = idGenerator?.gerarId()
    private var produtos = mutableListOf<String>()

    override fun obterId() = id
    override fun obterNome() = nome
    override fun obterProdutos() = produtos

    constructor(nome: String):this(nome, null)

    override fun atualizarCategoria(categoriaAtualizada: CategoriaPadraoInterface) {
        nome = categoriaAtualizada.obterNome()
        produtos = categoriaAtualizada.obterProdutos()
    }

    override fun addProduto(idProduto: String){
        if(!produtos.contains(idProduto)){
            produtos.add(idProduto)
        }
        else
            throw ExistsException("Produto já cadastrado em Categoria: $nome")
    }

    override fun addProduto(idProdutos: MutableList<String>){
        if(!produtos.containsAll(idProdutos)){
            produtos.addAll(idProdutos)
        }
        else
            throw ExistsException("Pelo menos um produto já cadastrado em Categoria: $nome")
    }

    override fun removerProduto(idProduto: String){
        if(produtos.contains(idProduto)){
            produtos.remove(idProduto)
        }
        else
            throw ExistsException("Produto não cadastrado em Categoria: $nome")
    }

    override fun removerProduto(idProdutos: MutableList<String>) {
        if(produtos.containsAll(idProdutos)){
            produtos.removeAll(idProdutos)
        }
        else
            throw ExistsException("Produto não cadastrado em Categoria: $nome")
    }

}