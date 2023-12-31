plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.serialization)
    application
}

group = "dev.michalkonkel.gameshop"
version = "1.0.0"
application {
    mainClass.set("dev.michalkonkel.gameshop.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    implementation(projects.domain)
    implementation(projects.repository)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.auth)
    implementation(libs.ktor.auth.jwt)
    implementation(libs.ktor.request.validation)
    implementation(libs.ktor.status.pages)
    implementation(libs.ktor.server.resources)
    implementation(libs.db.exposed.core)
    implementation(libs.db.exposed.dao)
    implementation(libs.db.exposed.jdbc)
    implementation(libs.db.h2)
    implementation(libs.koin.ktor)
    implementation(libs.koin.ktor.slf4j)
    implementation(libs.date.time)

    implementation(libs.serialization)

    testImplementation(libs.ktor.server.tests)
}
