package co.excentri.techtalk.api.repository

import co.excentri.techtalk.api.domain.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long> {

    fun findPersonById(id: Long): Person
}