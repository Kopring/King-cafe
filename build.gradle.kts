import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.convert") version "1.5.8"
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.spring") version "1.5.21"
    kotlin("plugin.jpa") version "1.5.21"
}

apply(plugin = "io.spring.dependency-management")

group = "com.kingcafe.server"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val snppetsDir = file("build/generated-snippets")


tasks.test {
    outputs.dir(snppetsDir)
}

tasks.asciidoctor {
    inputs.dir(snppetsDir)
    dependsOn(tasks.test)
}

tasks.register("copyHTML", Copy::class) {
    dependsOn(tasks.findByName("asciidoctor"))
    from(file("build/asciidoc/html5"))
    into(file("src/main/resources/static/docs"))
}

tasks.bootJar {
    dependsOn(tasks.asciidoctor)
    dependsOn(tasks.getByName("copyHTML"))
}
