package br.iesb.ecommerce.entities.produtos

import br.iesb.ecommerce.exceptions.EmptyStockException
import br.iesb.ecommerce.exceptions.InvalidValueException
import br.iesb.ecommerce.services.frete.FreteInterface
import br.iesb.ecommerce.util.key.IdGeneratorInterface

class ProdutoVendedor(
        private val idVendedor: String,
        private var nome: String,
        private var listaCaracteristicas: MutableList<String>,
        private var descricao: String,
        private var valor: Double,
        private var qtdEstoque: Int,
        freteDisponivel: FreteInterface?,
        idGenerator: IdGeneratorInterface?
): ProdutoVendedorInterface{
    private val idProduto = idGenerator?.gerarId(idVendedor)
    private var classificacao = 0.0f
    private var taxaInicialFrete = freteDisponivel?.calcularFrete()
    private var qtdClassificacao = 0
    private var qtdVendidas = 0

    constructor(
            idVendedor: String,
            nome: String,listaCaracteristicas: MutableList<String>,
            descricao: String,
            valor: Double,
            qtdEstoque: Int
    ):this(idVendedor, nome, listaCaracteristicas,descricao,
            valor, qtdEstoque, null, null)

    override fun obterNome() = nome
    override fun obterId() = idProduto
    fun obterIdVendedor() = idVendedor
    fun obterListaCaracteristicas() = listaCaracteristicas
    fun obterDescricao() = descricao
    fun obterValor() = valor
    fun obterQtdEstoque() = qtdEstoque

    override fun atualizarInformacoes(nome: String,
                                listaCaracteristicas: MutableList<String>,
                                descricao: String,
                                valor: Double,
                                qtdEstoque: Int
    ) {
        this.nome = nome
        this.listaCaracteristicas = listaCaracteristicas
        this.descricao = descricao
        this.valor = valor
        this.qtdEstoque = qtdEstoque
    }


    override fun addEstoque(qtd: Int){
        qtdEstoque.plus(qtd)
    }

    override fun venderProduto(qtd: Int){
        if(qtdEstoque >= 0){
            qtdEstoque.minus(qtd)
            qtdVendidas.plus(qtd)
        }
        else
            throw EmptyStockException("Não foi possível efetuar a compra")
    }

    override fun extornoProduto(qtd: Int){
        qtdEstoque.plus(qtd)
        qtdVendidas.minus(qtd)
    }

    override fun addClassificacao(novaClassificacao: Float){
        if(novaClassificacao >= 0.0f || novaClassificacao <= 5.0f)
        {
            classificacao.times(qtdClassificacao)
            qtdClassificacao.plus(1.0f)
            classificacao.plus(novaClassificacao).div(qtdClassificacao)
        }
        else
            throw InvalidValueException("Valor menor que 0 e maior que 5")
    }


}