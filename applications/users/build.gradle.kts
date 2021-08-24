import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "2.4.1"
  id("io.spring.dependency-management") version "1.0.10.RELEASE"
  id("io.swagger.core.v3.swagger-gradle-plugin") version "2.1.10"
  kotlin("jvm") version "1.4.21"
  kotlin("plugin.spring") version "1.4.21"
  idea
}

group = "com.goshop.usersapp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

buildscript {
  repositories {
    mavenCentral()
  }

  dependencies {
    classpath("org.springframework:springloaded:1.2.8.RELEASE")
  }
}

repositories {
  mavenCentral()
}

dependencies {
  // Kotlin
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.1")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.2.1")
  implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.2")

  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("io.swagger.core.v3:swagger-core:2.1.10")
  implementation("io.swagger.core.v3:swagger-annotations:2.1.10")
  implementation("javax.ws.rs:javax.ws.rs-api:2.1.1")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("io.projectreactor:reactor-test")
  testImplementation("org.testcontainers:mongodb:1.16.0")
  testImplementation("org.testcontainers:junit-jupiter:1.16.0")
  testImplementation("com.google.code.gson:gson:2.8.8")

  // Dev
  runtimeOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "15"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks {
  resolve {
    outputFileName = "swagger"
    outputFormat = io.swagger.v3.plugins.gradle.tasks.ResolveTask.Format.YAML
    prettyPrint = true
    classpath = sourceSets["main"].runtimeClasspath
    resourcePackages = setOf("com.goshop.usersapp")
    outputDir = file("api")
    readAllResources = true
    openApiFile = file("src/main/resources/swagger-base.yaml")
  }
}

idea {
  module {
    inheritOutputDirs = false
    outputDir = file("$buildDir/classes/main/")
  }
}

sourceSets.named("test") {
  java.srcDir("src/integrationTest/kotlin")
  resources.srcDir("src/integrationTest/resources")
}
