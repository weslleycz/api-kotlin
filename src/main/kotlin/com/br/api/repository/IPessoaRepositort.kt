package com.br.api.repository

import com.br.api.model.Pessoa
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IPessoaRepositort: CrudRepository<Pessoa,Long> {
    fun findByEmail(email: String): Optional<Pessoa>
}