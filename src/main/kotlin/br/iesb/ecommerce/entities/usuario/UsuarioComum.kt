package br.iesb.ecommerce.entities.usuario

import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.util.key.IdGeneratorInterface
import br.iesb.ecommerce.util.timeFormat.TimeFormatInterface

import java.lang.RuntimeException

class UsuarioComum(
        private var nome: String,
        private var email: String,
        private var endereco: String,
        timeFormat: TimeFormatInterface?,
        idGenerator: IdGeneratorInterface?
        ){
    private val id = idGenerator?.gerarId()
    private val dataCadastro = timeFormat?.obterDataHoraAtual()
    private var historicoPedidos = mutableListOf<String>()
    private var carrinho = mutableListOf<String>()
    private var favoritos = mutableListOf<String>()
    //private var historicoComentarios

    fun obterId() = id
    fun obterNome() = nome
    fun obterEmail() = email
    fun obterEndereco() = endereco
    fun obterFavoritos() = favoritos
    fun obterCarrinho() = carrinho

    constructor(nome: String, email: String, endereco: String)
        :this(nome, email, endereco, null, null)

    fun atualizarInformações(novoNome: String, novoEmail: String, novaSenha: String, novoEndereco: String){
        nome = novoNome
        email = novoEmail
        endereco = novoEndereco
    }

    fun addCarrinho(idProduto: String){
        carrinho.add(idProduto)
    }

    fun removerCarrinho(idProduto: String){
        if(carrinho.contains(idProduto))
            carrinho.remove(idProduto)
        else
            throw ExistsException("Produto não existe em Carrinho")
    }

    fun removerHistoricoPedidos(idProduto: String){
        if(historicoPedidos.contains(idProduto))
            historicoPedidos.remove(idProduto)
        else
            throw ExistsException("Produto não existe em Histórico")
    }

    fun efetuarCompra(){
        if(carrinho.isNotEmpty()){
            historicoPedidos.addAll(carrinho)
            carrinho.clear()
        }
        else
            throw RuntimeException("Carrinho está vazio")
    }

    fun addFavorito(idProduto: String){
        if(!favoritos.contains(idProduto)){
            favoritos.add(idProduto)
        }
        else
            throw ExistsException("Produto já exite em favoritos")
    }

    fun addFavorito(idProdutos: MutableList<String>){
        if(!favoritos.containsAll(idProdutos)){
            favoritos.addAll(idProdutos)
        }
        else
            throw ExistsException("Pelo menos um produto já existe em Favoritos")
    }

    fun removeFavorito(idProduto: String){
        if(favoritos.contains(idProduto)){
            favoritos.remove(idProduto)
        }
        else
            throw ExistsException("Produto não existe em Favoritos")
    }

    fun removeFavorito(idProdutos: MutableList<String>){
        if(favoritos.containsAll(idProdutos)){
            favoritos.removeAll(idProdutos)
        }
        else
            throw ExistsException("Produto não existe em Favoritos")
    }

}