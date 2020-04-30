package co.excentri.techtalk.api.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TechtalkApiApplication

fun main(args: Array<String>) {
	runApplication<TechtalkApiApplication>(*args)
}
