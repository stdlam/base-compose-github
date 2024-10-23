package scripts

import BuildType
import extensions.loadGradleProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")

    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

@Suppress("UnstableApiUsage")
android {

    defaultConfig {
        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles(BuildTypeRelease.proguardFile)
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            enableUnitTestCoverage = BuildTypeRelease.enableUnitTestCoverage
            enableAndroidTestCoverage = BuildTypeRelease.enableAndroidTestCoverage
            //signingConfig = signingConfigs["config"]
            //buildConfigField("boolean", "ENABLE_CRASHLYTICS_COLLECT", "${BuildTypeRelease.enableCrashlyticsCollect}")
        }

        getByName(BuildType.DEBUG) {
            // applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            // versionNameSuffix = BuildTypeDebug.versionNameSuffix
            proguardFiles(BuildTypeDebug.proguardFile)
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            enableUnitTestCoverage = BuildTypeDebug.enableUnitTestCoverage
            enableAndroidTestCoverage = BuildTypeDebug.enableAndroidTestCoverage
            //signingConfig = signingConfigs["config"]
            //buildConfigField("boolean", "ENABLE_CRASHLYTICS_COLLECT", "${BuildTypeDebug.enableCrashlyticsCollect}")
        }
    }

    flavorDimensions.add(BuildProductDimensions.ENVIRONMENT)
    productFlavors {
        ProductFlavorDevelop.appCreate(this)
        ProductFlavorStaging.appCreate(this)
        ProductFlavorProduction.appCreate(this)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
        /*freeCompilerArgs = listOf(
            *kotlinOptions.freeCompilerArgs.toTypedArray(),
            "-Xuse-experimental=kotlin.ExperimentalUnsignedTypes"
        )*/
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