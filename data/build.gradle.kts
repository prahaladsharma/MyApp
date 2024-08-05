plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug{
            buildConfigField("String", "BASE_URL", "\"https://api.gameofthronesquotes.xyz/v1/\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://api.gameofthronesquotes.xyz/v1/\"")
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
    implementation(project(":domain"))
    implementation(project(":common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Hilt dependencies ---------------------------------------------------------------------------
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    //RETROFIT, OKHTTP & GSON ----------------------------------------------------------------------
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.retrofitConverterGson)
    implementation(libs.okhttp)
    implementation(libs.okhttpUrlconnection)
    implementation(libs.okhttpLogging)

    //Glider----------------------------------------------------------------------------------------
    implementation(libs.glide)

    testImplementation(libs.junit)
    testImplementation(libs.strikt)
    testImplementation(libs.mock)
    testImplementation(libs.coroutineKotlinTest)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}