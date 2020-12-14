package br.iesb.ecommerce.entities.vendedores

import br.iesb.ecommerce.entities.produtos.ProdutoVendedorInterface
import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.exceptions.InvalidValueException
import br.iesb.ecommerce.util.key.IdGeneratorInterface
import br.iesb.ecommerce.util.timeFormat.TimeFormatInterface


class VendedorPadrão(
        private var nome: String,
        private var sobre: String,
        private var email: String,
        private var telefone: String,
        private var endereco: String,
        timeFormat: TimeFormatInterface?,
        idGenerator: IdGeneratorInterface?,
): VendedorInterface {
    private val id = idGenerator?.gerarId()
    private val dataCadastro = timeFormat?.obterDataHoraAtual()
    private var listaProdutos = mutableListOf<ProdutoVendedorInterface>()
    private var qtdProdutosVendidos = 0
    private var classificacao = 0.0f
    private var qtdClassificacao = 0

    constructor(nome: String, sobre: String, email: String, telefone: String, endereco: String)
    :this(nome, sobre, email, telefone, endereco, null, null)

    override fun obterNome() = nome
    override fun obterId() = id
    override fun obterLista() = listaProdutos
    fun obterSobre() = sobre
    fun obterEmail() = email
    fun obterTelefone() = telefone
    fun obterEndereco() = endereco

    override fun alterarInformacoes(nome: String,
                                    sobre: String,
                                    email: String,
                                    endereco: String) {
        this.nome = nome
        this.sobre = sobre
        this.email = email
    }

    override fun addProdutoCatalogo(novoProduto: ProdutoVendedorInterface){
        if(!existeProduto(novoProduto)){
            listaProdutos.add(novoProduto)
        }
        else
            throw ExistsException("Nome de produto já está cadastrado")
    }

    override fun removeProdutoCatalogo(produto: ProdutoVendedorInterface){
        if(existeProduto(produto)){
            listaProdutos.removeAt(obterIndexProduto(produto))
        }
        else
            throw ExistsException("Produto não está cadastrado")
    }

    override fun venderProduto(produto: ProdutoVendedorInterface, qtd: Int) {
        if(existeProduto(produto)){
            listaProdutos.elementAt(obterIndexProduto(produto))
                    .venderProduto(qtd)
            qtdProdutosVendidos++
        }
        else
            throw ExistsException("Produto não listado")
    }

    override fun extornarProduto(produto: ProdutoVendedorInterface, qtd: Int){
        if(existeProduto(produto)){
            listaProdutos.elementAt(obterIndexProduto(produto))
                    .extornoProduto(qtd)
            qtdProdutosVendidos--
        }
        else
            throw ExistsException("Produto não listado")
    }

    override fun addClassificao(novaClassificacao: Float) {
        if(novaClassificacao >= 0.0f || novaClassificacao <= 5.0f)
        {
            classificacao.times(qtdClassificacao)
            qtdClassificacao.plus(1)
            classificacao.plus(novaClassificacao).div(qtdClassificacao)
        }
        else
            throw InvalidValueException("Valor menor que 0 e maior que 5")
    }

    override fun alterarClassificacao(classificacaoAntiga: Float, novaClassificacao: Float) {
        if(novaClassificacao >= 0.0f || novaClassificacao <= 5.0f)
        {
            classificacao.times(qtdClassificacao)
            classificacao.minus(classificacaoAntiga)
            classificacao.plus(novaClassificacao).div(qtdClassificacao)
        }
        else
            throw InvalidValueException("Valor menor que 0 e maior que 5")
    }


    private fun existeProduto(produto: ProdutoVendedorInterface): Boolean{
        for(x in listaProdutos){
            if(produto.obterNome() == x.obterNome()){
                return true
            }
        }
        return false
    }

    private fun obterIndexProduto(produto: ProdutoVendedorInterface): Int{
        var i = 0

        for(x in listaProdutos){
            if(produto.obterNome() == x.obterNome()){
                break
            }
            else
                i++
        }

        return i
    }

}