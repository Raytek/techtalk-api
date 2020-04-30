package co.excentri.techtalk.api.domain

import com.google.gson.Gson
import org.springframework.data.annotation.Id
import java.time.Instant
import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.PrePersist
import javax.persistence.PreUpdate


@Entity
data class Person(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private val id: Long,
        private val fullName: String? = null,
        private val nickname: String? = null,
        private val role: String? = null,
        private var createdAt: Date,
        private var updatedAt: Date,
        private var createdBy: Long,
        private var updatedBy: Long
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
        return Gson().toJson(super.toString())
    }
}