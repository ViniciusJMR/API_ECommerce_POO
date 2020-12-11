package br.iesb.ecommerce.entities.produtos

class ProdutoComprador(
        private var nomeVendedor: String,
        private var nome: String,
        private var listaCaracteristicas: MutableList<String>,
        private var descricao: String,
        private var valor: Float
): ProdutoInterface{
    override fun obterNome() = nome
}