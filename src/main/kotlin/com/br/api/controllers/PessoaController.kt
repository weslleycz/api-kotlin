package com.br.api.controllers

import com.amdelamar.jhash.Hash
import com.amdelamar.jhash.Hash.password
import com.br.api.dto.LoginDTO
import com.br.api.model.Pessoa
import com.br.api.repository.IPessoaRepositort
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.security.*

@RestController
@RequestMapping("/user")
public class PessoaController(val repository: IPessoaRepositort) {
  @PostMapping("/create")
 fun create(@RequestBody pessoa: Pessoa): Pessoa {
      pessoa.password = password(pessoa.password.toCharArray()).create()
      val save = repository.save(pessoa)
      return save
 }

    @PostMapping("/login")
    fun login(@RequestBody body: LoginDTO) {
      val pessoa = repository.findByEmail(body.email)
        if (pessoa.isPresent()){
            val password =  body.password.toCharArray()
            val correctHash = pessoa.get().password
            if(Hash.password(password).verify(correctHash)){
                print("Foi")
            }else{
                print("N foi")
            }
        }else{
            throw IllegalArgumentException("User not found")
        }
    }

}