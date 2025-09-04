// settings.gradle.kts
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "haru"

include(
    ":api-core",
    ":api-notification",
    ":core-web",
    ":common",
    ":batch-server",
    ":core-event-publisher",
    ":api-front",
)
