package br.iesb.ecommerce.util.key

import java.util.*

class UUIDGenerator(): IdGeneratorInterface {
    override fun gerarId() = UUID.randomUUID().toString()
    override fun gerarId(id: String) = id + "-" + UUID.randomUUID().toString()
}