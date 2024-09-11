val modName: String by extra

pluginManagement {
    repositories {
        maven(url = "https://maven.fabricmc.net/")
        maven(url = "https://maven.architectury.dev/")
        maven(url = "https://maven.minecraftforge.net/")
        gradlePluginPortal()
    }
}

include("common", "fabric", "forge", "neoforge")
rootProject.name = "ThisBoatIsMine"