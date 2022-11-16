package com.br.api.controllers

import com.amdelamar.jhash.Hash.password
import com.br.api.model.Pessoa
import com.br.api.repository.IPessoaRepositort
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.*


@RestController
@RequestMapping("/user")
public class PessoaController(val repository: IPessoaRepositort) {
  @PostMapping()
 fun create(@RequestBody pessoa: Pessoa): Pessoa {
      pessoa.password = password(pessoa.password.toCharArray()).create()
      val save = repository.save(pessoa)
      return save
 }
}