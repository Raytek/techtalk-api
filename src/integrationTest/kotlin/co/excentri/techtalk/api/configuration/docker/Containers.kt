package co.excentri.techtalk.api.configuration.docker

import org.testcontainers.containers.FixedHostPortGenericContainer
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.utility.MountableFile

class Containers {
    companion object {
        val postgres: GenericContainer<Nothing> = FixedHostPortGenericContainer<Nothing>("postgres:10.5-alpine").also {
            it.withFixedExposedPort(5432, 5432)
            it.addEnv("POSTGRES_PASSWORD", "techtalk")
            it.withCopyFileToContainer(
                MountableFile.forClasspathResource(
                    "schema.sql"
                ),
                "/docker-entrypoint-initdb.d/10-schema.sql"
            )
            it.withCopyFileToContainer(
                MountableFile.forClasspathResource(
                    "data.sql"
                ),
                "/docker-entrypoint-initdb.d/20-data.sql"
            )
            it.waitingFor(Wait.forListeningPort())
            it.start()
        }
    }
}