@file:Suppress("DEPRECATION", "HasPlatformType")

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.fabricmc.loom.task.RemapJarTask

plugins {
    id("com.github.johnrengelman.shadow")
}

architectury {
    platformSetupLoomIde()
    fabric()
}

loom {
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)
}

val minecraftVersion: String by extra
val fabricApiVersion: String by extra
val fabricLoaderVersion: String by extra
val fabricLoaderRange: String by extra
val fabricMinecraftVersionRange: String by extra
val modVersion: String by extra
val modName: String by extra
val modLicense: String by extra
val modIssueTracker: String by extra
val modAuthor: String by extra
val modDescription: String by extra

val common by configurations.creating
val shadowCommon by configurations.creating

configurations["compileClasspath"].extendsFrom(common)
configurations["runtimeClasspath"].extendsFrom(common)
configurations["developmentFabric"].extendsFrom(common)

dependencies {
    modImplementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")

    // Dependencies (OPTIONAL)
    modApi("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion+$minecraftVersion") // Fabric API

    common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    shadowCommon(project(path = ":common", configuration = "transformProductionFabric")) { isTransitive = false }
}

tasks.withType<ProcessResources> {
    val replaceProperties = mapOf(
            "modVersion" to modVersion, "modName" to modName, "modLicense" to modLicense, "modIssueTracker" to modIssueTracker,
            "fabricLoaderRange" to fabricLoaderRange, "fabricMinecraftVersionRange" to fabricMinecraftVersionRange,
            "modAuthor" to modAuthor, "modDescription" to modDescription)

    inputs.properties(replaceProperties)

    filesMatching("fabric.mod.json") {
        expand(replaceProperties)
    }
}

tasks.withType<ShadowJar> {
    configurations = listOf(shadowCommon)
    archiveClassifier.set("dev-shadow")
}

tasks.withType<RemapJarTask> {
    val shadowTask = tasks.shadowJar.get()
    input.set(shadowTask.archiveFile)
}

