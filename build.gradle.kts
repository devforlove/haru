import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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