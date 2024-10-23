import dependencies.Dependencies
import extensions.apiNavigation
import extensions.apiLifecycle
import extensions.compose

// import extensions.apiRxJava

plugins {
    id(ScriptPlugins.android_library)
}

android {
    namespace = "com.practices.githubusers.internal.core"
    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = BuildDependenciesVersions.COMPOSE_COMPILER
    }
}

dependencies {

    api(project(Modules.DATA))
    api(project(Modules.DOMAIN))

    apiNavigation()
    apiLifecycle()
    compose()
}