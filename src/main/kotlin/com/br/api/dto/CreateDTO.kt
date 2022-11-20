package com.br.api.dto

import java.util.*

data class CreateDTO(
    val name:String,
    val email:String,
    var password:String,
    val id: UUID?,
)
