import dependencies.Dependencies
import extensions.*

plugins {
    id(ScriptPlugins.android_library)
}

android {
    namespace = "com.practices.githubusers.internal.data"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    preference()
    room()
    daggerHilt()
    apiRetrofit()
    apiLifecycle()
    implementation(Dependencies.COMPOSE_COIL)

    // module
    implementation(project(Modules.DOMAIN))
}