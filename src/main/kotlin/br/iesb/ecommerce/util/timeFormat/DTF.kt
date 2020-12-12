package br.iesb.ecommerce.util.timeFormat

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DTF(): TimeFormatInterface{
    val dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

    override fun obterDataHoraAtual() = dtf.format(LocalDateTime.now())
}

