plugins {
    id("org.jetbrains.kotlin.jvm")
}

group = "com.makentoshe.booruchan.danbooru"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile(project(":booruchan-core"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}
