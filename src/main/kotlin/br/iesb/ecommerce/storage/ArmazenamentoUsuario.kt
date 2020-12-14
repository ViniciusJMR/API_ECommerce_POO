package br.iesb.ecommerce.storage

import br.iesb.ecommerce.entities.usuario.UsuarioComumInterface
import br.iesb.ecommerce.entities.usuario.UsuarioInterface

interface ArmazenamentoUsuario {
    fun obterUsuarios(): MutableList<UsuarioComumInterface>
    fun obterUsuario(usuario: String): UsuarioComumInterface

    fun addUsuario(novoUsuario: UsuarioComumInterface)
    fun removerUsuario(usuario: UsuarioComumInterface)
}