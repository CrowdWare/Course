//import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "2.0.0"
    id("org.jetbrains.compose") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" // ✅ NEU
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"
    id("application")
}

group = "at.crowdware.course"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://jogamp.org/deployment/maven/")
    maven("https://maven.pkg.jetbrains.space/public/p/skiko/maven")
}
dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.skiko:skiko-awt-runtime-macos-arm64:0.9.4.2")
    implementation("org.jetbrains.compose.ui:ui:1.6.10")
    implementation("org.jetbrains.compose.foundation:foundation:1.6.10")
    implementation("org.jetbrains.compose.material3:material3:1.6.10")
    implementation("com.github.h0tk3y.betterParse:better-parse:0.4.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.0")
    implementation("network.chaintech:compose-multiplatform-media-player:1.0.40")
    implementation("org.jetbrains.compose.material:material-icons-extended:1.6.10")
}
application {
    mainClass.set("at.crowdware.course.MainKt") // ✅ dein Entry-Point
}