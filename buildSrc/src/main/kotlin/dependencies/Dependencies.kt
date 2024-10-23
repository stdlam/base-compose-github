package dependencies

object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${BuildDependenciesVersions.KOTLIN}"
    const val CORE_KTX = "androidx.core:core-ktx:${BuildDependenciesVersions.CORE_KTX}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${BuildDependenciesVersions.APPCOMPAT}"
    const val ANNOTATION = "androidx.annotation:annotation:${BuildDependenciesVersions.ANNOTATION}"
    const val MATERIAL = "com.google.android.material:material:${BuildDependenciesVersions.MATERIAL}"
    const val CONSTRAIN_LAYOUT = "androidx.constraintlayout:constraintlayout:${BuildDependenciesVersions.CONSTRAIN_LAYOUT}"
    const val SPLASHSCREEN = "androidx.core:core-splashscreen:${BuildDependenciesVersions.SPLASHSCREEN}"
    const val MULTIDEX = "androidx.multidex:multidex:${BuildDependenciesVersions.MULTIDEX}"

    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${BuildDependenciesVersions.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${BuildDependenciesVersions.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${BuildDependenciesVersions.LIFECYCLE}"

    const val ROOM = "androidx.room:room-runtime:${BuildDependenciesVersions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${BuildDependenciesVersions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${BuildDependenciesVersions.ROOM}"

    const val RECYCLE_VIEW = "androidx.recyclerview:recyclerview:${BuildDependenciesVersions.RECYCLE_VIEW}"
    const val VIEWPAGER2 = "androidx.viewpager2:viewpager2:${BuildDependenciesVersions.VIEWPAGER2}"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${BuildDependenciesVersions.SWIPE_REFRESH_LAYOUT}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${BuildDependenciesVersions.FRAGMENT_KTX}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${BuildDependenciesVersions.ACTIVITY_KTX}"

    const val NAVIGATION_RUNTIME = "androidx.navigation:navigation-runtime-ktx:${BuildDependenciesVersions.NAVIGATION}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${BuildDependenciesVersions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${BuildDependenciesVersions.NAVIGATION}"

    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.COROUTINES}"

    const val RX_ANDROID = "io.reactivex.rxjava3:rxandroid:${BuildDependenciesVersions.RX_VERSION}"
    const val RX_JAVA = "io.reactivex.rxjava3:rxjava:${BuildDependenciesVersions.RX_VERSION}"

    const val HILT = "com.google.dagger:hilt-android:${BuildDependenciesVersions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${BuildDependenciesVersions.HILT}"
    const val HILT_EXT_COMPILER = "androidx.hilt:hilt-compiler:${BuildDependenciesVersions.HILT_EXT}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_GSON  = "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_MOSHI = "com.squareup.retrofit2:converter-moshi:${BuildDependenciesVersions.RETROFIT}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${BuildDependenciesVersions.OKHTTP}"
    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.OKHTTP}"

    const val MOSHI = "com.squareup.moshi:moshi:${BuildDependenciesVersions.MOSHI}"
    const val MOSHI_ADAPTERS = "com.squareup.moshi:moshi-adapters:${BuildDependenciesVersions.MOSHI}"
    const val MOSHI_KTX = "com.squareup.moshi:moshi-kotlin:${BuildDependenciesVersions.MOSHI}"
    const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${BuildDependenciesVersions.MOSHI}"

    const val TIMBER = "com.jakewharton.timber:timber:${BuildDependenciesVersions.TIMBER}"
    const val WORK_KTX = "androidx.work:work-runtime-ktx:${BuildDependenciesVersions.WORK_KTX}"
    const val WORK_HILT = "androidx.hilt:hilt-work:${BuildDependenciesVersions.WORK_HILT}"
    const val STARTUP = "androidx.startup:startup-runtime:${BuildDependenciesVersions.STARTUP}"

    const val JAVAX_INJECT = "javax.inject:javax.inject:${BuildDependenciesVersions.JAVAX_INJECT_VERSION}"
    const val PREFERENCE_KTX = "androidx.preference:preference-ktx:${BuildDependenciesVersions.PREFERENCE}"

    const val COMPOSE_MATERIAL = "androidx.compose.material3:material3:${BuildDependenciesVersions.COMPOSE_MATERIAL}"
    const val COMPOSE_UI = "androidx.compose.ui:ui:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_UI_GRAPHICS = "androidx.compose.ui:ui-graphics:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_UI_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_UI_TEST = "androidx.compose.ui:ui-test-manifest:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:${BuildDependenciesVersions.COMPOSE_ACTIVITY}"
    const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:${BuildDependenciesVersions.COMPOSE_NAVIGATION}"
    const val COMPOSE_PAGING = "androidx.paging:paging-compose:${BuildDependenciesVersions.COMPOSE_PAGING}"
    const val COMPOSE_HILT = "androidx.hilt:hilt-navigation-compose:${BuildDependenciesVersions.COMPOSE_HILT}"
    const val PAGING_KTX = "androidx.paging:paging-runtime-ktx:${BuildDependenciesVersions.PAGING_KTX}"
    const val COMPOSE_COIL = "io.coil-kt:coil-compose:${BuildDependenciesVersions.COMPOSE_COIL}"
    const val COMPOSE_GLIDE = "com.github.bumptech.glide:compose:${BuildDependenciesVersions.COMPOSE_GLIDE}"
    const val GLIDE = "com.github.bumptech.glide:glide:${BuildDependenciesVersions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${BuildDependenciesVersions.GLIDE}"
}