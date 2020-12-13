package br.iesb.ecommerce.services.categorias

import br.iesb.ecommerce.exceptions.ExistsException
import br.iesb.ecommerce.util.key.IdGeneratorInterface

class CategoriaPadrao(
        private var nome:String,
        idGenerator: IdGeneratorInterface
): CategoriaInterface{
    private val id = idGenerator.gerarId()
    private var subCategorias = mutableListOf<SubCategoriaInterface>()

    override fun obterId() = id
    override fun obterNome() = nome
    override fun obterSubCategorias() = subCategorias

    override fun atualizarSubCategoria(subCategoriaAtualizada: SubCategoriaInterface) {
        if(existeSubCategoria(subCategoriaAtualizada)){
            val x = obterSubCategoria(subCategoriaAtualizada)

            x.atualizarCategoria(subCategoriaAtualizada)
        }
        else
            throw ExistsException("SubCategoria não cadastrada em Categoria: $nome")
    }

    override fun atualizarCategoria(categoriaAtualizada: CategoriaInterface) {
        nome = categoriaAtualizada.obterNome()
    }

    override fun addSubCategoria(novaSubCategoria: SubCategoriaInterface) {
        if(!existeSubCategoria(novaSubCategoria)){
            subCategorias.add(novaSubCategoria)
        }
        else
            throw ExistsException("SubCategoria já cadastrada em Categoria: $nome")
    }

    override fun removerSubCategoria(subCategoria: SubCategoriaInterface) {
        if(existeSubCategoria(subCategoria)){
            subCategorias.remove(obterSubCategoria(subCategoria))
        }
        else
            throw ExistsException("SubCategoria não cadastrada em Categoria: $nome")
    }

    override fun obterSubCategoria(subCategoria: SubCategoriaInterface): SubCategoriaInterface{
        for(x in subCategorias){
            if(subCategoria.obterNome() == x.obterNome()){
                return x
            }
        }

        return SubCategoriaPadrao()
    }

    private fun existeSubCategoria(subCategoria: SubCategoriaInterface): Boolean{
        for(x in subCategorias){
            if(subCategoria.obterNome() == x.obterNome()){
                return true
            }
        }
        return false
    }

}