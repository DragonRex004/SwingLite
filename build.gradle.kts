plugins {
    id("java")
}

group = "de.dragonrex"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.formdev:flatlaf:3.4")
    implementation("com.miglayout:miglayout-swing:11.1")
}