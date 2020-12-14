package br.iesb.ecommerce.entities.vendedores

class Vendedor(
    private var id: String?,
    private var nome: String,
    private var sobre: String,
    private var email: String,
    private var telefone: String,
    private var endereco: String,
): VendedorInterface {
    override fun obterNome() = nome
    override fun obterSobre() = sobre
    override fun obterEmail() = email
    override fun obterId() = id
    override fun obterEndereco() = endereco
    override fun obterTelefone() = telefone
}
