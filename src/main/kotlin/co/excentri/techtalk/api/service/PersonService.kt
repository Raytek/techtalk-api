package co.excentri.techtalk.api.service

import co.excentri.techtalk.api.domain.Person
import co.excentri.techtalk.api.exception.EntityNotFoundException
import co.excentri.techtalk.api.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(private val personRepository: PersonRepository) {
    fun findPersonById(id: Long): Person {
        return personRepository.findById(id).orElseThrow {
            EntityNotFoundException("Person with id $id not found")
        }
    }
}