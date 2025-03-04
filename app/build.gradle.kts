plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id ("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.gestiotech_v3"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.gestiotech_v3"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        freeCompilerArgs += "-Xjvm-default=all"
    }
    kapt {
        correctErrorTypes = true
        correctErrorTypes = true
        useBuildCache = true
    }

}

dependencies {
    androidTestImplementation(libs.core.testing)
    // Kotlin
    dependencies {
        // Kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.21")
        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.21")

        // AndroidX e UI
        implementation("androidx.core:core-ktx:1.10.1")
        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("com.google.android.material:material:1.9.0")
        implementation("androidx.activity:activity-ktx:1.7.2")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation("androidx.legacy:legacy-support-v4:1.0.0")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
        implementation("androidx.fragment:fragment-ktx:1.5.7")

        // Firebase (usando BoM)
        implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
        implementation("com.google.firebase:firebase-analytics-ktx")
        implementation("com.google.firebase:firebase-firestore-ktx")
        implementation("com.google.firebase:firebase-auth-ktx")

        // Testes
        testImplementation("junit:junit:4.13.2")
        testImplementation("com.google.truth:truth:1.1.3")
        testImplementation("androidx.arch.core:core-testing:2.2.0")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
        androidTestImplementation("com.google.truth:truth:1.1.3")

        // Hilt para Injeção de Dependência
        implementation("com.google.dagger:hilt-android:2.55")
        kapt("com.google.dagger:hilt-compiler:2.55")
        // Para testes instrumentados
        androidTestImplementation("com.google.dagger:hilt-android-testing:2.55")
        kaptAndroidTest("com.google.dagger:hilt-compiler:2.55")
        // Para testes unitários
        testImplementation("com.google.dagger:hilt-android-testing:2.55")
        kaptTest("com.google.dagger:hilt-compiler:2.55")

        // ViewModel com Compose
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
        implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    }
}

