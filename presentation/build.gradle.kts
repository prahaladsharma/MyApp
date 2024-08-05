plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        viewBinding  = true
        compose = true
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))

    // Hilt dependencies ---------------------------------------------------------------------------
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
    implementation(libs.hilt)


    //VIEW MODEL ----------------------------------------------------------------------------------------
    implementation(libs.lifecyccleLiveData)
    implementation(libs.viewmodelKtx)
    implementation(libs.lifecycleCompiler)

    // ktx libraries -------------------------------------------------------------------------------
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.fragment)
    implementation(libs.constraintlayout)

    // Compose
    implementation(libs.composeUi)
    implementation(libs.composeMateral)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.composeLifeCycleViewModel)
    implementation(libs.composeRunTimeLiveData)
    debugImplementation(libs.composeUiTooling)
    implementation(libs.composeCoil)

    // Navigation Animation
    implementation(libs.navCompose)

    testImplementation(libs.junit)
    testImplementation(libs.strikt)
    testImplementation(libs.mock)
    testImplementation(libs.coroutineKotlinTest)
    testImplementation(libs.archCoreTesting)
    testImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}