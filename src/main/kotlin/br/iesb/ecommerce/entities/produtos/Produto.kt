package br.iesb.ecommerce.entities.produtos

class Produto(
        private val idProduto: String,
        private var nomeVendedor: String,
        private var nome: String,
        private var listaCaracteristicas: MutableList<String>,
        private var descricao: String,
        private var valor: Float
): ProdutoInterface{
    override fun obterNome() = nome
    override fun obterId() = idProduto

    //construtor apenas para pesquisa
    constructor(idProduto: String, nomeVendedor: String, nome: String, valor:Float)
    :this(idProduto, nomeVendedor, nome, mutableListOf<String>(), "", valor)
}