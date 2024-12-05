plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.srinivas.apiwithtestcasevirtusa"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.srinivas.apiwithtestcasevirtusa"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField( "String", "BASE_URL", "\"https://dummyjson.com/\"")
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            buildConfigField( "String", "BASE_URL", "\"https://dummyjson.com/\"")
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
    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Unit Testing
    testImplementation(libs.mockito.core)
//    testImplementation( libs.mockito.inline)

    testImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.core.testing)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // Hilt Testing
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.compiler.v244)
    testImplementation(libs.turbine)
    testImplementation( libs.kotlinx.coroutines.test)


    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.glide)
    annotationProcessor(libs.compiler)
    kapt(libs.compiler)


}