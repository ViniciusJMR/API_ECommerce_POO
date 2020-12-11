package br.iesb.ecommerce.entities.produtos

import br.iesb.ecommerce.exceptions.EmptyStockException
import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.exceptions.InvalidValueException
import br.iesb.ecommerce.services.frete.FreteInterface
import br.iesb.ecommerce.services.frete.FretePadrão

class ProdutoVendedor(
    private var nome: String,
    private var listaCaracteristicas: MutableList<String>,
    private var descricao: String,
    private var valor: Double,
    private var qtdEstoque: Int,
    private var freteDisponivel: FreteInterface
): ProdutoVendedorInterface{
    //private val id = 0 // A definir funcionamento
    private var classificacao = 0.0f
    private var qtdClassificacao = 0
    private var qtdVendidas = 0

    //construtor apenas para pesquisa e alterar caracteristicas
    constructor(nome:String, listaCaracteristicas: MutableList<String>)
            : this(nome,
            listaCaracteristicas,
            "",
            0.0,
            0,
            FretePadrão("",0.0f, "")
    )

    override fun obterNome() = nome

    override fun addCaracteristica(novaCaracteristica: String){
        if(!listaCaracteristicas.contains(novaCaracteristica))
            listaCaracteristicas.add(novaCaracteristica)
        else
            throw ExistsException("Caracteristica já existente em produto")
    }

    override fun removeCaracteristica(caracteristica: String){
        if(listaCaracteristicas.contains(caracteristica))
            listaCaracteristicas.remove(caracteristica)
        else
            throw ExistsException("Caracteristica não existente em produto")
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
            qtdClassificacao.plus(1)
            classificacao.plus(novaClassificacao).div(qtdClassificacao)
        }
        else
            throw InvalidValueException("Valor menor que 0 e maior que 5")
    }


}