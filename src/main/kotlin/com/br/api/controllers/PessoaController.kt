package com.br.api.controllers

import com.amdelamar.jhash.Hash
import com.amdelamar.jhash.Hash.password
import com.br.api.dto.CreateDTO
import com.br.api.dto.LoginDTO
import com.br.api.dto.ResponseCreateDTO
import com.br.api.dto.ResponseLoginDTO
import com.br.api.model.Pessoa
import com.br.api.repository.IPessoaRepositort
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.security.Key

val mapper = jacksonObjectMapper()

@RestController
@RequestMapping("/user")
public class PessoaController(val repository: IPessoaRepositort) {
  @PostMapping("/create")
 fun create(@RequestBody pessoa: Pessoa): ResponseCreateDTO {
      pessoa.password = password(pessoa.password.toCharArray()).create()
      val save = repository.save(pessoa)
      val res = ResponseCreateDTO("User created!!")
      return res
 }

    @PostMapping("/login")
    fun login(@RequestBody body: LoginDTO): String {
      val pessoa = repository.findByEmail(body.email)
        if (pessoa.isPresent()){
            val password =  body.password.toCharArray()
            val correctHash = pessoa.get().password
            if(Hash.password(password).verify(correctHash)){
                val key: Key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256)
                val jws: String = Jwts.builder().setSubject(pessoa.get().id.toString()).signWith(key).compact()
                val res = ResponseLoginDTO(jws)
                return mapper.writeValueAsString(res)
            }else{
                throw ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Not authorized"
                )
            }
        }else{
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found",
            )
        }
    }

}