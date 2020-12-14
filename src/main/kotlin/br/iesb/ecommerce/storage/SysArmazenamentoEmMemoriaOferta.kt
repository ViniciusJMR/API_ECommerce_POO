package br.iesb.ecommerce.storage

import br.iesb.ecommerce.services.ofertas.OfertaDescontoInterface
import br.iesb.ecommerce.exceptions.ExistsException

class SysArmazenamentoEmMemoriaOferta {
    companion object: ArmazenamentoOfertas{
        private var ofertas = mutableListOf<OfertaDescontoInterface>()

        override fun obterOfertas() = ofertas

        override fun obterOferta(ofertaId: String): OfertaDescontoInterface {
            if(existeOferta(ofertaId)){
                return ofertas.elementAt(obterIndexOferta(ofertaId))
            }
            else
                throw ExistsException("Oferta não cadastrada cadastrada")
        }

        override fun addOferta(novaOferta: OfertaDescontoInterface) {
            if(!existeOferta(novaOferta)){
                ofertas.add(novaOferta)
            }
            else
                throw ExistsException("Oferta já está cadastrada")
        }

        override fun removerOferta(oferta: OfertaDescontoInterface) {
            if(ofertas.contains(oferta)){
                ofertas.remove(oferta)
            }
            else
                throw ExistsException("Oferta não está cadastrada")
        }

        private fun existeOferta(oferta: OfertaDescontoInterface): Boolean{
            for(x in ofertas){
                if(oferta.obterProdutoId() == x.obterProdutoId()){
                    return true
                }
            }

            return false
        }

        private fun existeOferta(ofertaId: String): Boolean{
            for(x in ofertas){
                if(ofertaId == x.obterId()){
                    return true
                }
            }

            return false
        }

        private fun obterIndexOferta(oferta: String): Int{
            var i = 0

            for(x in ofertas){
                if(oferta == x.obterId())
                    break
                else
                    i++
            }

            return i
        }
    }
}