package br.iesb.ecommerce.util.key

interface IdGeneratorInterface {
    fun gerarId(): String
    fun gerarId(id: String): String
}