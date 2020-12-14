package br.iesb.ecommerce.entities.usuario

interface UsuarioInterface {
    fun obterId(): String?
    fun obterNome(): String
    fun obterEmail(): String
    fun obterEndereco(): String

    fun atualizarInformações(novoNome: String, novoEmail: String, novoEndereco: String)
}