package br.iesb.ecommerce.factory

import br.iesb.ecommerce.entities.produtos.Produto
import br.iesb.ecommerce.entities.produtos.ProdutoVendedor
import br.iesb.ecommerce.services.frete.FreteVendedorPadrão
import br.iesb.ecommerce.util.key.UUIDGenerator

class ProdutoFactory{

    fun criarProduto(dataProduto: Produto) =
            Produto(
                    dataProduto.obterId(),
                    dataProduto.obterNome(),
                    dataProduto.obterListaCaracteristicas(),
                    dataProduto.obterDescricao(),
                    dataProduto.obterValor(),
                    dataProduto.obterQtdEstoque()
            )

    fun criarProdutoVendedorFretePadrao(dataProdutoVendedor: ProdutoVendedor) =
            ProdutoVendedor(
                    dataProdutoVendedor.obterIdVendedor(),
                    dataProdutoVendedor.obterNome(),
                    dataProdutoVendedor.obterListaCaracteristicas(),
                    dataProdutoVendedor.obterDescricao(),
                    dataProdutoVendedor.obterValor(),
                    dataProdutoVendedor.obterQtdEstoque(),
                    FreteVendedorPadrão(
                            dataProdutoVendedor.obterValor(),
                            dataProdutoVendedor.obterIdVendedor()
                    ),
                    UUIDGenerator()
            )
}