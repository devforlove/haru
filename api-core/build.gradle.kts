plugins {
    id("java-test-fixtures")
}

tasks.bootJar {
    enabled = true
    launchScript()
    manifest {
        attributes["Start-Class"] = "com.senok.ApiCoreApplication"
    }
}

tasks.jar {
    enabled = true
}

dependencies {
    implementation(project(":common"))
    implementation(project(":core-web"))
    implementation(project(":core-event-publisher"))
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // db connector
    runtimeOnly("com.mysql:mysql-connector-j")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("com.h2database:h2")
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(testFixtures(project(":core-event-publisher")))
    testImplementation("io.kotest:kotest-runner-junit5:5.8.1")
    testImplementation("io.kotest:kotest-assertions-core:5.8.1")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.3.0")
    testImplementation("io.mockk:mockk:1.13.11")
    testImplementation("io.mockk:mockk-jvm:1.13.11")

    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:mysql")
}