package br.iesb.ecommerce.factory

import br.iesb.ecommerce.entities.vendedores.VendedorPadrao
import br.iesb.ecommerce.util.key.UUIDGenerator
import br.iesb.ecommerce.util.timeFormat.DTF

class VendedorFactory {
    fun criarVendedorPadrao(dataVendedorPadrao: VendedorPadrao) =
            VendedorPadrao(
                    dataVendedorPadrao.obterNome(),
                    dataVendedorPadrao.obterSobre(),
                    dataVendedorPadrao.obterEmail(),
                    dataVendedorPadrao.obterTelefone(),
                    dataVendedorPadrao.obterEndereco(),
                    DTF(),
                    UUIDGenerator()
            )
}