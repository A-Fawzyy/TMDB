plugins {
	alias(libs.plugins.android.application)
	libs.plugins.androidx.navigation.safeArgs
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.hilt.gradle)
	alias(libs.plugins.ksp)
	alias(libs.plugins.kotlin.kapt)
}

android {
	namespace = "com.fawry.tmdb"
	compileSdk = 33

	defaultConfig {
		applicationId = "com.fawry.tmdb"
		minSdk = 24
		targetSdk = 33
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "android.template.HiltTestRunner"
		vectorDrawables {
			useSupportLibrary = true
		}

		// Enable room auto-migrations
		ksp {
			arg("room.schemaLocation", "$projectDir/schemas")
		}
	}

	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
		aidl = false
		buildConfig = false
		renderScript = false
		shaders = false
	}

	packagingOptions {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
	externalNativeBuild {
		cmake {
			path = File("CMakeLists.txt")
		}
	}
}

dependencies {
	// Core Android dependencies
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)

	// Ui Components
	implementation(libs.androidx.appCompat)
	implementation(libs.material)
	implementation(libs.androidx.constraintLayout)
	implementation(libs.androidx.navigation.fragment)
	implementation(libs.androidx.navigation.ui)

	// Retrofit Networking library
	implementation(libs.retrofit)
	implementation(libs.retrofit.converter.gson)
	implementation(libs.okhttp3.logging.interceptor)

	// Hilt Dependency Injection
	implementation(libs.hilt.android)
	kapt(libs.hilt.compiler)
	// Hilt and instrumented tests.
	androidTestImplementation(libs.hilt.android.testing)
	kaptAndroidTest(libs.hilt.android.compiler)
	// Hilt and Robolectric tests.
	testImplementation(libs.hilt.android.testing)
	kaptTest(libs.hilt.android.compiler)

	// Arch Components
	implementation(libs.androidx.room.runtime)
	implementation(libs.androidx.room.ktx)
	ksp(libs.androidx.room.compiler)

	// Local tests: jUnit, coroutines, Android runner
	testImplementation(libs.junit)
	testImplementation(libs.kotlinx.coroutines.test)

	// Instrumented tests: jUnit rules and runners

	androidTestImplementation(libs.androidx.test.core)
	androidTestImplementation(libs.androidx.test.ext.junit)
	androidTestImplementation(libs.androidx.test.runner)
}
