import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

plugins {
    id("org.springframework.boot") version "2.3.3.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.4.0"
    kotlin("plugin.spring") version "1.4.0"
    kotlin("plugin.jpa") version "1.4.0"
    id("org.jlleitschuh.gradle.ktlint") version "9.4.0"
}

group = "com.raynigon.mco"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven { url = URI.create("https://jitpack.io") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.github.raynigon.unit-api:jackson-starter:a4f550075d")
    implementation("com.github.raynigon.unit-api:jpa-starter:a4f550075d")
    implementation("com.github.raynigon.unit-api:unit-api-kotlin:a4f550075d")
    // Future implementation("com.github.raynigon.unit-api:springdoc-starter:master-SNAPSHOT")

    runtimeOnly("org.postgresql:postgresql")

    implementation("org.testcontainers:postgresql") // We dont want to setup a real DB here
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.testcontainers:junit-jupiter")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:1.14.3")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
