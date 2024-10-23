package dependencies

import BuildDependenciesVersions

object TestDependencies {
    const val MOCKITO = "org.mockito:mockito-core:${BuildDependenciesVersions.MOCKITO}"
    const val KOTLIN_MOCKITO = "org.mockito.kotlin:mockito-kotlin:${BuildDependenciesVersions.KOTLIN_MOCKITO}"
    const val TEST_COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildDependenciesVersions.TEST_COROUTINE}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${BuildDependenciesVersions.ROBOLECTRIC}"
}
