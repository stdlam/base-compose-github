package extensions

import dependencies.Dependencies
import dependencies.TestAndroidDependencies
import dependencies.TestDependencies
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ResolutionStrategy
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.debugImplementation(dependencyNotation: String): Dependency? =
    add("debugImplementation", dependencyNotation)

fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.api(dependencyNotation: String): Dependency? =
    add("api", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: String): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.annotationProcessor(dependencyNotation: String): Dependency? =
    add("annotationProcessor", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: String): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.platform2(dependencyNotation: String): Dependency? =
    platform(dependencyNotation)

fun DependencyHandler.addTestsDependencies() {
    testImplementation(TestDependencies.TEST_COROUTINE)
    testImplementation(TestDependencies.MOCKITO)
    testImplementation(TestDependencies.KOTLIN_MOCKITO)

    androidTestImplementation(TestAndroidDependencies.JUNIT)
    androidTestImplementation(TestAndroidDependencies.COMPOSE_JUNIT)

    implementation(TestAndroidDependencies.JUNIT4)
    implementation(TestDependencies.ROBOLECTRIC)
    implementation(TestAndroidDependencies.ESPRESSO)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_UI_PREVIEW)
    implementation(Dependencies.COMPOSE_UI_GRAPHICS)
    implementation(Dependencies.COMPOSE_ACTIVITY)
    implementation(Dependencies.COMPOSE_NAVIGATION)
    implementation(Dependencies.COMPOSE_PAGING)
    implementation(Dependencies.COMPOSE_HILT)
    implementation(Dependencies.PAGING_KTX)

    debugImplementation(Dependencies.COMPOSE_UI_TOOLING)
    debugImplementation(Dependencies.COMPOSE_UI_TEST)
}

fun DependencyHandler.composeGlide() {
    implementation(Dependencies.GLIDE)
    implementation(Dependencies.GLIDE_COMPILER)
    implementation(Dependencies.COMPOSE_GLIDE)
}

fun DependencyHandler.apiRetrofit() {
    api(Dependencies.RETROFIT)
    api(Dependencies.RETROFIT_GSON)
    api(Dependencies.RETROFIT_MOSHI)
    api(Dependencies.OKHTTP)
    api(Dependencies.OKHTTP_LOGGING)
    api(Dependencies.MOSHI)
    api(Dependencies.MOSHI_KTX)
    api(Dependencies.MOSHI_ADAPTERS)
    kapt(Dependencies.MOSHI_CODEGEN)
}

fun DependencyHandler.daggerHilt() {
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_COMPILER)
    kapt(Dependencies.HILT_EXT_COMPILER)
}

fun DependencyHandler.work() {
    implementation(Dependencies.WORK_KTX)
    implementation(Dependencies.WORK_HILT)
    implementation(Dependencies.STARTUP)
}

fun DependencyHandler.apiRxJava() {
    api(Dependencies.RX_ANDROID)
    api(Dependencies.RX_JAVA)
}

fun DependencyHandler.room() {
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    annotationProcessor(Dependencies.ROOM_COMPILER)
    kapt(Dependencies.ROOM_COMPILER)
}

fun DependencyHandler.apiLifecycle() {
    api(Dependencies.LIFECYCLE_LIVEDATA)
    api(Dependencies.LIFECYCLE_VIEWMODEL)
    api(Dependencies.LIFECYCLE_RUNTIME)
}

fun DependencyHandler.apiNavigation() {
    api(Dependencies.NAVIGATION_RUNTIME)
    api(Dependencies.NAVIGATION_FRAGMENT)
    api(Dependencies.NAVIGATION_UI)
}

fun DependencyHandler.preference() {
    // implementation(Dependencies.SECURITY_CRYPTO)
    implementation(Dependencies.PREFERENCE_KTX)
}

fun DependencyHandler.apiFirebaseAuth() {
    api("com.google.firebase:firebase-messaging-ktx")
    api("com.google.firebase:firebase-auth-ktx")
    api("com.google.firebase:firebase-appcheck-playintegrity")
    api("com.google.android.gms:play-services-auth:20.4.1")
    //api("com.facebook.android:facebook-login:14.1.1")
    api("androidx.browser:browser:1.4.0")
}

fun DependencyHandler.apiFirebaseCrashlytics() {
    api("com.google.firebase:firebase-crashlytics-ktx")
    api("com.google.firebase:firebase-analytics-ktx")
}