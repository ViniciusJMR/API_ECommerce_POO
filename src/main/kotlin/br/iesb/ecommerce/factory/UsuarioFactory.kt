package br.iesb.ecommerce.factory

import br.iesb.ecommerce.entities.usuario.UsuarioComum
import br.iesb.ecommerce.util.key.UUIDGenerator
import br.iesb.ecommerce.util.timeFormat.DTF

class UsuarioFactory {

    fun criarClasseUsuario(dataUsuarioComum: UsuarioComum) =
            UsuarioComum(
                    dataUsuarioComum.obterNome(),
                    dataUsuarioComum.obterEmail(),
                    dataUsuarioComum.obterEndereco(),
                    DTF(),
                    UUIDGenerator()
            )
}