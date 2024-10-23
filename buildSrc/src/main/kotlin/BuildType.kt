interface BuildType {
    companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }
    val isMinifyEnabled: Boolean
    val enableUnitTestCoverage: Boolean
    val enableAndroidTestCoverage: Boolean
    val proguardFile: String
    // custom fields
    val enableCrashlyticsCollect: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
    override val enableUnitTestCoverage = false
    override val enableAndroidTestCoverage = false
    override val proguardFile = "proguard-rules.pro"

    const val applicationIdSuffix = ".debug"
    const val versionNameSuffix = "-DEBUG"

    // custom fields
    override val enableCrashlyticsCollect = false
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
    override val enableAndroidTestCoverage = false
    override val enableUnitTestCoverage = false
    override val proguardFile = "proguard-rules.pro"

    // custom fields
    override val enableCrashlyticsCollect = true
}