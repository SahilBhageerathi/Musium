 plugins {
            alias(libs.plugins.android.application)
            alias(libs.plugins.kotlin.android)
            alias(libs.plugins.kotlin.compose)
            alias(libs.plugins.hilt.android)
            alias(libs.plugins.ksp)
            id("kotlin-parcelize")
//            alias(libs.plugins.kotlin.parcelize)
//            kotlin("plugin.serialization") version "2.2.20"
            kotlin("plugin.serialization") version "2.0.21"


 }

android {
    namespace = "com.example.musium"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.musium"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "SPOTIFY_CLIENT_ID",
            "\"${project.findProperty("SPOTIFY_CLIENT_ID")}\""
        )
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.kotlinx.serialization.json)
    implementation (libs.androidx.core.splashscreen)
    implementation (libs.androidx.material.icons.extended)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.android)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.datastore.core)
//    implementation(libs.androidx.room.common.jvm)
    ksp(libs.hilt.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.browser)

    implementation(libs.coil.compose)
    implementation(libs.compose.shimmer)

    val roomDbVersion = "2.7.0-alpha01"
    implementation("androidx.room:room-runtime:$roomDbVersion")
    implementation("androidx.room:room-ktx:$roomDbVersion")
    ksp("androidx.room:room-compiler:$roomDbVersion")


    implementation(libs.okhttp.logging.interceptor)

}