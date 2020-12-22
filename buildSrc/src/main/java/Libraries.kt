object Libraries {
    /**
     * Basic dependencies
     */
    const val KTX = "androidx.core:core-ktx:${Versions.KTX}"

    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"

    /**
     * Support and basic android dependencies
     */
    @JvmField
    val SUPPORT = arrayOf(
        "androidx.appcompat:appcompat:${Versions.APP_COMPAT}",
        "com.google.android.material:material:${Versions.MATERIAL}",
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT}"
    )

    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_ANDROID}"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"

    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"

    @JvmField
    val SERIALIZATION = arrayOf(
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.SERIALIZATION}",
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.SERIALIZATION}"
    )

    @JvmField
    val NAVIGATION = arrayOf(
        "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}",
        "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    )

    const val VIEW_BINDING_EXTENSIONS = "fr.blegrand.libraries:view-binding-extensions:${Versions.VIEW_BINDING_EXTENSIONS}"

    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.ROBOLECTRIC}"
    const val ANDROIDX_TEST = "androidx.test:core:${Versions.ANDROIDX_TEST}"

}