package br.iesb.ecommerce.storage

import br.iesb.ecommerce.entities.usuario.UsuarioInterface

interface ArmazenamentoUsuario {
    fun obterUsuarios(): MutableList<UsuarioInterface>
    fun obterUsuario(usuario: String): UsuarioInterface

    fun addUsuario(novoUsuario: UsuarioInterface)
    fun removerUsuario(usuario: UsuarioInterface)
}