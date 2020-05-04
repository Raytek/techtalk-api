package co.excentri.techtalk.api.controllers

import co.excentri.techtalk.api.domain.Person
import co.excentri.techtalk.api.repository.PersonRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController(private val personRepository: PersonRepository) {

    @GetMapping
    fun person(@RequestParam id: Long): ResponseEntity<Person> {
        return ResponseEntity.of(personRepository.findPersonById(id))
    }
}