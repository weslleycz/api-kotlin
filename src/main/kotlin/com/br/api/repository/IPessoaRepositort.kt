package com.br.api.repository

import com.br.api.model.Pessoa
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IPessoaRepositort: CrudRepository<Pessoa,Long> {

}