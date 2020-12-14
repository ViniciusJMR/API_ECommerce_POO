package br.iesb.ecommerce.management

import br.iesb.ecommerce.services.ofertas.OfertaDescontoInterface
import br.iesb.ecommerce.services.ofertas.OfertaInterface
import br.iesb.ecommerce.entities.produtos.ProdutoInterface
import br.iesb.ecommerce.entities.usuario.UsuarioInterface
import br.iesb.ecommerce.entities.vendedores.VendedorInterface
import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.services.categorias.CategoriaInterface
import br.iesb.ecommerce.services.favoritos.FavoritoInterface
import br.iesb.ecommerce.storage.ArmazenamentoCategorias
import br.iesb.ecommerce.storage.ArmazenamentoOfertas
import br.iesb.ecommerce.storage.ArmazenamentoUsuario
import br.iesb.ecommerce.storage.ArmazenamentoVendedor
import br.iesb.ecommerce.util.key.UUIDGenerator

class GerenciamentoRemocao {
    fun excluirUsuario(usuario: UsuarioInterface, sysArmazenamento: ArmazenamentoUsuario){
        try{
            val x = sysArmazenamento.obterUsuario(usuario.obterId()!!)

            sysArmazenamento.removerUsuario(x)
        }
        catch(e: ExistsException){
            throw ExistsException("Erro ao exluir Usuario: ${e.message}")
        }
    }

    fun excluirVendedor(vendedor: VendedorInterface, sysArmazenamento: ArmazenamentoVendedor){
        try{
            val x = sysArmazenamento.obterVendedor(vendedor.obterId()!!)

            sysArmazenamento.removerVendedor(x)
        }
        catch(e: ExistsException){
            throw ExistsException("Erro ao remover Usuario: ${e.message}")
        }
    }

    fun excluirProduto(produto: ProdutoInterface, sysArmazenamento: ArmazenamentoVendedor){
        try{
            val vendedorId = UUIDGenerator().obterPrimeiraId(produto.obterId()!!)
            val vendedor = sysArmazenamento.obterVendedor(vendedorId)

            vendedor.removeProdutoCatalogo(produto.obterId()!!)
        }
        catch(e: ExistsException){
            throw ExistsException("Erro ao excluir Produto: ${e.message}")
        }
    }

    fun excluirOferta(oferta: OfertaInterface, sysArmazenamentoOfertas: ArmazenamentoOfertas){
        try{
            val ofertaExcluida = sysArmazenamentoOfertas.obterOferta(oferta.obterId()!!)

            sysArmazenamentoOfertas.removerOferta(ofertaExcluida)
        }
        catch(e: ExistsException){
            throw ExistsException("Erro ao excluir oferta: ${e.message}")
        }
    }

    fun excluirCategoria(categoria: CategoriaInterface, sysArmazenamento: ArmazenamentoCategorias){
        try{
            sysArmazenamento.removeCategoria(categoria)
        }
        catch(e: ExistsException){
            throw ExistsException("Erro ao excluir Categoria: ${e.message}")
        }
    }

    fun excluirFavoritoEmUsuario(favorito: FavoritoInterface, sysArmazenamento: ArmazenamentoUsuario){
        try{
            val usuario = sysArmazenamento.obterUsuario(favorito.obterIdUsuario())

            usuario.removeFavorito(favorito.obterFavoritos())
        }
        catch(e: ExistsException){
            throw ExistsException("Erro ao excluir Favorito em Usuario: ${e.message}")
        }
    }
}