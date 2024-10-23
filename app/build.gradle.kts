import dependencies.Dependencies
import extensions.daggerHilt
import extensions.work
import extensions.addTestsDependencies
import extensions.compose
import extensions.composeGlide
import extensions.implementation

plugins {
    id(ScriptPlugins.android_app)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = BuildAndroidConfig.NAMESPACE
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = BuildDependenciesVersions.COMPOSE_COMPILER
    }

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        /*ndk {
            // "armeabi-v7a", "arm64-v8a", "armeabi", "x86", "x86_64")
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "armeabi")
        }*/

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    testOptions.unitTests.isIncludeAndroidResources = true
}

dependencies {

    // Core module
    implementation(project(Modules.CORE))

    // App dependencies
    implementation(Dependencies.MULTIDEX)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.ANNOTATION)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)
    implementation(Dependencies.CORE_KTX)

    daggerHilt()
    work()
    compose()
    composeGlide()
    implementation(Dependencies.COMPOSE_COIL)

    implementation(Dependencies.SPLASHSCREEN)

    // Dependencies for tests
    addTestsDependencies()
}