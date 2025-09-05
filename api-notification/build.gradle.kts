plugins {
    id("java-test-fixtures")
}

tasks.bootJar {
    enabled = true
    launchScript()
    manifest {
        attributes["Start-Class"] = "com.senok.ApiNotificationApplication"
    }
}

tasks.jar {
    enabled = true
}

dependencies {
    implementation(project(":core-web"))
    implementation(project(":core-event-publisher"))
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.kafka:spring-kafka")

    runtimeOnly("com.mysql:mysql-connector-j")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")


    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("com.h2database:h2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
