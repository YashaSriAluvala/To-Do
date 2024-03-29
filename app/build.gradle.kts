plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.app"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        dataBinding = true
    }

}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")



//    implementation (fileTree(dir: 'libs', include: ['*.jar']))
//    implementation ("com.android.support:appcompat-v7:27.0.0")
//    implementation ("com.android.support.constraint:constraint-layout:1.1.3")
//    implementation ("com.android.support:design:27.0.0")
//    testImplementation ("junit:junit:4.12")
    androidTestImplementation ("com.android.support.test:runner:1.0.2")
    androidTestImplementation ("com.android.support.test.espresso:espresso-core:3.0.2")
    implementation ("androidx.recyclerview:recyclerview-selection:1.1.0")
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation ("androidx.appcompat:appcompat:1.3.0")



}