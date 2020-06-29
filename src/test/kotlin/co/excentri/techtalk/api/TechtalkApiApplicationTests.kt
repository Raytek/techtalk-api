package co.excentri.techtalk.api

import co.excentri.techtalk.api.application.TechtalkApiApplication
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [TechtalkApiApplication::class])
class TechtalkApiApplicationTests {

	@Test
	fun contextLoads() {
	}

}
