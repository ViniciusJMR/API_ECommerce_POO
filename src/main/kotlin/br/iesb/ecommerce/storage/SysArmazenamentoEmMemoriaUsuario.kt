package br.iesb.ecommerce.storage

import br.iesb.ecommerce.entities.usuario.UsuarioComum
import br.iesb.ecommerce.entities.usuario.UsuarioComumInterface
import br.iesb.ecommerce.entities.usuario.UsuarioInterface
import br.iesb.ecommerce.exceptions.ExistsException

class SysArmazenamentoEmMemoriaUsuario {
    companion object: ArmazenamentoUsuario {
        private var usuarios = mutableListOf<UsuarioComumInterface>()

        override fun obterUsuarios() = usuarios

        override fun obterUsuario(usuarioId: String): UsuarioComumInterface {
            if(existeUsuario(usuarioId)){
                return usuarios[obterIndexUsuario(usuarioId)]
            }
            else
                throw ExistsException("Usuário não cadastrado")
        }

        override fun addUsuario(novoUsuario: UsuarioComumInterface) {
            if(!existeUsuario(novoUsuario)){
                usuarios.add(novoUsuario)
            }
            else
                throw ExistsException("Email de usuário já cadastrado")
        }

       override fun removerUsuario(usuario: UsuarioComumInterface){
           if(usuarios.contains(usuario))
               usuarios.remove(usuario)
           else
               throw ExistsException("Usuario não cadastrado")
       }


        private fun existeUsuario(usuario: UsuarioInterface): Boolean {
            for (x in usuarios) {
                if (usuario.obterEmail() == x.obterEmail()) {
                    return true
                }
            }

            return false
        }

        private fun existeUsuario(usuarioId: String): Boolean {
            for (x in usuarios) {
                if (usuarioId == x.obterId()) {
                    return true
                }
            }

            return false
        }

        private fun obterIndexUsuario(usuario: UsuarioInterface): Int {
            var i = 0

            for (x in usuarios) {
                if (usuario.obterEmail() == x.obterEmail()) {
                    break
                } else
                    i++
            }
            return i
        }

        private fun obterIndexUsuario(usuarioId: String): Int {
            var i = 0

            for (x in usuarios) {
                if (usuarioId == x.obterId()) {
                    break
                } else
                    i++
            }
            return i
        }
    }
}
