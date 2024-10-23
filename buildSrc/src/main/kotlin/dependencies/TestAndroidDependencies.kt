package dependencies

object TestAndroidDependencies {
    const val LEAKCANARY = "com.squareup.leakcanary:leakcanary-android-instrumentation:${BuildDependenciesVersions.LEAKCANARY}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${BuildDependenciesVersions.ESPRESSO}"
    const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:${BuildDependenciesVersions.COMPOSE_JUNIT}"
    const val JUNIT4 = "androidx.compose.ui:ui-test-junit4-android:${BuildDependenciesVersions.JUNIT4}"
    const val JUNIT = "androidx.test.ext:junit:${BuildDependenciesVersions.JUNIT}"
}