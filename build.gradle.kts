import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    war
    id("java")
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    kotlin("plugin.jpa") version "1.8.21"
}

group = "top.abr"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "top.abr.demo_spring_kt.DemoSpringKtApplicationKt"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE // To avoid the duplicate handling strategy error
    // To add all the dependencies
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
             configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
         })
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict") // JSR 305: Annotations for Software Defect Detection
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
