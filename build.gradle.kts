import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

plugins {
	id("org.springframework.boot") version "3.4.5" apply false
	id("io.spring.dependency-management") version "1.1.4" apply false
	id("org.jetbrains.kotlin.jvm") version "1.9.25" apply false
	id("org.jetbrains.kotlin.plugin.spring") version "1.9.25" apply false
	id("org.jetbrains.kotlin.plugin.jpa") version "1.9.25" apply false
	id("org.jetbrains.kotlin.kapt") version "1.9.25" apply false
}

allprojects {
	group = "com.senok"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	// (필요 시) 집계용 모듈 스킵 예시
	// if (path == ":publishers") return@subprojects
	
	pluginManager.withPlugin("java") {
		extensions.configure(JavaPluginExtension::class.java) {
			toolchain.languageVersion.set(JavaLanguageVersion.of(17))
		}
		tasks.withType(JavaCompile::class.java).configureEach {
			options.release.set(17)
		}
	}
	
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
	apply(plugin = "org.jetbrains.kotlin.kapt")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")

	configurations {
		create("mockitoAgent")
	}

	dependencies {
	}

	tasks.withType<KotlinCompile>().configureEach {
		kotlinOptions {
			jvmTarget = "17"
		}
	}
}

tasks.register("jibDockerBuildAll") {
	// 프로젝트 네이밍에 맞게 필터 조정 가능
	val targets = subprojects.filter { it.name.startsWith("api-core") || it.name.startsWith("api-notification") }
	dependsOn(targets.map { "${it.path}:jibDockerBuild" })
}

tasks.register<Exec>("composeUp") {
	dependsOn("jibDockerBuildAll")
	commandLine(composeCmd())
}

fun composeCmd() =
	listOf("bash", "-lc", "docker compose up -d")


