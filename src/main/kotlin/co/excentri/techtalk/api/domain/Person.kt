package co.excentri.techtalk.api.domain

import java.time.Instant
import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table


@Entity
@Table(schema = "public", name = "person")
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val fullName: String? = null,
    val nickname: String? = null,
    val role: String? = null,
    var createdAt: Date? = null,
    var updatedAt: Date? = null,
    var createdBy: Long? = null,
    var updatedBy: Long? = null
) {
    // TODO: Change fixed id

    @PrePersist
    fun onCreate() {
        createdAt = Date.from(Instant.now())
        updatedAt = Date.from(Instant.now())
        createdBy = 1L
        updatedBy = 1L
    }

    @PreUpdate
    fun onUpdate() {
        updatedAt = Date.from(Instant.now())
        updatedBy = 1L
    }

    override fun toString(): String {
        return "Person [id=$id, " +
            "fullName=$fullName, " +
            "nickname=$nickname, " +
            "role=$role, " +
            "createdAt=$createdAt, " +
            "updatedAt=$updatedAt, " +
            "createdBy=$createdBy, " +
            "updatedBy=$updatedBy]"
    }
}