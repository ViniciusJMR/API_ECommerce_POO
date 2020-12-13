package br.iesb.ecommerce.storage

import br.iesb.ecommerce.entities.usuario.UsuarioInterface
import br.iesb.ecommerce.exceptions.ExistsException

class SysArmazenamentoEmMemoriaUsuario {
    companion object: ArmazenamentoUsuario {
        private var usuarios = mutableListOf<UsuarioInterface>()

        override fun obterUsuarios() = usuarios

        override fun obterUsuario(usuario: UsuarioInterface): UsuarioInterface {
            if(existeUsuario(usuario)){
                return usuarios.elementAt(obterIndexUsuario(usuario))
            }
            else
                throw ExistsException("Usuário não cadastrado")
        }

        override fun addUsuario(novoUsuario: UsuarioInterface) {
            if(!existeUsuario(novoUsuario)){
                usuarios.add(novoUsuario)
            }
            else
                throw ExistsException("Email de usuário já cadastrado")
        }

        override fun removerUsuario(usuario: UsuarioInterface) {
            if(existeUsuario(usuario)){
                usuarios.removeAt(obterIndexUsuario(usuario))
            }
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
    }
}