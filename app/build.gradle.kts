plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}





android {
    namespace = "com.ivansan.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ivansan.newsapp"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    runtimeOnly("androidx.compose.material:material-icons-extended:1.6.0-alpha04")

    implementation("androidx.compose.runtime:runtime-livedata:1.5.0")

    //NavCompose
    val navVersion = "2.7.1"
    implementation ("androidx.navigation:navigation-compose:$navVersion")


    //Hilt:
    val hiltVersion = "2.47"
    implementation ("com.google.dagger:hilt-android:$hiltVersion")
    kapt ("com.google.dagger:hilt-compiler:$hiltVersion")

    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Lifecycle
    val lifecycleVersion = "2.7.0-alpha01"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")


    //Coroutines:
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    val ktxVersion = "2.6.1"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$ktxVersion")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$ktxVersion")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:$ktxVersion")


    //Room database:
    val roomVersion = "2.5.2"
    implementation ("androidx.room:room-runtime:$roomVersion")
    // annotationProcessor ("androidx.room:room-compiler:$roomVersion")
    // To use Kotlin annotation processing tool (kapt)
    ksp ("androidx.room:room-compiler:$roomVersion")


    // optional - Paging 3 Integration
    implementation ("androidx.room:room-paging:$roomVersion")
    implementation ("androidx.room:room-ktx:$roomVersion")

    //Retrofit:
    val retrofitVersion = "2.9.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    //@RetryOnError annotation on fail for retrofit
    implementation ("com.lembergsolutions:retrofitretry:1.0.0")


    //Logger for retrofit
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //Material3
    implementation ("androidx.compose.material3:material3:1.1.1")

    //AsyncImage
    implementation("io.coil-kt:coil-compose:2.4.0")

    //Pager for Tabs
    implementation ("com.google.accompanist:accompanist-pager:0.27.1")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.23.0")

    //Material extra icons
    implementation ("androidx.compose.material:material-icons-extended:1.5.0")



    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}