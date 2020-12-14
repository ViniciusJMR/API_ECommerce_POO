package br.iesb.ecommerce.entities.vendedores

interface VendedorInterface{
    fun obterNome(): String
    fun obterId(): String?
    fun obterSobre(): String
    fun obterEmail(): String
    fun obterTelefone(): String
    fun obterEndereco(): String
}
