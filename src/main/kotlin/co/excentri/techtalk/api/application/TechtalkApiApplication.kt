package co.excentri.techtalk.api.application

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableAutoConfiguration
@ComponentScan(basePackages = ["co.excentri.techtalk.api"])
@ConfigurationPropertiesScan(basePackages = ["co.excentri.techtalk.api"])
@EnableJpaRepositories(basePackages = ["co.excentri.techtalk.api.repository"])
@EntityScan(basePackages = ["co.excentri.techtalk.api.domain"])
class TechtalkApiApplication

fun main(args: Array<String>) {
	runApplication<TechtalkApiApplication>(*args)
}
