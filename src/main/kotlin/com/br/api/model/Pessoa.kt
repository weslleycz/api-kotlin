package com.br.api.model

import java.util.*
import javax.persistence.*

@Entity
data class Pessoa (
    @Id @GeneratedValue
    val id: UUID?,
    val name:String,
    @Column(unique=true)
    val email:String,
    var password:String )