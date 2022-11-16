package com.br.api.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Pessoa (
    @Id @GeneratedValue
                   val id:Long?,
    val name:String,
    val email:String,
    var password:String )