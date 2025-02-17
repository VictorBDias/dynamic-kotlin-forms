plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    id("com.google.devtools.ksp")
    alias(libs.plugins.compose.compiler)
    id("kotlin-kapt")
}

android {
    namespace = "com.cloud_evalutaion"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cloud_evalutaion"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val room_version = "2.6.1"
    ksp("androidx.room:room-compiler:$room_version")

    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Room (Database)
    implementation("androidx.room:room-runtime:2.5.0")
    ksp("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")

    // Lifecycle (ViewModel & LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")

    // Jetpack Compose UI
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material:material:1.5.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")

    // Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.5.3")

    // Dependency Injection (Hilt)
    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-compiler:2.48") // Fixed compiler dependency
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.0")
}
