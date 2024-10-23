plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

object PluginsVersions {
    const val GRADLE_ANDROID = "8.5.2"
    const val KOTLIN = "1.8.21"
    const val HILT = "2.44"
    const val NAVIGATION = "2.5.3"
}

dependencies {
    // android gradle plugin, required by custom plugin
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    // kotlin plugin, required by custom plugin
    implementation(kotlin("gradle-plugin", PluginsVersions.KOTLIN))

    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginsVersions.HILT}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
}