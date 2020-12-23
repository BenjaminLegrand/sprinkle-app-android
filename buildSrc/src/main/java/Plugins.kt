object Plugins {
    const val ANDROID_GRADLE_PLUGIN = "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val KOTLIN_SERIALIZATION_PLUGIN = "org.jetbrains.kotlin:kotlin-serialization:${Versions.KOTLIN}"
    const val SAFE_ARGS_PLUGIN = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}"

    const val STATIC_ANALYSIS_PLUGIN =  "com.novoda:gradle-static-analysis-plugin:${Versions.STATIC_ANALYSIS}"
    const val STATIC_ANALYSIS_PLUGIN_ID =  "com.novoda.static-analysis"

    const val KTLINT_PLUGIN_ID =  "org.jlleitschuh.gradle.ktlint"

    const val DETEKT_PLUGIN_ID =  "io.gitlab.arturbosch.detekt"
}