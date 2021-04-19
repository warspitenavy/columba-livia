import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.serialization") version "1.4.32"
    id("net.minecrell.plugin-yml.bukkit") version "0.3.0"
}

group = "navy.warspite.minecraft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots") }
    maven {
        name = "m2-dv8tion"
        url = uri("https://m2.dv8tion.net/releases")
    }
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    api("com.charleskorn.kaml:kaml:0.30.0")
    api("net.dv8tion:JDA:4.2.1_253")
    api("com.github.kittinunf.fuel:fuel:2.3.1")
    api("com.beust:klaxon:5.5")
    api("org.slf4j:slf4j-log4j12:1.7.30")
    implementation("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

bukkit {
    name = "ColumbaLivia"
    main = "navy.warspite.minecraft.Main"
    version = "1.0"
    apiVersion = "1.16"
    author = "warspite.navy"

    defaultPermission = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.Permission.Default.OP
    commands {
        register("clivia") {
            permission = "columbalivia.admin"
        }
    }

    permissions {
        register("columbalivia.*") {
            children = listOf("columbalivia.admin")
        }
        register("columbalivia.admin") {
            default = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.Permission.Default.OP
        }
    }
}


val jar by tasks.getting(Jar::class) {
    configurations.api.get().isCanBeResolved = true
    from(
        configurations.api.get().filter {
            !it.name.endsWith("pom")
        }.map {
            if (it.isDirectory) it else zipTree(it)
        }
    )
}
