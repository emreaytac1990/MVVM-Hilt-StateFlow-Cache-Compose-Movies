plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.emreaytac.mvvm_hilt_stateflow_movies"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.emreaytac.mvvm_hilt_stateflow_movies"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        val baseUrl = rootProject.properties["MOVIES_BASE_URL"].toString()
        val imageBaseUrl = rootProject.properties["MOVIES_BASE_IMAGE_URL"].toString()
        val apiKey = rootProject.properties["API_KEY"].toString()

        buildConfigField("String", "BASE_URL", baseUrl)
        buildConfigField("String", "IMAGE_BASE_URL", imageBaseUrl)
        buildConfigField("String", "API_KEY", apiKey)

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
    implementation ("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation ("androidx.compose.material3:material3:1.1.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.06.01"))

    //Coil Compose
    implementation ("io.coil-kt:coil-compose:2.2.2")

    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.4.3")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Paging
    implementation ("androidx.paging:paging-runtime-ktx:3.2.0")
    implementation ("androidx.paging:paging-compose:3.2.0")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.7.2")

    //Room
    implementation ("androidx.room:room-ktx:2.5.2")
    kapt ("androidx.room:room-compiler:2.5.2")
    implementation ("androidx.room:room-paging:2.5.2")

    //Hilt Test
    testImplementation("com.google.dagger:hilt-android-testing:2.44")
    kaptTest("com.google.dagger:hilt-android-compiler:2.44")
    testAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.44")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")
    androidTestAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.44")
}


class RoomSchemaArgProvider(
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val schemaDir: File
) : CommandLineArgumentProvider {

    override fun asArguments(): Iterable<String> {
        // Note: If you're using KSP, change the line below to return
        // listOf("room.schemaLocation=${schemaDir.path}").
        return listOf("-Aroom.schemaLocation=${schemaDir.path}")
    }
}

kapt {
    correctErrorTypes = true
}