plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

}

dependencies {
    implementation("net.minestom:minestom-snapshots:0366b58bfe")
}

tasks.test {
    useJUnitPlatform()
}