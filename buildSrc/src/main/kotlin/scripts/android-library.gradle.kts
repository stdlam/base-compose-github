package scripts

import BuildAndroidConfig
import BuildProductDimensions
import BuildType
import BuildTypeDebug
import BuildTypeRelease
import ProductFlavorDevelop
import ProductFlavorStaging
import ProductFlavorProduction
import dependencies.Dependencies
import extensions.implementation

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

@Suppress("UnstableApiUsage")
android {

    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles(BuildTypeRelease.proguardFile)
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            enableUnitTestCoverage = BuildTypeRelease.enableUnitTestCoverage
            enableAndroidTestCoverage = BuildTypeRelease.enableAndroidTestCoverage
        }

        getByName(BuildType.DEBUG) {
            proguardFiles(BuildTypeDebug.proguardFile)
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            enableUnitTestCoverage = BuildTypeDebug.enableUnitTestCoverage
            enableAndroidTestCoverage = BuildTypeDebug.enableAndroidTestCoverage
        }
    }

    flavorDimensions.add(BuildProductDimensions.ENVIRONMENT)
    productFlavors {
        ProductFlavorDevelop.libCreate(this)
        ProductFlavorStaging.libCreate(this)
        ProductFlavorProduction.libCreate(this)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }

    packagingOptions {
        resources {
            excludes += mutableSetOf(
                "META-INF/DEPENDENCIES.txt",
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE.txt",
                "META-INF/NOTICE",
                "META-INF/MANIFEST.MF",
                "META-INF/MANIFEST.mf",
                "META-INF/LICENSE",
                "META-INF/DEPENDENCIES",
                "META-INF/notice.txt",
                "META-INF/license.txt",
                "META-INF/dependencies.txt",
                "META-INF/services/javax.annotation.processing.Processor",
                "META-INF/rxjava.properties"
            )
        }
    }

    lint {
        lintConfig = rootProject.file(".lint/config.xml")
        quiet = true
        abortOnError = false
        checkAllWarnings = true
        warningsAsErrors = true
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDir("libs")
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }
}

dependencies {
    //implementation(Dependencies.KOTLIN)
    implementation(Dependencies.JAVAX_INJECT)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)
    api(Dependencies.TIMBER)
}