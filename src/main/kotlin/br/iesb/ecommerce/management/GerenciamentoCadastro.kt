package br.iesb.ecommerce.management

import br.iesb.ecommerce.services.ofertas.OfertaDescontoPorcentagem
import br.iesb.ecommerce.entities.produtos.ProdutoVendedor
import br.iesb.ecommerce.entities.usuario.UsuarioComum
import br.iesb.ecommerce.entities.vendedores.VendedorPadrao
import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.factory.*
import br.iesb.ecommerce.services.categorias.CategoriaPadrao
import br.iesb.ecommerce.services.favoritos.FavoritoPadrao
import br.iesb.ecommerce.storage.ArmazenamentoCategorias
import br.iesb.ecommerce.storage.ArmazenamentoOfertas
import br.iesb.ecommerce.storage.ArmazenamentoUsuario
import br.iesb.ecommerce.storage.ArmazenamentoVendedor

class GerenciamentoCadastro {
    fun cadastrarUsuarioComum(novoUsuario: UsuarioComum, sysArmazenamento: ArmazenamentoUsuario){
        val usuario = UsuarioFactory().criarClasseUsuario(novoUsuario)

        try{
            sysArmazenamento.addUsuario(usuario)
        }catch (e: ExistsException) {
            throw ExistsException("Erro ao cadastrar usuario: ${e.message}")
        }
    }

    fun cadastrarVendedorPadrao(novoVendedorPadrao: VendedorPadrao, sysArmazenamento: ArmazenamentoVendedor){
        val vendedor = VendedorFactory().criarVendedorPadrao(novoVendedorPadrao)

        try{
            sysArmazenamento.addVendedor(vendedor)
        }catch (e: ExistsException){
            throw ExistsException("Erro ao cadastrar Vendedor: ${e.message}")
        }
    }

    fun cadastrarProdutoVendedorFretePadrao(novoProduto: ProdutoVendedor, sysArmazenamento: ArmazenamentoVendedor){
        try{
            val vendedor = sysArmazenamento.obterVendedor(novoProduto.obterIdVendedor())
            val produto = ProdutoFactory().criarProdutoVendedorFretePadrao(novoProduto)

            vendedor.addProdutoCatalogo(produto)
        }catch(e: ExistsException){
            throw ExistsException("Erro ao cadastrar produto: ${e.message}")
        }
    }

    fun cadastrarOfertaDescontoPorcentagem(novaOferta: OfertaDescontoPorcentagem,
                                           sysArmazenamentoOfertas: ArmazenamentoOfertas,
                                           sysArmazenamentoVendedor: ArmazenamentoVendedor){
        try{
            val produto = sysArmazenamentoVendedor.obterProduto(novaOferta.obterProdutoId())
            val oferta = OfertaFactory().criarOfertaDescontoPorcentagem(novaOferta)

            sysArmazenamentoOfertas.addOferta(oferta)
        }
        catch(e: ExistsException){
            throw ExistsException("Erro ao cadastrar Oferta: ${e.message}")
        }
    }

    fun cadastrarCategoriaPadrao(novaCategoria: CategoriaPadrao, sysArmazenamento: ArmazenamentoCategorias){
        val categoria = CategoriaFactory().criarCategoriaPadrao(novaCategoria)

        try{
            sysArmazenamento.addCategoria(categoria)
        }
        catch(e: ExistsException){
            throw ExistsException("Erro ao cadastrar Categoria: ${e.message}")
        }
    }

    fun cadastrarFavoritoPadraoEmUsuario(novoFavorito: FavoritoPadrao, sysArmazenamento: ArmazenamentoUsuario){
        try{
            val usuario = sysArmazenamento.obterUsuario(novoFavorito.obterIdUsuario())

            usuario.addFavorito(novoFavorito.obterFavoritos())
        }
        catch(e: ExistsException){
            throw ExistsException("Erro ao cadastrar favorito: ${e.message}")
        }
    }
}