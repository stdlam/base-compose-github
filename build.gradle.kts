// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
//    id("com.github.ben-manes.versions") version "0.42.0"
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}