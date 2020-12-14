package br.iesb.ecommerce.entities.usuario

class Usuario(
    private var id: String,
    private var nome: String,
    private var email: String,
    private var endereco: String
): UsuarioInterface {
    override fun obterId() = id
    override fun obterNome() = nome
    override fun obterEmail() = email
    override fun obterEndereco() = endereco

    override fun atualizarInformações(novoNome: String, novoEmail: String, novoEndereco: String) {
    }
}