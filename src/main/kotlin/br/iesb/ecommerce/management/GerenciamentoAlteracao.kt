package br.iesb.ecommerce.management

import br.iesb.ecommerce.entities.produtos.Produto
import br.iesb.ecommerce.entities.usuario.Usuario
import br.iesb.ecommerce.entities.vendedores.VendedorInterface
import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.services.categorias.CategoriaInterface
import br.iesb.ecommerce.services.categorias.CategoriaPadraoInterface
import br.iesb.ecommerce.services.favoritos.FavoritoPadrao
import br.iesb.ecommerce.services.ofertas.OfertaInterface
import br.iesb.ecommerce.storage.*

class GerenciamentoAlteracao {
    fun atualizarUsuario(usuarioAtualizado: Usuario, sysArmazenamento: ArmazenamentoUsuario){

        try{
            val usuario = sysArmazenamento.obterUsuario(usuarioAtualizado.obterId())

            usuario.atualizarInformações(usuarioAtualizado.obterNome(), usuarioAtualizado.obterEmail()
                , usuarioAtualizado.obterEndereco())
        }
        catch(e: ExistsException){
            throw ExistsException("Não foi possível atualizar Usuario: ${e.message}")
        }
    }

    fun atualizarVendedor(vendedorAtualizado: VendedorInterface, sysArmazenamento: ArmazenamentoVendedor){
        try{
            val vendedor = sysArmazenamento.obterVendedor(vendedorAtualizado.obterId()!!)

            vendedor.alterarInformacoes(vendedorAtualizado.obterNome(),
            vendedorAtualizado.obterSobre(), vendedorAtualizado.obterEmail(),
                vendedorAtualizado.obterEndereco())
        }
        catch(e: ExistsException){
            throw ExistsException("Não foi possível atualizar o Vendedor: ${e.message}")
        }
    }

    fun atualizarProduto(produtoAtualizado: Produto, sysArmazenamento: ArmazenamentoVendedor){
        try{
            val produto = sysArmazenamento.obterProduto(produtoAtualizado.obterId())

            produto.atualizarInformacoes(produtoAtualizado.obterNome(),
                produtoAtualizado.obterListaCaracteristicas(),
                produtoAtualizado.obterDescricao(),
                produtoAtualizado.obterValor(),
                produtoAtualizado.obterQtdEstoque()
            )
        }
        catch (e: ExistsException){
            throw ExistsException("Erro ao atualizar produto: ${e.message}")
        }
    }

    fun atualizarOferta(ofertaAtualizada: OfertaInterface, sysArmazenamento: ArmazenamentoOfertas){
        try{
            val oferta = sysArmazenamento.obterOferta(ofertaAtualizada.obterId()!!)

            oferta.atualizarOferta(ofertaAtualizada)
        }
        catch(e: ExistsException){
            throw ExistsException("Erro ao atualizar oferta: ${e.message}")
        }
    }

    fun atualizarCategoria(categoriaAtualizada: CategoriaPadraoInterface,
                           sysArmazenamento: ArmazenamentoCategorias){
        try{
            val categoria = sysArmazenamento.obterCategoria(categoriaAtualizada.obterId()!!)
            categoria.atualizarCategoria(categoriaAtualizada)
        }
        catch(e: ExistsException){
            throw ExistsException("Erro ao atualizar Categoria: ${e.message}")
        }
    }

    fun addProdutoEmCategoria(categoriaComProdutos: CategoriaInterface,
                            sysArmazenamento: ArmazenamentoCategorias){
        try{
            val categoria = sysArmazenamento.obterCategoria(categoriaComProdutos.obterId()!!)

            categoria.addProduto(categoriaComProdutos.obterProdutos())
        }
        catch (e: ExistsException){
            throw ExistsException("Erro ao adicionar Produto Em Categoria: ${e.message}")
        }
    }

    fun removerProdutoEmCategoria(categoriaComProdutos: CategoriaInterface,
                                sysArmazenamento: ArmazenamentoCategorias){
        try{
            val categoria = sysArmazenamento.obterCategoria(categoriaComProdutos.obterId()!!)

            categoria.removerProduto(categoriaComProdutos.obterProdutos())
        }
        catch(e: ExistsException){
            throw ExistsException("Erro ao remover Produto Em Categoria: ${e.message}")
        }
    }

    fun addFavoritoEmUsuario(novosFavoritos: FavoritoPadrao, sysArmazenamento: ArmazenamentoUsuario){
        try{
            val usuario = sysArmazenamento.obterUsuario(novosFavoritos.obterIdUsuario())

            usuario.addFavorito(novosFavoritos.obterFavoritos())
        }
        catch (e: ExistsException){
            throw ExistsException("Erro ao adicionar Favorito em Usuario")
        }
    }

    fun removerFavoritoEmUsuario(novosFavoritos: FavoritoPadrao, sysArmazenamento: ArmazenamentoUsuario){
        try{
            val usuario = sysArmazenamento.obterUsuario(novosFavoritos.obterIdUsuario())

            usuario.removeFavorito(novosFavoritos.obterFavoritos())
        }
        catch (e: ExistsException){
            throw ExistsException("Erro ao adicionar Favorito em Usuario")
        }
    }
}