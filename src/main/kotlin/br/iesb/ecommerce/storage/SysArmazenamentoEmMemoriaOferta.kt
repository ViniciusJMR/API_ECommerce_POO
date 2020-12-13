package br.iesb.ecommerce.storage

import br.iesb.ecommerce.entities.ofertas.OfertaInterface
import br.iesb.ecommerce.exceptions.ExistsException

class SysArmazenamentoEmMemoriaOferta {
    companion object: ArmazenamentoOfertas{
        private var ofertas = mutableListOf<OfertaInterface>()

        override fun obterOfertas() = ofertas

        override fun obterOferta(oferta: OfertaInterface): OfertaInterface {
            if(existeOferta(oferta)){
                return ofertas.elementAt(obterIndexOferta(oferta))
            }
            else
                throw ExistsException("Oferta não cadastrada cadastrada")
        }

        override fun addOferta(novaOferta: OfertaInterface) {
            if(!existeOferta(novaOferta)){
                ofertas.add(novaOferta)
            }
            else
                throw ExistsException("Oferta já está cadastrada")
        }

        override fun removerOferta(oferta: OfertaInterface) {
            if(existeOferta(oferta)){
                ofertas.removeAt(obterIndexOferta(oferta))
            }
            else
                throw ExistsException("Oferta não está cadastrada")
        }

        private fun existeOferta(oferta: OfertaInterface): Boolean{
            for(x in ofertas){
                if(oferta.obterProdutoId() == x.obterProdutoId()){
                    return true
                }
            }

            return false
        }

        private fun obterIndexOferta(oferta: OfertaInterface): Int{
            var i = 0

            for(x in ofertas){
                if(oferta.obterId() == x.obterId())
                    break
                else
                    i++
            }

            return i
        }
    }
}