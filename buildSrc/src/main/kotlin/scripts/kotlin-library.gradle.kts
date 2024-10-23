package scripts

import dependencies.Dependencies
import extensions.implementation

plugins {
    id("kotlin")
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    //implementation(Dependencies.KOTLIN)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.JAVAX_INJECT)
    implementation(Dependencies.MOSHI)
}