package co.excentri.techtalk.api.repository

import co.excentri.techtalk.api.domain.Person
import org.springframework.data.repository.CrudRepository

interface PersonRepository : CrudRepository<Person, Long>