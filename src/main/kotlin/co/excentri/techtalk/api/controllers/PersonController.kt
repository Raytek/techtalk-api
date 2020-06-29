package co.excentri.techtalk.api.controllers

import co.excentri.techtalk.api.domain.Person
import co.excentri.techtalk.api.service.PersonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController(private val personService: PersonService) {

    @GetMapping("{id}")
    fun person(@PathVariable id: Long): ResponseEntity<Person> {
        val person = personService.findPersonById(id)
        return ResponseEntity.ok(person)
    }
}