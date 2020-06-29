package co.excentri.techtalk.api.controllers

import co.excentri.techtalk.api.application.TechtalkApiApplication
import co.excentri.techtalk.api.configuration.docker.Containers
import co.excentri.techtalk.api.domain.Person
import co.excentri.techtalk.api.repository.PersonRepository
import io.restassured.RestAssured.expect
import io.restassured.RestAssured.given
import io.restassured.config.EncoderConfig.encoderConfig
import io.restassured.config.RestAssuredConfig.config
import io.restassured.http.ContentType.TEXT
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.emptyOrNullString
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.equalToCompressingWhiteSpace
import org.hamcrest.Matchers.not
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [TechtalkApiApplication::class, Containers::class])
class PersonControllerTest {

    companion object {
        private const val CONTENT_TYPE_JSON: String = "{\"type\":\"application\",\"subtype\":\"json\",\"parameters\":{\"charset\":\"UTF-8\"},\"qualityValue\":1.0,\"concrete\":true,\"wildcardType\":false,\"wildcardSubtype\":false,\"charset\":\"UTF-8\"}"
        private const val PATH: String = "/person"
    }

    @LocalServerPort
    internal var port: Int = 0

    @Autowired
    private lateinit var repository: PersonRepository

    @Test
    fun `When a get operation is performed with an existing Id, a 200 status-code should be returned`() {
        val id: Long = 1
        val personDB = repository.findById(id)
        val personAPI = given()
            .config(
                config()
                    .encoderConfig(encoderConfig().encodeContentTypeAs(CONTENT_TYPE_JSON, TEXT))
            )
            .port(port)
            .log().all()
            .contentType(APPLICATION_JSON.toString())
            .`when`()
            .get("$PATH/$id")
            .then()
            .log().all()
            .statusCode(OK.value())
            .spec(
                expect()
                    .header("content-type", equalToCompressingWhiteSpace(APPLICATION_JSON.toString()))
                    .body("$", not(emptyOrNullString()))
            )
            .extract().`as`(Person::class.java)
        assertThat(personAPI).isEqualTo(personDB.get())
    }

    @Test
    fun `When a get operation is performed with a non-existing Id, a 404 status-code should be returned`() {
        val id: Long = 999
        given()
            .config(
                config()
                    .encoderConfig(encoderConfig().encodeContentTypeAs(CONTENT_TYPE_JSON, TEXT))
            )
            .port(port)
            .log().all()
            .contentType(APPLICATION_JSON.toString())
            .`when`()
            .get("$PATH/$id")
            .then()
            .log().all()
            .statusCode(NOT_FOUND.value())
            .spec(
                expect()
                    .header("content-type", `is`(equalToCompressingWhiteSpace(APPLICATION_JSON.toString())))
                    .body("path", `is`(equalTo("$PATH/$id")))
                    .body("error", `is`(equalTo(NOT_FOUND.reasonPhrase)))
                    .body("message", `is`(equalTo("Person with id $id not found")))
                    .body("timestamp", `is`(not(emptyOrNullString())))
                    .body("status", `is`(equalTo(NOT_FOUND.value())))
            )
        val result = repository.findById(id)
        assertThat(result.isPresent).isFalse()
    }
}