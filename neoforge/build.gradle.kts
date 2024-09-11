@file:Suppress("DEPRECATION", "UnstableApiUsage")

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.fabricmc.loom.task.RemapJarTask

plugins {
    id("com.github.johnrengelman.shadow")
}

val minecraftVersion: String by extra
val neoforgeVersion: String by extra
val neoforgeLoaderRange: String by extra
val neoforgeMinecraftVersionRange: String by extra
val modVersion: String by extra
val modName: String by extra
val modLicense: String by extra
val modIssueTracker: String by extra
val modAuthor: String by extra
val modDescription: String by extra

architectury {
    platformSetupLoomIde()
    neoForge()
}

loom {
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)
}

val common by configurations.creating
val shadowCommon by configurations.creating

configurations["compileClasspath"].extendsFrom(common)
configurations["runtimeClasspath"].extendsFrom(common)
configurations["developmentNeoForge"].extendsFrom(common)

repositories {
    maven(url = "https://maven.neoforged.net/")
}

dependencies {
    neoForge("net.neoforged:neoforge:$neoforgeVersion")

    common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    shadowCommon(project(path = ":common", configuration = "transformProductionNeoForge")) { isTransitive = false }
}

tasks.withType<ProcessResources> {
    val replaceProperties = mapOf(
        "modVersion" to modVersion, "modName" to modName, "modLicense" to modLicense, "modIssueTracker" to modIssueTracker,
        "neoforgeLoaderRange" to neoforgeLoaderRange, "neoforgeMinecraftVersionRange" to neoforgeMinecraftVersionRange,
        "modAuthor" to modAuthor, "modDescription" to modDescription)
    inputs.properties(replaceProperties)

    filesMatching("META-INF/neoforge.mods.toml") {
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

