plugins {
    id("java")

    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "cn.fancraft.lobby"
version = "1.0-SNAPSHOT"
tasks.jar {
    manifest {
        attributes["Main-Class"] = "cn.fancraft.lobby.Main"
    }

}


repositories {
    mavenCentral()

}

dependencies {
    implementation("net.minestom:minestom-snapshots:1_21_5-0473b41b2a")
}

tasks.test {
    useJUnitPlatform()
}