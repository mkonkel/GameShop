plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.serialization) apply false
    alias(libs.plugins.spotless) apply false
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/iosMain/**")
            ktlint("1.0.1")
                .setEditorConfigPath("${rootDir.absolutePath}/config/.editorconfig")
        }
        kotlinGradle {
            target("*.gradle.kts")
            ktlint("1.0.1")
                .setEditorConfigPath("${rootDir.absolutePath}/config/.editorconfig")
        }
    }
}