import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.jpa") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}

group = "co.excentri"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.postgresql:postgresql:42.2.5")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("org.testcontainers:testcontainers:1.14.3")
	testImplementation("org.assertj:assertj-core:3.16.1")
	testImplementation("io.rest-assured:rest-assured:3.1.1")
}

tasks.withType<Test> {
	useJUnitPlatform()
	testLogging {
		events("passed", "skipped", "failed")
	}
}

sourceSets {
	create("integrationTest") {
		withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
			kotlin.srcDir("src/integrationTest/kotlin")
			resources.srcDir("src/integrationTest/resources")
			compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
			runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath
		}
	}
}

task<Test>("integrationTest") {
	description = "Runs the integration tests"
	group = "verification"
	testClassesDirs = sourceSets["integrationTest"].output.classesDirs
	classpath = sourceSets["integrationTest"].runtimeClasspath
	mustRunAfter(tasks["test"])
}

tasks["check"].dependsOn("integrationTest")

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
